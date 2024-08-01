package team6.java.ca.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import team6.java.ca.entities.CompensationClaimRecord;
import team6.java.ca.entities.Employee;
import team6.java.ca.entities.EmployeeLeaveRecord;
import team6.java.ca.serviceImpls.CompensationClaimRecordServiceImpl;
import team6.java.ca.serviceImpls.EmployeeLeaveRecordServiceImpl;
import team6.java.ca.serviceImpls.EmployeeServiceImpl;
import team6.java.ca.services.CompensationClaimRecordService;
import team6.java.ca.services.EmployeeLeaveRecordService;
import team6.java.ca.services.EmployeeService;

@Controller
public class ViewLeaveController {
    
    @Autowired
    private EmployeeLeaveRecordService lrservice;

    @Autowired
    private EmployeeService eservice;

    @Autowired
    private CompensationClaimRecordService crservice;

    @Autowired
    public void setLeaveRecordService(EmployeeLeaveRecordServiceImpl lrserviceImpl) {
        this.lrservice = lrserviceImpl;
    }

    @Autowired
    public void setEmployeeService(EmployeeServiceImpl eserviceImpl) {
        this.eservice = eserviceImpl;
    }

    @Autowired
    public void setClaimRecordService(CompensationClaimRecordServiceImpl crserviceImpl) {
        this.crservice = crserviceImpl;
    }

    @GetMapping("/home/{empId}")
    public String homePage(@PathVariable("empId") Long empId, Model model) {
        model.addAttribute("pagetitle", "Homepage");
        Employee e = eservice.findEmployeeByUserId(empId);
        
        if(e == null)
        {
        	return "redirect:/admin";
        }
        model.addAttribute("user", e);
       
        return "home";
    }

    @GetMapping("/myleave/{empId}")
    public String viewHistory(@PathVariable("empId") Long empId, Model model) {
        model.addAttribute("pagetitle", "My Leaves");
        model.addAttribute("user", eservice.findEmployeeByUserId(empId));

        List<EmployeeLeaveRecord> myleaves = lrservice.findEmployeeLeaveRecordsByEmpId(empId);
        model.addAttribute("totalleave", myleaves.size());
        model.addAttribute("myleaves", myleaves);

        List<CompensationClaimRecord> myclaims = crservice.findCompensationLeaveRecordsByEmpId(empId);
        model.addAttribute("totalclaim", myclaims.size());
        model.addAttribute("myclaims", myclaims);

        return "view-my-leave";
    }
}

