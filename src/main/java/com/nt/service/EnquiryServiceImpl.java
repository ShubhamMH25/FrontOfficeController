package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.DashbordResponse;
import com.nt.binding.EnquiryForm;
import com.nt.binding.EnquirySearchCriteria;
import com.nt.entity.CourseEntity;
import com.nt.entity.EnqStatusEntity;
import com.nt.entity.StudentEnqEntity;
import com.nt.entity.UserDtlsEntity;
import com.nt.repository.CourseRepo;
import com.nt.repository.EnqStatusRepo;
import com.nt.repository.StudentEnqRepo;
import com.nt.repository.UserDtlsRepo;

import jakarta.servlet.http.HttpSession;
@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	public UserDtlsRepo dtlsRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqStatusRepo;
	
	@Autowired
	private StudentEnqRepo studentEnqRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public DashbordResponse response(Integer userId) {
		
		DashbordResponse dResponse=new DashbordResponse();
		
		Optional<UserDtlsEntity>findById=dtlsRepo.findById(userId);
		
		if(findById.isPresent()) {
		
		UserDtlsEntity dtlsEntity=findById.get();
		
		List<StudentEnqEntity>enqEntities= dtlsEntity.getEnquries();
		
		Integer totalCount = enqEntities.size();
		
     Integer enrolledCnt = enqEntities.stream()
		        .filter(e -> e.getEnquiryStatus().equals("Enrolled"))
		        .collect(Collectors.toList()).size();
		       
				 
		Integer LostedCnt = enqEntities.stream()
		        .filter(e -> e.getEnquiryStatus().equals("Lost"))
		        .collect(Collectors.toList()).size();
		       
		
	    dResponse.setEnrolledcnt(enrolledCnt);
		dResponse.setLostEnt(LostedCnt);
		dResponse.setTotalEnquries(totalCount);
		
		}
		
		return dResponse;
		
	}

	@Override
	public List<String> getCoursesName() {
		
		List<CourseEntity> findAll=courseRepo.findAll();
		
		List<String> names= new ArrayList<>();
		
		for(CourseEntity entity : findAll)
		{
			names.add(entity.getCourseName());
		}
		
		
		return names;
	}

	@Override
	public List<String> getEnqurieStatus() {
		
		 List<EnqStatusEntity> findAll = enqStatusRepo.findAll();

		 List<String> status= new ArrayList<>();
			
			for(EnqStatusEntity entity : findAll)
			{
				status.add(entity.getEnquiryStatus());
			}
			
			
			return status;
		
		  
	}

	@Override
	public boolean addEnquiry(EnquiryForm forms) {
		
		StudentEnqEntity enqEntity=new StudentEnqEntity();
		BeanUtils.copyProperties(forms, enqEntity);
		
		 Integer userId=(Integer)session.getAttribute("userId");
		
		 UserDtlsEntity entity=dtlsRepo.findById(userId).get();
		 
		 enqEntity.setUser(entity);
		 
		 studentEnqRepo.save(enqEntity);
		
		return true;
	}

	@Override
	public List<StudentEnqEntity>getEnquries() {
		
		Integer userId=(Integer) session.getAttribute("userId");
		
		Optional<UserDtlsEntity> enquriesData=dtlsRepo.findById(userId);
		
		if (enquriesData.isPresent()) {
			
			UserDtlsEntity userDtlsEntity=enquriesData.get();
			
			List<StudentEnqEntity>enqEntities=userDtlsEntity.getEnquries();
			
			return enqEntities;
			
		}
		
			return null;
	}
	
	
	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
	    Optional<UserDtlsEntity> userDetails = dtlsRepo.findById(userId);

	    if (userDetails.isPresent()) {
	        UserDtlsEntity userDtlsEntity = userDetails.get();
	        List<StudentEnqEntity> enqEntities = userDtlsEntity.getEnquries();

	        if (null != criteria.getCname() & !"".equals(criteria.getCname())) {
	            enqEntities = enqEntities.stream()
	                    .filter(e -> e.getCourseName().equals(criteria.getCname()))
	                    .collect(Collectors.toList());
	            System.out.println(enqEntities);
	        }

	        if (null != criteria.getStatus() & !"".equals(criteria.getStatus())) {
	            enqEntities = enqEntities.stream()
	                    .filter(e -> e.getEnquiryStatus().equals(criteria.getStatus()))
	                    .collect(Collectors.toList());
	            
	        }

	        if (null != criteria.getMode() & !"".equals(criteria.getMode())) {
	            enqEntities = enqEntities.stream()
	                    .filter(e -> e.getClassMode().equals(criteria.getMode()))
	                    .collect(Collectors.toList());
	        }

	        return enqEntities;
	    }

	    return null;
	}


	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}
}
