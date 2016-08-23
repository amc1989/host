package com.axon.icloud.host.getconfinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetInfoFromProp {
	private String mysqlUrl;
	private String namespace;
	private String mysqlTablename;
	private Properties properties;
	private String hbaseTableName;
	private String	cluomFamliy;
	private static Logger logger = Logger.getLogger(GetInfoFromProp.class);
	public GetInfoFromProp(){
		properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File("conf/config.properties"));
			properties.load(fis);
		} catch (IOException e) {
			logger.error("获取数据流异常", e);
		}
	}
	
	public String getMysqlUrl(){
		String mysqlUrl = properties.getProperty("DATABASE_URL");
		return mysqlUrl;
	}
	public String getMysqlTablename(){
		String mysqlTablename = properties.getProperty("hostTableName");
		return mysqlTablename;
	}
	public String getNameSpace(){
		String namespace = properties.getProperty("namespace");
		return namespace;
	}
	
	public String getHbaseTableName(){
		String hbaseTableName = properties.getProperty("hbaseTableName");
		return hbaseTableName;
		
	}
	public String getCluomFamliy (){
		String cluomFamliy = properties.getProperty("cluomFamliy");
		return cluomFamliy;
	}
	/*public static void main(String [] args){
		GetInfoFromProp f = new GetInfoFromProp();
		System.out.println(f.getMysqlTablename());
		System.out.println(f.getMysqlUrl());
		System.out.println(f.getNameSpace());
		
	}*/
}
