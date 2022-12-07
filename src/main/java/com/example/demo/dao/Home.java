package com.example.demo.dao;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Controller
public class Home
{
    @GetMapping(value="/index", produces= MediaType.TEXT_HTML_VALUE)
    public String welcome()
    {
        return "index";
    }
}
