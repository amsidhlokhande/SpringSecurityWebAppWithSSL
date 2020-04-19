package com.amsidh.mvc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amsidh.mvc.entity.PersonEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM PERSON WHERE PERSONID = :personId")
	public PersonEntity getPersonEntityByPersonId(@Param("personId") Integer personId);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM PERSON WHERE PERSONID = :personId")
	public void deletePersonEntityByPersonId(@Param("personId") Integer personId);
}
