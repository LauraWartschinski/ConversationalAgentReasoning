import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interface {

	String current = "";
	public int speed = 1;
	public String filename = "log.html";
	int linecount = 0;
	
	public Interface(String logfile) {
		filename = logfile;
	}


	private List<String> readFile()
	{
	  List<String> records = new ArrayList<String>();

	    File locatedFile = new File(filename);
	    if (locatedFile.exists()){	    

		    BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(filename));
		
			    String line;
			    try {
					while ((line = reader.readLine()) != null)
					{
					  records.add(line);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			    try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    
			    return clean(records);
			    
			}
			catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		  
	    }
	    try {
			locatedFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
		return clean(records);
	}
	
	
	private List<String> clean(List<String> records) {
		int start = 0;
		if (records.size() < 2){
			start = 0;
		}
		else {
			start = records.size() - 2;
		}
		
		for (int i = start; i < records.size(); i++){
			String newstring = "";
			for (int k = 0; k < records.get(i).length(); k++){
				char c = records.get(i).charAt(k);
				if(Character.isLetterOrDigit(c) || c == ' ' || c == '.' || c == '?' || c == '!' || c == '"' || c == '\'' || 
						c == '~' || c == '+' || c == '-' || c == '*' ||c == ':' || c == ';' || c == '/' || c == '$' 
						|| c == '%' || c == '€' || c == '&' || c == '(' || c == ')' || c == '[' || c == ']' || c == '>' || c == '<' 
						|| c == ',' || c == '#' || c == '='|| c == '_' || c == '\\'|| c == '´'|| c == '`'|| c == '}'|| c == '{'){
					newstring = newstring + c;
				}
				else {
					
					System.out.println("Won't append this: " + c);
					//not a good letter, don't append!
				}
				
			}
			
			if(newstring.equals(records.get(i))){
				//allright
			}
			else {
				records.set(i, newstring);
			}
			
		}
		
		return records;
	}


	public void putOutput(String a) {
		
		while (a.contains("insertimage:")){
			
			String image = a.substring(a.indexOf("insertimage:")+12, a.length());
			if(image.contains(" ")){
				image = image.substring(0, image.indexOf(" "));
			}
			System.out.println(image);
			
			a = a.substring(0,a.indexOf("insertimage:")) + "<br><img src=\"../img/" + image + "\" /><br>"
					+ a.substring(a.indexOf("insertimage:")+12+image.length(),a.length());
		}


		List<String> lines = readFile();
		lines.add("<span class='writing'>Liza is writing...</span>");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(sentenceend(a)){
			String part = a.substring(0,a.indexOf(". ") +2);
			a = a.substring(a.indexOf(". ")+2, a.length());
			
			if(part.length() < 100 && sentenceend(a)){
				part = part +  a.substring(0,a.indexOf(". ") +2);
				a = a.substring(a.indexOf(". ")+2, a.length());	
			}


			removeWriting(lines);
			lines.add("<div class='msgln'><span style='color:#00376C'><b>Liza E.</b>: " + part + "<br></span></div>");
			lines.add("<span class='writing'>Liza is writing...</span>");
			System.out.println("output: " + part);
			Path file = Paths.get(filename);

			try {
				Files.write(file, lines, Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(speed == 0){
					Thread.sleep(part.length()*55);
				}
				if(speed == 1){
					Thread.sleep(part.length()*30);
				}
				if(speed == 2){
					Thread.sleep(part.length()*12);
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		if(a.length() > 0){
		lines.remove(lines.size()-1);
		lines.add("<div class='msgln'><span style='color:#00376C'><b>Liza E.</b>: " + a + "<br></span></div>");
		removeWriting(lines);
		System.out.println("output: " + a);
		Path file = Paths.get(filename);
			try {
				Files.write(file, lines, Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}

		


		
	}

	private boolean sentenceend(String a) {
		
		a= a.toUpperCase();
		if(a.contains(". ")){

			int min;
			if (a.indexOf(". ") <= 5)
				min = 0;
			else {
				min = a.indexOf(". ")-4;
			}
			String pretext = a.substring(min,a.indexOf(". ")+1);
			if(pretext.contains("MR.") ||pretext.contains("DR.") || pretext.contains("MRS.") || pretext.contains("J.") || pretext.contains("G.") || pretext.contains("K.") ||pretext.contains("G.")){
				return false;
			}
			else {
				return true;
			}
			
		}
		
		return false;
	}


	private void removeWriting(List<String> lines) {

		for (int i = 0; i < lines.size(); i++){
			
			if (i < lines.size()){			
				if (lines.get(i).contains("<span class='writing'>Liza is writing...</span>")){
					lines.remove(i);
				}
			}
		}
		
	}


	public void typeOutput(String output, boolean reality) {
		putOutput(output);
		
	}

	public String getInput() {
		return getInput(0);
		
		
	}
	
	public void goodbye(){
		List<String> lines = readFile();
		lines.add("<div class='msgln'><span style='font-style: italic;'>Liza E. has disconnected.</div>");
		Path file = Paths.get(filename);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.exit(0);
		
	}

	public String getInput(int wait) {
		System.out.println("get input.");
		//System.out.println("get input, wait for " + wait);
		
		boolean nowait = false;
		int time = 0;
		if (wait == 0){
			nowait = true;
			System.out.println("wait forever!");
		}
		else if (wait == 1){
			wait = wait*20;
			
		}
		else {
			wait = wait*35;
		}


		if (speed == 0){
			wait = wait + (int) (Math.round(wait*0.4));
		}
		if (speed == 2){
			wait =  wait - (int) (Math.round(wait*0.4));
		}
		
		if (wait > 4){
		wait = wait * 5;
		}
		String last = "";
		

		while(time < wait || nowait){
			//System.out.println(time);
			
			//System.out.println("check again.");
			List<String> lines = readFile();
			//System.out.println(lines.size() + " is the lenght");
			
			for(int i = linecount; i < lines.size(); i++){
				//System.out.println("check line "  + i + " " + lines.get(i).substring(0, 40));
				if(lines.get(i).contains("<b>You</b>: ")){
				last = lines.get(i);
				linecount = i+1;
				return last.substring(30, last.length()-10);
				}
				
			}
			time++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			if(time== 6*60*30){
				putOutput("I'm not allowed to run forever, you know. I'm sorry. At some point, I will have to shut down again.");
			}
			
			if(time == 6*60*60){
				putOutput("I have to shut down now. Too bad you didn't continue the experiment.");
				putOutput("Goodbye!");
				goodbye();
				System.exit(0);
			}
			
			if(time == 6*60*60){
				goodbye();
				System.exit(0);
			}
		}
		
		
		
		return "";
		
		
	}

}
