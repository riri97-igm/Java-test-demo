package ca.java.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.java.team6.entities.CompensationClaimRecord;
@Repository
public interface CompensationClaimRecordRepository extends JpaRepository<CompensationClaimRecord, Long> {

}
