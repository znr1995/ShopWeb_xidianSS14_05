package com.project_management.shoppingweb.controller.error;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class errorHandler {

    @RequestMapping("/error/errorHandler")
    public String errorHandler(@ModelAttribute("errorMessage")String msg)
    {
        return msg;
    }
}
