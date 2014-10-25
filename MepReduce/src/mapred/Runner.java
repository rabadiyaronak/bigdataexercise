package mapred;

import java.io.IOException;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Runner {

		public static void main(String args[]) throws IOException,InterruptedException,ClassNotFoundException {
			
			Configuration conf = new Configuration();
			
			conf.set("heading", "this is subscriberMR");
		
			Job job = new Job(conf);
			
			job.setJarByClass(Runner.class);
			
			FileInputFormat.addInputPath(job, new Path("hdfs://localhost:8020/ronak/Data_File.txt"));
			
			FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:8020/ronak/dataoutput1/"));
			
			job.setMapperClass(SubscriberMapper.class);
			job.setReducerClass(SubscriberReducer.class);
			
			job.setCombinerClass(SubscriberReducer.class);
			
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(DoubleWritable.class);
			
			System.exit(job.waitForCompletion(true)?0:1);
		}
}
