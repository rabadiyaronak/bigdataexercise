package mapred;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SubscriberReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable> {

		@Override
		
		public void reduce(Text key , Iterable<DoubleWritable> values,Context context) throws InterruptedException,IOException{
			
			double totalBytes =0 ;
			for(DoubleWritable value:values){
				totalBytes+=value.get();
			}
			context.write(key,new DoubleWritable(totalBytes));
			System.out.println(key + "\t"+ totalBytes);
		}
}
