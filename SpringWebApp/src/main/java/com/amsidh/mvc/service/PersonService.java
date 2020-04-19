package com.amsidh.mvc.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amsidh.mvc.entity.PersonEntity;
import com.amsidh.mvc.model.PersonDto;
import com.amsidh.mvc.repository.PersonRepository;
import com.amsidh.mvc.util.BeanUtil;

@Service
public class PersonService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonRepository personRepository;

	
	public void addPerson(PersonDto personDto) {
		
		PersonEntity personEntity = BeanUtil.toPersonEntity(personDto);
		personRepository.save(personEntity);
	}

	public PersonDto getPerson(final Integer personId) {
		PersonDto personDto = BeanUtil.toPersonDto(personRepository.getPersonEntityByPersonId(personId));
		return personDto;
	}

	public List<PersonDto> getAllPersons() {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false)
				.map(personEntity ->BeanUtil.toPersonDto(personEntity)).collect(Collectors.toList());

	}

	@Transactional()
	public void deletePerson(final Integer personId) {
		personRepository.deletePersonEntityByPersonId(personId);
	}

	public void updatePerson(final Integer personId, final PersonDto personDto) {
		
		Optional.of(personRepository.getPersonEntityByPersonId(personId)).ifPresent(personEntity -> {
			personEntity.setPersonName(personDto.getPersonName());
			personEntity.setAge(personDto.getAge());
			personEntity.setAddress(personDto.getAddress());
			personEntity.getPersonCompositeKey().setMobileNumber(personDto.getMobileNumber());
			personEntity.getPersonCompositeKey().setEmailId(personDto.getEmailId());
		});

	}

}
