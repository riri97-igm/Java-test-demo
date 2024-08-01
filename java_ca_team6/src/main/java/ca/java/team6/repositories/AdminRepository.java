package ca.java.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.java.team6.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
