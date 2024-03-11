package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.binding.DashbordResponse;
import com.nt.binding.EnquiryForm;
import com.nt.binding.EnquirySearchCriteria;
import com.nt.entity.StudentEnqEntity;
import com.nt.service.EnquiryService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	private HttpSession httpSession;
 
	@GetMapping("/dashbord")
	public String dashbordPage(Model model)
	{
		
    Integer userId=	(Integer)httpSession.getAttribute("userId");
    DashbordResponse dashbordData =enquiryService.response(userId);
		
		
		model.addAttribute("dashbordData", dashbordData);
		
		return "dashbord";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model)
	{
		//Get courses fore droupdown
		List<String>courses= enquiryService.getCoursesName();
		
		//Get enquiry status fore droupdown
		List<String>status=enquiryService.getEnqurieStatus();
		
		//Create a binding class object
		EnquiryForm form=new EnquiryForm();
		
		model.addAttribute("names", courses);
		
		model.addAttribute("statuss", status);
		
		model.addAttribute("forms", form);
		
		return "enquiry";
	}
	
	@PostMapping("/addEnq")
	public String addEnquries(@ModelAttribute("forms") EnquiryForm forms, Model model)
	{
		boolean status =enquiryService.addEnquiry(forms);
		
		if(status)
		{
			model.addAttribute("succMsg","Enquiry added");
		}else {
			model.addAttribute("errMsg","Problem Occured");
		}
		
		return "enquiry";
	}
	
	@GetMapping("/enquries")
	public String viewEnquiries(Model model)
	{
		
		//Get courses fore droupdown
				List<String>courses= enquiryService.getCoursesName();
				
				//Get enquiry status fore droupdown
				List<String>status=enquiryService.getEnqurieStatus();
				
				//Create a binding class object
				EnquiryForm form=new EnquiryForm();
				
				model.addAttribute("names", courses);
				
				model.addAttribute("statuss", status);
				
				model.addAttribute("forms", form);
		
		List<StudentEnqEntity> enquiry=enquiryService.getEnquries();
		
		        model.addAttribute("list",enquiry);
		
		
		return "enquries";
	}
	
	
	 @GetMapping("/searchEnq")
	    public String getFilteredEnqs(@RequestParam String cname,
	                                  @RequestParam String status,
	                                  @RequestParam String mode, 
	                                  Model model) {
	        

	        EnquirySearchCriteria criteria = new EnquirySearchCriteria();    
	        criteria.setCname(cname);
	        criteria.setMode(mode);
	        criteria.setStatus(status);

	        Integer userId = (Integer) httpSession.getAttribute("userId");
	        
	        List<StudentEnqEntity> filteredEnqs = enquiryService.getFilteredEnqs(criteria, userId);

	        // Add the filteredEnqs to the model to send back to the view
	        model.addAttribute("filteredEnqs", filteredEnqs);

	        // Add the criteria to the model for debugging/logging
	        model.addAttribute("criteria", criteria);

	        return "final";
	    }
	

}
