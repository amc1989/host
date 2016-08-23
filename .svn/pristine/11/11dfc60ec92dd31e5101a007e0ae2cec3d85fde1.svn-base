package com.axon.icloud.host.getconfinfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URI;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;


public class UploadConfToHDFS {
	private static Logger logger = Logger.getLogger(UploadConfToHDFS.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FileInputStream fis = null;
		BufferedInputStream bufs = null;
		FileSystem fs = null;
		try {
			fis = new FileInputStream(new File("conf/app.txt"));
			fs = FileSystem.get(new URI("hdfs://gxzny-136-50:9000"),
					new Configuration(), "root");
			OutputStream out = fs.create(new Path("/conf/app/app.txt"));
			IOUtils.copyBytes(fis, out, 1024, true);
		} catch (Exception e) {
			logger.error("上传失败", e);
		}

	}

}
