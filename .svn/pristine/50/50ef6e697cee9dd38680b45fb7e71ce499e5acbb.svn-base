package com.axon.icloud.host.javabean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MobileBean implements Writable {
	   
      private String phone;
      private float taoCanFee;
      private float currentFee;
      private float currentFlow;
	  private String openDate;
	public void write(DataOutput out) throws IOException {
		out.writeUTF(phone);
		out.writeFloat(taoCanFee);
		out.writeFloat(currentFee);
		out.writeFloat(currentFlow);
		out.writeUTF(openDate);
		
	}
	public void readFields(DataInput in) throws IOException {
		this.phone = in.readUTF();
		this.taoCanFee = in.readFloat();
		this.currentFee = in.readFloat();
		this.currentFlow = in.readFloat();
		this.openDate = in.readUTF();
		
	}
	@Override
	public String toString() {
		return "MobileBean [phone=" + phone + ", taoCanFee=" + taoCanFee
				+ ", currentFee=" + currentFee + ", currentFlow=" + currentFlow
				+ ", openDate=" + openDate + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getTaoCanFee() {
		return taoCanFee;
	}
	public void setTaoCanFee(float taoCanFee) {
		this.taoCanFee = taoCanFee;
	}
	public float getCurrentFee() {
		return currentFee;
	}
	public void setCurrentFee(float currentFee) {
		this.currentFee = currentFee;
	}
	public float getCurrentFlow() {
		return currentFlow;
	}
	public void setCurrentFlow(float currentFlow) {
		this.currentFlow = currentFlow;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	  
	
}
