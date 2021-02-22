package com.sample.myproject.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.myproject.model.EmployerDetails;
import com.sample.myproject.model.UserDetails;
import com.sample.myproject.service.UserDetailsService;

/**
 * MyProjectController is the REST endpoint consumer for providing API
 * @author alwal
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MyProjectController {

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping(path = "/getUserData/{employerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployerDetails> getUserData (
			@PathVariable(value = "employerId", required = true) long employerId) {

		EmployerDetails emplDetails = new EmployerDetails();

		if (employerId == 5) {
			emplDetails.setCity("Missouri");
			emplDetails.setCountry("United States");
			emplDetails.setName("Sharath");
			emplDetails.setPostalCode("61701");
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(emplDetails, HttpStatus.OK);

	}

	@PostMapping(path = "/registerUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> registerUser (
			@RequestBody UserDetails userDetails) {

		Integer responseVal = userDetailsService.registerUser(userDetails);

		return new ResponseEntity<>(responseVal, HttpStatus.OK);

	}

	@PostMapping(path = "/loginUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> loginUser (
			@RequestBody UserDetails userDetails) {

		UserDetails responseVal = userDetailsService.loginUser(userDetails);

		if (responseVal.getUsername() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (responseVal.getId() == -1) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {			
			return new ResponseEntity<>(responseVal, HttpStatus.OK);
		}

	}
	
	@DeleteMapping(path = "/deleteUser")
	public ResponseEntity<Integer> deleteUser (
			@RequestParam(value = "userName", required = true) String userName) {

		int responseVal = userDetailsService.deleteUser(userName);

		if (responseVal == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {			
			return new ResponseEntity<>(responseVal, HttpStatus.OK);
		}

	}
	
	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDetails>> getAllUsers () {
		List<UserDetails> getAllusers = new ArrayList<>();
		
		getAllusers = userDetailsService.getAllUsers();
		return new ResponseEntity<>(getAllusers, HttpStatus.OK);
	}

}
