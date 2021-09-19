package com.cg.controller;

import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ISneakerService sneakerService;

    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home/Home");
        modelAndView.addObject("sneakers", sneakerService.findAll());
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("lastP",sneakerService.findTopByOrderByIdDesc());
        return modelAndView;
    }
}
