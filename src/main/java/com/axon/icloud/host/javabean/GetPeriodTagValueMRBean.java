package com.axon.icloud.host.javabean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class GetPeriodTagValueMRBean implements Writable {

	private float typeNumMax;
	private float dayNumMax;
	private float UseNumMax;
	private float rbyteMax;
	private float lastestDayMax;
	private String phone;
	private String subId;

	public void write(DataOutput out) throws IOException {
		out.writeFloat(typeNumMax);
		out.writeFloat(dayNumMax);
		out.writeFloat(UseNumMax);
		out.writeFloat(rbyteMax);
		out.writeFloat(lastestDayMax);
		out.writeUTF(phone);
		out.writeUTF(subId);
		

	}

	public void readFields(DataInput in) throws IOException {
		this.typeNumMax = in.readFloat();
		this.dayNumMax = in.readFloat();
		this.UseNumMax = in.readFloat();
		this.rbyteMax = in.readFloat();
		this.lastestDayMax = in.readFloat();
		this.phone = in.readUTF();
		this.subId = in.readUTF();
	}

	@Override
	public String toString() {
		return "最后结果的bean：：：： [typeNumMax=" + typeNumMax
				+ ", dayNumMax=" + dayNumMax + ", UseNumMax=" + UseNumMax
				+ ", rbyteMax=" + rbyteMax + ", lastestDayMax=" + lastestDayMax
				+ ", phone=" + phone + ", subId=" + subId + "]";
	}

	public float getTypeNumMax() {
		return typeNumMax;
	}

	public void setTypeNumMax(float typeNumMax) {
		this.typeNumMax = typeNumMax;
	}

	public float getDayNumMax() {
		return dayNumMax;
	}

	public void setDayNumMax(float dayNumMax) {
		this.dayNumMax = dayNumMax;
	}

	public float getUseNumMax() {
		return UseNumMax;
	}

	public void setUseNumMax(float useNumMax) {
		UseNumMax = useNumMax;
	}

	public float getRbyteMax() {
		return rbyteMax;
	}

	public void setRbyteMax(float rbyteMax) {
		this.rbyteMax = rbyteMax;
	}

	public float getLastestDayMax() {
		return lastestDayMax;
	}

	public void setLastestDayMax(float lastestDayMax) {
		this.lastestDayMax = lastestDayMax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	
}
