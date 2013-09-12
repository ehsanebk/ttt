
import java.io.*;
import java.util.*;

public class gameStage {
	 
	
	private String initialStage = "EEEEEEEEE"; 
	private String stage = "EEEEEEEEE";
	private int numberOfHappenings = 1; 

	public String getInitialStage() {
		return initialStage;
	}
	public void setInitialStage(String initialStage) {
		this.initialStage = initialStage;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public int getNumberOfHappenings() {
		return numberOfHappenings;
	}
	public void setNumberOfHappenings(int numberOfHappenings) {
		this.numberOfHappenings = numberOfHappenings;
	}
	public void fillTheStageDataBase() {	
		try {
			 
			File file = new File("./src/data/" + this.initialStage + ".data");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(this.numberOfHappenings + "\t" + this.stage);
				bw.close();
				
			}
			else { 
				try {
		            // If the file exists we edit the file
		            BufferedReader br = new BufferedReader(new FileReader("./src/data/" + this.initialStage + ".data"));
		            String strLine;
		            StringBuilder fileContent = new StringBuilder();
		            
		            List<String> list = new ArrayList <String>(); // temprary list for storing the stages
		            
		            //Read File Line By Line
		            boolean exist = false; // if the next stage lareaady exist in the data
		            while ((strLine = br.readLine()) != null) {
		                // Print the content on the console
		                //System.out.println(strLine);
		                String tokens[] = strLine.split("\t");
		                
		                
		                if (tokens[1].equals(this.stage)) {
		                	this.numberOfHappenings = Integer.parseInt(tokens[0]) + 1 ;
		                	String newLine = this.numberOfHappenings + "\t" + this.stage;
		                	
		                	list.add(newLine);
	                        exist= true;
	                        
		                	
		                } else {		                	
		                	list.add(strLine);
		                }
		            }
		             
		            if (exist == false){
		            	list.add(this.numberOfHappenings + "\t" + this.stage);
		            }
		            
		            Collections.sort(list , Collections.reverseOrder());
		            
		            for(String val : list ){
		            	fileContent.append(val);	
		            	fileContent.append('\n');
		            }
		            
		            // Now fileContent will have updated content , which you can override into file
		            BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/" + this.initialStage + ".data"));
		            out.write(fileContent.toString());
		            out.close();
		            //Close the input stream
		            br.close();
		            list.clear();
		        
				} catch (Exception e) {//Catch exception if any
		            System.err.println("Error: " + e.getMessage());
		        }
			}
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getNextStage(String stage) {
		
		String nextStage=null; 
		File file = new File("./src/data/" + stage + ".data");
		// if file doesnt exists, then create it
		if (!file.exists()) {
			nextStage= null;	
		}
		else { 
			try {
				// If the file exists we edit the file
				BufferedReader br = new BufferedReader(new FileReader("./src/data/" + stage + ".data"));
				String strLine;
				strLine = br.readLine() ;
				// Print the content on the console
				System.out.println(strLine);
				String tokens[] = strLine.split("\t");
				nextStage= tokens[1];
				br.close();
			} catch (Exception e) {//Catch exception if any
				System.err.println("Error: " + e.getMessage());   
			}	
		}
		return nextStage;
	}
}


