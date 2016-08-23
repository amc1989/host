package com.axon.icloud.mobilehour;

import java.io.IOException;

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

import com.axon.icloud.host.javabean.MobileHourMRBean;

public class MobileHourMR {
	private static Logger logger = Logger.getLogger(MobileHourMR.class);

	public static void main(String[] args) {
		logger.info("开始计算MobileHour数据");
		Configuration configuration = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(configuration);
			String commaSeparatedPaths = "/clientHour/jiangsu/201603/part-m-00000,/clientHour/jiangsu/201604/part-m-00000,/clientHour/jiangsu/201605/part-m-00000";
			
			job.setJarByClass(MobileHourMR.class);
			
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(MobileHourMRBean.class);
			job.setMapperClass(MobileHourMRMapper.class);
			FileInputFormat.addInputPaths(job, commaSeparatedPaths);
			 
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			job.setReducerClass(MobileHourMRReducer.class);
			FileOutputFormat.setOutputPath(job, new Path("/clienthour/jiangsu/FinalResult/20163_5"));

			job.waitForCompletion(true);
		} catch (Exception e) {
			logger.error("配置错误", e);
		}

	}

	public static class MobileHourMRMapper extends
			Mapper<LongWritable, Text, Text, MobileHourMRBean> {

		@Override
		protected void map(
				LongWritable key,
				Text value,
				Mapper<LongWritable, Text, Text, MobileHourMRBean>.Context context)
				throws IOException, InterruptedException {
			// phone,client_id,Time_interval_PV_1,Time_interval_PV_2,Time_interval_PV_3,Time_interval_PV_4,Time_interval_PV_5,Time_interval_PV_6
			// 0 1 2 3 4 5 6 7
			// ,Time_interval_PV_7,Time_interval_PV_8,Time_interval_PV_9,Time_interval_bytes_1,Time_interval_bytes_2,Time_interval_bytes_3,Time_interval_bytes_4,
			// 8 9 10 11 12 13 14

			// Time_interval_bytes_5,Time_interval_bytes_6,Time_interval_bytes_7,Time_interval_bytes_8,Time_interval_bytes_9
			// 15 16 17 18 19
			String[] str = value.toString().split(",");
			MobileHourMRBean mb = new MobileHourMRBean();
			mb.setPhone(str[0]);
			mb.setTimeIntervalPv1(Integer.parseInt(str[2]));
			mb.setTimeIntervalPv2(Integer.parseInt(str[3]));
			mb.setTimeIntervalPv3(Integer.parseInt(str[4]));
			mb.setTimeIntervalPv4(Integer.parseInt(str[5]));
			mb.setTimeIntervalPv5(Integer.parseInt(str[6]));
			mb.setTimeIntervalPv6(Integer.parseInt(str[7]));
			mb.setTimeIntervalPv7(Integer.parseInt(str[8]));
			mb.setTimeIntervalPv8(Integer.parseInt(str[9]));
			mb.setTimeIntervalPv9(Integer.parseInt(str[10]));
			mb.setTimeIntervalBytes1(Float.parseFloat(str[11]));
			mb.setTimeIntervalBytes2(Float.parseFloat(str[12]));
			mb.setTimeIntervalBytes3(Float.parseFloat(str[13]));
			mb.setTimeIntervalBytes4(Float.parseFloat(str[14]));
			mb.setTimeIntervalBytes5(Float.parseFloat(str[15]));
			mb.setTimeIntervalBytes6(Float.parseFloat(str[16]));
			mb.setTimeIntervalBytes7(Float.parseFloat(str[17]));
			mb.setTimeIntervalBytes8(Float.parseFloat(str[18]));
			mb.setTimeIntervalBytes9(Float.parseFloat(str[19]));
			context.write(new Text(str[0]), mb);
		}
	}

	public static class MobileHourMRReducer extends
			Reducer<Text, MobileHourMRBean, Text, Text> {

		@Override
		protected void reduce(Text text, Iterable<MobileHourMRBean> values,
				Reducer<Text, MobileHourMRBean, Text, Text>.Context context)
				throws IOException, InterruptedException {
			int timeIntervalPv1Sum = 0;
			int timeIntervalPv2Sum = 0;
			int timeIntervalPv3Sum = 0;
			int timeIntervalPv4Sum = 0;
			int timeIntervalPv5Sum = 0;
			int timeIntervalPv6Sum = 0;
			int timeIntervalPv7Sum = 0;
			int timeIntervalPv8Sum = 0;
			int timeIntervalPv9Sum = 0;
			float timeIntervalBytes1Sum = 0;
			float timeIntervalBytes2Sum = 0;
			float timeIntervalBytes3Sum = 0;
			float timeIntervalBytes4Sum = 0;
			float timeIntervalBytes5Sum = 0;
			float timeIntervalBytes6Sum = 0;
			float timeIntervalBytes7Sum = 0;
			float timeIntervalBytes8Sum = 0;
			float timeIntervalBytes9Sum = 0;
			for (MobileHourMRBean mb : values) {
				timeIntervalPv1Sum += mb.getTimeIntervalPv1();
				timeIntervalPv2Sum += mb.getTimeIntervalPv2();
				timeIntervalPv3Sum += mb.getTimeIntervalPv3();
				timeIntervalPv4Sum += mb.getTimeIntervalPv4();
				timeIntervalPv5Sum += mb.getTimeIntervalPv5();
				timeIntervalPv6Sum += mb.getTimeIntervalPv6();
				timeIntervalPv7Sum += mb.getTimeIntervalPv7();
				timeIntervalPv8Sum += mb.getTimeIntervalPv8();
				timeIntervalPv9Sum += mb.getTimeIntervalPv9();
				timeIntervalBytes1Sum += mb.getTimeIntervalBytes1();
				timeIntervalBytes2Sum += mb.getTimeIntervalBytes2();
				timeIntervalBytes3Sum += mb.getTimeIntervalBytes3();
				timeIntervalBytes4Sum += mb.getTimeIntervalBytes4();
				timeIntervalBytes5Sum += mb.getTimeIntervalBytes5();
				timeIntervalBytes6Sum += mb.getTimeIntervalBytes6();
				timeIntervalBytes7Sum += mb.getTimeIntervalBytes7();
				timeIntervalBytes8Sum += mb.getTimeIntervalBytes8();
				timeIntervalBytes9Sum += mb.getTimeIntervalBytes9();
			}
			context.write(text, new Text(timeIntervalPv1Sum + "\t"
					+ timeIntervalPv2Sum + "\t" + timeIntervalPv3Sum + "\t"
					+ timeIntervalPv4Sum + "\t" + timeIntervalPv5Sum + "\t"
					+ timeIntervalPv6Sum + "\t" + timeIntervalPv7Sum + "\t"
					+ timeIntervalPv8Sum + "\t" + timeIntervalPv9Sum + "\t"
					+ timeIntervalBytes1Sum + "\t" + timeIntervalBytes2Sum
					+ "\t" + timeIntervalBytes3Sum + "\t"
					+ timeIntervalBytes4Sum + "\t" + timeIntervalBytes5Sum
					+ "\t" + timeIntervalBytes6Sum + "\t"
					+ timeIntervalBytes7Sum + "\t" + timeIntervalBytes8Sum
					+ "\t" + timeIntervalBytes9Sum));
		}

	}
}
