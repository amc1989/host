package com.axon.icloud.host.tag;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.hadoop.compression.lzo.LzoCodec;
import com.hadoop.compression.lzo.LzopCodec;

public class FinalResult {
    private static Logger logger = Logger.getLogger(FinalResult.class);
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		//map端结果输出压缩
		conf.setBoolean(Job.MAP_OUTPUT_COMPRESS, true);
		conf.setClass(Job.MAP_OUTPUT_COMPRESS_CODEC, LzoCodec.class, CompressionCodec.class);
		Job job = null;
		try {
			job = Job.getInstance(conf);
			job.addCacheFile(new URI(
					"hdfs://gxzny-136-50:9000/host/jiangsu/conf/201604/part-r-00000"));
			job.setJarByClass(FinalResult.class);

			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/part-r-00000"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/part-r-00001"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/part-r-00002"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/part-r-00003"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/part-r-00004"));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setMapperClass(FinalResultMapper.class);
			
			//reduce端结果压缩
			FileOutputFormat.setCompressOutput(job, true);
			FileOutputFormat.setOutputCompressorClass(job, LzopCodec.class);
			
			
			FileOutputFormat.setOutputPath(job, new Path(
					"/host/jiangsu/finalreslut/201604/"));

			job.setNumReduceTasks(0);
			job.waitForCompletion(true);
		} catch (Exception e) {
			logger.error("初始化出错", e);
		}

	}
   public static class FinalResultMapper extends Mapper<LongWritable, Text, Text, Text>{
    private HashMap<String, float[]>  scope = new HashMap<String, float[]>();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// context.write(text,new Text(dayNumSum + "\t" + typeNumSum + "\t"
					// + rbyteSum + "\t" + useSum + "\t" + lastestDay));
		String[] values = value.toString().split("\\s+");
		String[] phoneSubId = values[0].split("-");
	    float dayNumMax =  Float.parseFloat(values[1]);
	    float typeNumMax =  Float.parseFloat(values[2]);
	    float rbyteMax =  Float.parseFloat(values[3]);
	    float useNumMax =  Float.parseFloat(values[4]);
	    float lastestDayMax =  Float.parseFloat(values[5]);	 
		//subid,sbDayNum,sbTypeNum,sbRbytes,sbUseNum,sbLastestDay
	    String subId = phoneSubId[1];
		float [] dayNumScope = scope.get(subId+"-sbDayNum");
		float [] typeNumScope = scope.get(subId+"-sbTypeNum");
		float [] rbyteScope = scope.get(subId+"-sbRbytes");
		float [] useNumScope = scope.get(subId+"-sbUseNum");
		float [] lastestDayScope = scope.get(subId+"-sbLastestDay");
		
		float dayNumScore = getScore(dayNumMax,dayNumScope);
		float typeNumScore =  getScore(typeNumMax,typeNumScope);
		float rbyteScore =  getScore(rbyteMax,rbyteScope);
		float useNumScore =  getScore(useNumMax,useNumScope);
		float lastestDayScore =  getScore(lastestDayMax,lastestDayScope);
		context.write(new Text(phoneSubId[0]+"\t"+phoneSubId[1]), new Text(dayNumScore+"\t"
				+typeNumScore+"\t"+rbyteScore+"\t"+useNumScore+"\t"+lastestDayScore));
		
	}
	public float getScore(float key, float[] scope) {
		  float score=0;
		for (int i = 0; i < scope.length; i++) {
			boolean tt = key > scope[i];
			if (tt == false) {
				score = (i + 1) / 10f * 20;
				break;
			} else if (i == 8 && tt == true) {
				score = 20;
			}
		}
		return score;
	
	}
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		URI[] cacheFiles = context.getCacheFiles();
		Path path = new Path(cacheFiles[0]);
		FileSystem fs = FileSystem.get(cacheFiles[0], new Configuration());
		InputStream in = fs.open(path);
		List<String> list = IOUtils.readLines(in);
		for (String line : list) {
			//subid,sbDayNum,sbTypeNum,sbRbytes,sbUseNum,sbLastestDay
			String[] tagValue = line.split("\t");
			String subId = tagValue[0];
			float [] sbDayNum =transferType(tagValue[1].split(","));
			float [] sbTypeNum =transferType(tagValue[1].split(","));
			float [] sbRbytes =transferType(tagValue[1].split(","));
			float [] sbUseNum =transferType(tagValue[1].split(","));
			float [] sbLastestDay =transferType(tagValue[1].split(","));
			
			scope.put(subId+"-sbDayNum", sbDayNum);
			scope.put(subId+"-sbTypeNum", sbTypeNum);
			scope.put(subId+"-sbRbytes", sbRbytes);
			scope.put(subId+"-sbUseNum", sbUseNum);
			scope.put(subId+"-sbLastestDay", sbLastestDay);
		}
		logger.info("scope的长度：："+scope.size() );
	}
    public float[] transferType(String[] str){
		float [] type = new float[str.length];
    	for(int i=0;i<str.length;i++){
    		type[i] = Float.parseFloat(str[i]);
    	}
    	return type;
	  
  }	
}
	   
   
}
