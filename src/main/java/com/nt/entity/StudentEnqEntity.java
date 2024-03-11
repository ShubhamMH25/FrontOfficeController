package com.nt.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class StudentEnqEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enquiryId;

    private String studentName;
    private String phno;
    private String classMode;
    private String courseName;
    private String enquiryStatus; // Default value
    
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDtlsEntity user;
    
   
	
	public void setUser(UserDtlsEntity user) {
		this.user = user;
	}
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
    public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public void setEnquiryStatus(String enquiryStatus) {
	    this.enquiryStatus = enquiryStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public UserDtlsEntity getUser() {
		return user;
	}
	@Override
	public String toString() {
		return "StudentEnqEntity [enquiryId=" + enquiryId + ", studentName=" + studentName + ", phno=" + phno
				+ ", classMode=" + classMode + ", courseName=" + courseName + ", enquiryStatus=" + enquiryStatus
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", user=" + user + "]";
	}
	
    
	
}
