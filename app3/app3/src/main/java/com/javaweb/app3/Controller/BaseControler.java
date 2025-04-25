package com.javaweb.app3.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseControler {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
