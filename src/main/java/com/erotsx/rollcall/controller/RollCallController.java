package com.erotsx.rollcall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RollCallController {

    @RequestMapping("/call")
    public String call(Model model) {
        return "call";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
