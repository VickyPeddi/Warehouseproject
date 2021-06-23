package first.man.controller;

import first.man.model.Saleorder;
import first.man.service.Saleorderservice;
import first.man.service.Shipmentservice;
import first.man.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RestControllerAdvice
@RequestMapping("/sale")
public class SaleorderController {
    @Autowired
    private Saleorderservice service;
    @Autowired
    private Userservice userservice;
    @Autowired
    private Shipmentservice shipmentservice;

    @RequestMapping("/")
    public ModelAndView getallsaleorder() {
        return new ModelAndView("sale/saleorderdetails", "sales", service.findall());
    }

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("sale") Saleorder saleorder, Model m) {
        m.addAttribute("ship", shipmentservice.getidandshipmentcode());
        m.addAttribute("user", userservice.getuseridandusertype());
        return new ModelAndView("sale/saleorderregister");
    }

    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute("sale") Saleorder saleorder, Model m) {
        if (saleorder == null) {
            m.addAttribute("message", "unable to save the saleorder");
        } else {
            m.addAttribute("message", "saved sale order sucessfully");
            m.addAttribute("ship", shipmentservice.getidandshipmentcode());
            m.addAttribute("user", userservice.getuseridandusertype());
            service.savesaleorder(saleorder);
        }

        return new ModelAndView("sale/saleorderregister");
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, Model m) {
        m.addAttribute("ship", shipmentservice.getidandshipmentcode());
        m.addAttribute("user", userservice.getuseridandusertype());
        Saleorder saleorder = service.getonesaleorder(id);
        return new ModelAndView("sale/saleorderregister", "sale", saleorder);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deletesaleorder(@PathVariable("id") int id, Model m) {
        String message;
        try {
            m.addAttribute("message", "deleted sale order sucessfully");


        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());
        }
        return new ModelAndView("sale/saleorderdetails","sales",service.findall());
    }

}