package com.axon.icloud.host.tag;

import java.io.IOException;
import java.util.ArrayList;




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.axon.icloud.host.javabean.GetPeriodTagValueMRBean;

public class GetPeriodTagValueMR {
	private static Logger logger = Logger.getLogger(GetPeriodTagValueMR.class);

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Configuration config = new Configuration();
		Job job = null;
		try {
			job = new Job(config, "ExampleSummary1");
			job.setJarByClass(GetPeriodTagValueMR.class);

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
			job.setMapOutputValueClass(GetPeriodTagValueMRBean.class);
			job.setMapperClass(GerPeriodTagValueMRMapper.class);

			job.setReducerClass(GerPeriodTagValueMRReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileOutputFormat.setOutputPath(job, new Path(
					"/host/jiangsu/conf/201604"));

			job.setNumReduceTasks(1);
			boolean b = job.waitForCompletion(true);
			if (!b) {
				throw new IOException("error with job!");
			}
		} catch (Exception e) {
			logger.error("io异常", e);
		}
	}

	public static class GerPeriodTagValueMRMapper extends
			Mapper<LongWritable, Text, Text, GetPeriodTagValueMRBean> {
		@Override
		protected void map(
				LongWritable key,
				Text value,
				Mapper<LongWritable, Text, Text, GetPeriodTagValueMRBean>.Context context)
				throws IOException, InterruptedException {
			// context.write(text,new Text(dayNumSum + "\t" + typeNumSum + "\t"
			// + rbyteSum + "\t" + useSum + "\t" + lastestDay));
			String[] values = value.toString().split("\\s+");
			String[] phoneSubId = values[0].split("-");
			GetPeriodTagValueMRBean gmb = new GetPeriodTagValueMRBean();
			gmb.setDayNumMax((Float.parseFloat(values[1])));
			gmb.setTypeNumMax(Float.parseFloat(values[2]));
			gmb.setRbyteMax(Float.parseFloat(values[3]));
			gmb.setUseNumMax(Float.parseFloat(values[4]));
			gmb.setLastestDayMax(Float.parseFloat(values[5]));
			gmb.setPhone(phoneSubId[0]);
			gmb.setSubId(phoneSubId[1]);
			context.write(new Text(phoneSubId[1]), gmb);
			/*logger.info("map输入值为：key为：" + phoneSubId[1] + "resultMaxValue对象值为："
					+ gmb.toString());*/
		}
	}

	public static class GerPeriodTagValueMRReducer extends
			Reducer<Text, GetPeriodTagValueMRBean, Text, Text> {
		@Override
		protected void reduce(
				Text text,
				Iterable<GetPeriodTagValueMRBean> values,
				Reducer<Text, GetPeriodTagValueMRBean, Text, Text>.Context context)
				throws IOException, InterruptedException {
			ArrayList<GetPeriodTagValueMRBean> getAllUser = new ArrayList<GetPeriodTagValueMRBean>();
			float maxDayNum = 0;
			float maxTypeNum = 0;
			float maxRbytes = 0;
			float maxUseNum = 0;
			float maxLastestDay = 0;
			// 获取每个标签的最大值
			for (GetPeriodTagValueMRBean gtb : values) {
				logger.info("values结果中每个值为：" + gtb.toString());
				getAllUser.add(gtb);
				
				if (maxDayNum < gtb.getDayNumMax()) {
					maxDayNum = gtb.getDayNumMax();
				}
				if (maxTypeNum < gtb.getTypeNumMax()) {
					maxTypeNum = gtb.getTypeNumMax();
				}
				if (maxRbytes < gtb.getRbyteMax()) {
					maxRbytes = gtb.getRbyteMax();
				}
				if (maxUseNum < gtb.getUseNumMax()) {
					maxUseNum = gtb.getUseNumMax();
				}
				if (maxLastestDay < gtb.getLastestDayMax()) {
					maxLastestDay = gtb.getLastestDayMax();
				}
			}
			/*logger.info("每个subid下所有的用户的最大值：" + "maxDayNum::" + maxDayNum
					+ "maxTypeNum::" + maxTypeNum + "maxRbytes::" + maxRbytes
					+ "maxUseNum::" + maxUseNum + "maxLastestDay::"
					+ maxLastestDay);*/
			float[] dayNumScope = getGlobalScope(maxDayNum);
			float[] typeNumScope = getGlobalScope(maxTypeNum);
			float[] rbytesScope = getGlobalScope(maxRbytes);
			float[] useNumScope = getGlobalScope(maxUseNum);
			float[] lastestDayScope = getGlobalScope(maxLastestDay);
			StringBuffer sbDayNum= new StringBuffer();
			StringBuffer sbTypeNum= new StringBuffer();
			StringBuffer sbRbytes= new StringBuffer();
			StringBuffer sbUseNum= new StringBuffer();
			StringBuffer sbLastestDay= new StringBuffer();
			for(int i=0;i<dayNumScope.length;i++){
				sbDayNum.append(dayNumScope[i]+",");
				sbTypeNum.append(typeNumScope[i]+",");
				sbRbytes.append(rbytesScope[i]+",");
				sbUseNum.append(useNumScope[i]+",");
				sbLastestDay.append(lastestDayScope[i]+",");
			}
			context.write(text, new Text(sbDayNum.toString().substring(0, sbDayNum.length()-1)+"\t"
			+sbTypeNum.toString().substring(0, sbTypeNum.length()-1)+"\t"
			+sbRbytes.toString().substring(0, sbRbytes.length()-1)+"\t"
			+sbUseNum.toString().substring(0, sbUseNum.length()-1)+"\t"
			+sbLastestDay.toString().substring(0, sbLastestDay.length()-1)));
		 
		}

		public float[] getGlobalScope(float value) {
			float[] arr = new float[9];
			for (int i = 1; i < 10; i++) {
				arr[i - 1] = (value / 10) * i;
			}
			return arr;
		}

	}
}
