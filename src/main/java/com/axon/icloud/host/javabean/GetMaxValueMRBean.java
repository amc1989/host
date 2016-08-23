package com.axon.icloud.host.javabean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class GetMaxValueMRBean implements Writable {
	private float dayNum;
	private float rbytes;
	private float useNum;
	private String date;
	private String hostId;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public void write(DataOutput out) throws IOException {
		out.writeFloat(dayNum);
		out.writeFloat(rbytes);
		out.writeFloat(useNum);
		out.writeUTF(date);
		out.writeUTF(hostId);

	}

	public void readFields(DataInput in) throws IOException {
		this.dayNum = in.readFloat();
		this.rbytes = in.readFloat();
		this.useNum = in.readFloat();
		this.date = in.readUTF();
		this.hostId = in.readUTF();
	}

	public float getDayNum() {
		return dayNum;
	}

	public void setDayNum(float dayNum) {
		this.dayNum = dayNum;
	}

	public float getRbytes() {
		return rbytes;
	}

	public void setRbytes(float rbytes) {
		this.rbytes = rbytes;
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

	@Override
	public String toString() {
		return "GetMaxValueMRBean [dayNum=" + dayNum + ", rbytes=" + rbytes
				+ ", useNum=" + useNum + ", date=" + date + ", hostId="
				+ hostId + "]";
	}

}
