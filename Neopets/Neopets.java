package neopets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Neopets {
	private static Integer lineCount = 0;
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File file = new File("E:\\BreachDatabases\\Neopets\\neopets_2013_68M");

		for (Integer i = 1; i <= 275; i++) {
			System.out.println("Currently converting file: npdump_" +  i + ".txt");
			readFile(file.getAbsolutePath() + "\\npdump_" + i + ".txt");			
		}
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach-neopets\", \"_type\" : \"_doc\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\Neopets\\neopets_2013_68M\\output\\neopets_"+outputFileNumber.toString()+".json");
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
				lineCount++;
				maxLineCount++;
			} catch (IOException e) {
				System.out.println("Error in writeFile: " + e.getMessage());
			}
		
		return "";
	}
	
	private static String parseDate(String strDate) {
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(escape(strDate));
			String formatDate = simpleDateFormat.format(date);
			return formatDate;
		}
		catch (ParseException e) {
			return "0000-01-01";
		}
	}
	
	private static boolean createDataJSON(String line) {
		  try {
			  String[] arLines = line.split(":");
			  if (arLines.length >= 3) {
				  if (arLines[2].contains("@")) {
					String index = "";
					index = "{\"Id\": \"" + arLines[0] + "\"," +
									"\"Password\": \"" + escape(arLines[1]) + "\"," +
									"\"Email\": \"" + escape(arLines[2]) + "\"";
					if (arLines.length >= 4) {
						String formatDate = parseDate(escape(arLines[3]));
						index += ", \"DOB\": \"" + formatDate + "\"";
					} else { index += ", \"DOB\": \"0000-01-01\""; }
					if (arLines.length >= 5) {
						index += ",\"Country\": \"" + escape(arLines[4]) + "\"";
					} else { index += ",\"Country\": \"NULL\""; }
					if (arLines.length >= 6) {
						index += ",\"Gender\": \"" + escape(arLines[5]) + "\"";
					} else { index += ",\"Gender\": \"NULL\""; }
					if (arLines.length >= 7) {
						index += ",\"IP Address\": \"" + escape(arLines[6]) + "\"";
					} else { index += ",\"IP Address\": \"NULL\""; } 
					if (arLines.length >= 8) {				
						index += ",\"Username\": \"" + escape(arLines[7]) + "\"";
					} else { index += ",\"Username\": \"NULL\""; }
					index += "}";
					//System.out.println(index);
					String metajson = createMetaJSON();
					writeFile(metajson, index);
					return true;
				  }
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
				    	if (maxLineCount > 37000) {
				    		maxLineCount = 0;
				    		outputFileNumber++;
				    	}
				    }
				}
			} catch (IOException e) {
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
