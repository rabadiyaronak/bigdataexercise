package invertedindex;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class IndexMapper extends Mapper <LongWritable,Text,Text,Text>{
	
	
	
	@Override
	
	public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException{
		
		/* for getting file name for adding to value
		 * 
		 * FileSplite package : org.apache.hadoop.mapreduce.lib.input.FileSplit;
		 * used to get path of file and through we can get the name of file 
		 * line of code :- 
		 * Path <obj_Path> = <FileSplit_obj>.getPath();
		 * String <OBJ> = <obj_Path>.getName();
		 */
		
		FileSplit fileSplit = (FileSplit) context.getInputSplit();
		System.out.println("key : "+key + "VALE : "+ value);
		Path path = fileSplit.getPath();
		String wordPlace = path.getName()+"@"+ key.toString(); //this stores that word occure at which document and in that doc which line
		String s = value.toString().toLowerCase();
		for(String word : s.split("\\W+")){
			if (word.length() > 0) {
				context.write(new Text(word), new Text(wordPlace));
			}
			
		}
	}
}
/*
public class IndexMapper extends MapReduceBase implements
    Mapper<Text, Text, Text, Text> {

  @Override
  public void map(Text key, Text value, OutputCollector<Text, Text> output,
      Reporter reporter) throws IOException {

    FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
    Path path = fileSplit.getPath();
    String wordPlace = path.getName() + "@" + key.toString();
    Text location = new Text(wordPlace);
    String s = value.toString().toLowerCase();
    for (String word : s.split("\\W+")) {
      if (word.length() > 0) {
        output.collect(new Text(word), location);
      }
    }
  }
}
*/