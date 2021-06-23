package first.man.controller;

import first.man.config.Chartutil;
import first.man.config.Shipmentexcel;
import first.man.dao.Orderinterface;
import first.man.model.Ordermethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimePartDataSource;
import javax.servlet.ServletContext;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/order")
public class Ordercontroller {
    @Autowired
    private Orderinterface repos;
    @Autowired
    private ServletContext context;
    @Autowired
    private Chartutil chartutil;

    @RequestMapping("/")
    public ModelAndView getallorders() {
        List<Ordermethod> orders = repos.findAll();
        return new ModelAndView("order/orderdetailspage", "orders", orders);
    }

    @RequestMapping("/register")
    public ModelAndView gotoregister(@ModelAttribute("order") Ordermethod order,Model m)
    {
        return new ModelAndView("order/orderregisterpage");
    }

    @RequestMapping("/save")
    public ModelAndView register(@ModelAttribute("order") Ordermethod order, Model m)
    {
        if(order==null){
            m.addAttribute("message","unble to save order bcoz its null");
        }
        else{
            m.addAttribute("message","order saved sucessfully");
             repos.save(order);
        }
        return new ModelAndView("order/orderregisterpage","order",new Ordermethod());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView updateorder(@PathVariable("id") int id) {
        Ordermethod one = repos.getOne(id);
        return new ModelAndView("order/orderregisterpage", "order", one);
    }

    @RequestMapping("/delete/id")
    public ModelAndView deletebyid(@PathVariable("id") int id, Model m) {

        try {

            repos.deleteById(id);
            m.addAttribute("message","delted order Sucessfully");
        }
        catch (Exception e)
        {
            m.addAttribute("message",e.getMessage());
        }
        return new ModelAndView("order/orderdetailspage","orders",repos.findAll());
    }

    @RequestMapping("/viewdetails/{id}")
    public ModelAndView viewdetails(@PathVariable("id") int id) {
        Ordermethod one = repos.getOne(id);
        return new ModelAndView("order/orderdetails", "order", one);
    }

    @RequestMapping("/barchart")
    public ModelAndView generatebarchart() {
        //get the data from the database
        List<Object[]> ordercount = repos.getordercodecount();
        //get the realpath
        String realPath = context.getRealPath("/");
        //user the method by using has-a relationship
        chartutil.generateBarchart(realPath, ordercount);

        return new ModelAndView("order/orderbarchart");
    }

    @RequestMapping("/piechart")
    public ModelAndView generatepiechart() {
        //get the data from the database
        List<Object[]> ordercount = repos.getordercodecount();
        //get the realpath
        String realPath = context.getRealPath("/");
        //user the method by using has-a relationship
        chartutil.generatePiechart(realPath, ordercount);
        return new ModelAndView("order/orderbarchart");
    }

    @RequestMapping("/excel")
    public ModelAndView excelview() {
        return new ModelAndView(new Shipmentexcel(), "list", repos.findAll());
    }

}
