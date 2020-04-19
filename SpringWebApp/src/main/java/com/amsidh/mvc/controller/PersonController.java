package com.amsidh.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amsidh.mvc.model.PersonDto;
import com.amsidh.mvc.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ResponseEntity<String> welcome() {
		System.out.println("PersonController's welcome called");
		return new ResponseEntity<String>("SpringRestApi is up and running fine", HttpStatus.OK);
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PersonDto>> savePerson(@RequestBody PersonDto personDto) {
		System.out.println("PersonController's savePerson called");
		personService.addPerson(personDto);
		return new ResponseEntity<List<PersonDto>>(personService.getAllPersons(), HttpStatus.OK);
	}
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PersonDto>> getAllPersons() {
		System.out.println("PersonController's getAllPersons called");
		return new ResponseEntity<List<PersonDto>>(personService.getAllPersons(), HttpStatus.OK);
	}

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PersonDto> getPerson(@PathVariable("personId") final Integer personId) {
		return new ResponseEntity<PersonDto>(personService.getPerson(personId), HttpStatus.OK);
	}

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PersonDto>> deletePerson(@PathVariable("personId") final Integer personId) {
		System.out.println("PersonController's deletePerson called");
		personService.deletePerson(personId);
		return new ResponseEntity<List<PersonDto>>(personService.getAllPersons(), HttpStatus.OK);
	}

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PersonDto>> updatePerson(@PathVariable("personId") final Integer personId, @RequestBody final PersonDto personDto) {
		System.out.println("PersonController's updatePerson called");
		personService.updatePerson(personId, personDto);
		return new ResponseEntity<List<PersonDto>>(personService.getAllPersons(), HttpStatus.OK);
	}

}
