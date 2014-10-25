package invertedindex;

import java.io.IOException;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;




public class InvertedIndex {

	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
Configuration conf= new Configuration();
		
		Job job = new Job(conf,"InvertedIndex");
		
		//job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:8020/user/training/invertedinput"));
		
	    FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:8020/user/training/invertedoutput6"));
	
	
	    job.setMapperClass(IndexMapper.class);
	    job.setReducerClass(IndexReducer.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	 
	    job.waitForCompletion(true);
	    
	    if(job.isSuccessful()){
	    	System.out.println("job success");
	    	
	    }
	    else{
	    	System.out.println("failed");
	    }
	}
		
	}
	
	/*
//	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
	public int run(String args[]) throws IOException, InterruptedException, ClassNotFoundException{	
		Configuration conf= new Configuration();
		
		Job job = new Job(conf,"InvertedIndex");
		
		//job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, "hdfs://localhost:8020/user/training/invertedinput");
		
	    FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:8020/user/training/invertedoutput"));
	
	    job.setMapperClass(IndexMapper.class);
	    job.setReducerClass(IndexReducer.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	 
	    job.waitForCompletion(true);
	    
	    if(job.isSuccessful()){
	    	System.out.println("job success");
	    	
	    }
	    else{
	    	System.out.println("failed");
	    }
		return 0;
	    
	}
	public static void main(String[] args) throws Exception {
	    int exitCode = ToolRunner.run(new InvertedIndex(),args);
	    System.exit(exitCode);
	  }

	
	
}
*/
/*

public class InvertedIndex extends Configured implements Tool {

  public int run(String[] args) throws Exception {

    String input, output;
    if(args.length == 2) {
      input = args[0];
      output = args[1];
    } else {
      input = "your-input-dir";
      output = "your-output-dir";
    }

    JobConf conf = new JobConf(getConf(), InvertedIndex.class);
    conf.setJobName(this.getClass().getName());

    conf.setInputFormat(KeyValueTextInputFormat.class);

    FileInputFormat.setInputPaths(conf, new Path(input));
    FileOutputFormat.setOutputPath(conf, new Path(output));

    conf.setMapperClass(IndexMapper.class);
    conf.setReducerClass(IndexReducer.class);

    conf.setMapOutputKeyClass(Text.class);
    conf.setMapOutputValueClass(Text.class);

    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(Text.class);

    JobClient.runJob(conf);
    return 0;
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new InvertedIndex(), args);
    System.exit(exitCode);
  }
}
*/