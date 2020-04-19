package com.amsidh.mvc.util;

import com.amsidh.mvc.entity.PersonCompositeKey;
import com.amsidh.mvc.entity.PersonEntity;
import com.amsidh.mvc.model.PersonDto;

public class BeanUtil {

	public static PersonEntity toPersonEntity(PersonDto personDto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPersonName(personDto.getPersonName());
		personEntity.setAge(personDto.getAge());
		personEntity.setAddress(personDto.getAddress());
		personEntity.setPersonCompositeKey(
				new PersonCompositeKey(personDto.getPersonId(), personDto.getEmailId(), personDto.getMobileNumber()));
		return personEntity;

	}
	public static PersonDto toPersonDto(PersonEntity personEntity) {
		PersonDto personDto = new PersonDto();
		personDto.setPersonName(personEntity.getPersonName());
		personDto.setAge(personEntity.getAge());
		personDto.setAddress(personEntity.getAddress());
		personDto.setPersonId(personEntity.getPersonCompositeKey().getPersonId());
		personDto.setEmailId(personEntity.getPersonCompositeKey().getEmailId());
		personDto.setMobileNumber(personEntity.getPersonCompositeKey().getMobileNumber());
		return personDto;
	}
}
