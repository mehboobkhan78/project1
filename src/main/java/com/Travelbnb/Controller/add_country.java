package com.Travelbnb.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/v1/country")
public class add_country {
    @PostMapping("/countries")
    public String Addcountry(){

        return "Granted Permissions ";
    }

}
