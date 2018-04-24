


public class UI {
	GUI inout;
	
	//Interface inout;
	//GUI inout;
	boolean reality;
	
	public UI (boolean real, String logfile) {
	//inout = new Interface(logfile);
	inout = new GUI();
	reality = real;
	}
	

	// write as Eliezer
	public int tell (String output){
		
		 //System.out.println("LIZA: " + output ); 	// can be made more complicated later
		
		//System.out.println(a);
		
		//inout.putOutput(output);
		inout.typeOutput("Liza: " + output, reality);
		return 0;
	}

	// get user input
	public String listen (){

		String input = inout.getInput();
		inout.putOutput("You: " + input);
		input = "~" + input + "~"; 
		/*
		

		System.out.print("YOU:      ");
	    Scanner scanner = new Scanner(System.in);
	    String input = scanner.next();
		input = "~" + input + "~"; 
		*/
		return input;
	}


	public String listen_timeout(int x)  {
		
		String input = inout.getInput(x);
		if(input.length() > 0){
			inout.putOutput("You: "+input);
			input = "~" + input + "~"; 
		}
		
		return input;
	}


	public void setSpeed(int i) {
		inout.speed = i;
	}


	public void goodbye() {
		inout.goodbye();
		
	}


	public int getSpeed() {
		return inout.speed;
		
	}


}
