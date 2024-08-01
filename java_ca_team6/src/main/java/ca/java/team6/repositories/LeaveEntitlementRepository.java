package ca.java.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import ca.java.team6.entities.LeaveEntitlement;
@Repository
public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, String>{
	@Query(" SELECT l FROM LeaveEntitlement l WHERE l.empType = 'Full-Time'")
	public LeaveEntitlement findFullTimeLeaveEntitlement();
}
