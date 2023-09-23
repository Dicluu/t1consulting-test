package com.example.demo.controllers;

import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    @ResponseBody
    public List get(@RequestParam String value) {
        return indexService.calculate(value);
    }


}
