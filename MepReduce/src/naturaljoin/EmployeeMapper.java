package naturaljoin;

import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmployeeMapper extends Mapper <LongWritable, Text, LongWritable, Text> {
	
	@Override
	
	public void map (LongWritable key , Text value , Context context){
		
		String maskfield = "Employee";
		try{
			
			String line = value.toString();
			StringTokenizer token = new StringTokenizer(line);
			
			Long keymap = Long.parseLong(token.nextToken());
			
			
			line = maskfield + "\t"+ line;
			
			
			context.write(new LongWritable(keymap), new Text(line));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
