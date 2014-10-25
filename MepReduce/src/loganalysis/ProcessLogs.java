package loganalysis;

import java.io.IOException;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ProcessLogs {

	public static void main (String args[]) throws IOException, InterruptedException, ClassNotFoundException{
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf,"Processing Logs");
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:8020/user/training/mapreduce_excr/loganalysis"));
	    FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:8020/user/training/mapreduce_excr/logana_outEclips1"));
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(LogFileMapper.class);
		job.setReducerClass(SumReducer.class);
		
		job.waitForCompletion(true);
		
		
		if(job.isSuccessful()){
		
			System.out.println("Succefully job done");
			
		}
		else{
			
			System.out.println("error occure some where");
		}
		
		
		
	}
	
}
