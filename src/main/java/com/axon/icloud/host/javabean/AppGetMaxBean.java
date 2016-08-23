package com.axon.icloud.host.javabean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class AppGetMaxBean  implements Writable{
	private float dayNum;
	private float ibytes;
	private float useNum;
	private String date;
	private String clientId;
	public void write(DataOutput out) throws IOException {
		out.writeFloat(dayNum);
		out.writeFloat(ibytes);
		out.writeFloat(useNum);
		out.writeUTF(date);
		out.writeUTF(clientId);
		
	}

	public void readFields(DataInput in) throws IOException {
		this.dayNum = in.readFloat();
		this.ibytes = in.readFloat();
		this.useNum = in.readFloat();
		this.date = in.readUTF();
		this.clientId = in.readUTF();
		
	}

	public float getDayNum() {
		return dayNum;
	}

	public void setDayNum(float dayNum) {
		this.dayNum = dayNum;
	}

	public float getIbytes() {
		return ibytes;
	}

	public void setIbytes(float ibytes) {
		this.ibytes = ibytes;
	}

	public float getUseNum() {
		return useNum;
	}

	public void setUseNum(float useNum) {
		this.useNum = useNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClientId() {
		return clientId;
	}

	@Override
	public String toString() {
		return "AppGetMaxBean [dayNum=" + dayNum + ", ibytes=" + ibytes
				+ ", useNum=" + useNum + ", date=" + date + ", clientId="
				+ clientId + "]";
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
