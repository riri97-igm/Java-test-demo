package team6.java.ca.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.java.ca.entities.Employee;
import team6.java.ca.repositories.EmployeeRepository;
import team6.java.ca.services.EmployeeService;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findByLastnameOrFirstname(name);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public List<Employee> findAllManagers() {
        return employeeRepository.findAllManagers();
    }

    @Override
    public List<Employee> findAllEmployeesOfMgr(Long managerId) {
        return employeeRepository.findEmployeesUnderMgr(managerId);
    }

    @Override
    public Employee findEmployeeByUserId(Long userId) {
        return employeeRepository.findEmployeeByUserId(userId);
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findEmployeeByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return employeeRepository.existsByUsername(username);
    }

    @Override
    public String findEmailByUserId(Long userId) {
        return employeeRepository.findEmailByUserId(userId);
    }

    @Override
    public List<Employee> findEmployeesByEmpType(Long empTypeId) {
        return employeeRepository.findEmployeesByEmpType(empTypeId);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public Employee findManagerByEmpId(Long empId)
    {
    	return employeeRepository.findManagerByEmpId(empId);
    }
    
    @Override
    public void changeManager(long wishingEmployeeId, long preferManagerId)
    {
    	employeeRepository.changeManager(wishingEmployeeId, preferManagerId);
    }

    @Override
    public Employee updateEmployee(Long userId, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(userId).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setEmpType(employee.getEmpType());
        existingEmployee.setJoinDate(employee.getJoinDate());
        existingEmployee.setEmpStatus(employee.getEmpStatus());
        existingEmployee.setManager(employee.getManager());
        existingEmployee.setManager(employee.isManager());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long userId) {
        employeeRepository.deleteById(userId);
    }
    
    @Override
    public void save(Employee e)
    {
    	 employeeRepository.save(e);
    }
}

