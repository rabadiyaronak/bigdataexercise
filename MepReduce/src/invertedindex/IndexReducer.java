package invertedindex;

import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IndexReducer extends Reducer<Text,Text,Text,Text> {
	
	private static final String SEP=",";
	
	@Override
	public void reduce(Text key,Iterable<Text> values,Context context) throws IOException , InterruptedException{
	
		StringBuilder valueList = new StringBuilder();
		boolean firstValue= true; //this is a flag that keep track that document value is first or not 
		
		for(Text x : values){
			
			 if (!firstValue) {
			        valueList.append(SEP);
			      }

			      valueList.append(x.toString());
			      firstValue = false;
		}
		
		context.write(key, new Text(valueList.toString()));
	}
}
	

/*
public class IndexReducer extends MapReduceBase implements
    Reducer<Text, Text, Text, Text> {

  private static final String SEP = ",";

  @Override
  public void reduce(Text key, Iterator<Text> values,
      OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

    StringBuilder valueList = new StringBuilder();
    boolean firstValue = true;

    while (values.hasNext()) {

      if (!firstValue) {
        valueList.append(SEP);
      }

      valueList.append(values.next().toString());
      firstValue = false;
    }

    output.collect(key, new Text(valueList.toString()));
  }
}
*/