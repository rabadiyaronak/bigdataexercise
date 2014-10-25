package naturaljoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer <LongWritable,Text,LongWritable,Text>{
	
	@Override
	
	public void reduce (LongWritable key , Iterable<Text> value , Context context) throws IOException , InterruptedException{
		
		ArrayList<String> deptList = new ArrayList<String>(); 
		deptList.clear();
		ArrayList<String> slavekey=new ArrayList<String>();
		slavekey.clear();
		String outkey = "";
		
		for(Text val : value){
			
			String txt=val.toString();
			String emp = "Employee";
			StringTokenizer token = new StringTokenizer(txt);
			String temp = token.nextToken("\t");
			if(temp.equals(emp)){
				
				token.nextToken();
				while(token.hasMoreTokens()){
					
					outkey += token.nextToken()+"\t";
					
				}
				
			}
			else if((temp.equals("dept")))
			{
				token.nextToken();
				
				while(token.hasMoreTokens()){
					slavekey.add(token.nextToken()+"\t");
				}
				
			}
			
			
			}
		if(slavekey.isEmpty()){
			
			outkey+= null;
			deptList.add(outkey);
		}
		for(String x1 : slavekey){
			x1 = outkey + x1;
			deptList.add(x1);
		}
		
		for(int i=0; i < deptList.size();i++){
			
			context.write(key,new Text(deptList.get(i)));
			
		}
			
		}
	}
	
		

