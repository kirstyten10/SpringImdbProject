package com.example.web_imdb_project;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(Movies movies) {
        return "greeting";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid Movies movies, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "greeting";
        }

        return "redirect:/results";
    }
}