package mymr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class MyReducer extends Reducer<LongWritable,Text,LongWritable,Text>{
		
	static long  okey = 0;
		
	@Override
	public void reduce(LongWritable key,Iterable<Text> value,Context context) throws IOException,InterruptedException{
		
		okey++;
		String head ="";
		for(Text val : value){
			
			head += val.toString();
		}
	//	System.out.println(key);
			StringTokenizer t  = new StringTokenizer(head,":");
			String cmpr =t.nextToken();
			if(cmpr.equals(" rabadiyaronak")){
				//System.out.println(key+ " " + head);
				context.write(new LongWritable(okey), new Text(head));
			}
			else if(cmpr.equals(" Kaumudi")){
				head = "\n\t\t\t"+head+"\n";
				//System.out.println(key+ " " + head);
				context.write(new LongWritable(okey), new Text(head));
			}
			else
			{
				//System.out.println(key + "MISSING : "+ head );	
				context.write(new LongWritable(okey), new Text("MISSING"));
				
			}
		
	}
	
}
