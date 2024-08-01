package team6.java.ca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;
import team6.java.ca.entities.LeaveEntitlement;
import team6.java.ca.services.LeaveEntitlementService;

@Controller
@RequestMapping("/admin/entitlement")
public class LeaveEntitlementController {

    @Autowired
    private LeaveEntitlementService leaveEntitlementService;

    @GetMapping("/list")
    public String entitlementListPage(Model model) {
        List<LeaveEntitlement> entitlementList = leaveEntitlementService.findAllLeaveEntitlements();
        model.addAttribute("entitlementList", entitlementList);
        return "leaveEntitlement-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        LeaveEntitlement entitlement = leaveEntitlementService.findLeaveEntitlementById(id);
        model.addAttribute("entitlement", entitlement);
        return "leaveEntitlement-edit";
    }

    
    //----------------*************validation didn't opprate *****************--------------------//
    @PostMapping("/edit/{id}")
    public String updateEntitlement(@PathVariable("id") long id, @ModelAttribute("entitlement") @Valid LeaveEntitlement entitlement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("entitlement", entitlement);
            return "leaveEntitlement-edit";
        }
        
        leaveEntitlementService.updateLeaveEntitlement(id, entitlement);
        return "redirect:/admin/entitlement/list";
    }
}
