package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpServletRequest;
import team6.java.ca.entities.CompensationClaimRecord;
import team6.java.ca.entities.CompensationClaimRecord.ClaimStatus;
import team6.java.ca.entities.Employee;
import team6.java.ca.serviceImpls.EmployeeServiceImpl;
import team6.java.ca.services.CompensationClaimRecordService;

@Controller
@RequestMapping("compensation")
public class CompensationClaimController {
	@Autowired
	private CompensationClaimRecordService ccrService;
	
	@Autowired
	private EmployeeServiceImpl empServImp;
	

	// temporary method to show all claims first
	// later need to differentiate between employee, manager views
	// this should be for indiviudal claim history
	@GetMapping("/claims")
	public String viewClaims(Model model, HttpServletRequest request) {
		String curr_username = (String) request.getSession().getAttribute("username");
		Employee curr_emp = empServImp.findEmployeeByUsername(curr_username);
		model.addAttribute("claims", ccrService.findAllClaimRecordsOfEmployee(curr_emp));
		return "my-claims";
	}
	
	@GetMapping("/claims/team")
	public String viewClaimsTeam(Model model, HttpServletRequest request) {
		String curr_username = (String) request.getSession().getAttribute("username");
		Employee curr_emp = empServImp.findEmployeeByUsername(curr_username);
		if (curr_emp.isManager()) {
			model.addAttribute("claims", ccrService.findAllClaimRecordsOfMgr(curr_emp));
			return "team-claims";
		}
		return "redirect:/compensation/claims";
	}
	
	@GetMapping("/claim/{id}")
	public String viewClaim(@PathVariable("id") long claimId, Model model, HttpServletRequest request) {
		// Retrieve current viewer
		String curr_username = (String) request.getSession().getAttribute("username");
		Employee curr_viewer = empServImp.findEmployeeByUsername(curr_username);

		// Find claim by ID
		CompensationClaimRecord curr_claim = ccrService.findClaimByClaimId(claimId);
		if (curr_claim == null) {
			return "redirect:/compensation/claims";
		}

		Employee curr_appr_mgr = curr_claim.getApproveManager();
		Employee curr_claimant = curr_claim.getEmployee();
		
		model.addAttribute("claimstatus", curr_claim.getStatus().toString());

		// Check if viewer is claimant or manager
		if (curr_viewer.equals(curr_claimant)) {
			model.addAttribute("userRole", "EMPLOYEE");
		} else if (curr_viewer.equals(curr_appr_mgr)) {
			model.addAttribute("userRole", "MANAGER");
		} else {
			return "redirect:/compensation/claims"; // Unauthorized access
		}

		model.addAttribute("claim", curr_claim);
		return "claim-details";
	}
	
	@PostMapping("/claim/{id}/cancel")
	public String cancelClaim(@PathVariable("id") long claimId) {
		CompensationClaimRecord curr_claim = ccrService.findClaimByClaimId(claimId);
		if (curr_claim != null) {
			curr_claim.setStatus(ClaimStatus.Cancelled);
			ccrService.updateClaimStatus(claimId, curr_claim.getStatus());
		}
		return "redirect:/compensation/claim/" + claimId;
	}
	
	@PostMapping("/claim/{id}/approve")
	public String approveClaim(@PathVariable("id") long claimId) {
		CompensationClaimRecord curr_claim = ccrService.findClaimByClaimId(claimId);
		if (curr_claim != null) {
			curr_claim.setStatus(ClaimStatus.Approved);
			ccrService.updateClaimStatus(claimId, curr_claim.getStatus());
		}
		return "redirect:/compensation/claim/" + claimId;
	}
	
	@PostMapping("/claim/{id}/reject")
	public String rejectClaim(@PathVariable("id") long claimId) {
		CompensationClaimRecord curr_claim = ccrService.findClaimByClaimId(claimId);
		if (curr_claim != null) {
			curr_claim.setStatus(ClaimStatus.Rejected);
			ccrService.updateClaimStatus(claimId, curr_claim.getStatus());
		}
		return "redirect:/compensation/claim/" + claimId;
	}
	
	
	
	@GetMapping("/create")
	public String createClaim(Model model) {
		model.addAttribute("claim", new CompensationClaimRecord());
		return "create-claim";
	}
	
	
	@PostMapping("/create")
	public String saveClaim(@ModelAttribute CompensationClaimRecord inCCR, 
							HttpServletRequest request) {
		String curr_username = (String) request.getSession().getAttribute("username");
		Employee curr_emp = empServImp.findEmployeeByUsername(curr_username);
		inCCR.setEmployee(curr_emp);
		Employee mgr = curr_emp.getManager();
		if (mgr != null) {
			inCCR.setApproveManager(curr_emp.getManager());
		}
		else {
			inCCR.setApproveManager(null);
		}
		
		inCCR.setStatus(ClaimStatus.Pending);
		ccrService.createClaim(inCCR);
		return "redirect:/compensation/claims";
	}

}
