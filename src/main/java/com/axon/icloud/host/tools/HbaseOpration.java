package com.axon.icloud.host.tools;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class HbaseOpration {
    private static Logger logger = Logger.getLogger(HbaseOpration.class);
	
	public  void createTable(HBaseAdmin hBaseAdmin,String nameSpace,String table,String famliy){
		
		try {
			
			if (hBaseAdmin.tableExists(nameSpace + ":" + table)) {
				
				hBaseAdmin.disableTable(nameSpace + ":" + table);
				logger.info("HbaseOpration放弃表成功");
				hBaseAdmin.deleteTable(nameSpace + ":" + table);
				logger.info("HbaseOpration删除表成功");
			}
			@SuppressWarnings("all")
			HTableDescriptor tableDescriptor = new HTableDescriptor(nameSpace
					+ ":" + table);
			HColumnDescriptor hcd = new HColumnDescriptor(
					Bytes.toBytes(famliy));
			tableDescriptor.addFamily(hcd);
			byte[] a0 = "010000000000000000".getBytes();
			byte[] a1 = "020000000000000000".getBytes();
			byte[] a2 = "030000000000000000".getBytes();
			byte[] a3 = "040000000000000000".getBytes();
			byte[] a4 = "050000000000000000".getBytes();
			byte[] a5 = "060000000000000000".getBytes();
			byte[] a6 = "070000000000000000".getBytes();
			byte[] a7 = "080000000000000000".getBytes();
			byte[] a8 = "090000000000000000".getBytes();
			byte[] a9 = "100000000000000000".getBytes();
			byte[] k0 = "110000000000000000".getBytes();
			byte[] k1 = "120000000000000000".getBytes();
			byte[] k2 = "130000000000000000".getBytes();
			byte[] k3 = "140000000000000000".getBytes();
			byte[] k4 = "150000000000000000".getBytes();
			byte[] k5 = "160000000000000000".getBytes();
			byte[] k6 = "170000000000000000".getBytes();
			byte[] k7 = "180000000000000000".getBytes();
			byte[] k8 = "190000000000000000".getBytes();
			byte[] k9 = "200000000000000000".getBytes();
			byte[] b0 = "210000000000000000".getBytes();
			byte[] b1 = "220000000000000000".getBytes();
			byte[] b2 = "230000000000000000".getBytes();
			byte[] b3 = "240000000000000000".getBytes();
			byte[] b4 = "250000000000000000".getBytes();
			byte[] b5 = "260000000000000000".getBytes();
			byte[] b6 = "270000000000000000".getBytes();
			byte[] b7 = "280000000000000000".getBytes();
			byte[] b8 = "290000000000000000".getBytes();
			byte[] b9 = "300000000000000000".getBytes();
			byte[] c0 = "310000000000000000".getBytes();
			byte[] c1 = "320000000000000000".getBytes();
			byte[] c2 = "330000000000000000".getBytes();
			byte[] c3 = "340000000000000000".getBytes();
			byte[] c4 = "350000000000000000".getBytes();
			byte[] c5 = "360000000000000000".getBytes();
			byte[] c6 = "370000000000000000".getBytes();
			byte[] c7 = "380000000000000000".getBytes();
			byte[] c8 = "390000000000000000".getBytes();
			byte[] c9 = "400000000000000000".getBytes();
			byte[] d0 = "410000000000000000".getBytes();
			byte[] d1 = "420000000000000000".getBytes();
			byte[] d2 = "430000000000000000".getBytes();
			byte[] d3 = "440000000000000000".getBytes();
			byte[] d4 = "450000000000000000".getBytes();
			byte[] d5 = "460000000000000000".getBytes();
			byte[] d6 = "470000000000000000".getBytes();
			byte[] d7 = "480000000000000000".getBytes();
			byte[] d8 = "490000000000000000".getBytes();
			byte[] d9 = "500000000000000000".getBytes();
			byte[] e0 = "510000000000000000".getBytes();
			byte[] e1 = "520000000000000000".getBytes();
			byte[] e2 = "530000000000000000".getBytes();
			byte[] e3 = "540000000000000000".getBytes();
			byte[] e4 = "550000000000000000".getBytes();
			byte[] e5 = "560000000000000000".getBytes();
			byte[] e6 = "570000000000000000".getBytes();
			byte[] e7 = "580000000000000000".getBytes();
			byte[] e8 = "590000000000000000".getBytes();
			byte[] e9 = "600000000000000000".getBytes();
			byte[] f0 = "610000000000000000".getBytes();
			byte[] f1 = "620000000000000000".getBytes();
			byte[] f2 = "630000000000000000".getBytes();
			byte[] f3 = "640000000000000000".getBytes();
			byte[] f4 = "650000000000000000".getBytes();
			byte[] f5 = "660000000000000000".getBytes();
			byte[] f6 = "670000000000000000".getBytes();
			byte[] f7 = "680000000000000000".getBytes();
			byte[] f8 = "690000000000000000".getBytes();
			byte[] f9 = "700000000000000000".getBytes();
			byte[] j0 = "710000000000000000".getBytes();
			byte[] j1 = "720000000000000000".getBytes();
			byte[] j2 = "730000000000000000".getBytes();
			byte[] j3 = "740000000000000000".getBytes();
			byte[] j4 = "750000000000000000".getBytes();
			byte[] j5 = "760000000000000000".getBytes();
			byte[] j6 = "770000000000000000".getBytes();
			byte[] j7 = "780000000000000000".getBytes();
			byte[] j8 = "790000000000000000".getBytes();
			byte[] j9 = "800000000000000000".getBytes();
			byte[] g0 = "810000000000000000".getBytes();
			byte[] g1 = "820000000000000000".getBytes();
			byte[] g2 = "830000000000000000".getBytes();
			byte[] g3 = "840000000000000000".getBytes();
			byte[] g4 = "850000000000000000".getBytes();
			byte[] g5 = "860000000000000000".getBytes();
			byte[] g6 = "870000000000000000".getBytes();
			byte[] g7 = "880000000000000000".getBytes();
			byte[] g8 = "890000000000000000".getBytes();
			byte[] g9 = "900000000000000000".getBytes();
			byte[] h0 = "910000000000000000".getBytes();
			byte[] h1 = "920000000000000000".getBytes();
			byte[] h2 = "930000000000000000".getBytes();
			byte[] h3 = "940000000000000000".getBytes();
			byte[] h4 = "950000000000000000".getBytes();
			byte[] h5 = "960000000000000000".getBytes();
			byte[] h6 = "970000000000000000".getBytes();
			byte[] h7 = "980000000000000000".getBytes();
			byte[] h8 = "990000000000000000".getBytes();
			
			
			
			
			//admin.createTable(dsc);
			byte[][] splitkeys =
					new byte[][]
							{
					a0,
					a1,
					a2,
					a3,
					a4,
					a5,
					a6,
					a7,
					a8,
					a9,
					k0,
					k1,
					k2,
					k3,
					k4,
					k5,
					k6,
					k7,
					k8,
					k9,
					b0,
					b1,
					b2,
					b3,
					b4,
					b5,
					b6,
					b7,
					b8,
					b9,
					c0,
					c1,
					c2,
					c3,
					c4,
					c5,
					c6,
					c7,
					c8,
					c9,
					d0,
					d1,
					d2,
					d3,
					d4,
					d5,
					d6,
					d7,
					d8,
					d9,
					e0,
					e1,
					e2,
					e3,
					e4,
					e5,
					e6,
					e7,
					e8,
					e9,
					f0,
					f1,
					f2,
					f3,
					f4,
					f5,
					f6,
					f7,
					f8,
					f9,
					j0,
					j1,
					j2,
					j3,
					j4,
					j5,
					j6,
					j7,
					j8,
					j9,
					g0,
					g1,
					g2,
					g3,
					g4,
					g5,
					g6,
					g7,
					g8,
					g9,
					h0,
					h1,
					h2,
					h3,
					h4,
					h5,
					h6,
					h7,
					h8
					};
			hBaseAdmin.createTable(tableDescriptor,splitkeys);
			logger.info("HbaseOpration创建表成功！！！");
			/*hBaseAdmin.enableTable(nameSpace + ":" + table);
			logger.info("使表enable成功！！！");*/
		} catch (IOException e) {
			logger.error("创建表失败:::::",e);
		}
		
	}
}
