package com.amsidh.mvc.model;

import lombok.Data;

@Data
public class PersonDto {
   private Integer personId;
   private String personName;
   private String emailId;
   private Integer age;
   private String address;
   private Long mobileNumber;
      
}
