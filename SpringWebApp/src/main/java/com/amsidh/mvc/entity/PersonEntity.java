package com.amsidh.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSON")
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	PersonCompositeKey personCompositeKey;
	@Column(name = "PERSONNAME")
	private String personName;
	@Column(name = "AGE")
	private Integer age;
	@Column(name = "ADDRESS")
	private String address;
}
