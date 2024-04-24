package com.art.stuff.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.stuff.domain.Item;
import com.art.stuff.domain.ItemRepository;
import com.art.stuff.domain.LocationRepository;
import com.art.stuff.domain.TypeRepository;

import jakarta.validation.Valid;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/stuff")
    public String showItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "itemlist";
    }

    @GetMapping("/additem")
    public String addItem(Model model){
        model.addAttribute("item", new Item());
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "additem";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long itemId, @Valid Item item, BindingResult result, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "edititem";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:../stuff";
    }

    @PostMapping("/save")
    public String saveItem(@Valid Item item){
        itemRepository.save(item);
        return "redirect:stuff";
    }

}
