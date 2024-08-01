package ca.java.team6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.java.team6.entities.Employee;
import ca.java.team6.entities.EmployeeLeaveRecord;
import ca.java.team6.entities.LeaveEntitlement;
import ca.java.team6.repositories.EmployeeLeaveRecordRepository;
import ca.java.team6.repositories.EmployeeRepository;
import ca.java.team6.repositories.LeaveEntitlementRepository;

@SpringBootTest
public class EmployeeLeaveRecordRepositoryTest {
	@Autowired
	private EmployeeLeaveRecordRepository employeeLeaveRecordRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LeaveEntitlementRepository leaveEntitlementRepository;
	@Test 
	public void testSaveEntity() {
		
		employeeLeaveRecordRepository.deleteAll();
		employeeRepository.deleteAll();
		
		leaveEntitlementRepository.deleteAll();
		
		LeaveEntitlement leavePartTime = new LeaveEntitlement("Part-Time", 20, 30);
		LeaveEntitlement leaveFullTime = new LeaveEntitlement("Full-Time", 10, 5);
		leaveEntitlementRepository.save(leavePartTime);
		leaveEntitlementRepository.save(leaveFullTime);
		LeaveEntitlement leaveEntitlement = leaveEntitlementRepository.findFullTimeLeaveEntitlement();
		
		Employee approveManager = new Employee("Ray", "Sun", leaveEntitlement, Date.valueOf("2014-06-15"), "Active",null, "password123");
	
        employeeRepository.save(approveManager);
		Employee coveringEmployee = new Employee("Zeyu", "Lin", leaveEntitlement, Date.valueOf("2014-06-15"),"Active", approveManager, "password123");
	
		employeeRepository.save(coveringEmployee);
		Employee employee = new Employee("Joyish", "zhao", leaveEntitlement, Date.valueOf("2014-06-15"), "Active", coveringEmployee,"password123");
		employeeRepository.save(employee);
		

		EmployeeLeaveRecord elr = new EmployeeLeaveRecord();
		elr.setLeaveQty(1.0);
		elr.setLeaveDate(Date.valueOf("2014-06-15"));
		elr.setEmployee(employee);
		elr.setLeaveType("Dayoff");
		elr.setStatus("Approval");
		elr.setApproveManager(approveManager);
		elr.setCoveringEmployee(coveringEmployee);
		elr.setAdditionalReason("Personal reasons");
		elr.setOverseas(false);
		elr.setContactDetail("123-456-7890");
		
		employeeLeaveRecordRepository.save(elr);
		
	
		EmployeeLeaveRecord elrRepo = employeeLeaveRecordRepository.findEmployeeLeaveRecordById(2L);
	
		assertThat(elrRepo.getLeaveId()).isNotNull();
		assertThat(elrRepo).isNotNull();
		assertEquals(2L, elrRepo.getLeaveId());
		assertEquals(1.0, elrRepo.getLeaveQty());
		assertThat(elrRepo.getLeaveDate()).isNotNull();
		assertEquals(employee.toString(), elrRepo.getEmployee().toString());
		assertEquals("Dayoff", elrRepo.getLeaveType());
		assertEquals("Approval", elrRepo.getStatus());
		assertEquals(approveManager.toString(), elrRepo.getApproveManager().toString());
		assertEquals(coveringEmployee.toString(), elrRepo.getCoveringEmployee().toString());
		assertEquals("Personal reasons", elrRepo.getAdditionalReason());
		assertEquals(false, elrRepo.isOverseas());
		assertEquals("123-456-7890", elrRepo.getContactDetail());
		
	}
}
