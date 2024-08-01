package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import team6.java.ca.entities.Admin;
import team6.java.ca.entities.Employee;
import team6.java.ca.entities.User;
import team6.java.ca.serviceImpls.UserServiceImpl;

@Controller
public class CommonController {

    @Autowired
    private UserServiceImpl userServImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                              Model model, HttpSession session) {

        User currUser = userServImp.findUserByUsername(username);

        if (currUser != null && passwordEncoder.matches(password, currUser.getPassword())) {
            session.setAttribute("username", username);
            if (currUser instanceof Admin) {
                session.setAttribute("admin", true);
                return "redirect:/admin";
            } else if (currUser instanceof Employee) {
                Employee empUser = (Employee) currUser;
                if (empUser.getEmpStatus() == Employee.EmpStatus.ACTIVE) {
                    session.setAttribute("admin", false);
                    if (empUser.isManager()) {
                        session.setAttribute("manager", true);
                        return "redirect:/manager";
                    }
                    session.setAttribute("manager", false);
                    return "redirect:/employee";
                }
            }
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
