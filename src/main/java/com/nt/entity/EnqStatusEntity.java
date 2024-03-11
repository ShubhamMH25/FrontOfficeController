package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnqStatusEntity {
	     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer ststusId;
		private String enquiryStatus;
		public Integer getStstusId() {
			return ststusId;
		}
		public void setStstusId(Integer ststusId) {
			this.ststusId = ststusId;
		}
		public String getEnquiryStatus() {
			return enquiryStatus;
		}
		public void setEnquiryStatus(String enquiryStatus) {
			this.enquiryStatus = enquiryStatus;
		}
		
}
