package com.nt.binding;

import lombok.Data;

@Data
public class SignupForm {

	public String name;
	
	public String email;
	
	public String phno;
	
	

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
