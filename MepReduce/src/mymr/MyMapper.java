package mymr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable,Text,LongWritable,Text> {
	static long prevkey=0;
	@Override
	public void map(LongWritable key ,Text value ,Context context ) throws IOException ,InterruptedException{
		
		String line = value.toString();
		String val="",temp="";
		StringTokenizer token = new StringTokenizer(line,"-");
		while(token.hasMoreTokens()){
			temp=token.nextToken();
			val = token.nextToken();
			//StringTokenizer t = new StringTokenizer(val,":");
			//if(t.nextElement().equals(" rabadiyaronak") || t.nextElement().equals(" Kaumudi") ){
			//	prevkey = key.get();
				//System.out.println(key + " " + val);
				context.write(key,new Text(val));
		//	}
			//else
			//{
				//System.out.println(prevkey + " " + val);
			//}
		}
		
		//	context.write(prevkey,new Text(val));
			
			
			
			
		
		
		
	}

}
