package grep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import grep.GrepMapper;
import grep.GrepReducer;

public class GrepDriver {
	static String param="";
	public static void main(String args[]) throws Exception{
		
		GrepReducer grepobj = new GrepReducer();
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf,"grep");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		job.setMapperClass(GrepMapper.class);
		job.setReducerClass(GrepReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job,new Path("hdfs://localhost:8020/ronak/greptest.txt"));
		FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:8020/ronak/grepout/"));

		job.waitForCompletion(true);
		
		
		if(job.isSuccessful()){
			
			Runtime.getRuntime().exec("clear");
			
			System.out.println("hello world");
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the word u want to Found : ");
			param=br.readLine();
			
			grepobj.findPattern(param);
			
		}
		
	
}

}
