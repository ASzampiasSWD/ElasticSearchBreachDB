package Dropbox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Dropbox {

	private static Integer lineCount = 82209229; // This number is where you last finished off.
	private static Integer maxLineCount = 0;
	private static Integer outputFileNumber = 0;
	
	public static void main(String[] args) {
		File file = new File("E:\\BreachDatabases\\Dropbox\\dropbox");
		ArrayList<String> arFileList = getAllFiles(file);
	
		for (String fileName: arFileList) {
			System.out.println("Currently converting file: " + fileName);
			readFile(file.getAbsolutePath() + "\\" + fileName);			
		}
	}
	
	private static String createMetaJSON() {
		String metajson = "{\"index\" : {\"_index\": \"breach\", \"_type\" : \"Dropbox\", \"_id\": \"" + lineCount + "\"}}";
		//System.out.println(metajson);
		return metajson;
	}
	
	private static String writeFile(String metajson, String datajson) {
		File f = new File("E:\\BreachDatabases\\Dropbox\\dropbox\\output\\dropbox_"+outputFileNumber.toString()+".json");
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
							       "\"Password\": \"" + arLines[1] + "\"," +
							       "\"Username\": \"\", " +
							       "\"Name\": \"\", " + 
							       "\"First Name\": \"\", " +
							       "\"Last Name\": \"\", " +
							       "\"Middle Name\": \"\", " +
							       "\"Website\": \"\", " +
							       "\"Phone\": \"\", " +
							       "\"Hash1\": \"\", " +
							       "\"Hash2\": \"\", " +
							       "\"Physical Address\": \"\", " +
							       "\"Mailing Address\": \"\", " +
							       "\"Password Hint\": \"\"} ";
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
	            	if (maxLineCount > 26000) {
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
        ArrayList<String> allFiles = new ArrayList<String>();
        for(File f : filesList){
            if(f.isFile() && f.getName().contains(".txt")) {
                System.out.println(f.getName());
                allFiles.add(f.getName());

            }
        }
        System.out.println(allFiles);
        return allFiles;
    }
}
