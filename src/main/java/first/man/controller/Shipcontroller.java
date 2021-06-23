package first.man.controller;

import first.man.config.Chartutil;
import first.man.config.Shipmentexcel;
import first.man.config.Shipmentpdfview;
import first.man.email.Shipmentmail;
import first.man.model.Shipmentmodel;
import first.man.service.Shipmentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/ship")
public class Shipcontroller {
    @Autowired
    private Shipmentservice service;

    @Autowired
    private ServletContext context;
    @Autowired
    private Shipmentmail mailsender;
    @Autowired
    private Chartutil shipmenttypeutil;
//    @Autowired
//    private JavaMailSender mailSender;

//    public void sendemail(String to, String body, String text) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("nandakishorepeddi4@gmail.com");
//        mailMessage.setTo(to);
//        mailMessage.setSubject(body);
//        mailMessage.setText(text);
//        mailSender.send(mailMessage);
//    }

    @RequestMapping("/")
    public ModelAndView getall() {
        return new ModelAndView("shipment/shipdetailspage", "ships", service.getall());
    }

    @RequestMapping("/register")
    public ModelAndView gotoregistepage(@ModelAttribute("ship") Shipmentmodel ship) {
        return new ModelAndView("shipment/shipregisterpage");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("ship") Shipmentmodel shipmentmodel, Model m) {
        if (shipmentmodel == null) {
            m.addAttribute("message", "shipmentorder unable to save bcoz Null");
        } else {
            service.createorupdate(shipmentmodel);
            m.addAttribute("message", "saved sucessfully");
        }


//        if (shipmentmodel.getShipid() != 0) {
//            boolean flag = mailsender.send(shipmentmodel.getShipmentcode(), "welcome", "hello username" + shipmentmodel.getDescription());
//            if (flag) {
//                System.out.println("mail sended");
//            } else {
//                System.out.println("mail not sended");
//            }
//        }

        return new ModelAndView("shipment/shipregisterpage","ship",new Shipmentmodel());
    }

//    @ExceptionHandler(Exception.class)
//    public String showerrors(Exception ex, Date date) {
//        return "" + ex.getMessage() + date;
//    }


    @RequestMapping("/update/{id}")
    public ModelAndView updateShipment(@PathVariable("id") int id) throws Exception {
        Shipmentmodel getbyid = service.getbyid(id);
        return new ModelAndView("shipment/shipregisterpage", "ship", getbyid);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Model m) {
        try {
            service.deletebyid(id);
            m.addAttribute("message", "SHipment order deleted sucessfully");
        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());
        }
        return new ModelAndView("shipment/shipdetailspage", "ships", service.getall());

    }


    @GetMapping("/validatecode")
    public @ResponseBody
    String validateshipmentcode(@RequestParam("code") String code) {
        String message = null;

        if (service.shipmentcodeexists(code)) {
            return message = "shipmentcode" + code + "Already Exists";
        }

        return message;

    }

    @RequestMapping("/excelview")
    public ModelAndView settoexcelview() {
        List<Shipmentmodel> list = service.getall();
        return new ModelAndView(new Shipmentexcel(), "shipment", list);
    }

    @RequestMapping("/excel/{id}")
    public ModelAndView excelperid(@PathVariable("id") int id) throws Exception {
        Shipmentmodel getbyid = service.getbyid(id);
        return new ModelAndView(new Shipmentexcel(), "shipment", Arrays.asList(getbyid));
    }

    @RequestMapping("/pdf")
    public ModelAndView pdfview() {
        List<Shipmentmodel> getall = service.getall();
        return new ModelAndView(new Shipmentpdfview(), "getall", getall);
    }

    //generate chart Method
    @RequestMapping("/piechart")
    public ModelAndView generateCharts() {
        //data to show on chart
        List<Object[]> count = service.getshipmentmodecount();
        //dynamic tempfolder location
        String realPath = context.getRealPath("/");
        System.out.println(realPath);

        //call the methods in shipmenttype utils
        shipmenttypeutil.generatePiechart(realPath, count);

        return new ModelAndView("shipment/shipmenttypechart");
    }

    @RequestMapping("/barchart")
    public ModelAndView barchart() {
        //data showed on chart
        List<Object[]> shipmentmodecount = service.getshipmentmodecount();
        //dynamic temp folder location
        String realpath = context.getRealPath("/");
        //call  the method for barchart
        shipmenttypeutil.generateBarchart(realpath, shipmentmodecount);
        return new ModelAndView("shipment/shipmenttypebarchart");


    }
}
