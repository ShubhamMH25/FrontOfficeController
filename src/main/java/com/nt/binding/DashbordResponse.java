package com.nt.binding;

import lombok.Data;

@Data
public class DashbordResponse {

	public Integer totalEnquries;
	
	public Integer enrolledcnt;
	
	public Integer lostEnt;
	
	public Integer newEnt;
	
	

	public Integer getNewEnt() {
		return newEnt;
	}

	public void setNewEnt(Integer newEnt) {
		this.newEnt = newEnt;
	}

	public Integer getTotalEnquries() {
		return totalEnquries;
	}

	public void setTotalEnquries(Integer totalEnquries) {
		this.totalEnquries = totalEnquries;
	}

	public Integer getEnrolledcnt() {
		return enrolledcnt;
	}

	public void setEnrolledcnt(Integer enrolledcnt) {
		this.enrolledcnt = enrolledcnt;
	}

	public Integer getLostEnt() {
		return lostEnt;
	}

	public void setLostEnt(Integer lostEnt) {
		this.lostEnt = lostEnt;
	}
	
	
	
	
}
