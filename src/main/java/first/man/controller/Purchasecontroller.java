package first.man.controller;

import first.man.dao.Purchaseorderinterface;
import first.man.model.Purchaseorder;
import first.man.service.Purchaseorderservice;
import first.man.service.Shipmentservice;
import first.man.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class Purchasecontroller {
    @Autowired
    private Purchaseorderservice service;
    @Autowired
    private Shipmentservice shipmentservice;
    @Autowired
    private Userservice userservice;
    @Autowired
    private Purchaseorderinterface repos;

    @RequestMapping("/")
    public ModelAndView getall() {
        List<Purchaseorder> purchase = service.getallpurchaseorders();
        return new ModelAndView("purchase/purchasedetailspage", "purchases", purchase);
    }

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("purchase") Purchaseorder purchaseorder, Model m) {
        m.addAttribute("usercode", userservice.getuseridandusercode());
        m.addAttribute("shipmentcode", shipmentservice.getidandshipmentcode());
        return new ModelAndView("purchase/purchaseregisterpage");
    }

    @RequestMapping("/purhase/{id}")
    public ModelAndView getbyid(@PathVariable("id") int id) {

        Purchaseorder purchase = service.getpurchaseorderbyid(id);
        return new ModelAndView("purchase/purchasepage", "purchase", purchase);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("purchase") Purchaseorder purchaseorder, Model m) {

        purchaseorder.setDefaultstatus("OPEN");
        if (purchaseorder == null) {
            m.addAttribute("message", "unable to save to purchase order");
        } else {

            Purchaseorder savepurchaseorder = service.savepurchaseorder(purchaseorder);

            m.addAttribute("shipmentcode", shipmentservice.getidandshipmentcode());
            m.addAttribute("usercode", userservice.getuseridandusercode());
            m.addAttribute("message", "saved purchase order sucessfully");
        }
        return new ModelAndView("purchase/purchaseregisterpage", "purchase", new Purchaseorder());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, Model m) {
        Purchaseorder purchase = service.getpurchaseorderbyid(id);
        m.addAttribute("shipmentcode", shipmentservice.getidandshipmentcode());
        m.addAttribute("usercode", userservice.getuseridandusercode());
        return new ModelAndView("purchase/purchaseregisterpage", "purchase", purchase);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Model m) {
        try {
            service.deletepurchaseorderbyid(id);
            m.addAttribute("message", "delete purchase order sucessfully");

        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());

        }
        return new ModelAndView("purchase/purchasedetailspage", "purchases", service.getallpurchaseorders());

    }

    /*Screen 2 operations*/

    //    show purchasedtls page
    @GetMapping("/dtls/{id}")
    public ModelAndView showdetails(@PathVariable("id") Integer id, Model m) {
        Optional<Purchaseorder> purchaseorder = repos.findById(id);
        if (!purchaseorder.isEmpty()) {
            m.addAttribute("po", purchaseorder.get());

            return new ModelAndView("purchase/purchasedtls");
        } else {
            return new ModelAndView("redirect:/purchase/");
        }


    }


}
