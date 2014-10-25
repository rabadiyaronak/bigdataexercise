package naturaljoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;




public class JoinRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
	
		Configuration conf = new Configuration();
		
		Job job = new Job(conf,"natural join");
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setReducerClass(JoinReducer.class);
		
		MultipleInputs.addInputPath(job, new Path("hdfs://localhost:8020/ronak/employee.txt"), TextInputFormat.class,EmployeeMapper.class);
		MultipleInputs.addInputPath(job, new Path("hdfs://localhost:8020/ronak/dept.txt"), TextInputFormat.class, DeptMapper.class);
		
		FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:8020/ronak/naturaljoinout/"));
		
		job.waitForCompletion(true);
	}

}
