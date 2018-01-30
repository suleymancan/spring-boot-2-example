package com.suleymancan.myblog.controller;

import com.suleymancan.myblog.model.Category;
import com.suleymancan.myblog.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public String listCategories(Model model){
        model.addAttribute("categories",categoryRepository.findAll());
        return "categories/listCategories";
    }

    @GetMapping("/new")
    public String getNewCategory(Model model){
        model.addAttribute("category",new Category());
        return "categories/newCategory";
    }

    @PostMapping("/new")
    public String postNewCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            log.warn("New category is not valid");
            return "categories/newCategory";
        }
        else {
            categoryRepository.save(category);
            return "redirect:/categories";
        }
    }

}
