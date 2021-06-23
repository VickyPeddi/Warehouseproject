package first.man.controller;

import first.man.model.Document;
import first.man.service.Documentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class Documentcontroller {
    @Autowired
    private Documentservice service;


    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("document") Document document) {
        return new ModelAndView("document/documentregister");

    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("document") Document document, Model m, @RequestParam("documentdata") MultipartFile multipartFile) throws Exception {
        //through raghu way
        String path =new ClassPathResource("static/images/").getFile().getAbsolutePath();
        document.setDocname(multipartFile.getOriginalFilename());
        document.setDocdata(multipartFile.getBytes());
        Files.copy(multipartFile.getInputStream(), Paths.get(path + File.separator + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        if (document == null) {
            m.addAttribute("message","Unable to  save the Document");
        }
        else {
            service.savedocument(document);
            m.addAttribute("message","Document save sucessfully");
        }

        return new ModelAndView("document/documentregister", "document",new Document());
//        //Static path changes from system to system
////        String path = "E:\\Microservices\\shipmentupdateproject\\src\\main\\resources\\static\\images\\";
//        //dynamic path in target folder if not just build the project
//        String path =thResource new ClassPa("static/images/").getFile().getAbsolutePath();
//        if (multipartFile.isEmpty()) {
//            System.out.println("empty file");
//            return new ModelAndView("document/documentdetailpage", "message", "this is empty image");
//        } else if (multipartFile.getContentType().equals("image/jpeg")) {
//            return new ModelAndView("document/documentdetailspage", "message", "this is image jpeg");
//        }
//
//        Files.copy(multipartFile.getInputStream(), Paths.get(path + File.separator + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//        return new ModelAndView("redirect:/doc/", "message", "saved sucessfully");
//
//
////Through Normal Way
//
////        document.setDocname(multipartFile.getOriginalFilename());
////        try {
////            document.setDocdata(multipartFile.getBytes());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        m.addAttribute("message", "documentsavedsucessfully of id" + document.getDocid());
////        service.savedocument(document);
////        return new ModelAndView("document/documentregister");
    }

    @RequestMapping("/")
    public ModelAndView getall() {
        List<Document> al = service.getlall();
        if (al == null) {
            return new ModelAndView("document/documentdetailspage", "message", "no details");
        }
        return new ModelAndView("document/documentdetailspage", "documents", al);


    }


}
