package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.LoginForm;
import com.nt.binding.SignupForm;
import com.nt.binding.UnlockForm;
import com.nt.entity.UserDtlsEntity;
import com.nt.repository.UserDtlsRepo;
import com.nt.util.EmailUtils;
import com.nt.util.PwdUtils;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDtlsRepo userDtlsRepo;
	
	@Autowired
	public EmailUtils emailUtils;
	
	@Autowired
	public HttpSession httpSession;
	

	@Override
	public String login(LoginForm form) {
		UserDtlsEntity dtlsEntity=userDtlsRepo.findByEmailAndPwd(form.getEmail(),form.getPassword());
		
		if(dtlsEntity== null)
		{
		return "invalid password or Email";	
		}
		
		if(dtlsEntity.getAccStatus().equalsIgnoreCase("Locked"))
		{
			return "your ac is not Unlocked";
		}
		
		//Create session and store user data in Session
		httpSession.setAttribute("userId", dtlsEntity.getUserId());
		
		return "Success";
	}
	
	

	@Override
	public Boolean signup(SignupForm form) {
		String to=form.getEmail();
		UserDtlsEntity user= userDtlsRepo.findByEmail(to);
		
		if (user != null) {
			return false;
		}
		
		//TODO Copy data from binding class to entity class
		UserDtlsEntity entity=new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		//TODO: Generate random password and set to object
		String tempPws=PwdUtils.generateRandomPassword();
		entity.setPwd(tempPws);
		
		//TODO Set Ac Status as Locked
		entity.setAccStatus("Locked");
		
		//TODO: Insert record
		userDtlsRepo.save(entity);	
		
		//TODO: Send Email to unlock the AC
		
		String subject="unlock your account";
		StringBuffer body=new StringBuffer("");
		body.append("<h1> use below temporary password to unlock your account</h1>");
		body.append("Temporary password :" + tempPws);
		body.append("<a href=\"http://localhost:8090/unlock?email="+to+"\">Click here to unlock your account</a>");
		
		emailUtils.sendEmail(to, subject, body.toString());
		
		return true;
	}
	
	

	@Override
	public boolean unlockAccount(UnlockForm form) {
		
		UserDtlsEntity entity=userDtlsRepo.findByEmail(form.getMail());
		
		if(entity.getPwd().equals(form.getTempPwd()))
		{
		  entity.setPwd(form.getNewPwd());
		  entity.setAccStatus("Unlocked");
		  userDtlsRepo.save(entity);
		  
		} else {
			
			return false;
			
		}
		
		
		return true;
	}
	
	

	@Override
	public boolean forgotPws(String email) {
		UserDtlsEntity entity= userDtlsRepo.findByEmail(email);
		
		if (entity==null) {
			
			return false;
		} 
		String subject="Recover Password";
		String body="Your PWD :: "+entity.getPwd();
		emailUtils.sendEmail(email, subject, body);
		
		return true;
	}

}
