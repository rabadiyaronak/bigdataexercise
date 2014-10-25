package grep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GrepReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
	
	
	
	
	private static Map<String, ArrayList> store = new HashMap<String,ArrayList>();
	
	@Override
	public void reduce(Text key , Iterable<LongWritable> value,Context context) throws IOException,InterruptedException{
		
		String txtky = key.toString();
		
		ArrayList<Long> lines = new ArrayList<Long>();
		int i=0;
		for(LongWritable v : value){
			lines.add(i, v.get());
			//System.out.println();
			//System.out.println("key : " + key + " \t value  : " + v);
			i++;
		}
		//System.out.println(lines);
		store.put(txtky,lines);

		
		context.write(key,new LongWritable(1));
		
		
	}
	
	public void findPattern(String text){
	
			
			ArrayList list = new ArrayList() ;
						
			System.out.println("the word :"+ text+" found in following lines ");
			list=store.get(text);
			if(list.isEmpty()){
			
				System.out.println("NO MATCH FOUND FOR WORD  :--> " + text );
			}
			else{
				int i=0;
				while(i<list.size()){
					System.out.println(list.get(i));
					i++;
				}
			}
	}

}
