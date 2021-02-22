package com.sample.myproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sample.myproject.model.UserDetails;

@Service
public class UserDetailsService {
	
	private static final Map<String, UserDetails> userDetailsCache = new HashMap<>();
	
	public Integer registerUser(UserDetails userDetails) {
		Integer response = 0;
		long generatedLong = new Random().nextLong();
		userDetails.setId(generatedLong);
		
		if (userDetails != null && userDetails.getUsername() != null) {
			
			if (userDetailsCache.containsKey(userDetails.getUsername())) {
				return response = -1; // User already exists
			}
			
			userDetailsCache.put(userDetails.getUsername(), userDetails);
			
		} else {

		}
		return response;
		
	}
	
	public UserDetails loginUser(UserDetails userDetails) {
		UserDetails loginresponse = new UserDetails();
		
		if (userDetails != null && userDetails.getUsername() != null) {
			
			if (userDetailsCache.containsKey(userDetails.getUsername())) {
				loginresponse = getuserDetailsFromCache(userDetails.getUsername());
				if (loginresponse.getPassword().equals(userDetails.getPassword())) {
					return loginresponse;
				} else {
					loginresponse.setId((long) -1);
					return loginresponse;	
				}
			}
			
		}
		return loginresponse;
		
	}
	
	public synchronized UserDetails getuserDetailsFromCache(String userName) {
		return userDetailsCache.get(userName);
	}

	public int deleteUser(String userName) {
		int returnCode = 0;
		if (userName != null) {
			
			if (userDetailsCache.containsKey(userName)) {
				userDetailsCache.remove(userName);
				return returnCode = 1;
			}
			
		}
		return returnCode;
		
	}

	public List<UserDetails> getAllUsers() {
		List<UserDetails> getAllUsers = new ArrayList(userDetailsCache.values());
		/*
		 * if (!userDetailsCache.isEmpty()) { getAllUsers =
		 * userDetailsCache.remove(userName); return getAllUsers; }
		 */
		// TODO Auto-generated method stub
		return getAllUsers;
	}

}
