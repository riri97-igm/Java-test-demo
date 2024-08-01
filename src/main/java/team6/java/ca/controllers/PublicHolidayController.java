package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import team6.java.ca.entities.PublicHoliday;
import team6.java.ca.services.PublicHolidayService;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/public-holidays")
public class PublicHolidayController {
    @Autowired
    private PublicHolidayService publicHolidayService;

    @GetMapping
    public String getAllPublicHolidays(Model model) {
        List<PublicHoliday> publicHolidays = publicHolidayService.getAllPublicHolidays();
        
        if(publicHolidays == null)
        {
        	publicHolidays = new ArrayList<>();
        }
        
        model.addAttribute("publicHolidays", publicHolidays);
        return "public-holidays";
    }

    @GetMapping("/add")
    public String showAddPublicHolidayForm(Model model) {
        model.addAttribute("publicHoliday", new PublicHoliday());
        return "add-public-holiday";
    }

    @PostMapping("/add")
    public String addPublicHoliday(@ModelAttribute PublicHoliday publicHoliday) {
        publicHolidayService.savePublicHoliday(publicHoliday);
        return "redirect:/public-holidays";
    }

    @GetMapping("/delete/{id}")
    public String deletePublicHoliday(@PathVariable Long id) {
        publicHolidayService.deletePublicHoliday(id);
        return "redirect:/public-holidays";
    }
}
