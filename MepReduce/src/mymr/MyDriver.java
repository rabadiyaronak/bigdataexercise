package mymr;



import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MyDriver {
	
	public static void main(String s[]) throws IOException, InterruptedException, ClassNotFoundException{
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf,"FUN");
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job,new Path("hdfs://localhost:8020/ronak/dataMR/"));
		FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:8020/ronak/dataMr/out"));
		
		job.waitForCompletion(true);

	}

}
