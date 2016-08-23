package com.axon.icloud.host.javabean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MobileHourMRBean implements Writable {
	private String phone;
	private int timeIntervalPv1;
	private int timeIntervalPv2;
	private int timeIntervalPv3;
	private int timeIntervalPv4;
	private int timeIntervalPv5;
	private int timeIntervalPv6;
	private int timeIntervalPv7;
	private int timeIntervalPv8;
	private int timeIntervalPv9;
	private float timeIntervalBytes1;
	private float timeIntervalBytes2;
	private float timeIntervalBytes3;
	private float timeIntervalBytes4;
	private float timeIntervalBytes5;
	private float timeIntervalBytes6;
	private float timeIntervalBytes7;
	private float timeIntervalBytes8;
	private float timeIntervalBytes9;

	public void write(DataOutput out) throws IOException {
		out.writeUTF(phone);
		out.writeInt(timeIntervalPv1);
		out.writeInt(timeIntervalPv2);
		out.writeInt(timeIntervalPv3);
		out.writeInt(timeIntervalPv4);
		out.writeInt(timeIntervalPv5);
		out.writeInt(timeIntervalPv6);
		out.writeInt(timeIntervalPv7);
		out.writeInt(timeIntervalPv8);
		out.writeInt(timeIntervalPv9);
		out.writeFloat(timeIntervalBytes1);
		out.writeFloat(timeIntervalBytes2);
		out.writeFloat(timeIntervalBytes3);
		out.writeFloat(timeIntervalBytes4);
		out.writeFloat(timeIntervalBytes5);
		out.writeFloat(timeIntervalBytes6);
		out.writeFloat(timeIntervalBytes7);
		out.writeFloat(timeIntervalBytes8);
		out.writeFloat(timeIntervalBytes9);
	}

	public void readFields(DataInput in) throws IOException {
		this.phone = in.readUTF();
		this.timeIntervalPv1 = in.readInt();
		this.timeIntervalPv2 = in.readInt();
		this.timeIntervalPv3 = in.readInt();
		this.timeIntervalPv4 = in.readInt();
		this.timeIntervalPv5 = in.readInt();
		this.timeIntervalPv6 = in.readInt();
		this.timeIntervalPv7 = in.readInt();
		this.timeIntervalPv8 = in.readInt();
		this.timeIntervalPv9 = in.readInt();
		this.timeIntervalBytes1 = in.readFloat();
		this.timeIntervalBytes2 = in.readFloat();
		this.timeIntervalBytes3 = in.readFloat();
		this.timeIntervalBytes4 = in.readFloat();
		this.timeIntervalBytes5 = in.readFloat();
		this.timeIntervalBytes6 = in.readFloat();
		this.timeIntervalBytes7 = in.readFloat();
		this.timeIntervalBytes8 = in.readFloat();
		this.timeIntervalBytes9 = in.readFloat();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTimeIntervalPv1() {
		return timeIntervalPv1;
	}

	public void setTimeIntervalPv1(int timeIntervalPv1) {
		this.timeIntervalPv1 = timeIntervalPv1;
	}

	public int getTimeIntervalPv2() {
		return timeIntervalPv2;
	}

	public void setTimeIntervalPv2(int timeIntervalPv2) {
		this.timeIntervalPv2 = timeIntervalPv2;
	}

	public int getTimeIntervalPv3() {
		return timeIntervalPv3;
	}

	public void setTimeIntervalPv3(int timeIntervalPv3) {
		this.timeIntervalPv3 = timeIntervalPv3;
	}

	public int getTimeIntervalPv4() {
		return timeIntervalPv4;
	}

	public void setTimeIntervalPv4(int timeIntervalPv4) {
		this.timeIntervalPv4 = timeIntervalPv4;
	}

	public int getTimeIntervalPv5() {
		return timeIntervalPv5;
	}

	public void setTimeIntervalPv5(int timeIntervalPv5) {
		this.timeIntervalPv5 = timeIntervalPv5;
	}

	public int getTimeIntervalPv6() {
		return timeIntervalPv6;
	}

	public void setTimeIntervalPv6(int timeIntervalPv6) {
		this.timeIntervalPv6 = timeIntervalPv6;
	}

	public int getTimeIntervalPv7() {
		return timeIntervalPv7;
	}

	public void setTimeIntervalPv7(int timeIntervalPv7) {
		this.timeIntervalPv7 = timeIntervalPv7;
	}

	public int getTimeIntervalPv8() {
		return timeIntervalPv8;
	}

	public void setTimeIntervalPv8(int timeIntervalPv8) {
		this.timeIntervalPv8 = timeIntervalPv8;
	}

	public int getTimeIntervalPv9() {
		return timeIntervalPv9;
	}

	public void setTimeIntervalPv9(int timeIntervalPv9) {
		this.timeIntervalPv9 = timeIntervalPv9;
	}

	public float getTimeIntervalBytes1() {
		return timeIntervalBytes1;
	}

	public void setTimeIntervalBytes1(float timeIntervalBytes1) {
		this.timeIntervalBytes1 = timeIntervalBytes1;
	}

	public float getTimeIntervalBytes2() {
		return timeIntervalBytes2;
	}

	public void setTimeIntervalBytes2(float timeIntervalBytes2) {
		this.timeIntervalBytes2 = timeIntervalBytes2;
	}

	public float getTimeIntervalBytes3() {
		return timeIntervalBytes3;
	}

	public void setTimeIntervalBytes3(float timeIntervalBytes3) {
		this.timeIntervalBytes3 = timeIntervalBytes3;
	}

	public float getTimeIntervalBytes4() {
		return timeIntervalBytes4;
	}

	public void setTimeIntervalBytes4(float timeIntervalBytes4) {
		this.timeIntervalBytes4 = timeIntervalBytes4;
	}

	public float getTimeIntervalBytes5() {
		return timeIntervalBytes5;
	}

	public void setTimeIntervalBytes5(float timeIntervalBytes5) {
		this.timeIntervalBytes5 = timeIntervalBytes5;
	}

	public float getTimeIntervalBytes6() {
		return timeIntervalBytes6;
	}

	public void setTimeIntervalBytes6(float timeIntervalBytes6) {
		this.timeIntervalBytes6 = timeIntervalBytes6;
	}

	public float getTimeIntervalBytes7() {
		return timeIntervalBytes7;
	}

	public void setTimeIntervalBytes7(float timeIntervalBytes7) {
		this.timeIntervalBytes7 = timeIntervalBytes7;
	}

	public float getTimeIntervalBytes8() {
		return timeIntervalBytes8;
	}

	public void setTimeIntervalBytes8(float timeIntervalBytes8) {
		this.timeIntervalBytes8 = timeIntervalBytes8;
	}

	public float getTimeIntervalBytes9() {
		return timeIntervalBytes9;
	}

	public void setTimeIntervalBytes9(float timeIntervalBytes9) {
		this.timeIntervalBytes9 = timeIntervalBytes9;
	}

	@Override
	public String toString() {
		return "MobileHourMRBean [phone=" + phone + ", timeIntervalPv1="
				+ timeIntervalPv1 + ", timeIntervalPv2=" + timeIntervalPv2
				+ ", timeIntervalPv3=" + timeIntervalPv3 + ", timeIntervalPv4="
				+ timeIntervalPv4 + ", timeIntervalPv5=" + timeIntervalPv5
				+ ", timeIntervalPv6=" + timeIntervalPv6 + ", timeIntervalPv7="
				+ timeIntervalPv7 + ", timeIntervalPv8=" + timeIntervalPv8
				+ ", timeIntervalPv9=" + timeIntervalPv9
				+ ", timeIntervalBytes1=" + timeIntervalBytes1
				+ ", timeIntervalBytes2=" + timeIntervalBytes2
				+ ", timeIntervalBytes3=" + timeIntervalBytes3
				+ ", timeIntervalBytes4=" + timeIntervalBytes4
				+ ", timeIntervalBytes5=" + timeIntervalBytes5
				+ ", timeIntervalBytes6=" + timeIntervalBytes6
				+ ", timeIntervalBytes7=" + timeIntervalBytes7
				+ ", timeIntervalBytes8=" + timeIntervalBytes8
				+ ", timeIntervalBytes9=" + timeIntervalBytes9 + "]";
	}

}
