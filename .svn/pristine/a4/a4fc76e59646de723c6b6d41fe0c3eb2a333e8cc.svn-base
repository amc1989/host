package com.axon.icloud.host.tag;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.axon.icloud.host.javabean.GetMaxValueMRBean;

/**
 * 
 * @author zhulei
 * @ClassName: GetMaxValueMR
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016年7月7日 下午5:18:29
 */
public class GetMaxValueMR {
	private static Logger logger = Logger.getLogger(GetMaxValueMR.class);

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		Job job = null;
		logger.info("开始4月的数据计算");
		try {
			job = new Job(conf);
			job.addCacheFile(new URI(
					"hdfs://gxzny-136-50:9000/conf/host/host.txt"));
			job.setJarByClass(GetMaxValueMR.class);

			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/201604/part-m-00000"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/201604/part-m-00001"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/201604/part-m-00002"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/201604/part-m-00003"));
			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/201604/part-m-00004"));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(GetMaxValueMRBean.class);
			job.setMapperClass(GetMaxValueMRMap.class);

			job.setReducerClass(GetMaxValueMRReduce.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileOutputFormat.setOutputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/201604"));

			//job.setNumReduceTasks(5);
			job.waitForCompletion(true);
		} catch (Exception e) {
			logger.error("初始化出错", e);
		}
	}

	public static class GetMaxValueMRMap extends
			Mapper<LongWritable, Text, Text, GetMaxValueMRBean> {
		// key为hostid ，value为subid
		private HashMap<String, String> subHostId = new HashMap<String, String>();

		@Override
		protected void map(
				LongWritable key,
				Text value,
				Mapper<LongWritable, Text, Text, GetMaxValueMRBean>.Context context)
				throws IOException, InterruptedException {
			// phone,idate,host_id,rbytes,num,daynum
			String[] info = value.toString().split(",");
			// String key = info[0]+"-"
			String subId = subHostId.get(info[2]);
			StringBuilder sb = new StringBuilder();
			if (StringUtils.isNotBlank(subId)&&StringUtils.isNotBlank(info[0])) {
				sb.append(info[0]);
				sb.append("-");
				sb.append(subId);
			} else {
				return;
			}
			String phone = sb.toString();
			GetMaxValueMRBean gmBean = new GetMaxValueMRBean();
			gmBean.setDate(info[1]);
			gmBean.setHostId(info[2]);
			gmBean.setRbytes(Float.parseFloat(info[3]));
			gmBean.setUseNum(Float.parseFloat(info[4]));
			gmBean.setDayNum(Float.parseFloat(info[5]));
			
			context.write(new Text(phone), gmBean);
			logger.info(phone + ":" + gmBean.toString());
		}

		@Override
		protected void setup(
				Mapper<LongWritable, Text, Text, GetMaxValueMRBean>.Context context)
				throws IOException, InterruptedException {
			URI[] cacheFiles = context.getCacheFiles();
			Path path = new Path(cacheFiles[0]);
			FileSystem fs = FileSystem.get(cacheFiles[0], new Configuration());
			InputStream in = fs.open(path);
			List<String> list = IOUtils.readLines(in);
			for (String line : list) {
				String[] tagValue = line.split("-");
				String[] hostId = tagValue[2].split(",");
				for (String id : hostId) {
					subHostId.put(id, tagValue[0].trim());
				}
			}
			logger.info("subid和hostid的关系:" + subHostId.toString());
		}

	}

	public static class GetMaxValueMRReduce extends
			Reducer<Text, GetMaxValueMRBean, Text, Text> {

		HashSet<String> countTypeSet = new HashSet<String>();

		@Override
		protected void reduce(Text text, Iterable<GetMaxValueMRBean> value,
				Reducer<Text, GetMaxValueMRBean, Text, Text>.Context context)
				throws IOException, InterruptedException {
			float dayNumSum = 0;
			float typeNumSum = 0;
			int rbyteSum = 0;
			float useSum = 0;
			int lastestDay = 0;
			String date = null;
			for (GetMaxValueMRBean gb : value) {
				dayNumSum += gb.getDayNum();
				countTypeSet.add(gb.getHostId().trim());
				rbyteSum += gb.getRbytes();
				useSum += gb.getUseNum();
				date = gb.getDate();
				String day = date.split("-")[2];
				if (!StringUtils.isNotBlank(day))
					continue;
				int dateMax = Integer.parseInt(day);
				if (dateMax > lastestDay) {
					lastestDay = dateMax;
				}
			}
			int year = Integer.parseInt(date.split("-")[0]);
			int month = Integer.parseInt(date.split("-")[1]);
			int maxDays = GetDaysInMonth(year, month);
			lastestDay = maxDays - lastestDay;
			typeNumSum = countTypeSet.size();
			context.write(text, new Text(dayNumSum + "\t" + typeNumSum + "\t"
					+ rbyteSum + "\t" + useSum + "\t" + lastestDay));
			countTypeSet.clear();
			if (typeNumSum > 1) {
				logger.info("使用的host数目大于1" + typeNumSum);
			}
		}

		public int GetDaysInMonth(int year, int month) {
			int d;
			int day[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			if (2 == month) {
				d = (((0 == year % 4) && (0 != year % 100) || (0 == year % 400)) ? 29
						: 28);
			} else {
				d = day[month - 1];
			}
			return d;
		}
	}
}
