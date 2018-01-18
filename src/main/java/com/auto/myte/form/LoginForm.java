package com.auto.myte.form;

import com.auto.myte.validator.CheckPassword;

@CheckPassword
public class LoginForm {

	String eid;
	String username;
	String password;
	String passwordConfrim;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfrim() {
		return passwordConfrim;
	}

	public void setPasswordConfrim(String passwordConfrim) {
		this.passwordConfrim = passwordConfrim;
	}

}
