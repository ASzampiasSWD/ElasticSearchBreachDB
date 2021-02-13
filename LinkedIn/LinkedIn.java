package linkedin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class LinkedIn {
	
	private static Integer lineCount = 0;
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File file = new File("E:\\BreachDatabases\\LinkedIn\\LinkedIn_RF\\Data\\");
		ArrayList<String> arFileList = getAllFiles(file);
		
		for (String fileName: arFileList) {
			System.out.println("Currently converting file: " + fileName + ".txt");
			readFile(file.getAbsolutePath() + "\\" + fileName + ".txt");
			
		}
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach-linkedin\", \"_type\" : \"_doc\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\LinkedIn\\LinkedIn_RF\\Data\\output\\linkedin_"+outputFileNumber.toString()+".json");
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
			  if (arLines.length >= 2) {
					String index = " {\"Email\": \"" + arLines[0] + "\"," +
							       "\"Password\": \"" + arLines[1] + "\"}";
					//System.out.println(index);
					String metajson = createMetaJSON();
					writeFile(metajson, index);
					return true;
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

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	           
	            if (line.contains("@")) {
	            	createDataJSON(line);
	            	//System.out.println(line);
	            	lineCount++;
	            	maxLineCount++;
	            	if (maxLineCount > 50000) {
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
	
	private static ArrayList<String> getAllFiles(File curDir) {
        File[] filesList = curDir.listFiles();
        ArrayList<Integer> integerFiles = new ArrayList<Integer>();
        ArrayList<String> allFiles = new ArrayList<String>();
        ArrayList<String> underscoreFiles = new ArrayList<String>();
        for(File f : filesList){
            if(f.isFile() && f.getName().contains(".txt")) {
                System.out.println(f.getName());
            	
            	String[] fileName = f.getName().split("\\.");
            	try {
            		Integer intNum = Integer.parseInt(fileName[0]);
            		integerFiles.add(intNum);
            		
            	} catch (Exception e) {
            		underscoreFiles.add(fileName[0]);
            	}
            }
        }
        Collections.sort(integerFiles);
        Collections.sort(underscoreFiles);
 
        for (Integer integerFile : integerFiles) {
        	allFiles.add(integerFile.toString());
        }
        
        for (String strangeFile : underscoreFiles) {
        	allFiles.add(strangeFile);
        }
        
        System.out.println(allFiles);
        return allFiles;
    }
}
