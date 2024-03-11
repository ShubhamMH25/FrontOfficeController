package com.nt.service;

import java.util.List;

import com.nt.binding.DashbordResponse;
import com.nt.binding.EnquiryForm;
import com.nt.binding.EnquirySearchCriteria;
import com.nt.entity.StudentEnqEntity;

public interface EnquiryService {

	
	//Get the data for drop down display(Courses name)
	public List<String> getCoursesName();
	
	//Get the data for drop down display(Enqurie Status)
	public List<String> getEnqurieStatus();
	
	//Get the data for dashbord
	public DashbordResponse response(Integer userId);
	
	//used to add the enquries
	public boolean addEnquiry(EnquiryForm form);
	
	//used to get the enquries
	public List<StudentEnqEntity>getEnquries();
	
	//used for edit particular enquiry ID
	public EnquiryForm getEnquiry(Integer enqId);
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria,Integer userId);
}
