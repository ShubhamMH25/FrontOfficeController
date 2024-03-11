package com.nt.binding;

import java.util.Objects;

import lombok.Data;

@Data
public class EnquirySearchCriteria {

	public String cname;
	
	public String status;
	
	public String mode;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "EnquirySearchCriteria [cname=" + cname + ", status=" + status + ", mode=" + mode + ", getCname()="
				+ getCname() + ", getStatus()=" + getStatus() + ", getMode()=" + getMode() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cname, mode, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnquirySearchCriteria other = (EnquirySearchCriteria) obj;
		return Objects.equals(cname, other.cname) && Objects.equals(mode, other.mode)
				&& Objects.equals(status, other.status);
	}

	
	
	
	
}
