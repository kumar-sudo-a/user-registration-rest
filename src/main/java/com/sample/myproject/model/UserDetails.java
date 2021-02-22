package com.sample.myproject.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {
	
	@Id
    @GeneratedValue
    private Long id;
	private String firstName;
    private String lastName;
    private String username;
    private String password;

}
