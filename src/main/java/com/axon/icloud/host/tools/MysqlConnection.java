package com.axon.icloud.host.tools;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.axon.icloud.host.getconfinfo.GetInfoFromProp;

public class MysqlConnection {
	private static Logger logger = Logger.getLogger(MysqlConnection.class);
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String mysqlUrl;
    
public  Connection getConnection() {
	Connection conn =null;
    try {
    	logger.info("开始获取connnection");
    	GetInfoFromProp getInfoFromProp = new GetInfoFromProp();
    	mysqlUrl= getInfoFromProp.getMysqlUrl();
        Class.forName(DRIVER_CLASS);
        conn = DriverManager.getConnection(mysqlUrl);
    } catch (Exception e) {
        logger.error("获取连接异常",e);
    }
    logger.info("获取connnection");
	 return conn;
}
}
