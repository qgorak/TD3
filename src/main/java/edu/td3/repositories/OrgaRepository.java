package edu.td3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.td3.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	
    List<Organization> findByDomain(String domain);
    List<Organization> findAll();
    List<Organization> deleteById(int id);


    
	public Optional<Organization> findByName(String name);
	public Organization findById(int id);
	
	@Query("update Organization o set o.name = :name WHERE o.id = :id")
	     void setOrgaName(@Param("id") int id, @Param("name") String name);
	
	


	
	@Query("SELECT o FROM Organization o WHERE o.name LIKE %?1%"
            + " OR o.domain LIKE %?1%"
            + " OR o.aliases LIKE %?1%")
    	public List<Organization> search(String keyword);
	
	  }


