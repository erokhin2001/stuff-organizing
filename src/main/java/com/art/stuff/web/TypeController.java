package com.art.stuff.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.stuff.domain.Type;
import com.art.stuff.domain.TypeRepository;

@Controller
public class TypeController {

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/type")
    public String showTypes(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        return "typelist";
    }

    @GetMapping("/addtype")
    public String addType(Model model){
        model.addAttribute("type", new Type());
        return "addtype";
    }

    @GetMapping("/deletetype/{id}")
    public String deleteType(@PathVariable("id") Long typeId) {
        typeRepository.deleteById(typeId);
        return "redirect:../type";
    }

    @PostMapping("/savetype")
    public String saveType(Type type){
        typeRepository.save(type);
        return "redirect:/type";
    }

}
