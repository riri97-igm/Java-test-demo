package ca.java.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.java.team6.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT e FROM Employee e WHERE e.id = :empId")
	Employee findManagerById(@Param("empId") long empId);
	
	@Query("SELECT e FROM Employee e WHERE e.id = :empId")
	Employee findCoveringEmployeeById(@Param("empId") long empId);

}
