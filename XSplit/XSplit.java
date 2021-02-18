package xsplit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XSplit {
	private static Integer lineCount = 0;
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File file = new File("E:\\BreachDatabases\\XSplit\\xsplit.txt");
		readFile(file.getAbsolutePath());			
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach-xsplit\", \"_type\" : \"_doc\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\XSplit\\output\\xsplit_"+outputFileNumber.toString()+".json");
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
			  String[] arLines = line.split("\\t");
			  String index = "";
			  if (arLines.length == 4) {
				index = "{\"Username\": \"" + escape(arLines[0]) + "\"," +
						  "\"Name\": \"" + escape(arLines[1]) + "\"," +
						  "\"Email\": \"" + escape(arLines[2]) + "\"," +
						  "\"Password\": \"" + escape(arLines[3]) + "\"}";
			  } else if (arLines.length == 3) {
				  index = "{\"Username\": \"" + escape(arLines[0]) + "\"," +
							"\"Name\": \"NULL\"," +
							"\"Email\": \"" + escape(arLines[1]) + "\""    +
							"\"Password\":" + escape(arLines[2]) + "\"}";
				  
			 }
			  		  
			  	if (index != "") {
					//System.out.println(index);
					String metajson = createMetaJSON();
					writeFile(metajson, index);
					lineCount++;
					maxLineCount++;
					return true;	
			  	}
			   
		  } catch (Exception e) {
			  System.out.println("Exception happened in createDataJSON" + e + " " + line);
			  return false;
		  }
		  return false;	
	}
	
	private static void readFile(String fileName) {
		try {
	    	BufferedReader br = new BufferedReader(new FileReader(fileName));
	    	String line;
	    	try {
				while((line = br.readLine()) != null) {
				    if (line.contains("@")) {
				    	createDataJSON(line);
				    	//System.out.println(line)l
				    	if (maxLineCount > 45000) {
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
	
	  public static String escape(String input) {
		    StringBuilder output = new StringBuilder();

		    for(int i=0; i<input.length(); i++) {
		      char ch = input.charAt(i);
		      int chx = (int) ch;

		      // let's not put any nulls in our strings
		      assert(chx != 0);

		      if(ch == '\n') {
		        output.append("\\n");
		      } else if(ch == '\t') {
		        output.append("\\t");
		      } else if(ch == '\r') {
		        output.append("\\r");
		      } else if(ch == '\\') {
		        output.append("\\\\");
		      } else if(ch == '"') {
		        output.append("\\\"");
		      } else if(ch == '\b') {
		        output.append("\\b");
		      } else if(ch == '\f') {
		        output.append("\\f");
		      } else if(chx >= 0x10000) {
		        assert false : "Java stores as u16, so it should never give us a character that's bigger than 2 bytes. It literally can't.";
		      } else if(chx > 127) {
		        output.append(String.format("\\u%04x", chx));
		      } else {
		        output.append(ch);
		      }
		    }

		    return output.toString();
	}
}