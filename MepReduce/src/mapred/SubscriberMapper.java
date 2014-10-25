package mapred;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SubscriberMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>{
	
	private static final double  MISSING = 0;
	
	@Override
	
	public void map(LongWritable key ,Text value,Context context ) throws IOException,InterruptedException{
		
		try{
			String line = value.toString();
			
			if(line.length() < 86){
				return;
			}
			String subId= line.substring(15,26);
			
			Double bytes =Double.parseDouble(line.substring(87,97));
			
			if(bytes==null){
				bytes = MISSING;
				
			}
			context.write(new Text(subId), new DoubleWritable(bytes));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
