package com.amsidh.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersonCompositeKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "PERSONID")
	private Integer personId;
	@Column(name = "EMAILID")
	private String emailId;
	@Column(name = "MOBILENUMBER")
	private Long mobileNumber;
}
