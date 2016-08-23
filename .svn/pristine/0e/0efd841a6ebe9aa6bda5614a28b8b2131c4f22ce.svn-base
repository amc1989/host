package com.axon.icloud.host.show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.axon.icloud.host.javabean.HostBean;
import com.axon.tool.encrypt.AxonEncrypt;


public class ShowAllTag {
	private static Logger logger = Logger.getLogger(ShowAllTag.class);

	@SuppressWarnings("all")
	public static void main(String[] args) {
		//Configuration conf = HBaseConfiguration.create();
		Configuration conf = HBaseConfiguration.create();
		AxonEncrypt axonEncrypt = new AxonEncrypt();
		StringBuffer phoneSb = new StringBuffer(axonEncrypt.encrypt("86" + args[0]));
        String phone  = phoneSb.reverse().toString();
		
        conf.setLong("hbase.client.scanner.timeout.period", 999999999);
		ShowAllTag sat = new ShowAllTag();
		List<HostBean> app = sat.getAppTag(phone, conf);
		//List<HostBean> host = sat.getHostTag(phone, conf);
		for (HostBean hbApp : app) {
			System.out.println(hbApp.getSubID() + ":" + hbApp.getTagName()
					+ ":" + hbApp.getScore() + "———————" + "app使用个数："
					+ hbApp.getNum() + "    |点击次数和：" + hbApp.getUseNum()
					+ "    |使用天数和：" + hbApp.getDayNum() + "    |消耗流量和："
					+ hbApp.getrBytes() + "    |最后一次使用距今：" + hbApp.getIdate()
					+ "天 ) ");
			/*for (HostBean hbHost : host) {
				if (hbApp.getSubID().trim().equals(hbHost.getSubID().trim())) {
					System.out.println(hbHost.getSubID() + ":"
							+ hbHost.getTagName() + ":" + hbHost.getScore()
							+ "———————" + "host使用个数：" + hbHost.getNum()
							+ "    |点击次数和：" + hbHost.getUseNum()
							+ "    |使用天数和：" + hbHost.getDayNum()
							+ "    |消耗流量和：" + hbHost.getrBytes()
							+ "    |最后一次使用距今：" + hbHost.getIdate() + "天 ) ");
				}
			}*/
		}

	}

	@SuppressWarnings("all")
	public List<HostBean> getHostTag(String phone, Configuration conf) {
		HTable hostbefore = null;
		HTable confAllTag = null;
		List<HostBean> list = new ArrayList<HostBean>();
		try {
			hostbefore = new HTable(conf, "IDCTAG:hostbefore");
			confAllTag = new HTable(conf, "IDCTAG:confAllTag");
			Get get = new Get(Bytes.toBytes(phone));
			Result result = hostbefore.get(get);
			if (result.isEmpty()) {
				logger.error("该电话号码不存在" + phone);
				throw new RuntimeException("你所查号码不存在");
			}
			int sum = 0;
			for (Cell cell : result.rawCells()) {
				String subId = Bytes.toString(cell.getQualifier());
				logger.info("ShowAllTag :subId" + subId);
				String[] valueString = Bytes.toString(
						result.getValue("c".getBytes(), subId.getBytes()))
						.split(",");
				logger.info("ShowAllTag :sudid对应的value"
						+ Bytes.toString(result.getValue("c".getBytes(),
								subId.getBytes())));
				String valueScore = Bytes.toString(result.getValue(
						"c".getBytes(), (subId + "_score").getBytes()));
				Get getAllTag = new Get(subId.getBytes());
				Result rs1 = confAllTag.get(getAllTag);
				String name = Bytes.toString(rs1.getValue("c".getBytes(),
						"tag_name".getBytes()));
				if (name != null) {
					HostBean hb = new HostBean();
					hb.setTagName(name);
					hb.setScore(valueScore);
					hb.setSubID(subId);
					hb.setPhone(phone);
					hb.setNum(valueString[0].toString());
					hb.setUseNum(valueString[1].toString());
					hb.setDayNum(valueString[2].toString());
					hb.setrBytes(valueString[3].toString());
					hb.setIdate(valueString[4].toString());
					list.add(hb);
					sum++;

					/*
					 * System.out.println(subId + ":" + name + ":" + valueScore
					 * + "———————" + "app使用个数：" + valueString[0].toString() +
					 * "    |点击次数和：" + valueString[1].toString() + "    |使用天数和："
					 * + valueString[2].toString() + "    |消耗流量和：" +
					 * valueString[3].toString() + "    |最后一次使用距今：" +
					 * valueString[4].toString() + "天 ) "); sum++;
					 */
				}
			}
			System.out.println("标签总计：" + sum + "个");
		} catch (IOException e) {
			logger.error("获取表出错", e);
		}
		return null;

	}

	public List<HostBean> getAppTag(String phone, Configuration conf) {
		List<HostBean> list = new ArrayList<HostBean>();
		try {
			// String phone =phone1.reverse().toString();
			HTable table1 = new HTable(conf, "IDCTAG:conf_apptag");
			HTable table = new HTable(conf, "IDCTAG:app_process_one");
			Get get = new Get(phone.getBytes());
			Result rs = table.get(get);
			System.out.println(phone);
			int sum = 0;
			for (Cell r : rs.rawCells()) {
				String Tagid = Bytes.toString(r.getQualifier());
				// System.out.print(r.getTimestamp() + " " );

				String[] valueString = Bytes.toString(
						rs.getValue("p".getBytes(), Tagid.getBytes())).split(
						",");
				String valueString1 = Bytes.toString(rs.getValue(
						"p".getBytes(), (Tagid + "_score").getBytes()));
				Get get1 = new Get(Tagid.getBytes());
				Result rs1 = table1.get(get1);
				String name = Bytes.toString(rs1.getValue("c".getBytes(),
						"tag_name".getBytes()));
				if (name != null) {
					HostBean hb = new HostBean();
					hb.setTagName(name);
					hb.setScore(valueString1);
					hb.setSubID(Tagid);
					hb.setPhone(phone);
					hb.setNum(valueString[0].toString());
					hb.setUseNum(valueString[1].toString());
					hb.setDayNum(valueString[2].toString());
					hb.setrBytes(valueString[3].toString());
					hb.setIdate(valueString[4].toString());
					list.add(hb);
					/*
					 * System.out.println(Tagid+":"+name+":"+valueString1+"———————"
					 * + "app使用个数："+valueString[0].toString()+
					 * "    |点击次数和："+valueString[1].toString()+
					 * "    |使用天数和："+valueString[2].toString()+
					 * "    |消耗流量和："+valueString[3].toString()+
					 * "    |最后一次使用距今："+valueString[4].toString()+"天 ) ");
					 */
					sum++;
				}
			}
			System.out.println("标签总计：" + sum + "个");
		} catch (IOException e) {
			logger.info(e);
		}
		return list;

	}
}
