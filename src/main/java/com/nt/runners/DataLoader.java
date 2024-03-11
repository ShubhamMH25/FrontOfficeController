package com.nt.runners;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.CourseEntity;
import com.nt.entity.EnqStatusEntity;
import com.nt.repository.CourseRepo;
import com.nt.repository.EnqStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqStatusRepo;
	
	@Override
	public void run(ApplicationArguments org) throws Exception {
		
		courseRepo.deleteAll();
		
        
		CourseEntity c1=new CourseEntity();
		c1.setCourseName("Java");
		
		CourseEntity c2=new CourseEntity();
		c2.setCourseName("Python");
		
		CourseEntity c3=new CourseEntity();
		c3.setCourseName("DevOps");
		
        courseRepo.saveAll(Arrays.asList(c1,c2,c3));
        
        enqStatusRepo.deleteAll();
        
        EnqStatusEntity s1=new EnqStatusEntity();
       s1.setEnquiryStatus("New");
        
        EnqStatusEntity s2=new EnqStatusEntity();
        s2.setEnquiryStatus("Enrolled");
        
        EnqStatusEntity s3=new EnqStatusEntity();
        s3.setEnquiryStatus("Lost");
        
        enqStatusRepo.saveAll(Arrays.asList(s1,s2,s3));
    }
	
}
