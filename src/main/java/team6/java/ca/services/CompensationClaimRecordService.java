package team6.java.ca.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import team6.java.ca.entities.CompensationClaimRecord;
import team6.java.ca.entities.Employee;



public interface CompensationClaimRecordService {
	List<CompensationClaimRecord> findAllClaimRecords();
	List<CompensationClaimRecord> findAllClaimRecordsOfEmployee(Employee emp);
	List<CompensationClaimRecord> findAllClaimRecordsOfMgr(Employee mgr);
	List<CompensationClaimRecord> findAllClaimRecordsByStatus(CompensationClaimRecord.ClaimStatus status);
	CompensationClaimRecord createClaim(CompensationClaimRecord ccr);
	List<CompensationClaimRecord> findCompensationLeaveRecordsByEmpId(Long empId);
	
	CompensationClaimRecord updateClaim(long claimId, CompensationClaimRecord ccr);
    void deleteClaim(long claimId);
    CompensationClaimRecord findClaimByClaimId(long claimId);
    boolean existsById(long claimId);
    
    void updateClaimStatus( long claimId, CompensationClaimRecord.ClaimStatus status);

}
