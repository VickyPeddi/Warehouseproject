package first.man.controller;

import first.man.model.Part;
import first.man.service.Partservice;
import first.man.service.Uomservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/part")
public class Partcontroller {

    @Autowired
    private Partservice partservice;
    @Autowired
    private Uomservice uomservice;


    @RequestMapping("/")
    public ModelAndView getall() {
        List<Part> list = partservice.getallparts();
        return new ModelAndView("part/partdetailspage", "parts", list);
    }

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("part") Part part, Model model) {
        model.addAttribute("uomdrop", uomservice.getuomidandmodel());
        return new ModelAndView("part/partregisterpage");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("part") Part part, Model m) {
        if (part == null) {
            m.addAttribute("message", "unable to save the Part");
        } else {
            m.addAttribute("message", "saved Part sucesfully");

            partservice.savepart(part);
        }

        return new ModelAndView("part/partregisterpage", "part", new Part());
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, Model model) {
        model.addAttribute("uomdrop", uomservice.getuomidandmodel());
        Part part = partservice.getpartbypartid(id);
        return new ModelAndView("part/partregisterpage", "part", part);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Model m) {
        try {
            partservice.deletebyid(id);
            m.addAttribute("message", "delted the part sucessfully");

        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());
        }
        return new ModelAndView("part/partdetailspage", "part", new Part());
    }
}
