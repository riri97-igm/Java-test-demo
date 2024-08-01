package team6.java.ca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team6.java.ca.entities.CompensationClaimRecord;
import team6.java.ca.entities.CompensationClaimRecord.ClaimStatus;
import team6.java.ca.entities.Employee;



public interface CompensationClaimRecordRepository extends JpaRepository<CompensationClaimRecord, Long> {
	@Query("SELECT e FROM CompensationClaimRecord e")
	List<CompensationClaimRecord> findAllClaimRecords();

	@Query("SELECT e FROM CompensationClaimRecord e WHERE e.employee = :emp")
	List<CompensationClaimRecord> findAllClaimRecordsOfEmployee(@Param("emp") Employee emp);

	@Query("SELECT e FROM CompensationClaimRecord e WHERE e.approveManager = :mgr")
	List<CompensationClaimRecord> findAllClaimRecordsOfMgr(@Param("mgr") Employee mgr);
	
	@Query("SELECT e FROM CompensationClaimRecord e WHERE e.employee.userId = :empId")
    List<CompensationClaimRecord> findCompensationLeaveRecordsByEmpId(@Param("empId") Long empId);

	@Query("SELECT e FROM CompensationClaimRecord e WHERE e.status = :claimstatus")
	List<CompensationClaimRecord> findAllClaimRecordsByStatus(@Param("claimstatus") ClaimStatus status);
	
	@Query("SELECT e FROM CompensationClaimRecord e WHERE e.claimId = :claimId")
    CompensationClaimRecord findClaimByClaimId(@Param("claimId") long claimId);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM CompensationClaimRecord e WHERE e.claimId = :claimId")
    boolean existsById(@Param("claimId") long claimId);
    
    @Query("UPDATE CompensationClaimRecord c SET c.status = :status, c.lastUpdateTime = CURRENT_TIMESTAMP WHERE c.claimId = :claimId")
    void updateClaimStatus(@Param("claimId") long claimId, @Param("status") CompensationClaimRecord.ClaimStatus status);

}