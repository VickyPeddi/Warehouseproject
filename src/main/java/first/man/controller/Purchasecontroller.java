package first.man.controller;

import java.util.List;
import java.util.Optional;

import first.man.config.Vendorinvoicepdf;
import first.man.dao.Purchasedtlsreops;
import first.man.model.Purchasedtl;
import first.man.service.Partservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import first.man.dao.Purchaseorderinterface;
import first.man.model.Purchaseorder;
import first.man.service.Purchaseorderservice;
import first.man.service.Shipmentservice;
import first.man.service.Userservice;

import javax.persistence.Id;

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
    @Autowired
    private Partservice partservice;


    private void adddropdownforpartcodeandpartid(Model m) {
        m.addAttribute("parts", partservice.getpartidandpartcode());
    }

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


        if (purchaseorder == null) {
            m.addAttribute("message", "unable to save to purchase order");
        } else {
            purchaseorder.setDefaultstatus("OPENED");
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

            //for the status displaying
            m.addAttribute("purchase", purchaseorder.get());
            //for the form action part section
            adddropdownforpartcodeandpartid(m);
            //form backing object for adding part + linked with PO
            Purchasedtl purchasedtl = new Purchasedtl();
            purchasedtl.setPurchaseorder(purchaseorder.get());
            m.addAttribute("purchasedtls", purchasedtl);
            //show the details of purchase details
            List<Purchasedtl> purchasedtlList = service.findbypurchaseorderid(id);
            m.addAttribute("purchasedtlsadd", purchasedtlList);

            return new ModelAndView("purchase/purchasedtls");
        } else {
            return new ModelAndView("redirect:/purchase/");
        }
    }
    //on click add button of purchasedtls page
    /*
     * read purchase detailsobject and save in db
     * redirect to /dtls/{id} ->show dtls() method
     * */

    /*
     * this is one to many relation with the
     * purchase order with purchase dtls
     * on the many side( * ) the paramater is created
     * purchaseorder --* purchasedtls
     * */
    @PostMapping("/addpart")
    public ModelAndView saveparttopurchasedtls(@ModelAttribute("purchasedtls") Purchasedtl purchasedtl) {
        service.addparttopo(purchasedtl);
        service.updatepurchaseorderstatus("PICKING", purchasedtl.getPurchaseorder().getId());
        System.out.println(purchasedtl.toString());
        return new ModelAndView("redirect:/purchase/dtls/" + purchasedtl.getPurchaseorder().getId());

    }

    //when we want to delete the part we need to show the data after deletion
    //first id is delete the part and secound is the purchase id
    @RequestMapping("/removepart")
    public ModelAndView removepart(@RequestParam("dtlid") int dtlid, @RequestParam("purchaseid") int poid) {
        System.out.println("this is part id" + dtlid);
        System.out.println("this is product id" + poid);
        service.deletepurchasedtls(dtlid);
        int dtlscount = service.PurchasedtlcountByPurchaseorder(poid);
        if (dtlscount == 0) {
            service.updatepurchaseorderstatus("OPENED", poid);

        }
        return new ModelAndView("redirect:/purchase/dtls/" + poid);
    }

    //confirm part order with status change
    @RequestMapping("/confirm/{id}")
    public ModelAndView confirmpartorder(@PathVariable("id") int poid) {
        int count = service.PurchasedtlcountByPurchaseorder(poid);
        if (count > 0) {
            service.updatepurchaseorderstatus("ORDERED", poid);
        }
        return new ModelAndView("redirect:/purchase/dtls/" + poid);
    }

    @RequestMapping("/invoiceorder/{id}")
    public ModelAndView invoiceorder(@PathVariable("id") int poid) {
        int count = service.PurchasedtlcountByPurchaseorder(poid);
        if (count > 0) {
            service.updatepurchaseorderstatus("INVOICED", poid);
        }
        return new ModelAndView("redirect:/purchase/");
    }

    // for generating invoice order by vendorinvoice class
    @RequestMapping("/printinvoice/{id}")
    public ModelAndView printinvoice(@PathVariable("id") int poid) {
        return new ModelAndView(new Vendorinvoicepdf(), "purchaseorder", service.getpurchaseorderbyid(poid));
    }
}
