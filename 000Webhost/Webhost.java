package webhost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Webhost {

	private static Integer lineCount = 0;
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File file = new File("E:\\BreachDatabases\\000Webhost\\000webhost.com.txt");
		System.out.println("Currently converting file: 000webhost.com.txt");
		readFile(file.getAbsolutePath());			
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach-000webhost\", \"_type\" : \"_doc\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\000Webhost\\output\\webhost_"+outputFileNumber.toString()+".json");
		if(!f.exists()){
			  try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try(FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(metajson);
			    out.println(datajson);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				System.out.println("Error in writeFile: " + e.getMessage());
			}
		
		return "";
	}
	
	private static boolean createDataJSON(String line) {
		  try {
			  String[] arLines = line.split(":");
			  if (arLines.length >= 4) {
				  if (arLines[1].length() > 1) {
					String index = "{\"Username\": \"" + arLines[0] + "\"," +
									"\"Email\": \"" + arLines[1] + "\"," +
									"\"IP Address\": \"" + arLines[2] + "\"," +
							       "\"Password\": \"" + arLines[3] + "\"}";
					//System.out.println(index);
					String metajson = createMetaJSON();
					writeFile(metajson, index);
					return true;
				  }
			  } 
		  } catch (Exception e) {
			  System.out.println("Exception happened in createDataJSON" + e);
			  return false;
		  }
		  return false;	
	}
	
	private static void readFile(String fileName) {
		File file = new File(fileName);
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	String line;
	    	try {
				while((line = br.readLine()) != null) {
				    if (line.contains("@")) {
				    	createDataJSON(line);
				    	//System.out.println(line);
				    	lineCount++;
				    	maxLineCount++;
				    	if (maxLineCount > 44000) {
				    		maxLineCount = 0;
				    		outputFileNumber++;
				    	}
				    }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error Reading File" + e);
				e.printStackTrace();
			}
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}

