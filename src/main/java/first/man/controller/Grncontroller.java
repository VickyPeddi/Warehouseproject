package first.man.controller;

import first.man.model.Grn;
import first.man.service.Grnservice;
import first.man.service.Purchaseorderservice;
import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/grn")
public class Grncontroller {
    @Autowired
    private Grnservice service;
    @Autowired
    private Purchaseorderservice purchaseservice;

    @RequestMapping("/")
    public ModelAndView getall()
    {
        List<Grn> list = service.getall();
        return new ModelAndView("grn/grndetails", "grns", list);
    }

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("grn") Grn grn, Model m)
    {
        m.addAttribute("purchase",purchaseservice.getpurchaseorderandordercode());
        return new ModelAndView("grn/grnregister");
    }

    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute("grn") Grn grn,Model m)
    {
        if(grn==null){
            m.addAttribute("message","Unable to save Bcoz of Grn is null");
        }
        else {
            m.addAttribute("purchase", purchaseservice.getpurchaseorderandordercode());
            m.addAttribute("message", "saved Grn Sucessfully");
            Grn savegrn = service.savegrn(grn);
        }
        return new ModelAndView("grn/grnregister", "grn",new Grn());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id,Model m)
    {
        m.addAttribute("purchase",purchaseservice.getpurchaseorderandordercode());
        Grn grn = service.getgrn(id);
        return new ModelAndView("grn/grnregister", "grn", grn);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id,Model m)
    {
        try {
            service.deletegrn(id);
            m.addAttribute("message","deleted Grn Sucessfully");
        }
        catch (Exception e)
        {
            m.addAttribute("message",e.getMessage());

        }
        return new ModelAndView("grn/grndetails","grns",service.getall());

    }

}
