package com.auto.myte.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auto.myte.entity.User;
import com.auto.myte.form.LoginForm;
import com.auto.myte.service.SignUpService;
import com.auto.myte.utils.MD5Utils;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private SignUpService signUpService;
	
	/**
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/signup")
	public String signUp(Model model, LoginForm form, BindingResult result) throws Exception {
		return "signup";
	}

	/**
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insert(Model model, @Valid LoginForm form, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("MSG", "error");
			return "signup";
		} 
		
		User user = new User();
		user.setEid(form.getEid());
		user.setEname(form.getUsername());
		user.setPassword(MD5Utils.encode(form.getPassword()));
		int flg = signUpService.singUp(user);

		if (flg > 0) {
			model.addAttribute("MSG", "success");
		}
		
		return "login";
	}

	/**
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Model model, @Valid LoginForm form, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("MSG", "error");
			return "signup";
		} 
		
		if (signUpService.findByEid(form.getEid()) != null) {
			
			User user = new User();
			user.setEid(form.getEid());
			user.setEname(form.getUsername());
			user.setPassword(MD5Utils.encode(form.getPassword()));
			int flg = signUpService.updateUser(user);
			if (flg > 0) {
				model.addAttribute("MSG", "success");
			}
			
			
		} else {
			model.addAttribute("MSG", "error");
			return "signup";
		}
		
		return "login";
	}

}
