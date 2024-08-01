package ca.java.team6.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class LeaveEntitlement {
	
	@Id
	@Column(name="EmpType", length=50)
	private String empType;
	
	@Column(name="AnnualQty")
	private int annualQty;
	
	@Column(name="MedicalQty")
	private int medicalQty;
	
	
	@OneToMany(mappedBy="empType")
	private List<Employee> employees;
	
	
	
	public LeaveEntitlement()
	{
		
	}
	
	public LeaveEntitlement(String empType, int annualQty, int medicalQty)
	{
		this.setEmpType(empType);
		this.setAnnualQty(annualQty);
		this.setMedicalQty(medicalQty);
		
	}
	
	
	public int getAnnualQty() {
		return annualQty;
	}
	public void setAnnualQty(int annualQty) {
		this.annualQty = annualQty;
	}
	public int getMedicalQty() {
		return medicalQty;
	}
	public void setMedicalQty(int medicalQty) {
		this.medicalQty = medicalQty;
	}
	
	public String getEmpType() {
		return empType;
	}
	
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	

	
	@Override
	public String toString() {
		return "LeaveEntitlement [empType=" + empType + ", annualQty=" + annualQty + ", medicalQty=" + medicalQty + "]";
	}

	
	
}
