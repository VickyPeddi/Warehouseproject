package first.man.controller;

import first.man.model.Uommodel;
import first.man.service.Uomservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/uom")
public class Uomcontroller {
    @Autowired
    private Uomservice service;

    //uom/all?page=1 page no  of  page ?size=3//no of records per page
    @RequestMapping("/")
    public ModelAndView getall(@PageableDefault(page = 0,size = 2) Pageable pageable) {
        List<Uommodel> list = service.getall();
        Page<Uommodel> getpagination = service.getpagination(pageable);
        return new ModelAndView("uom/uomdetailspage", "uoms",getpagination);
    }

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("uom") Uommodel uommodel) {

        return new ModelAndView("uom/uomregisterpage");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("uom") Uommodel uommodel, Model m)
    {
        if(uommodel==null)
        {
            m.addAttribute("message","Not saved the Uom");
        }

        else {
            m.addAttribute("message","Saved the Uom ");
            Uommodel save = service.save(uommodel);

        }
        return new ModelAndView("uom/uomregisterpage","uom",new Uommodel());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id)
    {

        Uommodel uommodel = service.getuombyid(id);
        return new ModelAndView("uom/uomregisterpage", "uom", uommodel);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id,Model m,@PageableDefault(page = 0,size = 2) Pageable pageable) {
        try
        {
            m.addAttribute("message","deleted uom of id "+id);
            service.deleteuombyid(id);

        }
        catch (Exception e)
        {
            m.addAttribute("message",e.getMessage());
        }


        return new ModelAndView("uom/uomdetailspage","uoms",service.getpagination(pageable));
    }


}
