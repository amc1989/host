package com.axon.icloud.host.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.axon.icloud.host.javabean.GetFinalResultBean;

public class GetFinalResult {
	private static Logger logger = Logger.getLogger(GetFinalResult.class);

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		logger.info("开始计算4月的最终结果");
		Job job = null;
		try {
			job = new Job(conf);
			job.setJarByClass(GetFinalResult.class);

			FileInputFormat.addInputPath(job, new Path(
					"/host/jiangsu/resultMaxValue/201605/part-r-00000"));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(GetFinalResultBean.class);
			job.setMapperClass(GetFinalResultMapper.class);

			job.setReducerClass(GetFinalResultReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileOutputFormat.setOutputPath(job, new Path(
					"/host/jiangsu/FinalResult/201605"));

			job.setNumReduceTasks(1);
			job.waitForCompletion(true);
		} catch (Exception e) {
			logger.error("初始化出错", e);
		}
	}

	public static class GetFinalResultMapper extends
			Mapper<LongWritable, Text, Text, GetFinalResultBean> {

		@Override
		protected void map(
				LongWritable key,
				Text value,
				Mapper<LongWritable, Text, Text, GetFinalResultBean>.Context context)
				throws IOException, InterruptedException {
			// context.write(text,new Text(dayNumSum + "\t" + typeNumSum + "\t"
			// + rbyteSum + "\t" + useSum + "\t" + lastestDay));
			String[] values = value.toString().split("\\s+");
			String[] phoneSubId = values[0].split("-");
			GetFinalResultBean gfb = new GetFinalResultBean();
			gfb.setDayNumMax((Float.parseFloat(values[1])));
			gfb.setTypeNumMax(Float.parseFloat(values[2]));
			gfb.setRbyteMax(Float.parseFloat(values[3]));
			gfb.setUseNumMax(Float.parseFloat(values[4]));
			gfb.setLastestDayMax(Float.parseFloat(values[5]));
			gfb.setPhone(phoneSubId[0]);
			gfb.setSubId(phoneSubId[1]);
			context.write(new Text(phoneSubId[1]), gfb);
			/*
			 * logger.info("map输入值为：key为：" + phoneSubId[1] +
			 * "resultMaxValue对象值为：" + gfb.toString());
			 */
		}

	}

	public static class GetFinalResultReducer extends
			Reducer<Text, GetFinalResultBean, Text, Text> {

		@Override
		protected void reduce(Text text, Iterable<GetFinalResultBean> values,
				Reducer<Text, GetFinalResultBean, Text, Text>.Context context)
				throws IOException, InterruptedException {
			List<GetFinalResultBean> all = new ArrayList<GetFinalResultBean>();
			float maxDayNum = 0;
			float maxTypeNum = 0;
			float maxRbytes = 0;
			float maxUseNum = 0;
			float maxLastestDay = 0;
			// 获取每个标签的最大值
			for (GetFinalResultBean gtb : values) {
				GetFinalResultBean gtbClone = WritableUtils.clone(gtb,
						context.getConfiguration());
				all.add(gtbClone);
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
			/*
			 * logger.info("每个subid下所有的用户的最大值："+"maxDayNum::"+maxDayNum+
			 * "maxTypeNum::"+maxTypeNum+ "maxRbytes::"+maxRbytes+
			 * "maxUseNum::"+maxUseNum+ "maxLastestDay::"+maxLastestDay );
			 */
			float[] dayNumScope = getGlobalScope(maxDayNum);
			float[] typeNumScope = getGlobalScope(maxTypeNum);
			float[] rbytesScope = getGlobalScope(maxRbytes);
			float[] useNumScope = getGlobalScope(maxUseNum);
			float[] lastestDayScope = getGlobalScope(maxLastestDay);
			for (GetFinalResultBean gtb : all) {
				logger.info("all结果中每个值为：" + gtb.toString());
				float dayNumScore = getScore(gtb.getDayNumMax(), dayNumScope);
				float typeNumScore = getScore(gtb.getTypeNumMax(), typeNumScope);
				float rbytesScore = getScore(gtb.getRbyteMax(), rbytesScope);
				float useNumScore = getScore(gtb.getUseNumMax(), useNumScope);
				float lastestDayScore = getScore(gtb.getLastestDayMax(),
						lastestDayScope);
				context.write(new Text(gtb.getPhone() + "\t" + gtb.getSubId()),
						new Text(dayNumScore + "\t" + typeNumScore + "\t"
								+ rbytesScore + "\t" + useNumScore + "\t"
								+ lastestDayScore));
				logger.info("最终结果："
						+ new Text(gtb.getPhone() + "\t" + gtb.getSubId())
								.toString()
						+ "+++"
						+ new Text(dayNumScore + "\t" + typeNumScore + "\t"
								+ rbytesScore + "\t" + useNumScore + "\t"
								+ lastestDayScore).toString());
			}
		}

		@Override
		public void run(
				Reducer<Text, GetFinalResultBean, Text, Text>.Context arg0)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			super.run(arg0);
		}

		public float[] getGlobalScope(float value) {
			float[] arr = new float[9];
			for (int i = 1; i < 10; i++) {
				arr[i - 1] = (value / 10) * i;
			}
			return arr;
		}

		public float getScore(float key, float[] scope) {
			float score = 0;
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

	}
}
