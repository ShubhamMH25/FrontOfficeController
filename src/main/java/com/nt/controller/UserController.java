package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.binding.LoginForm;
import com.nt.binding.SignupForm;
import com.nt.binding.UnlockForm;
import com.nt.service.*;

@Controller
public class UserController {

	@Autowired
	public UserService userService;
	
	
	@GetMapping("/signup")
	public String signUpPage(Model model)
	{
		model.addAttribute("user", new SignupForm());
		return "signup";
	}
	
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignupForm form, Model model)
	{
		boolean status= userService.signup(form);
		
		if(status)
		{
			model.addAttribute("succMsg" , "Check Your Email");
		}else {
			model.addAttribute("errMsg", "Choose unique Email");
		}
		 
		return "signup";
	}
	
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model)
	{
		UnlockForm form= new UnlockForm();
		form.setMail(email);
		
		model.addAttribute("unlock", form);
		
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAc(@ModelAttribute("unlock") UnlockForm unlock, Model model)
	{
		 
		 if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
				
			 boolean status=userService.unlockAccount(unlock);
		 
		 if(status)
		 {
				model.addAttribute("succMsg", "Your AC Unlocked");
		 }else {
				model.addAttribute("errmsg", "Given temporary password is not correct");
		 }
		 
		 
		
			
		} else {
			
			model.addAttribute("errmsg", "Password and Confirm password should be same ");

		}
		return "unlock";
	}
	
	
	
	@GetMapping("/login")
	public String loginPage( Model model)
	{
		
		model.addAttribute("login", new LoginForm());
		
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model)
	{
		String login=userService.login(loginForm);
		
		if(login.contains("Success"))
		{
			return "redirect:/dashbord";
		}
		
		if (login.contains("invalid password or Email")) {
			
		     model.addAttribute("errMsg",login);
		}
		
		if (login.contains("your ac is not Unlocked")) {
			
		     model.addAttribute("errMsg",login);
		}
		model.addAttribute("login", new LoginForm());
		
		return "login";
	}
	
	
	@GetMapping("/forgot")
	public String Pwsforgot()
	{
		return "forgot";
	}
	
	@PostMapping("/forgot")
	public String forgot(@RequestParam("email") String email, Model model)
	{
		boolean status=userService.forgotPws(email);
		
		if(status)
		{
			model.addAttribute("succMsg", "Password sent successfully on your email");
		}else {
			
			model.addAttribute("errMsg", "Invalid Email ID");
		}
		
		return "forgot";
	}
}
