/*
 * File Name: GrepMapper.java
 * This file is one of component of a program used find pattern in particular file given as input
 * using Hadoop MapReduce Frame Work in JAVA
 * -----------------------------------------------------------------------------
 * this is mapper file  

 */

package grep;


import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public  class GrepMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
    /* intance variable of class */
	
	static long  count = 0;  //keep track of count for multiple map calls  
	
    /*
     * this method override the map method of supper-class MAPPER which implements the first phase of MAPREDUCE
     *  
     */
    @Override

	public void map(LongWritable key , Text value ,Context context) throws IOException,InterruptedException{
		count++;
		
		String line = value.toString();
		StringTokenizer token = new StringTokenizer(line," "); //space delimared lines
		while(token.hasMoreElements())
		{
				
				context.write(new Text(token.nextToken()),new LongWritable(count));
			
		}
		
		
		
	}

	
}
