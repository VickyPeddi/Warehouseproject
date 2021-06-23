package first.man.controller;

import first.man.config.Excelview;
import first.man.config.Userabstractview;
import first.man.dao.Userinterface;
import first.man.model.User;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Usecontroller {
	@Autowired
	private Userinterface repos;

	@RequestMapping("/")
	public ModelAndView getusers() {
		List<User> getall = repos.findAll();
		return new ModelAndView("user/userdetailspage", "users", getall);
	}

	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute("user") User user) {
		return new ModelAndView("user/userregisterpage");
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user, Model m)
	{
		if(user==null)
		{
			m.addAttribute("message","Unable to save user");
		}
		else{

			User save = repos.save(user);
			m.addAttribute("message","saved user sucessfully");
		}

		return new ModelAndView("user/userregisterpage","user",new User());
	}

	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") int id) {
		User user = repos.findById(id).get();
		return new ModelAndView("user/userregisterpage", "user", user);
	}

	@RequestMapping("/excel/{id}")
	public ModelAndView excelview(@PathVariable("id") int id) {
		User user = repos.findById(id).get();
		return new ModelAndView(new Excelview(), "user", user);
	}

	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id, Model m)
	{
		try {

			repos.deleteById(id);
			m.addAttribute("message","delted the user of id"+id);
		}
		catch (Exception e)
		{
			m.addAttribute("message",e.getMessage());

		}

		return new ModelAndView("user/userdetailspage");
	}

	@RequestMapping("/excel")
	public ModelAndView excel() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> findAll = repos.findAll();
		modelAndView.addObject("list", findAll);
		modelAndView.setView(new Excelview());
		return modelAndView;
	}
	@RequestMapping("/pdf")
	public ModelAndView addpdf() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> findAll = repos.findAll();
		modelAndView.addObject("list", findAll);
		modelAndView.setView(new Userabstractview());
		return modelAndView;
	}
}
