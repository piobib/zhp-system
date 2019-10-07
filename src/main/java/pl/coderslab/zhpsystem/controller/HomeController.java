package pl.coderslab.zhpsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.zhpsystem.service.EmailService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {




    @GetMapping("/")
    public String home() {
        //return "index";
        return "redirect:/login";
    }


}
