package com.example.Appointment.Booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactController {

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // This will forward to index.html for any request that doesn't contain a dot (i.e., non-asset requests)
        return "forward:/index.html";
    }
}
