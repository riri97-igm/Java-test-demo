package team6.java.ca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team6.java.ca.entities.LeaveType;



public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{
	@Query("SELECT lt FROM LeaveType lt WHERE lt.leaveTypeName = :leaveTypeName")
    LeaveType findByLeaveTypeName(@Param("leaveTypeName") String leaveTypeName);

    @Query("SELECT lt.leaveTypeName FROM LeaveType lt WHERE lt.leaveTypeId = :leaveTypeId")
    String findLeaveTypeNameById(@Param("leaveTypeId") long leaveTypeId);
}

