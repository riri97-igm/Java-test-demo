package ca.java.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.java.team6.entities.EmployeeLeaveRecord;
public interface EmployeeLeaveRecordRepository extends JpaRepository< EmployeeLeaveRecord, Long>{
	
	@Query("SELECT e FROM EmployeeLeaveRecord e WHERE e.leaveId = :id")
	public EmployeeLeaveRecord findEmployeeLeaveRecordById(@Param("id") Long id);
}
