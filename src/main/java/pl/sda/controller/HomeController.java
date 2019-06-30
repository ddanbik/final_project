package pl.sda.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String home(){

//        Authentication auth
//                = SecurityContextHolder.getContext().getAuthentication();



        return "index";
    }


}
