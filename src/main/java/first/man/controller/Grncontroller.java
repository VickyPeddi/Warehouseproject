package first.man.controller;

import java.util.List;
import java.util.Map;

import first.man.model.Grndtl;
import first.man.model.Purchasedtl;
import first.man.model.Purchaseorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import first.man.model.Grn;
import first.man.service.Grnservice;
import first.man.service.Purchaseorderservice;

@RestController
@RequestMapping("/grn")
@RestControllerAdvice
public class Grncontroller {
    @Autowired
    private Grnservice service;
    @Autowired
    private Purchaseorderservice purchaseservice;


    public void adddropdownpurchaseorder(Model m) {
        m.addAttribute("purchase", purchaseservice.getPurchaseorderidandcodebyStatus("INVOICED"));
    }

    @RequestMapping("/")
    public ModelAndView getall() {
        List<Grn> list = service.getall();
        return new ModelAndView("grn/grndetails", "grns", list);
    }

    @RequestMapping("/register")
    public ModelAndView register(Model m, @ModelAttribute("grn") Grn grn) {

        adddropdownpurchaseorder(m);
        return new ModelAndView("grn/grnregister");
    }

    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute("grn") Grn grn, Model m) {

        Integer savegrn = service.savegrn(grn);
        m.addAttribute("message", "saved Grn Sucessfully" + savegrn);
        if (savegrn != null) {
            int purchaseid=grn.getPurchaseorder().getId();
            purchaseservice.updatepurchaseorderstatus("RECIVED", grn.getPurchaseorder().getId());
            convertpurchasedtlstogrndetails(savegrn,purchaseid);
        } else {
            m.addAttribute("message", "Unable to save Bcoz of Grn is null");
        }
        adddropdownpurchaseorder(m);

        return new ModelAndView("grn/grnregister", "grn", new Grn());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, Model m) {

        Grn grn = service.getgrn(id).get();
        adddropdownpurchaseorder(m);
        return new ModelAndView("grn/grnregister", "grn", new Grn());
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Model m) {
        try {
            service.deletegrn(id);
            m.addAttribute("message", "deleted Grn Sucessfully");
        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());

        }
        return new ModelAndView("grn/grndetails", "grns", service.getall());

    }

    //code for screen 2
    public void convertpurchasedtlstogrndetails(int grnid, int purchaseid) {
        //get purchasedetails using po id
        List<Purchasedtl> purchasedtlList = purchaseservice.findbypurchaseorderid(purchaseid);
        //conver one purchase details list to grndtl object
        for (Purchasedtl pdtl : purchasedtlList) {
            Grndtl grndtl = new Grndtl();
            grndtl.setPartcode(pdtl.getPart().getPartcode());
            grndtl.setBasecost(pdtl.getPart().getBasecost());
            grndtl.setQty(pdtl.getQty());
            grndtl.setLinevalue(grndtl.getQty() * grndtl.getBasecost());
            //link with grn
            Grn grn = new Grn();
            grn.setGrnid(grnid);
            grndtl.setGrn(grn);
            //save the grn detais in grndtls repos
            Integer savegrndtls = service.savegrndtls(grndtl);


        }
    }

}
