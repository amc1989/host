package com.axon.icloud.host.transferhostdata;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.axon.icloud.host.getconfinfo.GetInfoFromProp;
import com.axon.icloud.host.javabean.HostBean;
import com.axon.icloud.host.tools.HbaseOpration;
import com.axon.icloud.host.tools.MysqlConnection;

/**
 * 
 * @author zhulei
 * @ClassName: TransferHostData
 * @Description: TODO(把mysql 的数据 转移到hbase表中)
 * @date 2016年7月7日 上午10:20:43
 */
public class TransferHostData {
	private static Logger logger = Logger.getLogger(TransferHostData.class);
	private  GetInfoFromProp getInfoFromProp = new GetInfoFromProp();
	/**
	 * 从mysql获取江苏库下 host表的数据
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("all")
	public void excuteQuery(String sql,Configuration conf) {
		// String sql = "select * from " +getInfoFromProp.getMysqlTablename();
		logger.info("开始获取mysqlConnection");
		MysqlConnection mysqlConnection = new MysqlConnection();
		Connection conn = mysqlConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		List<HostBean> hostList = new ArrayList<HostBean>();
		String table = "IDCTAG:"+getInfoFromProp.getHbaseTableName();
		HTable hTable =null;
		try {
		    hTable = new HTable(conf, table);
			hTable.setAutoFlush(false);
			hTable.setWriteBufferSize(314572800);
			preparedStatement = conn.prepareStatement(sql,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			preparedStatement.setFetchSize(Integer.MIN_VALUE);
			preparedStatement.setFetchDirection(ResultSet.FETCH_REVERSE);
		    resultSet = preparedStatement.executeQuery();
		    int sum =0;
		    Calendar start = Calendar.getInstance();
			while (resultSet.next()) {
				HostBean hb = new HostBean();
				if ("0".equals(resultSet.getString("phone").trim())) {
					continue;
				}
				StringBuilder sb = new StringBuilder(
						resultSet.getString("phone"));
				hb.setPhone(sb.reverse().toString());
				hb.setIdate(resultSet.getString("idate"));
				hb.setHostID(resultSet.getString("host_id"));
				hb.setrBytes(resultSet.getString("rbytes"));
				hb.setNum(resultSet.getString("num"));
				hb.setDayNum(resultSet.getString("daynum"));
				hostList.add(hb);
				//875891066081110841
				if("875891066081110841".equals(sb.reverse().toString())){
					logger.info("数据是真确的"+sb.reverse().toString()+"***************************************************");
				}
				sum++;
				if(hostList.size()%3000000==0){
					insertHbae(hTable,hostList,conf);
					hostList.clear();
				}
			}
			insertHbae(hTable,hostList,conf);
			Calendar end = Calendar.getInstance();
			logger.info( "共花费"
					+ ((start.getTimeInMillis() - end.getTimeInMillis())/1000)
					+ " sec");
			logger.info("总共导入了"+sum+"条数据");
		} catch (SQLException e) {
			logger.error("获取preparedStatement异常", e);
		} catch (IOException e) {
			logger.error("创建htable失败", e);
		}finally{
			try {
				preparedStatement.close();
				resultSet.close();
				hTable.close();
			} catch (SQLException e) {
				logger.error("关闭失败！！",e);
			} catch (IOException e) {
				logger.error("关闭失败！！",e);
			}
		}
		
		
	}

	/**
	 * 
	 * @param hostList
	 */
	@SuppressWarnings("all")
	public void insertHbae(HTable hTable,List<HostBean> hostList,Configuration conf) {
		//logger.info("开始向hbase插入数据，并且获得的长度："+hostList.size());
		String famliy = getInfoFromProp.getCluomFamliy();
		try {
			List<Put> putsList = new ArrayList<Put>();
			for (HostBean hb : hostList) {
				Put put = new Put(Bytes.toBytes(hb.getPhone()));
				put.setWriteToWAL(false);
				put.addColumn(Bytes.toBytes(famliy),
						Bytes.toBytes(hb.getHostID() + "_idate"),
						Bytes.toBytes(hb.getIdate()));
				put.addColumn(Bytes.toBytes(famliy),
						Bytes.toBytes(hb.getHostID() + "_rbytes"),
						Bytes.toBytes(hb.getrBytes()));
				put.addColumn(Bytes.toBytes(famliy),
						Bytes.toBytes(hb.getHostID() + "_num"),
						Bytes.toBytes(hb.getNum()));
				put.addColumn(Bytes.toBytes(famliy),
						Bytes.toBytes(hb.getHostID() + "_daynum"),
						Bytes.toBytes(hb.getDayNum()));
				putsList.add(put);
			}

			hTable.put(putsList);
			hTable.flushCommits();
			logger.info("此次共上传" + putsList.size() + "数据");
			putsList.clear();
			
		} catch (Exception e) {
			logger.error("获取zookeeper file失败", e);
		}
	}
	
	/**
	 *  创建一张hbase表
	 * @param conf
	 */
  @SuppressWarnings("all")
public void createHbasetable(Configuration conf){
	   
	    HBaseAdmin hBaseAdmin =null;
		try {
			hBaseAdmin = new HBaseAdmin(conf);
			HbaseOpration hbaseOpration = new HbaseOpration();
			String nameSpace = getInfoFromProp.getNameSpace();
			String table = getInfoFromProp.getHbaseTableName();
			String famliy = getInfoFromProp.getCluomFamliy();
			hbaseOpration.createTable(hBaseAdmin, nameSpace, table, famliy);
		} catch (Exception e) {
			
		}
 }
	public static void main(String[] arges) {
		GetInfoFromProp getInfoFromProp = new GetInfoFromProp();
		String sql = "select * from " + getInfoFromProp.getMysqlTablename();
		TransferHostData tfd = new TransferHostData();
		Configuration conf = HBaseConfiguration.create();
		tfd.createHbasetable(conf);
		tfd.excuteQuery(sql, conf);
	}
}
