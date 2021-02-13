package Adobe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Adobe {

	private static Integer lineCount = 0;
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File fileCred = new File("E:\\BreachDatabases\\Adobe\\cred");
		System.out.println("Currently converting file: cred");
		readFile(fileCred.getAbsolutePath());				
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach-adobe\", \"_type\" : \"_doc\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\Adobe\\output\\adobe_"+outputFileNumber.toString()+".json");
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
			  String[] arLines = line.split("\\|");
			  //System.out.println(Arrays.toString(arLines));
			  
			  String index = "{\"Email\": \"" + arLines[2].replace("-", "") + "\"," +
					       		"\"Password\": \"" + arLines[3].replace("-", "")  + "\"," +
					       		"\"Hint\": \"" + arLines[4].replace("-", "") + "\"}";
			  //System.out.println(index);
			  String metajson = createMetaJSON();
			  writeFile(metajson, index);
			  return true;
		  } catch (Exception e) {
			  System.out.println("Exception happened in createDataJSON on line:" + line);
			  return false;
		  }
	}
	
	private static void readFile(String fileName) {
		File file = new File(fileName);
	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	           
	            if (line.contains("@")) {
	            	createDataJSON(line);
	            	//System.out.println(line);
	            	lineCount++;
	            	maxLineCount++;
	            	if (maxLineCount > 55000) {
	            		maxLineCount = 0;
	            		outputFileNumber++;
	            	}
	            }
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
