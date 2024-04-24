package com.art.stuff.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.art.stuff.domain.Location;
import com.art.stuff.domain.LocationRepository;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/location")
    public String showLocations(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        return "locationlist";
    }

    @GetMapping("/addlocation")
    public String addLocation(Model model){
        model.addAttribute("location", new Location());
        return "addlocation";
    }

    @GetMapping("/deletelocation/{id}")
    public String deleteLocation(@PathVariable("id") Long locationId) {
        locationRepository.deleteById(locationId);
        return "redirect:../location";
    }

    @PostMapping("/savelocation")
    public String saveLocation(Location location){
        locationRepository.save(location);
        return "redirect:/location";
    }

}
