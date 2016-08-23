package com.axon.icloud.mobile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.axon.icloud.host.javabean.MobileBean;

public class GetMobileTagMR {
	private static Logger logger = Logger.getLogger(GetMobileTagMR.class);

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(configuration);
			job.setJarByClass(GetMobileTagMR.class);

			String commaSeparatedPaths = "/mobile/jiangsu/201603/part-m-00000,/mobile/jiangsu/201604/part-m-00000,/mobile/jiangsu/201605/part-m-00000";
			FileInputFormat.addInputPaths(job, commaSeparatedPaths);
			
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(MobileBean.class);
			job.setMapperClass(GetMobileTagMRMapper.class);

			job.setReducerClass(GetMobileTagMRReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileOutputFormat.setOutputPath(job, new Path(
					"/mobile/jiangsu/FinalResult/201603-5"));

			job.setNumReduceTasks(1);
			job.waitForCompletion(true);

		} catch (Exception e) {

		}

	}

	public static class GetMobileTagMRMapper extends
			Mapper<LongWritable, Text, Text, MobileBean> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, Text, MobileBean>.Context context)
				throws IOException, InterruptedException {
			// phone,current_fee,current_flow,c_outer_flow_fee,taocan_fee,open_date

			String[] str = value.toString().split(",");
			MobileBean mb = new MobileBean();
			if (StringUtils.isNotEmpty(str[0])) {
				mb.setPhone(str[0]);
				mb.setCurrentFee(Float.parseFloat(str[1]));
				mb.setCurrentFlow(Float.parseFloat(str[2]));
				mb.setTaoCanFee(Float.parseFloat(str[4]));
				mb.setOpenDate(str[5]);
				context.write(new Text(str[0]), mb);
			} else {
				return;
			}
		}
	}

	public static class GetMobileTagMRReducer extends
			Reducer<Text, MobileBean, Text, Text> {

		@Override
		protected void reduce(Text text, Iterable<MobileBean> values,
				Reducer<Text, MobileBean, Text, Text>.Context context)
				throws IOException, InterruptedException {
			List<MobileBean> list = new ArrayList<MobileBean>();
			float taoCanSum = 0;
			float currentFlowSum = 0;
			float currentFeeSum = 0;
            String date =null;
			for (MobileBean mb : values) {
				currentFlowSum += mb.getCurrentFlow();
				currentFeeSum += mb.getCurrentFee();
				taoCanSum += mb.getTaoCanFee();
				date = mb.getOpenDate();
				MobileBean mbb = WritableUtils.clone(mb,
						context.getConfiguration());
				list.add(mbb);
			}
			float taoCanAvg = taoCanSum / 3;
			float currentFlowAvg = currentFlowSum/(float)list.size();
			double currentFlowDev = getFlowDev(list,currentFlowAvg);
			float currentFeeAvg = currentFeeSum/(float)list.size();
			double currentFeeDev = getFeeDev(list,currentFeeAvg);
			int zaiWang = countDayInner(date,"2016-5-31");
			float liWang = (float)1-(float)list.size()/(float)3;
			context.write(text, new Text(taoCanAvg+"\t"+currentFlowAvg+"\t"+
			currentFlowDev+"\t"
			+currentFeeAvg+"\t"
			+currentFeeDev+"\t"
			+zaiWang+"\t"
			+liWang+"\t"));
		}

		/**
		 * 
		 * @param openDate
		 *            计算入网时间
		 * @param month
		 *            类似2016-5-31
		 * @return
		 */
		public int countDayInner(String openDate, String month) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = df.parse(openDate);
				endDate = df.parse(month);
			} catch (ParseException e) {
				logger.error("解析日期出错", e);
			}
			long starTimes = startDate.getTime();
			long endTime = endDate.getTime();
			long cha = endTime - starTimes;
			int day = (int) (cha / (1000 * 60 * 60 * 24));
			return day;

		}

		/**
		 * 
		 * @param list
		 * @param avg
		 *            平均值
		 * @return
		 */
		public double getFeeDev(List<MobileBean> list, float avg) {
			double dev = 0;
			for (MobileBean mb : list) {
				dev += ((avg - mb.getCurrentFee()) * (avg - mb.getCurrentFee()));
			}
			return Math.sqrt(dev / list.size());

		}

		/**
		 * 
		 * @param list
		 * @param avg
		 *            平均值
		 * @return
		 */
		public double getFlowDev(List<MobileBean> list, float avg) {
			double dev = 0;
			for (MobileBean mb : list) {
				dev += ((avg - mb.getCurrentFlow()) * (avg - mb
						.getCurrentFlow()));
			}
			return Math.sqrt(dev / list.size());
		}
	}
}
