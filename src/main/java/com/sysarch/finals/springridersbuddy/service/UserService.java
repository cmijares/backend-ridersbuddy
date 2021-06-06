package com.sysarch.finals.springridersbuddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sysarch.finals.springridersbuddy.model.User;
import com.sysarch.finals.springridersbuddy.repository.UserRepository;

@Service
public class UserService {
	
	private static UserRepository userrepository;
	
	public UserService(UserRepository userrepository) {
		UserService.userrepository = userrepository;
	}
	
	public static List<User> getAllUsers(){
		return userrepository.findAll();
	}
}
