package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;
import team6.java.ca.entities.Employee;
import team6.java.ca.serviceImpls.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl empServImp;

	@GetMapping("/employee")
	public String showEmployeeLandingPage(HttpSession session) {
		String curr_username = (String) session.getAttribute("username");
		Employee curr_viewer = empServImp.findEmployeeByUsername(curr_username);
		if (!curr_viewer.isManager()) {
			return "emp-lp";
		}
		return "redirect:/manager";
	}

	@GetMapping("/manager")
	public String showManagerLandingPage(HttpSession session) {
		String curr_username = (String) session.getAttribute("username");
		Employee curr_viewer = empServImp.findEmployeeByUsername(curr_username);
		if (curr_viewer.isManager()) {
			return "mgr-lp";
		}
		return "redirect:/employee";
	}
}
