package team6.java.ca.services;

import team6.java.ca.entities.Employee;

import java.util.List;

import org.springframework.data.repository.query.Param;


public interface EmployeeService {

    List<Employee> findEmployeeByName(String name);

    List<Employee> findAllEmployees();

    List<Employee> findAllManagers();

    List<Employee> findAllEmployeesOfMgr(Long managerId);

    Employee findEmployeeByUserId(Long userId);

    Employee findEmployeeByUsername(String username);

    boolean existsByUsername(String username);

    String findEmailByUserId(Long userId);

    List<Employee> findEmployeesByEmpType(Long empTypeId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long userId, Employee employee);

    void deleteEmployee(Long userId);
    
    Employee findManagerByEmpId(Long empId);
    
    void changeManager(long wishingEmployeeId, long preferManagerId);
    
    void save(Employee e);
}

