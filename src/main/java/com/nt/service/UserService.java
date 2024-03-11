package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.binding.LoginForm;
import com.nt.binding.SignupForm;
import com.nt.binding.UnlockForm;
@Service
public interface UserService {

	
	public String login(LoginForm form);
	
	public Boolean signup(SignupForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public boolean forgotPws(String email);
}
