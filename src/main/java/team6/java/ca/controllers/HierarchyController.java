package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import team6.java.ca.entities.Employee;
import team6.java.ca.services.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/admin/hierarchy")
public class HierarchyController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String hierarchyListPage(Model model) {
        List<Employee> employeeList = employeeService.findAllEmployees();
        model.addAttribute("employeeList", employeeList);
        // TODO: Implement login check
        return "Hierarchy-list";
    }

    @GetMapping("/edit/{empId}")
    public String showEditForm(@PathVariable("empId") long empId, Model model) {
        Employee employee = employeeService.findEmployeeByUserId(empId);
        List<Employee> managerEmployees = employeeService.findAllManagers();
        model.addAttribute("employee", employee);
        model.addAttribute("managerEmployees", managerEmployees);
        return "Hierarchy-edit";
    }

    @PostMapping("/edit/mgrId")
    public String updateManager(@RequestParam("managerId") Long managerId, @RequestParam("empId") Long empId) {
    	
    	Employee currentEmployee = employeeService.findEmployeeByUserId(empId);
    	Employee chosenManager = employeeService.findEmployeeByUserId(managerId);
    	
    	currentEmployee.setManager(chosenManager);
    	
    	employeeService.save(currentEmployee);
    	
    	return "redirect:/admin/hierarchy/edit/" + empId;

    }
}
