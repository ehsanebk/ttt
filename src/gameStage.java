
import java.io.*;

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
				bw.write(this.stage+ "\t" + this.numberOfHappenings);
				bw.close();
				
			}
			else { 
				try {
		            // If the file exists we edit the file
		            BufferedReader br = new BufferedReader(new FileReader("./src/data/" + this.initialStage + ".data"));
		            String strLine;
		            StringBuilder fileContent = new StringBuilder();
		            //Read File Line By Line
		            
		            boolean exist = false; // if the next stage lareaady exist in the data
		            while ((strLine = br.readLine()) != null) {
		                // Print the content on the console
		                System.out.println(strLine);
		                String tokens[] = strLine.split("\t");
		                if (tokens[0].equals(this.stage)) {
		                	this.numberOfHappenings = Integer.parseInt(tokens[1]) + 1 ;
		                	String newLine = this.stage  + "\t" + this.numberOfHappenings;
		                	fileContent.append(newLine);
	                        fileContent.append("\n");
	                        exist= true;
		                	
		                    } else {
		                        // update content as it is
		                        fileContent.append(strLine);
		                        fileContent.append("\n");
		                    }
		                
		            }
		             
		            if (exist == false){
		            	fileContent.append(this.stage+ "\t" + this.numberOfHappenings);
                        fileContent.append("\n");
		            }
		            
		            // Now fileContent will have updated content , which you can override into file
		            BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/" + this.initialStage + ".data"));
		            out.write(fileContent.toString());
		            out.close();
		            //Close the input stream
		            br.close();
		        
				} catch (Exception e) {//Catch exception if any
		            System.err.println("Error: " + e.getMessage());
		        }
			}
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
