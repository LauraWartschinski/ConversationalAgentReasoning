
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Iterator;
public class Parser {
    HashMap<String, Double> numbernames = new LinkedHashMap<String, Double>();
    HashMap<String, Double> estimates = new LinkedHashMap<String, Double>();
    HashMap<String, Double> estimates2 = new LinkedHashMap<String, Double>();
    HashMap<String, Double> estimates3 = new LinkedHashMap<String, Double>();
	private Vector<String>  information = new Vector<String>(1,1);
	private Vector<Integer> number = new Vector<Integer>(0,1);
	UserEvaluation eval;
	static Storystore store;

	double keyword = -1;

	
	
	
	public Parser(UserEvaluation ueval) {
		eval = ueval;
	}

	public double getKeyword(){
		return keyword;
	}
	
	//merge two vectors to one
	static Vector<InputType> mergeVectors(Vector<InputType> specials, Vector<InputType> generals) {
		  Vector<InputType> merge = new Vector<InputType>();
		  merge.addAll(specials);
		  merge.addAll(generals);
		  return merge;
	}

	
	public void init(Storystore mystore){
		
		store = mystore;
	    numbernames.put("ELEVEN",(double) 11); //TODO make uppercase
	    numbernames.put("TWELVE",(double) 12);
	    numbernames.put("THIRTEEN",(double) 13);
	    numbernames.put("FOURTEEN",(double) 14);
	    numbernames.put("FIFTEEN",(double) 15);
	    numbernames.put("SIXTEEN",(double) 16);
	    numbernames.put("SEVENTEEN",(double) 17);
	    numbernames.put("EIGHTEEN",(double) 18);
	    numbernames.put("NINETEEN",(double) 19);
	    numbernames.put("TWENTY",(double) 20);
	    numbernames.put("THIRTY",(double) 30);
	    numbernames.put("FOURTY",(double) 40);
	    numbernames.put("FIFTY",(double) 50);
	    numbernames.put("SIXTY",(double) 60);
	    numbernames.put("SEVENTY",(double) 70);
	    numbernames.put("EIGHTY",(double) 80);
	    numbernames.put("NINETY",(double) 90);
	    numbernames.put("HOUNDRED",(double) 100);
	    numbernames.put("ZERO",(double) 0);
	    numbernames.put("ONE",(double) 1);
	    numbernames.put("TWO",(double) 2);
	    numbernames.put("THREE",(double) 3);
	    numbernames.put("FOUR",(double) 4);
	    numbernames.put("FIVE",(double) 5);
	    numbernames.put("SIX",(double) 6);
	    numbernames.put("SEVEN",(double) 7);
	    numbernames.put("EIGHT",(double) 8);
	    numbernames.put("NINE",(double) 9);
	    numbernames.put("TEN",(double) 10);
	    

	    
	    estimates.put("ALMOST CERTAINLY NOT", (double) 7);
	    estimates.put("NOT HIGHLY LIKELY", (double) 30);
	    estimates.put("NOT HIGHLY PROBABLE", (double) 30);
	    estimates.put("NOT VERY HIGH", (double) 30);
	    estimates.put("NOT VERY LIKELY", (double) 30);
	    estimates.put("NOT VERY PROBABLE", (double) 30);
	    estimates.put("NOT SO HIGH", (double) 30);
	    estimates.put("NOT UNLIKELY", (double) 70);
	    estimates.put("NOT IMPROBABLE", (double) 30);
	    estimates.put("NOT TOTALLY IMPOSSIBLE",(double)  20);

	    estimates.put("NOT ABSOLUTELY SURE",(double)  70);
	    estimates.put("NOT TOTALLY SURE",(double)  70);
	    estimates.put("NOT QUITE SURE",(double)  60);
	    estimates.put("NOT VERY SURE",(double)  50);
	    estimates.put("NOT SO SURE",(double)  60);
	    estimates.put("NOT AT ALL SURE",(double)  50);
	    estimates.put("NOT SURE AT ALL",(double)  50);
	    estimates.put("NOT ABSOLUTELY CONVINC",(double)  70);
	    estimates.put("NOT TOTALLY CONVINC",(double)  70);
	    estimates.put("NOT QUITE CONVINC",(double)  60);
	    estimates.put("NOT VERY CONVINC",(double)  50);
	    estimates.put("NOT SO CONVINC",(double)  60);
	    estimates.put("NOT AT ALL CONVINC",(double)  50);
	    estimates.put("NOT CONVINC AT ALL",(double)  50);
	    estimates.put("NOT ABSOLUTELY CONFIDENT",(double)  70);
	    estimates.put("NOT TOTALLY CONFIDENT",(double)  70);
	    estimates.put("NOT QUITE CONFIDENT",(double)  60);
	    estimates.put("NOT VERY CONFIDENT",(double)  50);
	    estimates.put("NOT SO CONFIDENT",(double)  60);
	    estimates.put("NOT AT ALL CONFIDENT",(double)  50);
	    estimates.put("NOT CONFIDENT AT ALL",(double)  50);
	    estimates.put("NOT ABSOLUTELY CLEAR",(double)  70);
	    estimates.put("NOT TOTALLY CLEAR",(double)  70);
	    estimates.put("NOT QUITE CLEAR",(double)  60);
	    estimates.put("NOT VERY CLEAR",(double)  50);
	    estimates.put("NOT SO CLEAR",(double)  60);
	    estimates.put("NOT AT ALL CLEAR",(double)  50);
	    estimates.put("NOT CLEAR AT ALL",(double)  50);
	    estimates.put("NOT ABSOLUTELY OBVIOUS",(double)  70);
	    estimates.put("NOT TOTALLY OBVIOUS",(double)  70);
	    estimates.put("NOT QUITE OBVIOUS",(double)  60);
	    estimates.put("NOT VERY OBVIOUS",(double)  50);
	    estimates.put("NOT SO OBVIOUS",(double)  60);
	    estimates.put("NOT AT ALL OBVIOUS",(double)  50);
	    estimates.put("NOT OBVIOUS AT ALL",(double)  50);
	    

	    estimates2.put("ABSOLUTELY SURE",(double)  100);
	    estimates2.put("NOT IMPOSSIBLE",(double)  50);
	    estimates2.put("TOTALLY SURE",(double)  100);
	    estimates2.put("QUITE SURE",(double)  90);
	    estimates2.put("VERY SURE",(double)  95);
	    estimates2.put("PRETTY SURE",(double)  80);
	    estimates2.put("REALLY SURE",(double)  90);
	    estimates2.put("ABSOLUTELY CLEAR",(double)  100);
	    estimates2.put("TOTALLY CLEAR",(double)  100);
	    estimates2.put("QUITE CLEAR",(double)  90);
	    estimates2.put("VERY CLEAR",(double)  95);
	    estimates2.put("PRETTY CLEAR",(double)  80);
	    estimates2.put("REALLY CLEAR",(double)  90);
	    estimates2.put("ABSOLUTELY CONVINC",(double)  100);
	    estimates2.put("TOTALLY CONVINC",(double)  100);
	    estimates2.put("QUITE CONVINC",(double)  90);
	    estimates2.put("VERY CONVINC",(double)  95);
	    estimates2.put("PRETTY CONVINC",(double)  80);
	    estimates2.put("REALLY CONVINC",(double)  90);
	    estimates2.put("ABSOLUTELY OBVIOUS",(double)  100);
	    estimates2.put("TOTALLY OBVIOUS",(double)  100);
	    estimates2.put("QUITE OBVIOUS",(double)  90);
	    estimates2.put("VERY OBVIOUS",(double)  95);
	    estimates2.put("PRETTY OBVIOUS",(double)  80);
	    estimates2.put("REALLY CONFIDENT",(double)  90);
	    estimates2.put("ABSOLUTELY CONFIDENT",(double)  100);
	    estimates2.put("TOTALLY CONFIDENT",(double)  100);
	    estimates2.put("QUITE CONFIDENT",(double)  90);
	    estimates2.put("VERY CONFIDENT",(double)  95);
	    estimates2.put("PRETTY CONFIDENT",(double)  80);
	    estimates2.put("REALLY CONFIDENT",(double)  90);
	    estimates2.put("NOT CLEAR",(double)  60);
	    estimates2.put("NOT CONFIDENT",(double)  60);
	    estimates2.put("NOT OBVIOUS",(double)  60);
	    estimates2.put("NOT CONVINC",(double)  60);
	    estimates2.put("NOT SURE",(double)  60);

	    estimates2.put("NOT PROBABLE", (double) 30);
	    estimates2.put("NOT POSSIBLE",(double)  0);
	    estimates2.put("NOT CERTAIN",(double)  50);
	    estimates2.put("PROBABLY NOT", (double) 30);
	    estimates2.put("NOT LIKELY", (double) 30);
	    estimates2.put("ALMOST CERTAIN", (double) 95);
	    estimates2.put("ALMOST IMPOSSIBLE", (double) 5);
	    estimates2.put("UNLIKELY", (double) 30);
	    estimates2.put("IMPROBABLE", (double) 20);
	    estimates2.put("MOST LIKELY", (double) 80);
	    estimates2.put("HIGHLY LIKELY", (double) 80);
	    estimates2.put("HIGHLY PROBABLE", (double) 80);
	    estimates2.put("VERY LIKELY", (double) 70);
	    estimates2.put("VERY PROBABLE", (double) 70);
	    estimates2.put("UNBELIEVABL", (double) 20);
	    estimates2.put("IMPLAUSIBL", (double) 20);
	    estimates2.put("UNLIKEL", (double) 20);
	    estimates2.put("IRRATION", (double) 20);
	    estimates2.put("IMPROBABLE", (double) 20);
	    estimates2.put("UNSURE", (double) 50);
	    estimates2.put("UNCERTAIN", (double) 50);
	    estimates2.put("DOUBT", (double) 50);
	    
	    estimates3.put("SURE",(double)  90);
	    estimates3.put("CONFIDENT",(double)  90);
	    estimates3.put("OBVIOUS",(double)  95);
	    estimates3.put("CLEAR",(double)  95);
	    estimates3.put("CONVINC",(double)  80);
	    estimates3.put("LIKELY", (double) 75);
	    estimates3.put("VERY LOW",(double) 10);
	    estimates3.put("VERY HIGH",(double)  90);
	    estimates3.put("EXTREMELY LOW",(double) 2);
	    estimates3.put("EXTREMELY HIGH",(double)  98);
	    estimates3.put("EXTRAORDINARY LOW",(double) 1);
	    estimates3.put("EXTRAORDINARY HIGH",(double)  99);
	    estimates3.put("PROBABLE", (double) 75);
	    estimates3.put("CERTAIN", (double) 100);
	    estimates2.put("IMPOSSIBLE",(double)  0);
	    estimates3.put("POSSIBLE", (double) 50);
	    estimates3.put("EVEN CHANCES",(double)  50);
	    estimates3.put("EQUALLY",(double)  50);
	    estimates3.put("LOW",(double)  20);
	    estimates3.put("HIGH",(double)  80);
	    estimates3.put("PERHAPS",(double)  70);
	    estimates3.put("PRESUMABL",(double)  80);
	    estimates3.put("SEEM",(double)  80);
	    estimates3.put("DOUBTL",(double)  90);
	    estimates3.put("PLAUSIBL",(double)  70);
	    estimates3.put("LOGICAL",(double)  70);
	    estimates3.put("VALID",(double)  70);
	    estimates3.put("SOUND",(double)  80);
	    estimates3.put("CREDIBL",(double)  70);
	    
	    return;
	}
	
	//hand over the first stored String-typed information and delete it
	public String getString (){
		String get = "";
		if (!information.isEmpty()){
			get = information.get(0);
			information.remove(0);
		}
		return get;
	}

	//hand over the first stored Integer-typed information and delete it
	public int getNumber (){
		int num =0;
		if (!number.isEmpty()){
			num = number.get(0);
			number.remove(0);
		}
		return num;
	}

	//find the first whole word in a string
	private String getFirstWord(String s){
		for (int i=0;i< s.length(); ++i) {
			if (!Character.isLetter(s.charAt(i))) {
			s = s.substring(0,i);
			break;
			}
		}
			  
		//System.out.println("found word " + s);
		return s;
	}
	

	//find general tags in the answer.
	private Vector<InputType> parseGeneral(String input){
		String raw = input;
		
		Vector<String> howare = new Vector<String>();
		howare.add("HOW ARE"); howare.add("HOW DO YOU FEEL"); howare.add("ARE YOU OKAY");
		howare.add("WHATS UP"); howare.add("WHAT'S UP"); howare.add("HOW IS IT");howare.add("HOW'S IT");  howare.add("WHAT'S GOING ON");
		howare.add("HOWS IT"); 
		//System.out.println("INPUT: " + input);
		Vector<String> inits = new Vector<String>();
		inits.add("IT IS"); 		inits.add("IT'S");
		inits.add("ITS"); 		inits.add("'S BEEN");
		inits.add(" HAS BEEN"); 		inits.add(" WAS");
		inits.add(" IS"); 		inits.add(" HAVE BEEN");
		inits.add(" WERE");		inits.add(" ARE");
		inits.add("SEEMS");		inits.add("SOUNDS");
		inits.add(" BE ");		
		
		Vector<String> youre = new Vector<String>();
		youre.add("YOU ARE"); youre.add("YOU'RE"); youre.add("YOUR");//gnah, spelling mistakes
		
		
		Vector<String> pos = new Vector<String>();
		pos.addElement("GOOD"); pos.addElement("NICE"); pos.addElement("OK"); pos.addElement("GREAT"); pos.addElement("FINE");
		pos.addElement("AMAZING"); pos.addElement("EXCELLENT"); pos.addElement("AWESOME"); pos.addElement("FANTASTIC");
		pos.addElement("COOL");pos.addElement("INTERESTING");pos.addElement("WONDERULF");pos.addElement("PLEASING");
		pos.addElement("ACCEPTABLE");pos.addElement("NEAT"); pos.addElement("CLEVER"); pos.addElement("BRIGHT");
		 pos.addElement("LOVELY"); pos.addElement("IMPRESS"); pos.addElement("GREAT"); pos.addElement("INTELLIGENT");
		 pos.addElement("HAPPY TO"); pos.addElement("GLADLY"); pos.addElement(" JOY"); pos.addElement("DELIGHT"); pos.addElement("ENJOYMENT");

			
		Vector<String> ai = new Vector<String>();
		ai.add("COMPUTER"); ai.add("SOFTWARE"); ai.add("PROGRAM"); ai.add("NO HUMAN"); ai.addElement("AI"); ai.add("ARTIFICIAL"); ai.add("MACHINE");
		ai.add("ROBOT"); ai.add("SYNTHETIC"); ai.add("MADE UP"); ai.add("MANUFACTURED"); ai.add("PRETEND");ai.add(" BOT");ai.add("CHATBOT");ai.add("AGENT");
		
		Vector<String> bad = new Vector<String>();
		bad.addElement("AWFUL");bad.addElement("HORRIBLE");bad.addElement("BAD");bad.addElement("ATROCIOUS");
		bad.addElement("CHEAP");bad.addElement("UNACCEPTABLE");bad.addElement("LOUSY");    
		bad.addElement("UNPLEASANT");bad.addElement("STUPID");  
		bad.addElement("SILLY");bad.addElement("SHIT");bad.addElement("CRAP");   bad.addElement("DUMB"); 
		bad.addElement("IDIOT"); bad.addElement("DUMB");bad.addElement("FOOL");bad.addElement("PAIN");bad.addElement("ASS");     
		
		Vector<String> boring = new Vector <String>();
		boring.addElement("BORING"); boring.add("DULL"); boring.add("LAME"); boring.add("ANNOYING"); boring.add("MONOTO");
		boring.add("MUNDANE");boring.add("TIDIOUS");boring.add("UNINTEREST");boring.add("TIRING");    
		
		Vector<String> interesting = new Vector <String>();
		interesting.addElement("INTEREST"); interesting.add("EXCIT"); interesting.add("ALLUR"); interesting.add("STRIK");
		interesting.add("MAKES ME THINK"); interesting.add("MAKES ONE THINK"); interesting.add("MAKES YOU THINK");
		interesting.add("MAKE ME THINK"); interesting.add("MAKE ONE THINK"); interesting.add("MAKE YOU THINK");
		interesting.add("FASCINAT"); interesting.add("INTRIG"); interesting.add("THRILL"); 
		
		Vector<String> like = new Vector<String>();
		like.addElement("LIKE YOU"); like.addElement("LOVE YOU"); like.addElement("WANT TO MARRY");
		 like.addElement("WILL YOU MARRY ME"); like.addElement("CRUSH ON YOU"); like.addElement("WANT A CHILD");
		 like.addElement("SLEEP WITH YOU");  like.addElement("WANT TO MARRY"); like.addElement("ADMIRE YOU");
		 like.addElement("APPRECIATE YOU");  like.addElement("ADORE YOU");
		 like.addElement("CARE FOR YOU");  like.addElement("WANT YOU TO LIKE ME");  like.addElement("FOND OF YOU");
		 like.addElement("ATTRACTED TO YOU");
		 
		 Vector<String> dislike = new Vector<String>();
		 dislike.add("HATE YOU"); dislike.add("DESPISE YOU"); dislike.add("NOT STAND YOU"); dislike.add("CANNOT STAND YOU");
		 dislike.add("CAN'T STAND YOU"); dislike.add("HATE YOU"); dislike.add("DON'T LIKE YOU");
		 dislike.add("HATE YOU");
		 
		Vector<InputType> type =new Vector<InputType>(0,1);
		
		if(findTag(input,"FASTER").startsWith("+") ||findTag(input,"TOO SLOW").startsWith("+") ){
			//System.out.println("slower");
			type.add(InputType.FASTER);
		}
		if(findTag(input,"SLOWER").startsWith("+")|| findTag(input,"TOO FAST").startsWith("+")){
			//System.out.println("faster");
			type.add(InputType.SLOWER);
		}
		
		if(input.contains("THANK YOU") || 
				input.contains("THANKS") 
				){
			type.add(InputType.THANKYOU);
		}
		if(input.contains("GIVE ME A HINT") || 
				findTag(input,"CLUE").startsWith("+") ||
				findTag(input,"TIP ").startsWith("+") ||
				findTag(input,"HINT").startsWith("+") ||
				findTag(input,"ASSIST").startsWith("+") ||
				input.contains("GIVE ME A TIP") ||
				input.contains("NEED A HINT") ||
				input.contains("NEED A CLUE") ||
				input.contains("NEED A TIP") ||
				input.contains("HELP ME") ||
				input.contains("NEED HELP") ||
				input.contains("ASSIST ME") ||
				input.contains("NEED ASSIST")
				){
			type.add(InputType.HINT);
		}
				

			if(input.contains("EXPLAIN") ||
					input.contains("TELL ME") ||
					
					(input.contains("PLASE") || 
					input.contains("CAN YOU") ||
					input.contains("WOULD YOU") ||
					input.contains("COULD YOU") ||
					input.contains("GIVE ME") ||
					input.contains("TELL ME") ||
					input.contains("SAY"))&&				
					(input.contains("EXPLA") || 
							input.contains("SOLUT") ||
							input.contains("CORRECT") ||
							input.contains("RIGHT") ||
							input.contains("ANSWER") ||
							input.contains("TELL") ||
							input.contains("SAY"))){
				type.add(InputType.EXPLAIN);
			}
			
			
			if ( input.toUpperCase().contains("WANT TO STOP")||
					 input.toUpperCase().contains("STOP THE QUESTIONS")||
					 input.toUpperCase().contains("STOP THE CHAT")||
					 input.toUpperCase().contains("END THE QUESTIONS")||
					 input.toUpperCase().contains("END THE CHAT")||
					 input.toUpperCase().contains("STOP TALKING")||
					 input.toUpperCase().contains("LET ME OUT")||
					 input.toUpperCase().contains("I'LL STOP")||
					 input.toUpperCase().contains("I WILL STOP")||
					 input.toUpperCase().contains("STOP HERE")||
					 input.toUpperCase().contains("LET ME LEAVE")||
					 input.toUpperCase().contains("STOP")||
					 input.toUpperCase().contains("I HAVE ENOUGH")||
					 input.toUpperCase().contains("HAVE HAD ENOUGH")||
					 input.toUpperCase().contains("TO MUCH WORK")||
					 input.toUpperCase().contains("WANT TO QUIT")||
					 (input.toUpperCase().contains("DON'T WANT") && (input.toUpperCase().contains("ANYMORE") || input.toUpperCase().contains("ANY LONGER")))||
					 input.toUpperCase().contains("WANT TO LEAVE")){
				type.add(InputType.STOP);
			}
			
			
		
		if ( input.toUpperCase().contains("WHY AM I DOING THIS")||
				 input.toUpperCase().contains("WHY AM I EVEN DOING THIS")||
				 input.toUpperCase().contains("WHAT IS THIS FOR")||
				 input.toUpperCase().contains("WHAT PURPOSE")||
				 input.toUpperCase().contains("WHAT'S THE PURPOSE")||
				 input.toUpperCase().contains("WHY SHALL I DO THIS")||
				 input.toUpperCase().contains("WHY WOULD I DO THIS")||
				 input.toUpperCase().contains("DO I HAVE TO")||
				 input.toUpperCase().contains("DO I REALLY HAVE TO")){
			type.add(InputType.PURPOSE);
		}
		
		if (( input.toUpperCase().contains("HOW ARE YOU")||
				 input.toUpperCase().contains("HOW ARE THINGS")||
				 input.toUpperCase().contains("HOW DO YOU DO")||
				 input.toUpperCase().contains("HOW HAVE YOU BEEN")||
				 input.toUpperCase().contains("HOW ARE YOU DOING")||
				 input.toUpperCase().contains("WHAT'S UP"))&&( input.toUpperCase().contains("?"))){
			type.add(InputType.HOW);
		}

		
		if (( input.toUpperCase().contains("HOLD ON")||
				 input.toUpperCase().contains("WAIT")||
				 input.toUpperCase().contains("HAVE TO WAIT")||
				 input.toUpperCase().contains("PLEASE WAIT")||
				 input.toUpperCase().contains("ONE SEC")||
				 input.toUpperCase().contains("FOR A WHILE")||
				 input.toUpperCase().contains("INTERRUPT")||
				 input.toUpperCase().contains("LET ME THINK")||
				 input.toUpperCase().contains("INTERRUPT")||
				 input.toUpperCase().contains("BREAK")||
				 input.toUpperCase().contains("TIRED")||
				 input.toUpperCase().contains("EXCUSE ME FOR")||
				 input.toUpperCase().contains("I HAVE TO GET SOMETHING")||
				 input.toUpperCase().contains("I HAVE TO GO")||
				 	((input.toUpperCase().contains("HAVE TO GET")||
				 			input.toUpperCase().contains(" WANT ")||
				 			input.toUpperCase().contains(" NEED")
				 			)&&
				 		(input.toUpperCase().contains(" ME ")||
						 input.toUpperCase().contains("DRINK")||
						 input.toUpperCase().contains("FOOD")||
						 input.toUpperCase().contains("BITE")||
						 input.toUpperCase().contains("COFFEE")||
						 input.toUpperCase().contains("WATER")||
						 input.toUpperCase().contains("EAT")
						 )
					)||
				 input.toUpperCase().contains("ONE MOMENT")||
				 input.toUpperCase().contains("BE RIGHT BACK")||
				 input.toUpperCase().contains("WILL BE BACK"))){
			type.add(InputType.WAIT);
		}
		
			boolean init = false, positive = false, negative = false, bored = false, okay = false, interest = false,
					your = false, love = false, hate = false, selfbad = false, artificial = false;
		
			//System.out.println(input);
			
			for(int i = 0; i < inits.size(); i++){
				//System.out.println(inits.get(i)+ "?");
				if (input.contains(inits.get(i))){
					input = input.substring(input.indexOf(inits.get(i))+inits.get(i).length()-1,input.length());// cut off everything up to the inits-expression
					init = true;
				}	
			}
			
			for(int i = 0; i < youre.size(); i++){
				if (findTag(input,youre.get(i)).startsWith("+")){
					your = true;
				}	
			}
			
			if(init){
				//System.out.println("IT IS: " + input);
				//System.out.println("init");
				for(int i = 0; i < pos.size(); i++){
					if (findTag(input,pos.get(i)).startsWith("+")){
						positive = true;
					//	System.out.println("positive true");
					}	
				}
				for(int i = 0; i < bad.size(); i++){
					if (findTag(input,bad.get(i)).startsWith("+")){
						//System.out.println("bad true");
						negative = true;
					}	
					if (findTag(input,bad.get(i)).startsWith("-")){
						//System.out.println("bad false");
						negative = false;
						okay = true;
					}	
				}
				for(int i = 0; i < boring.size(); i++){
					//System.out.println(boring.get(i) +" is searched in " + input);
					if (findTag(input,boring.get(i)).startsWith("+")){
						//System.out.println("boring true");
						bored = true;
					}	
					if (findTag(input,boring.get(i)).startsWith("-")){
						//System.out.println("boring false");
						bored = false;
						okay = true;
					}
				}
				for(int i = 0; i < interesting.size(); i++){
					if (findTag(input,interesting.get(i)).startsWith("+")){
					//	System.out.println("Interesting true");
						interest = true;
					}	
				}
			}
			if(your){
			//	System.out.println("YOU ARE");
				for(int i = 0; i < pos.size(); i++){
					if (findTag(input,pos.get(i)).startsWith("+")){
					//	System.out.println("YOU:");
						love = true;
					}	
				}
				for(int i = 0; i < bad.size(); i++){
					if (findTag(input,bad.get(i)).startsWith("+")){
					//	System.out.println("bad true" + bad.get(i));
						hate = true;
					}	
					if (findTag(input,bad.get(i)).startsWith("-")){
					//	System.out.println("bad false");
						hate = false;
						okay = true;
					}	
				}

				for(int i = 0; i < ai.size(); i++){
					if (findTag(input,ai.get(i)).startsWith("+")){
						//System.out.println("AI true");
						artificial = true;
					}	
				}
				for(int i = 0; i < boring.size(); i++){
					if (findTag(input,boring.get(i)).startsWith("+")){
						//System.out.println("boring true");
						bored = true;
					}	
					if (findTag(input,boring.get(i)).startsWith("-")){
						//System.out.println("boring false");
						bored = false;
						okay = true;
					}
				}
				for(int i = 0; i < interesting.size(); i++){
					if (findTag(input,interesting.get(i)).startsWith("+")){
					//	System.out.println("interesting true");
						interest = true;
					}
					if (findTag(input,interesting.get(i)).startsWith("-")){
					//	System.out.println("interesting true");
						interest = false;
						bored = true;
					}	
				}
			}
			for(int i = 0; i < dislike.size(); i++){
				if (findTag(input,dislike.get(i)).startsWith("+")){
				//	System.out.println("dislike true");
					hate = true;
				}	
				if (findTag(input,dislike.get(i)).startsWith("-")){
				//	System.out.println("dislike false");
					hate = false;
					okay = true;
				}	
			}
		
		if(raw.contains("I AM")||raw.contains("I'M")){
			for(int i = 0; i < boring.size(); i++){
				if (findTag(raw,boring.get(i)).startsWith("+")){
			//		System.out.println("boring true");
					bored = true;
				}	
				if (findTag(raw,boring.get(i)).startsWith("-")){
			//		System.out.println("boring false");
					bored = false;
					okay = true;
				}
			}
			for(int i = 0; i < bad.size(); i++){
				if (findTag(raw,bad.get(i)).startsWith("+")){
			//		System.out.println("self-bad true");
					selfbad = true;
				}	
			}
		}
		
		

		for(int i = 0; i < howare.size(); i++){
			if (findTag(raw,howare.get(i)).startsWith("+") && input.contains("?")){
		//		//System.out.println("how are you true");
				type.add(InputType.HOW_ARE);
			}
		}		
			
		
		if(positive) type.add(InputType.COOL);
		if(okay) type.add(InputType.OK);
		if(hate) type.add(InputType.HATE);
		if(love) type.add(InputType.LOVE);
		if(bored) type.add(InputType.BORING);
		if(negative) type.add(InputType.BAD);
		if(interest) type.add(InputType.INTERESTING);
		if(selfbad) type.add(InputType.SELFCRITIC);	
		if(artificial) type.add(InputType.ARTIFICIAL);
		input = raw;
		if((
				input.toUpperCase().contains("AND YOU")
				|| input.toUpperCase().contains("HOW ")
				|| input.toUpperCase().contains("WHEN ")
				|| input.toUpperCase().contains("WOULD YOU ")
				|| input.toUpperCase().contains("CAN YOU ")
				|| input.toUpperCase().contains("WHY ")
				|| input.toUpperCase().contains("WHERE ")
				|| input.toUpperCase().contains("WHAT "))
				&& input.toUpperCase().contains("?")
				){

			for(int i = 0; i < ai.size(); i++){
				if (findTag(input,ai.get(i)).startsWith("+")){
					type.add(InputType.ARTIFICIAL_Q);
				}	
			}
			if(!type.contains(InputType.ARTIFICIAL_Q)){
				type.add(InputType.QUESTION);
			}
		}
		//System.out.println(input);
		if((input.toUpperCase().contains("ARE YOU")
				|| input.toUpperCase().contains("DID YOU")
				|| input.toUpperCase().contains("IS THAT")
				|| input.toUpperCase().contains("REALLY")
				|| input.toUpperCase().contains("HAVE YOU")
				|| input.toUpperCase().contains("DO YOU"))
				&& input.toUpperCase().contains("?")
				){
			if (!type.contains(InputType.QUESTION)){
				for(int i = 0; i < ai.size(); i++){
					if (findTag(input,ai.get(i)).startsWith("+")){
						type.add(InputType.ARTIFICIAL_Q);
					}	
				}
				if(!type.contains(InputType.ARTIFICIAL_Q)){
					type.add(InputType.YES_NO);
				}

			}
		}
		
		if(input.toUpperCase().contains("DON'T KNOW")
		|| input.toUpperCase().contains("DONT KNOW")
		|| input.toUpperCase().contains("CAN'T DECIDE")
		|| input.toUpperCase().contains("CANT DECIDE")
		|| input.toUpperCase().contains("I'M NOT SURE")
		|| input.toUpperCase().contains("AM NOT SURE")
		||	input.toUpperCase().contains("HAVE NO CLUE")
		||	input.toUpperCase().contains("HAVE NO IDEA")){
			type.add(InputType.NO_CLUE);
		}
		if( input.toUpperCase().contains("ANOTHER CHANCE")
				|| input.toUpperCase().contains("SECOND CHANCE")
				||	input.toUpperCase().contains("CAN I TRY")
				|| input.toUpperCase().contains("ANOTHER QUESTION ABOUT")
				|| input.toUpperCase().contains("ANOTHER QUESTION ON")
				||	input.toUpperCase().contains("ANOTHER STORY ON")
				||	input.toUpperCase().contains("ANOTHER STORY ABOUT")){
					type.add(InputType.REQUEST_STORY);
				}

		
 
		if (input.toUpperCase().contains("WHY") && input.contains("?")){
			type.add(InputType.WHY);
			//System.out.println("why");
		}

			if (input.toUpperCase().contains("WHAT IS") && input.contains("?")||
					input.toUpperCase().contains("WHAT'S") && input.contains("?")||
					input.toUpperCase().contains("WHAT ARE") && input.contains("?")||
					input.toUpperCase().contains("WHAT DOES") && input.contains("?")){
				type.add(InputType.WHATIS);

			
			
		}
		
		return type;
	}
	

			
			

	private InputType ParseName(String input){
		input = input.substring(1,input.length()-1);
		if (ParseGreeting(input) == InputType.CORRECT) return InputType.CORRECT;
		
		if (input.startsWith("YES")) input = input.substring(4,input.length());
		if (input.startsWith("OF COURSE")) input = input.substring(10,input.length());
		if (input.startsWith("SURE")) input = input.substring(5,input.length());
		if (input.startsWith("YEAH")) input = input.substring(5,input.length());
		if (input.startsWith("SORRY")) input = input.substring(6,input.length());
		if (input.startsWith("IT WAS")) input = input.substring(7,input.length());
		if (input.startsWith("IT'S")) input = input.substring(5,input.length());
		
		if (input.startsWith(" ")) input = input.substring(1,input.length());
		if (input.startsWith(".")) input = input.substring(1,input.length());
		if (input.startsWith(",")) input = input.substring(1,input.length());
		if (input.startsWith(" ")) input = input.substring(1,input.length());
		if (input.startsWith("IT'S")) input = input.substring(5,input.length());
		
		if (input.endsWith(" ")) input = input.substring(0,input.length()-1);
		if (input.endsWith("!")) input = input.substring(0,input.length()-1);
		if (input.endsWith(".")) input = input.substring(0,input.length()-1);
		


		String name = getFirstWord(input);
		if (!(name.contains(" ")) && name.length() > 1){
					
			String realname = name.substring(0,1);
			realname = realname + name.substring(1,name.length()).toLowerCase();
			eval.name = realname;
	    	information.addElement(realname);
	    	//System.out.println(realname);
	    	return InputType.CORRECT;
		}
		
		return InputType.NO_IDEA;
	}
	
	//find tags in the reply to the greeting
	private InputType ParseGreeting(String input){
		String upper = input.toUpperCase();
		Vector<String> introduction=new Vector<String>(2,1);
		introduction.add("MY NAME IS ");
		introduction.add("CALL ME ");
		introduction.add("I AM ");
		introduction.add("I'M ");
		String match, name ="";
	    Iterator<String> i = introduction.iterator();
	    while (i.hasNext()) {
	    	match = (String) i.next();
	    	if (upper.contains(match)){			
	    	  int start = upper.indexOf(match) + match.length();	
		      name = getFirstWord(input.substring(start));
		      if ((name.length() > 0) && (!name.toUpperCase().equals("A")) && !name.toUpperCase().equals("THE") && (!name.toUpperCase().equals("AN"))){
					
		    	  if(name=="SAD" || name=="BAD" || 
		  				name == "EXCITED" || name == "INTERESTED" ||
		  				 name == "COOL" || name == "NEW" || name == "RATIONAL" || 
		  				 name == "HUMAN" || name == "FUNNY" 
		  				 || name.contains("LIZA") || name.contains("ELIEZER") || name == "YOU"
		  				 || name.contains("NOBODY")|| name.contains("NO")|| name.contains("NO ONE")){
		  		    	return InputType.INCORRECT;
		    	  }
		    	  
		    	  String realname = name.substring(0,1);
				realname = realname + name.substring(1,name.length()).toLowerCase();
		    	  information.addElement(realname);
		    	  return InputType.CORRECT;
		      }
	    	}
	    }
		return InputType.NO_IDEA;
	}

	
	//find tags in the reply to the explanation
	private InputType ParseAgreement(String input, boolean orientation){
		//System.out.println("ParseAgreement");
		
		if (input.toUpperCase().contains("YES")
		|| input.toUpperCase().contains("YEAH")
		|| input.toUpperCase().contains("JEP")
		|| input.toUpperCase().contains("YEP")
		|| (input.toUpperCase().contains("OBVIOUSLY") && !input.toUpperCase().contains("NOT"))
		|| input.toUpperCase().contains("OK")
		|| input.toUpperCase().contains("OKAY")
		|| input.toUpperCase().contains("COOL")
		|| input.toUpperCase().contains("FINE") 
		|| input.toUpperCase().contains("KEWL")
		||( input.toUpperCase().contains("OF COURSE")  && !input.toUpperCase().contains("NOT"))
		|| input.toUpperCase().contains("SURE")
		|| input.toUpperCase().contains("I THINK SO")
		|| input.toUpperCase().contains("IT'S CORRECT")
		|| input.toUpperCase().contains("IT' RIGHT")
		|| (input.toUpperCase().contains("AGREE") && !input.toUpperCase().contains("DIS"))
		|| (input.toUpperCase().contains("I DO.") && !input.toUpperCase().contains("NOT"))
		|| (input.toUpperCase().contains("I DO ") && !input.toUpperCase().contains("NOT"))){
			//System.out.println("yes");
			if(orientation) return InputType.CORRECT;
			else 			return InputType.INCORRECT;
		};
		
		if (input.toUpperCase().contains("~NO~") ||
				input.toUpperCase().contains("~NO.") ||
				input.toUpperCase().contains("~NO ") ||
				input.toUpperCase().contains("~NO,") ||
				input.toUpperCase().contains("~NO!") ||
				input.toUpperCase().contains(" NO ") ||
				input.toUpperCase().contains(" NO.") ||
				input.toUpperCase().contains(" NO,") ||
				input.toUpperCase().contains(" NO!") ||
				input.toUpperCase().contains(" NO~") ||
				input.toUpperCase().contains("NOPE") ||
				input.toUpperCase().contains("DISAGREE") ||
				input.toUpperCase().contains("NOT ") ||
				input.toUpperCase().contains("WRONG") ||
				input.toUpperCase().contains("NAH,") ||
				input.toUpperCase().contains("NAH ") ||
				input.toUpperCase().contains("NAH~") ||
				input.toUpperCase().contains("NAH.") ||
				input.toUpperCase().contains("~NA.") ||
				input.toUpperCase().contains("~NA,") ||
				input.toUpperCase().contains("~NA ") ||
				input.toUpperCase().contains("~NA~") ||
				input.toUpperCase().contains(" NA.") ||
				input.toUpperCase().contains(" NA~") ||
				input.toUpperCase().contains(" NA,") ||
				input.toUpperCase().contains(" NA ") ||
				input.toUpperCase().contains("I DONT") ||
				input.toUpperCase().contains("DONT THINK") ||
				input.toUpperCase().contains("I DON'T") ||
				input.toUpperCase().contains("DON'T THINK") ||
				input.toUpperCase().contains("FALSE")) {
			//System.out.println("no");
			
			if(orientation) return InputType.INCORRECT;
			else 			return InputType.CORRECT;
		};
		
		
		if (input.contains("PLEASE DO") || input.contains("DO IT") || input.contains("DO THIS") || input.contains("WHY NOT")   ){
			return InputType.CORRECT;
		}		
		if (input.contains("PLEASE DON'T") || input.contains("DON'T DO") ||input.contains("NO WAY")  ||input.contains("ANYTHING BUT") ){
			return InputType.INCORRECT;
		}
		return InputType.NO_IDEA;
	}
	
	
	
	
	//find tags in the reply to the explanation
	private InputType ParseExplanation(String input){
		
		if (input.toUpperCase().contains("YES")
		|| input.toUpperCase().contains("YEAH")
		|| input.toUpperCase().contains("OK")
		|| input.toUpperCase().contains("OKAY")
		|| input.toUpperCase().contains("JEP")
		|| input.toUpperCase().contains("YEP")
		|| input.toUpperCase().contains("JUP")
		|| input.toUpperCase().contains("YUP")
		|| input.toUpperCase().contains("OF COURSE")
		|| input.toUpperCase().contains("UNDERSTAND")
		|| input.toUpperCase().contains("I THINK SO")
		|| input.toUpperCase().contains("I GUESS")
		|| input.toUpperCase().contains("ABSOLUTELY")
		|| input.toUpperCase().contains("OF COURSE")
		|| input.toUpperCase().contains("GET IT")
		|| input.toUpperCase().contains("GOT IT")
		|| input.toUpperCase().contains("SURE")
		|| ((input.toUpperCase().contains("I DID")|| input.toUpperCase().contains("UNDERSTAND")	|| input.toUpperCase().contains("UNDERSTOOD"))
				&& !(input.toUpperCase().contains("NOT") ||input.toUpperCase().contains("NOTHING") || input.toUpperCase().contains("N'T")))){
					
			return InputType.CORRECT;
		};
		
		if (input.toUpperCase().contains("NO") || input.toUpperCase().contains("NOPE") || input.toUpperCase().contains("NOT UNDERSTAND")
				 || input.toUpperCase().contains("NOTHING") || input.toUpperCase().contains("UNKLEAR") || input.toUpperCase().contains("EXPLAIN IT")
				 || input.toUpperCase().contains("REPEAT") || input.toUpperCase().contains("EXPLAIN AGAIN") || input.toUpperCase().contains("DON'T UNDERSTAND")
				 || input.toUpperCase().contains("DONT UNDERSTAND")){
			return InputType.INCORRECT;
		};
		return InputType.NO_IDEA;
	}

// shall only be called if the tag is in fact contained in the input!
// returns true if the tag is contained in a positive way ("i think it is tag")
// returns false if the tag is contained in a negative way ("i don't think it is tag")
// handles also nested negation
	
	public boolean findLower(String input){
		
		Vector<String> lowering = new Vector<String>(7,1);
		lowering.add("LESS"); 
		lowering.add("BELOW");
		lowering.add("FEWER");
		lowering.add("UNDER");
		lowering.add("AT MOST");
		Vector<String> rising = new Vector<String>(7,1);
		rising.add("OVER");
		rising.add("MORE");
		rising.add("ABOVE");
		rising.add("AT LEAST");

		if (!input.contains("NO")){
			for (int i = 0; i < lowering.size(); i++){
				if (input.contains(lowering.get(i))){
					return true;
				}
			}
		}
		else {
			input = input.substring(input.indexOf("NO")+2);
			for (int i = 0; i < rising.size(); i++){
				if (input.contains(rising.get(i))){
					return true;
				}
			}
			
		}
		return false;
	}
	
	public boolean findUpper(String input){
		
		Vector<String> lowering = new Vector<String>(7,1);
		lowering.add("LESS"); 
		lowering.add("BELOW");
		lowering.add("FEWER");
		lowering.add("UNDER");
		lowering.add("AT MOST");
		Vector<String> rising = new Vector<String>(7,1);
		rising.add("OVER");
		rising.add("MORE");
		rising.add("ABOVE");
		rising.add("AT LEAST");

		if (!input.contains("NO")){
			for (int i = 0; i < rising.size(); i++){
				if (input.contains(rising.get(i))){
					return true;
				}
			}
		}
		else {
			input = input.substring(input.indexOf("NO")+2);
			for (int i = 0; i < lowering.size(); i++){
				if (input.contains(lowering.get(i))){
					return true;
				}
			}
			
		}
		return false;
	}
	
	public String findTag(String input, String tag){
		
		//System.out.println("Looking for " + tag + " in " + input);
		if (!input.contains(tag))
			return "";
		
		Vector<String> negatives = new Vector<String>(7,1);
		negatives.add("NO ");
		negatives.add("NOT ");
		negatives.add("DOUBT ");
		negatives.add("DONT ");
		negatives.add("DON'T ");
		negatives.add("DOESN'T ");
		negatives.add("CAN'T ");
		negatives.add("ISN'T ");
		negatives.add("WON'T ");
		negatives.add("WOULDN'T ");
		negatives.add("UNLIKELY");
		negatives.add("RATHER THAN");
		negatives.add("ANYTHING BUT");
		negatives.add("ANYTHING BESIDES");
		
		input = input.substring(0, input.indexOf(tag));
		boolean searching = true;
		boolean value = true;
		
		while(searching){
			boolean found = false;
			for (int i = 0; i < negatives.size(); i++){
				if (input.contains(negatives.get(i))){
					String thistag=negatives.get(i);
					
					int min = input.indexOf(thistag)+15;
					if (min > input.length()) min = input.length();
					String post = input.substring(input.indexOf(thistag),min);

					if (post.contains("KNOW")||post.contains("SURE")||post.contains("CERTAIN")||post.contains("DECIDE")){
						continue;
					}
					else {								
						String first = input.substring(0,input.indexOf(thistag));
						String last = input.substring(input.indexOf(thistag)+thistag.length());
						input = first + last;
						value = !value;
						found = true;
					}
				}
			}
			if (!found){ searching = false;}
		}
		System.out.println(tag + " was found in a " + value + " way." + " remaining string: " + input);
	
		if(value){
			return "+" + input;
		}
		else {
			return "-" + input;
		}
		

	}
	


	private InputType parseLevel1(String input, Story story) {
		//System.out.println("parse Level 1");
		input = input.toUpperCase();
		
		if (story.context == ContextType.BORING_AUNT){
			//System.out.println("It's this one.");
			InputType in = parseStory(input,story);
			if(in == InputType.CORRECT){
				// has chosen to continue to watch
				store.boring_aunt(1,story.next);
				
				//System.out.println("first tag would be now: " + story.next.tags[0][0]);
				return InputType.CONTINUE;
				
			}
			if(in == InputType.INCORRECT){
				// has chosen to quit the boring movie
				store.boring_aunt(2,story.next);
				//System.out.println("first tag would be now: " + story.next.tags[0][0]);
				return InputType.CONTINUE;
				
			}
			if(in == InputType.NO_IDEA){
				
				return InputType.NO_IDEA;
			}
			
			
		}
		
		
		else {
		
			//System.out.println("What story is this? o.O");
			
		}
		
		//System.out.println("Parse Level 1 returns No Idea");

		return InputType.NO_IDEA;
	}

	

	private Double getDouble(String numbername, String input) {
		String end = "";
		String addendum = "";
		if (input.length() > input.indexOf(numbername)+numbername.length()+4){
			end = input.substring(input.indexOf(numbername)+numbername.length()+1, input.indexOf(numbername)+numbername.length()+4);
			//System.out.println("END: "+ end);
			if (end.equals("ONE") || end.equals("TWO") || end.equals("SIX")){
				//System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (input.length() > input.indexOf(numbername)+numbername.length()+5 && addendum.equals("")){
			end = input.substring(input.indexOf(numbername)+numbername.length()+1, input.indexOf(numbername)+numbername.length()+5);
			//System.out.println("END:\""+ end +"\"");
			if (end.equals("-ONE") || end.equals(" ONE") || end.equals("-TWO") || end.equals(" TWO") || end.equals("-SIX" )|| end.equals(" SIX") ||
				end.equals("FOUR") || end.equals("FIVE") || end.equals("NINE")){
				//System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (input.length() > input.indexOf(numbername)+numbername.length()+6 && addendum.equals("")){
			end = input.substring(input.indexOf(numbername)+numbername.length()+1, input.indexOf(numbername)+numbername.length()+6);
			//System.out.println("END: "+ end);
			if (end.equals("-FOUR") || end.equals(" FOUR") || end.equals("-FIVE") || end.equals(" FIVE") || end.equals("-NINE") || end.equals(" NINE") ||
				end.equals("EIGHT") || end.equals("THREE") || end.equals("SEVEN")){
				//System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (input.length() > input.indexOf(numbername)+numbername.length()+7 && addendum.equals("")){
			end = input.substring(input.indexOf(numbername)+numbername.length()+1, input.indexOf(numbername)+numbername.length()+7);
			//System.out.println("END: "+ end);
			if ((end.equals("-EIGHT" ))||( end.equals(" EIGHT")) || (end.equals("-THREE" ))|| (end.equals(" THREE" ))|| (end.equals("-SEVEN" ))|| (end.equals(" SEVEN"))){
				//System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}
		
		
			Double n = numbernames.get(numbername.toLowerCase());
			
			if(addendum.contains("ONE")){ n = n+1; }
			if(addendum.contains("TWO")){ n = n+2; }
			if(addendum.contains("THREE")){ n = n+3; }
			if(addendum.contains("FOUR")){ n = n+4; }
			if(addendum.contains("FIVE")){ n = n+5; }
			if(addendum.contains("SIX")){ n = n+6; }
			if(addendum.contains("SEVEN")){ n = n+7; }
			if(addendum.contains("EIGHT")){ n = n+8; }
			if(addendum.contains("NINE")){ n = n+9; }					
		    // System.out.println("Found number " + n);

		     numbername = numbername.toUpperCase();
		     
		     if (input.indexOf(numbername) > 1){ 
			     if (findLower(input.substring(0,input.indexOf(numbername)))){
			    	 n = n*0.8;
			     }
			     
			     if (findUpper(input.substring(0,input.indexOf(numbername)))){
			    	 n = n*1.2;
			     }
		     }
		     return n;
		
	}



	private InputType parseQuantitative(String input, Story story) {

		input = input.toUpperCase();
		input = input.substring(1,input.length()-1);
		
		String number = extractNumerical(input);
	    double n = 0;

		double values[] = new double[3];
		Vector<Double> guesses = new Vector<Double>(5,1);
		
		if (!number.isEmpty()){
			n = Double.parseDouble(number);
			guesses.add(n);
		}
		else {
			values = extractNumbername(input,guesses);				
			if(values[1] != values[2]){
				n = values[0];
				guesses.add(n);
			}
		}
		
		if(guesses.isEmpty()){
			return InputType.NO_IDEA;
		}
		else {
			

			String[][] TagArray = new String[1][2];			
			
			if(story.context == ContextType.OUTCOME1A || story.context == ContextType.OUTCOME2A || story.context == ContextType.OUTCOME3A){

				TagArray[0][0] = Double.toString(n);
				TagArray[0][1] = "CORRECT";
				story.next.tags = TagArray;
				
				return InputType.CONTINUE;
			}
			if(story.context == ContextType.OUTCOME1B || story.context == ContextType.OUTCOME2B || story.context == ContextType.OUTCOME3B){
				if (story.tags[0][1].equals("CORRECT")){
					double e = Double.parseDouble(story.tags[0][0]);
					System.out.println(e);
					System.out.println(n);
					
					double v = e-n;
					System.out.println(v);
					if ((v > -2) && (v < 2)){
						return InputType.CORRECT;
					}
					else {
						return InputType.INCORRECT;
					}
					
				}
				System.out.println("Correct value was not stored.");
				return InputType.NO_IDEA;
			}
			
			
			
			
			
		}
		
		return InputType.NO_IDEA;
	}


	
	
	private InputType parseStatistic(String input, Story story) {
		keyword = -1;
		//System.out.println("parse Statistic");
		input = input.toUpperCase();
		input = input.substring(1,input.length()-1);
		String part = input;
		double prob = 0;
		
		//get the correct probability
		try {
		     double n = Double.parseDouble(story.tags[0][0]);
		     System.out.println("correct: " + n);
		     if(story.tags[0][1].equals("CORRECT")){
		    	prob = n; 
		     }
		     if(story.tags[0][1].equals("INCORRECT")){
		    	prob = 100-n; 
		     }
		} catch (NumberFormatException e) {
			System.out.println("Oops, something went wrong: the first tag of the story is not an integer though it is "
					+ "attemped to be parsed with parseStatistics!");
			  return InputType.NO_IDEA;
		}

		//System.out.println("correct probability: " + prob);

		
		Vector<Double> guesses = new Vector<Double>(5,1);
		guesses = lookForStatistics(part);
		
		if(!guesses.isEmpty()){
			System.out.println("an answer is there");
		}
		
 		boolean nonumbers = false;
		if(guesses.isEmpty()) nonumbers = true;
		
		if(nonumbers){
			guesses = lookForKeywords(part);
			if(input.toUpperCase().contains("EQUAL") || input.toUpperCase().contains("RANDOM") || input.toUpperCase().contains("SAME")){
				guesses.add((double) 50);
			}
			//System.out.println("Guesses: " + guesses.size());	
			if(guesses.isEmpty()){
				return InputType.NO_IDEA;
			}
			if(story.context == ContextType.BOARD_GAME ||
					story.context == ContextType.GLOBOMA || 
					story.context == ContextType.BREAST_CANCER || 
					story.context == ContextType.CAB || 
					story.context == ContextType.POLICE_DOG || 
					story.context == ContextType.STORM ||
					story.context == ContextType.SLOT_GAMBLER  ){
				if (!guesses.isEmpty())	keyword = guesses.get(0);
				return InputType.ASK_FOR_PERCENT;
			}
			
		}
		
		if (guesses.size() > 1 ){
			return InputType.NO_IDEA;
		}


		
		
		// AND NOW Check for the plausibility of the guess
		
		
		
		if (guesses.size() == 1){
			System.out.println("guess: " + guesses.get(0));
//			System.out.println("probability: " + prob);

			double deviation = 1 - (guesses.get(0) / prob);
//			System.out.println("Deviation " + deviation);
			System.out.print("Relative Differenz: " + Math.abs(deviation) +" | ");
			System.out.print("Absolute Differenz: " + Math.abs(guesses.get(0) - prob));
			if ( (Math.abs(deviation) < 0.7) &&  (Math.abs(guesses.get(0) - prob) < 15)){
//				System.out.println(" - good enough!");
				return InputType.CORRECT;
			}
			else {
//				System.out.println(" - meh...");
				return InputType.INCORRECT;
			}
			
		}
		
		
		
		
		return InputType.NO_IDEA;
	}
	

	private Vector<Double> lookForKeywords(String part) {
		// IF we didn't find any number yet - look for keywords
		Vector<Double> guesses = new Vector<Double>(5,1);
		
			System.out.println("Look for keywords!");
			String estimate = extractEstimate(part);
	
			if (estimate != ""){
				double n = (double) 0;
				if (estimates.containsKey(estimate)){
					n = estimates.get(estimate);
				}
				if (estimates2.containsKey(estimate)){
					n = estimates2.get(estimate);
				}
				if (estimates3.containsKey(estimate)){
					n = estimates3.get(estimate);
				}
			    guesses.add(n);
			    System.out.println("Found estimate of around " + n + "%");
			    part = part.substring(part.indexOf(estimate)+estimate.length());
			}

			return guesses;
	}


	private Vector<Double> lookForStatistics(String part) {
				//find all the numbers in the input
			Vector<Double> guesses = new Vector<Double>(5,1);
			Vector<Double> relative = new Vector<Double>(2,1);
			Vector<Double> variable = new Vector<Double>(2,1);
			
			boolean numberfound = false;
			boolean nothing = false;
			
			while(part.length() > 1 && !nothing){
				nothing = true;
				String suffix = "";
				String number = extractNumerical(part);
				double values[] = new double[3];
				double n = 0;
				
				if(!number.isEmpty()){
					suffix = part.substring(part.indexOf(number)+number.length());
					numberfound = true;
					n = Double.parseDouble(number);
				}
				else {			
					values = extractNumbername(part,guesses);	
					if(values[1] != values[2]){
						int pos = (int) values[2];
						if (pos == part.length()) suffix = "";
						else suffix = part.substring(pos);
						numberfound = true;
						n = values[0];
						System.out.println(n);
					}
					
					else {
						System.out.println("no number found");
						//no numbers found;
					}
				}
				if (numberfound){
					
					if (variable.size() > 0){
						//System.out.println("oh, it's a mix");
						n = (n + variable.firstElement())/2;
						variable.clear();
					}
					
					if (relative.size() > 0){
						relative.add(n);
						nothing = false;
						part = "";
						
					}
					if (suffix.contains("TO") || suffix.contains("-")){
						//System.out.println("-- to --");
						variable.clear();
						variable.add(n);
						part = suffix;
						nothing = false;
					}
					else if (suffix.contains("%") || suffix.contains("PERCENT")){
						//System.out.println("we have a %");
						//System.out.println("contains %");
						     
						     if (findLower(part.substring(0,part.indexOf(number)))){
						    	 //System.out.println("Lowering found.");
						    	 n = n*0.8;
						     }
						     
						     if (findUpper(part.substring(0,part.indexOf(number)))){
						    	 //System.out.println("Rising found.");
						    	 n = n*1.2;
						     }
						     
						     //System.out.println(n + " added.");
						     guesses.add(n);
						     part = "";
						     nothing = false;
		
					}
					else if (suffix.contains("OUT OF") || suffix.contains("IN") || suffix.contains("/") ){
						//System.out.println("-- out of --");
						relative.clear();
						relative.add(n);
						part = suffix;
						nothing = false;
					}
					else {
						//numbers alone are useless
					}
				}
			}
		
			if (relative.size() == 2){
				numberfound = true;
				double n = (relative.get(0) / relative.get(1)) * 100;
				System.out.println("it's a .... : " + n);
				guesses.add(n);
				
			}
		
			

		
		return guesses;
	}


	public static String extractNumerical(String str) {                
		//http://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
	    if(str == null || str.isEmpty()) return "";
	    StringBuilder sb = new StringBuilder();
	    boolean found = false;
	    boolean point = false;
	    for(char c : str.toCharArray()){
	        if(Character.isDigit(c)){
	            sb.append(c);
	            found = true;
	        }
	        else if((c == '.') && found && !point){
	            sb.append(c);
	            point = true;
	        }
	        else if(found){
	            // If we already found a digit before and this char is not a digit, stop looping
	            break;                
	        }
	    } 
	    if (found){
	    	if(sb.toString().equals(".")){
	    		return "";
	    	}
	    	else {
	    		//System.out.println("found numerical: " + sb.toString());
		    	return sb.toString();
	    	}
	    }
	    else return "";
	    
	}
	private String extractEstimate(String part) {
		
		for (String key : estimates.keySet()) {
		    //System.out.println("Key = " + key + " - " + estimates.get(key));
		    if (part.contains(key)){
		    	return key;
		    }
		}
		for (String key : estimates2.keySet()) {
		    //System.out.println("Key = " + key + " - " + estimates2.get(key));
		    if (part.contains(key)){
		    	return key;
		    }
		}
		for (String key : estimates3.keySet()) {
		   // System.out.println("Key = " + key + " - " + estimates3.get(key));
		    if (part.contains(key)){
		    	return key;
		    }
		}
		return "";
	}


	private double[] extractNumbername(String part, Vector<Double> guesses) {
		//System.out.println("Extract Numbername...");
		//System.out.println("Trying " + part);
		part = part.toUpperCase();
		String numbername = "";
		
		for (int i = 1; i < part.length(); i++){
			String p = part.substring(0, i);
			//System.out.println("part " + p);
			for (String key : numbernames.keySet()) {
				key = key.toUpperCase();
			    //System.out.println("Key = " + key + " - " + numbernames.get(key));
			    if (p.contains(key)){
			    	System.out.println("part contains " + key);
			    	numbername = part.substring(part.indexOf(key), part.indexOf(key)+key.length());
			    	i = part.length();
			    	break;
			    }
			}
		}
		if (numbername.length() == 0){
			double nothing [] = {0,0,0};
			return nothing;
		}

		//System.out.println("numbername: " + numbername);
	//	System.out.println("lenght: " + numbername.length());
	//	System.out.println("Index: "+ part.indexOf(numbername));
		String suffix = part.substring(part.indexOf(numbername)+numbername.length()+1);
		String end = "";
		String addendum = "";
		if (part.length() > part.indexOf(numbername)+numbername.length()+3){
			end = part.substring(part.indexOf(numbername)+numbername.length(), part.indexOf(numbername)+numbername.length()+3);
			//System.out.println("END: "+ end);
			if (end.equals("ONE") || end.equals("TWO") || end.equals("SIX")){
				suffix = part.substring(part.indexOf(numbername)+numbername.length()+4);
		//		System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (part.length() > part.indexOf(numbername)+numbername.length()+4 && addendum.equals("")){
			end = part.substring(part.indexOf(numbername)+numbername.length(), part.indexOf(numbername)+numbername.length()+4);
			//System.out.println("END: "+ end);
			if (end.equals("-ONE") || end.equals(" ONE") || end.equals("-TWO") || end.equals(" TWO") || end.equals("-SIX" )|| end.equals(" SIX") ||
				end.equals("FOUR") || end.equals("FIVE") || end.equals("NINE")){
				suffix = part.substring(part.indexOf(numbername)+numbername.length()+5);
			//	System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (part.length() > part.indexOf(numbername)+numbername.length()+5 && addendum.equals("")){
			end = part.substring(part.indexOf(numbername)+numbername.length(), part.indexOf(numbername)+numbername.length()+5);
			//System.out.println("END: "+ end);
			if (end.equals("-FOUR") || end.equals(" FOUR") || end.equals("-FIVE") || end.equals(" FIVE") || end.equals("-NINE") || end.equals(" NINE") ||
				end.equals("EIGHT") || end.equals("THREE") || end.equals("SEVEN")){
				suffix = part.substring(part.indexOf(numbername)+numbername.length()+6);
			//	System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}

		if (part.length() > part.indexOf(numbername)+numbername.length()+6 && addendum.equals("")){
			end = part.substring(part.indexOf(numbername)+numbername.length(), part.indexOf(numbername)+numbername.length()+6);
			//System.out.println("END: "+ end);
			if ((end.equals("-EIGHT" ))||( end.equals(" EIGHT")) || (end.equals("-THREE" ))|| (end.equals(" THREE" ))|| (end.equals("-SEVEN" ))|| (end.equals(" SEVEN"))){
				suffix = part.substring(part.indexOf(numbername)+numbername.length()+7);
			//	System.out.println("suffix = " + suffix);
				addendum = end;
			}
		}
		
		
		
		
		
			Double n = numbernames.get(numbername);
			
			if(addendum.contains("ONE")){ n = n+1; }
			if(addendum.contains("TWO")){ n = n+2; }
			if(addendum.contains("THREE")){ n = n+3; }
			if(addendum.contains("FOUR")){ n = n+4; }
			if(addendum.contains("FIVE")){ n = n+5; }
			if(addendum.contains("SIX")){ n = n+6; }
			if(addendum.contains("SEVEN")){ n = n+7; }
			if(addendum.contains("EIGHT")){ n = n+8; }
			if(addendum.contains("NINE")){ n = n+9; }

			
		   //  System.out.println("Found number " + n);
		    System.out.println(part.substring(0,part.indexOf(numbername)));
		     
		     if (findLower(part.substring(0,part.indexOf(numbername)))){
		    	 //System.out.println("Lowering found.");
		    	 n = n*0.8;
		     }
		     
		     if (findUpper(part.substring(0,part.indexOf(numbername)))){
		    	 //System.out.println("Rising found.");
		    	 n = n*1.2;
		     }
		
		int s = part.indexOf(suffix)-1;
		if (s == -1) s = part.length() -1;
		
		
/*
		System.out.println("suffix: "+ suffix);
		System.out.println("Found " + n);
		System.out.println("Begins at " + part.indexOf(numbername));
		System.out.println("Ends at " + s);
	*/	
		double result[] = {n,part.indexOf(numbername),s};
		return result;
	}

	private InputType parseStory(String input, Story story){
		
	//	System.out.println("parse Story...");
		input = input.toUpperCase();
		/*

		for (int w = 0; w < input.length()+1; w++){
			if(found){
				start = w-1;
				part = input.substring(start,w);
				found = false;
			}
			else {
				part = input.substring(start,w);
			}*/
		
		
		String part = input;
		
		if(part.contains("BECAUSE")){
			part = part.substring(0,part.indexOf("BECAUSE"));
		}
		if(part.contains("EVEN THOUGH")){
			part = part.substring(0,part.indexOf("EVEN THOUGH"));
		}
		if(part.contains("ALTHOUGH")){
			part = part.substring(0,part.indexOf("ALTHOUGH"));
		}
		if(part.contains("DESPITE")){
			part = part.substring(0,part.indexOf("DESPITE"));
		}
		if(part.contains("REGARDLESS")){
			part = part.substring(0,part.indexOf("REGARDLESS"));
		}
		if(part.contains("SINCE")){
			part = part.substring(0,part.indexOf("SINCE"));
		}
		if(part.contains("DUE TO")){
			part = part.substring(0,part.indexOf("DUE TO"));
		}
		if(part.startsWith("~NO")){
			part = part.substring(2,part.length());
		}
		
		//System.out.println("Part is now: " + part);
		
		/*if (!(understand(part,story) == InputType.NO_IDEA))
			return understand(part,story);
		*/
		return understand(input,story);
}
		

	

	private InputType understand(String input, Story story) {
		
		boolean no = false, yes = false;		
		input = input.toUpperCase();
		//System.out.println("trying to understand...");
		if (input.contains("NO, ")){
			no = true;
			String before = input.substring(0, input.indexOf("NO, "));
			String after = input.substring(input.indexOf("NO, ")+4, input.length());
			input = before + after;
		}
		if (input.contains("Yes, ")){
			yes = true;
			String before = input.substring(0, input.indexOf("YES, "));
			String after = input.substring(input.indexOf("YES, ")+5, input.length());
			input = before + after;
		}
		int correct = 0;
		int incorrect = 0;
		int found = 0;
		String part = "";
		boolean sinnvoll = true;
		
		sortbylength(story.tags);
		part = input;
		String remain = input;
		
		while(sinnvoll){

			//System.out.println(remain  + "\n----------");

			for (int n = 0; n < story.tags.length; n++){
				//System.out.println("check for " + story.tags[n][0] + " in " + part);
				

				if (part.contains(story.tags[n][0])){
					found++;
					System.out.println("found tag " + story.tags[n][0]);
					String finding = findTag(part,story.tags[n][0]);
					if (finding.startsWith("+")){ //if the tag is mentioned positive
						if (story.tags[n][1].equals("CORRECT")){ // if it is a correct tag
							correct++;
						}
						else {  // if it is an incorrect tag
							incorrect++;
						}
					}
					else { //if the tag is mentioned negative
						if (story.tags[n][1].equals("CORRECT")){
							incorrect++; // if it is a correct tag
						}
						else {// if it is an incorrect tag
							correct++; 
						}
					}	

					if(found == 1){
						remain = part.substring(part.indexOf(story.tags[n][0]) + story.tags[n][0].length(), part.length());
						//System.out.println("remaining is " + remain);
					}
					
					part = finding.substring(1, finding.length());
				}
			}
		
			

			if (found == 0){
				sinnvoll = false;
			}
			else {
				//System.out.println("Then now go on with " + remain);
				sinnvoll = true;
				part = remain;
				found = 0;
				
			}
		}

		//System.out.println("corrects: " + correct);
		//System.out.println("incorrects: " + incorrect);
	
  if (correct > 0 && incorrect == 0){
	//System.out.println("(Eliezer thinks you said cleaning)");
	return InputType.CORRECT;
  }
  if (incorrect > 0 && correct == 0){
	//System.out.println("(Eliezer thinks you said library)");
	return InputType.INCORRECT;
	}

  if(no) {
	  return InputType.INCORRECT;
  }
  if(yes) {
	  return InputType.CORRECT;
  }
  return InputType.NO_IDEA;
	
	
}


	private void sortbylength(String[][] tags) {

		String[][] temp = new String[tags.length][tags[0].length];
		
		
		int max; int n;
		
		for (int k = 0; k < temp.length; k++){
			max = 0;
			n = 0;
			for (int i=0; i < tags.length; i++){
				if (tags[i][0] != null){
					//System.out.println(tags[i][0] + " has length " + tags[i][0].length());
					if (tags[i][0].length() >max){
						max = tags[i][0].length();
						n = i;
					}	
				}
			}
			//System.out.println("Longest was: " + tags[n][0] + " with max " + max);
			if (max > 0){
			//	System.out.println("gets to "+ k);
				temp[k][0] = tags[n][0];
				temp[k][1] = tags[n][1];
				tags[n][0] = "";
				tags[n][1] = "";
			}
		}
		
		for (int j = 0; j < temp.length; j++){
			tags[j][0] = temp[j][0];
			tags[j][1] = temp[j][1];
		}
		
		return;
	}


	//parse the input given by the user and return all found tags
	public Vector<InputType> parseInput(String input, ContextType CONTEXT, Story story){
		input = input.toUpperCase();
		Vector<InputType> specials =new Vector<InputType>(0,1);
		Vector<InputType> generals =new Vector<InputType>(0,1);
		if (input.length() == 0){
			specials.add(InputType.NO_INPUT);
			System.out.println("No input.");
			return specials;
		}
		
		else if (input.startsWith("~") && CONTEXT != null){ //to discriminate it from failed or broken input
			generals = parseGeneral(input);
			switch (CONTEXT) {
		    case ONE:      specials.add(parseStory(input,story)); break;
	        case EXPLANATION:  specials.add(ParseExplanation(input));  break;
		    case GREETING:  specials.add(ParseGreeting(input));break;
		    case AGREEMENT:  specials.add(ParseAgreement(input,true));break;
		    case DISAGREEMENT:  specials.add(ParseAgreement(input,false));break;
		    case NUMBER:  specials.add(ParseNumber(input,false));break;
		    case RIGHT:      specials.add(parseRight(input,story)); break;
		    case WRONG:      specials.add(parseWrong(input,story)); break;
		    case GRUMP:      specials.add(parseStory(input,story)); break;
		    case CLARIFICATION: specials.add(parseClarification(input)); break;
		    case CAPTCHA: specials.add(parseCaptcha(input)); break;
		    case HUMAN_HELP: specials.add(parseHumanHelp(input)); break;
		    case WANT_RATIONAL: specials.add(parseWantRational(input)); break;
		    case GUT:			specials.add(parseGut(input)); break;
		    case HUMAN:			specials.add(parseHuman(input)); break;
		    case NAME:			specials.add(ParseName(input)); break;
		    case UNSURE:		specials.add(parseUnsure(input)); break;
		    case SPEED:		specials.add(parseSpeed(input)); break;
		    case LIKE:		//do nothing, just parse General // TODO 
		    				break;
		    case TEST:		specials.add(parseTest(input)); break;
		    
		    case RESTAURANTS:		specials.add(parseStory(input,story)); break;
		    case BASEBALL:			specials.add(parseStory(input,story)); break; 
		    case IVY_LEAGUE:		specials.add(parseStory(input,story)); break; 
		    case BALLET:			specials.add(parseStory(input,story)); break; 
		    case BAD_STUNTS:		specials.add(parseStory(input,story)); break;
		    case CARS:				specials.add(parseStory(input,story)); break;
		    case TEACHING_METHOD:	specials.add(parseStory(input,story)); break;
		    case BEST_OF_FIVE:		specials.add(parseStory(input,story)); break;
		    case HOSPITALS:			specials.add(parseStory(input,story)); break;
		    case WORD_COUNT: 		specials.add(parseStory(input,story)); break;
		    case MIXED:				specials.add(parseStory(input,story)); break;
		    case SLOTTING: 			specials.add(parseStory(input,story)); break;
		    case UNIVERSITIES:		specials.add(parseStory(input,story)); break;		
		    case PENNIES:		specials.add(parseStory(input,story)); break;		
		    case IMPERFECTIONS:		specials.add(parseStory(input,story)); break;
		    
		    case GLOBOMA:			specials.add(parseStatistic(input,story)); break;
		    case BREAST_CANCER: 	specials.add(parseStatistic(input,story)); break;
		    case STORM:				specials.add(parseStatistic(input,story)); break;
		    case POLICE_DOG:		specials.add(parseStatistic(input,story)); break;
		    case CAB:				specials.add(parseStatistic(input,story)); break;
		    
		    case COIN_TOSS:			specials.add(parseStory(input,story)); break;
		    case SLOT_GAMBLER:		specials.add(parseStatistic(input,story)); break;
		    case COIN_GAME:			specials.add(parseStory(input,story)); break;
		    case LOTTERY:			specials.add(parseStory(input,story)); break;
		    case ROULETTE:			specials.add(parseStory(input,story)); break;
		    case SUBWAY:			specials.add(parseStatistic(input,story)); break;
		    case BOARD_GAME:			specials.add(parseStatistic(input,story)); break;
		    case RAVENCLAW:			specials.add(parseStatistic(input,story)); break;
		    case GREEN_OUTFITS:			specials.add(parseStatistic(input,story)); break;
		    
		    case BORING_AUNT:			specials.add(parseLevel1(input,story));break;
		    case BORING_CINEMA:			specials.add(parseStory(input,story)); break;
		    case PAY_TV:			specials.add(parseStory(input,story)); break;
		    case THEATRE_TICKET:		specials.add(parseStory(input,story)); break;
		    case SPORTS_TICKET:		specials.add(parseStory(input,story)); break;
		    case POPCORN:		specials.add(parseStory(input,story)); break;
		    case UNIVERSITY_RENOVATION:			specials.add(parseStory(input,story)); break;
		    case TUNA_SANDWICH:				specials.add(parseStory(input,story)); break;
		    case WARDROBE:				specials.add(parseStory(input,story)); break;
		    case SOFTWARE_PROJECT:					specials.add(parseStory(input,story)); break;
		    
		    
		    case OUTCOME1A:					specials.add(parseQuantitative(input,story)); break;
		    case OUTCOME1B:					specials.add(parseQuantitative(input,story)); break;
		    case OUTCOME2A:					specials.add(parseQuantitative(input,story)); break;
		    case OUTCOME2B:					specials.add(parseQuantitative(input,story)); break;
		    case OUTCOME3A:					specials.add(parseQuantitative(input,story)); break;
		    case OUTCOME3B:					specials.add(parseQuantitative(input,story)); break;
		    

		    case SYLLOG1:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG2:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG3:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG4:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG5:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG6:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG7:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG8:					specials.add(parseSyllog(input,story)); break;
		    case SYLLOG9:					specials.add(parseSyllog(input,story)); break;

		    case DRUG:								specials.add(parseEffective(input,story)); break;
		    case BACK_PAIN:								specials.add(parseEffective(input,story)); break;
		    case GRADES:								specials.add(parseEffective(input,story)); break;
		    case GLASSES:								specials.add(parseEffective(input,story)); break;
		    case PARROTS:								specials.add(parseEffective(input,story)); break;
		    case TOMATO:								specials.add(parseEffective(input,story)); break;

		    case VOWELEVENNUMBER:			specials.add(parseSelection(input,story)); break;
		    case BOARD_GAME_DEVELOPMENT:			specials.add(parseSelection(input,story)); break;
		    case CAT_FLU:						specials.add(parseSelection(input,story)); break;
		    case WEDDING_CARDS:					specials.add(parseSelection(input,story)); break;
		    case TOWELS:						specials.add(parseSelection(input,story)); break;
		    case BIRDS:							specials.add(parseSelection(input,story)); break;
		    
		    case CONTEXT:					specials.add(parseStory(input,story)); break;
		    case NOTHING:				break;
			case INTRO:			specials.add(parseIntro(input,story)); break;
			case GOON:			specials.add(parseGoOn(input)); break;
		        
			default: 			specials.add(InputType.NO_IDEA);		break;
		    }
		}
		else {
			System.out.println("I got input without a starting ~ !" + input);
			specials.add(InputType.INPUT_FAULT);
		}

		Vector<InputType> type = mergeVectors(specials, generals);
		return type;
	}




	private InputType parseGoOn(String input) {
		input = input.toUpperCase();
		
		Vector<InputType> reply = new Vector<InputType>(1,0);
		
		if(
				findTag(input,"GO ON").startsWith("+") ||
				findTag(input,"LET'S").startsWith("+")||
				findTag(input,"TRY").startsWith("+")||
				findTag(input,"SEE").startsWith("+")||
				findTag(input,"ONWARDS").startsWith("+")||
				findTag(input,"GO ON").startsWith("+")||
				findTag(input,"SOLVE").startsWith("+")||
				findTag(input,"HEAR").startsWith("+")||
				findTag(input,"READY").startsWith("+")||
				findTag(input,"QUESTION").startsWith("+")||
				findTag(input,"GO FOR").startsWith("+")||
				findTag(input,"GO TO").startsWith("+")||
				findTag(input,"BRING IT ON").startsWith("+")||
				findTag(input,"GIVE ME THE STORY").startsWith("+")||
				findTag(input,"TELL ME THE STORY").startsWith("+")||
				findTag(input,"GIVE ME THE QUESTION").startsWith("+")||
				findTag(input,"TELL ME THE QUESTION").startsWith("+")||
				findTag(input,"ASK ME THE QUESTION").startsWith("+")||
				findTag(input,"ASK ME THE STORY").startsWith("+")||
				findTag(input,"ASK ME QUESTION").startsWith("+")||
				findTag(input,"ASK ME STORY").startsWith("+")||
				findTag(input,"DIRECTLY").startsWith("+")||
				findTag(input,"ATTEMPT").startsWith("+")||
				findTag(input," TRY").startsWith("+")||
				findTag(input," ASK").startsWith("+")||
				findTag(input," START").startsWith("+")||
				findTag(input," NOW").startsWith("+")){
			return InputType.ANSWER1;
		}
		
		else {
			return InputType.NO_IDEA;
			
		}
			
	}

	private InputType parseTest(String input) {

		if(input.contains("WHAT TEST") || input.contains("WHAT KIND OF") || input.contains("WHAT IS") 
				|| input.contains("WHAT IS") || input.contains("ABOUT") || input.contains("WHY DO THEY")){
			if (input.contains("?")){
				return InputType.ANSWER1;
			}
		}
		
		if(input.contains("PROUD")||input.contains("WHY DO YOU")||input.contains("NOT PROUD")||
				input.contains("AREN'T PROUD")||input.contains("DISAPPOINT")||input.contains("BAD TEACHER")||
				input.contains("GOOD TEACHER")||input.contains("PROUD")||input.contains("YOU WANT"))
			if (input.contains("?")){
				return InputType.ANSWER2;
			}
		
		if(input.contains("NO WORRIES")||findTag(input,"GOOD").startsWith("+")||input.contains("DON'T WORRY")||input.contains("IT'S ALRIGHT")
				||findTag(input,"WELL").startsWith("+")||findTag(input,"FINE").startsWith("+")||findTag(input,"RATION").startsWith("+")
				||findTag(input,"BEST").startsWith("+")||findTag(input,"OK").startsWith("+")||findTag(input,"OKAY").startsWith("+")
				||findTag(input,"IT'S GOING TO BE OK").startsWith("+")||input.contains("RELAX")||input.contains("YOU DON'T HAVE TO")||
				input.contains("TAKE IT EASY")||input.contains("HANG ON")||input.contains("I GOT THIS")||input.contains("CHILL")
				||input.contains("COUN ON")||input.contains("TRUST")||input.contains("WORK OUT")||input.contains("NOTHING TO WORRY"))
				{
			return InputType.CORRECT; 
		}
		

		if(input.contains("I'M BAD")||input.contains("I WON'T")||input.contains("I CAN'T")||input.contains("I CAN NOT")||input.contains("I WILL NOT")
				||findTag(input,"ABLE").startsWith("-")||input.contains("WOULD WORRY")||input.contains("NOT SURE")||input.contains("NOT CONVINCED")
				||findTag(input,"RATIONAL").startsWith("-")||findTag(input,"REASON").startsWith("-")||findTag(input,"CAN DO").startsWith("-")||
				findTag(input,"WILL DO").startsWith("-")){
			return InputType.INCORRECT; 
		}
		
		return InputType.NO_IDEA;
	}


	private InputType parseSpeed(String input) {

		input = input.toUpperCase();
		
		if (input.contains("SLOWER") || input.contains("NOT SO FAST") || input.contains("NOT AS FAST")|| input.contains("SLOW DOWN")){
			return InputType.ANSWER1;
		}
		if (input.contains("FASTER") || input.contains("NOT SO SLOW") || input.contains("NOT AS SLOW")|| input.contains("QUICKER")|| input.contains("SPEED UP")){
			return InputType.ANSWER2;
		}

		if (input.contains("OK") || input.contains("OKAY") || input.contains("GOOD")|| input.contains("KEEP")
				|| input.contains("STAY") || input.contains("DON'T CHANGE") || input.contains("FINE") 
				|| input.contains("ANYTHING") || input.contains("NEITHER") || input.contains("DON'T WANT YOU")
				|| input.contains("ALLRIGHT")){
			return InputType.INCORRECT;
		}


		InputType type = ParseAgreement(input,true);
		if (type == InputType.INCORRECT){
			return InputType.INCORRECT;
		}
		
		return InputType.NO_IDEA;
	}


	private InputType parseUnsure(String input) {
		
		if (input.contains("TURN OUT") || input.contains("WORK OUT") || input.contains("WILL BE") || input.contains("WILL DO")
				|| input.contains("NO WORRIEs")  || input.contains("DON'T WORRY")  || input.contains("DONT WORRY")
				 || input.contains("ALRIGHT") || input.contains("NO PROBLEM")|| input.contains("DON'T BE")
				 || input.contains("YOU CAN")  || input.contains("YOU WILL"))
			return InputType.ANSWER1;
		
		return InputType.NO_IDEA;
	}


	private InputType parseHuman(String input) {
		InputType type = ParseAgreement(input,true);
		input = input.toUpperCase();
		if (type == InputType.CORRECT)  {

			System.out.println("correct");
			return InputType.CORRECT;
		}
		if (type == InputType.INCORRECT){
			System.out.println("incorrect");
			return InputType.INCORRECT;
		}
		
		
		if (input.startsWith("NO")) input = input.substring(2, input.length());
		
		if((input.contains("DOG")||input.contains("CAT")||input.contains("ALIEN")||input.contains("ROBOT"))&& !(input.contains("NOT"))){
			return InputType.ANSWER1;
		}
		if (input.contains("COMPUTER") && !input.contains("NO") && !(input.contains("NOT"))){
			return InputType.ANSWER2;
		}

		
		
		return InputType.NO_IDEA;
	}


	private InputType parseGut(String input) {
		InputType type = ParseAgreement(input,true);
		if (type == InputType.CORRECT) return InputType.INCORRECT;
		if (type == InputType.INCORRECT) return InputType.CORRECT;
		
		if((input.contains("THINK")||input.contains("TOLD ME")||input.contains("REASON")||input.contains("REFLECT")||input.contains("DO NOT")||input.contains("TOLD ME")||input.contains("SAID"))){
			return InputType.CORRECT;
		}
		
		
		return InputType.NO_IDEA;
	}


	private InputType parseWantRational(String input) {
		

		InputType type = ParseAgreement(input,true);
		if (type != InputType.NO_IDEA) return type;
		
		if((input.contains("RATIONAL")||input.contains("REASON")||input.contains("RATIONAL")||input.contains("DECISION"))
		&& (input.contains("BE")||input.contains("IS")||input.contains("SEEM")||input.contains("ARE") )
		&& (input.contains("COOL")||input.contains("AWESOME")||input.contains("NICE")||input.contains("USEFUL")||input.contains("HELPFUL")
				||input.contains("GOOD")||input.contains("GREAT")||input.contains("DOPE")
		&& !(input.contains("NOT")))){
			
			return InputType.CORRECT;
			
		}
		
		
		return InputType.NO_IDEA;
	}


	private InputType parseHumanHelp(String input) {
		
		input = input.toUpperCase();
		
		if( (input.contains("WHAT") || input.contains("HOW") || input.contains("WHICH")||input.contains("HELP")||input.contains("PURPOSE")||input.contains("CAN"))
				&& input.contains("?")){
				return InputType.ANSWER1;
		}
		
		if ( input.contains("DONT WANT") || input.contains("DONT HELP") || input.contains("DON'T WANT") || input.contains("DON'T HELP") || input.contains("HAVE NO") || input.contains("WON'T")){
			return InputType.ANSWER2;
		}
		
		return null;
	}


	private InputType parseCaptcha(String input) {
		
		input = input.toUpperCase();
		
		if(findTag(input,"FIVE").startsWith("+")||findTag(input,"5").startsWith("+"))
			return InputType.CORRECT;

		if(findTag(input,"FIVE").startsWith("-")||findTag(input,"5").startsWith("-"))
			return InputType.INCORRECT;
		
		if ((input.contains("2")&& input.contains("3")) || (input.contains("TWO") && input.contains("THREE"))){
			return InputType.INCORRECT;
			
		}

		return InputType.NO_IDEA;
	}


	private InputType parseClarification(String input) {
		input = input.toUpperCase();
		
		Vector<String> explain = new Vector<String>(10,1);
		Vector<String> skip = new Vector<String>(10,1);
		Vector<String> unsure = new Vector<String>(10,1);
				
		explain.add("DETAIL");
		explain.add("EXPLAIN");
		explain.add("CONTINUE");
		explain.add("EXPLANA");
		explain.add("MORE");
		explain.add("HEAR");
		explain.add("HARD");
		explain.add("EXAMPLE");
		explain.add("INFORMATION");
		
		skip.add("SOLVE");
		skip.add("EASY");
		skip.add("~NOW");
		skip.add("HEAR");
		skip.add("READY");
		skip.add("QUESTION");
		skip.add("GO FOR");
		skip.add("GO ON");
		skip.add("GO TO");
		skip.add("GET IT");
		skip.add("GOT IT");
		skip.add("BRING IT ON");
		skip.add("GIVE ME THE STORY");
		skip.add("GIVE ME THE QUESTION");
		skip.add("TELL ME THE STORY");
		skip.add("TELL ME THE QUESTION");
		skip.add("ASK THE STORY");
		skip.add("ASK THE QUESTION");
		skip.add("ASK ME THE STORY");
		skip.add("ASK ME THE QUESTION");
		skip.add("UNDERSTAND");
		skip.add("UNDERSTOOD");
		skip.add("SKIP");
		skip.add("READY");
		skip.add("JUMP");
		skip.add("DIRECTLY");
		skip.add("TRY");
		skip.add("ATTEMPT");
		skip.add("ASK");
		skip.add(" NOW");

		unsure.add("DON'T KNOW");
		unsure.add("DONT KNOW");
		unsure.add("NOT SURE");
		unsure.add("MAYBE");
		unsure.add("UNSURE");
		
		int exp = 0;
		int skp = 0;
		int uns = 0;
		for(int i = 0; i < explain.size(); i++){
			if(findTag(input,explain.get(i)).startsWith("+")){
					exp++;
					//System.out.println("positive " + explain.get(i));
			}
			if(findTag(input,explain.get(i)).startsWith("-")) {
				skp++;
				//System.out.println("negative " + explain.get(i));
			}
		}
		for(int k = 0; k < skip.size(); k++){
			if(findTag(input,skip.get(k)).startsWith("+")){
				//System.out.println("positive " + skip.get(k));
				skp++;
			}
			if(findTag(input,skip.get(k)).startsWith("-")){
				//System.out.println("negative " + skip.get(k));
				exp++;
			}
		}		
		for(int j = 0; j < unsure.size(); j++){
			if(findTag(input,unsure.get(j)).startsWith("+"))
				uns++;
		}
		/*
		System.out.println("exp " + exp);
		System.out.println("skp " + skp);
		System.out.println("uns " + uns);
		*/
		if(exp > skp && exp > 0){
			return InputType.ANSWER1;
		}

		if(skp > exp && skp > 0){
			return InputType.ANSWER2;
		}	
		
		if (uns > 0){
			return InputType.NO_CLUE;
		}
			
		if(exp == skp && uns > 0){
			return InputType.NO_CLUE;
		}
		
		if (exp == skp && uns == 0){
			return InputType.NO_IDEA;
		}
		
		return InputType.NO_IDEA;
	}


	private InputType parseSelection(String input, Story story) {
		input = input.toUpperCase();
		//System.out.println("INPUT: " + input);
		Vector<String> correct1 = new Vector<String>(2,1);
		Vector<String> correct2 = new Vector<String>(2,1);
		Vector<String> incorrect = new Vector<String>(2,1);
		String correction = "";
		
		if (story.context==ContextType.VOWELEVENNUMBER){
			correct1.add(" E "); correct1.add("~E "); correct1.add(" E~");correct1.add(" E.");correct1.add(" E!");
			correct1.add("~E.");correct1.add("~E!");correct1.add("~E~");correct1.add("\"E\"");correct1.add("'E'");

			correct1.add(" E,");
			correct1.add("~E,");
			
			correct1.add("VOWEL");
			correct2.add("7"); correct2.add("SEVEN"); correct2.add("UNEVEN"); correct2.add("ODD");
			incorrect.add("CONSONANT");
			incorrect.add(" W ");incorrect.add(" W.");incorrect.add(" W!");incorrect.add(" W~");incorrect.add("\"W\"");incorrect.add("'W'");
			incorrect.add("~W ");incorrect.add("~W.");incorrect.add("~W!");incorrect.add("~W~");incorrect.add("~W,");incorrect.add(" W,");
			incorrect.add("TWO");
			incorrect.add("2");	
		}
		if (story.context == ContextType.BOARD_GAME_DEVELOPMENT){
			correct1.add("SHEEP");
			correct2.add("FOUR");correct2.add("4");correct2.add("WRONG AMOUNT");correct2.add("WRONG NUMBER");correct2.add("INCORRECT");
			incorrect.add("COAL"); incorrect.add("GRAIN");	incorrect.add("THREE"); incorrect.add("3");
 		}
		if (story.context == ContextType.CAT_FLU){
			correct1.add("SNOWBALL"); correct1.add("BELLA"); correct1.add("PREGNANT CAT"); correct1.add("PREGNANT DOG");
			correct2.add("MEOW");correct2.add("COCO");correct2.add("NOT VACCINATED");correct2.add("STREE");correct2.add("WASN'T VACCINATED");
			incorrect.add("MAX"); incorrect.add("FLUFFYPAWS");	incorrect.add("TOTO"); incorrect.add("PRINCESS"); incorrect.add("BISQUIT");
 		}

		if (story.context == ContextType.WEDDING_CARDS){
			correct1.add("FLOWER");correct1.add("ROSE");
			correct2.add("NEPHEW");correct2.add("YOUNG");correct2.add("LITTLE");
				correct2.add("BOY");correct2.add("CHILD");correct2.add("TEN");
			incorrect.add("AUNT"); incorrect.add("LADY");incorrect.add("BALLOON");
			 incorrect.add("OLD"); incorrect.add("CONFETTI");
 		}

		if (story.context == ContextType.TOWELS){
			correct1.add("303");correct1.add("GROUND");correct1.add("FLOOR");
			correct2.add("305");correct2.add("NOT CHANGED");correct2.add("DIRTY");
				correct2.add("OLD");correct2.add("UNCHANGED");correct2.add("USED");
			incorrect.add("301"); incorrect.add("302");incorrect.add("304");
 		}


		if (story.context == ContextType.BIRDS){
			correct1.add("1");correct1.add("ONE");correct1.add("SPOT");correct1.add("WHITE");
			correct2.add("4");correct2.add("FOUR");correct2.add("UP");correct2.add("TREE");correct2.add("HIGH");
			correct2.add("BRANCHES");
			incorrect.add("3"); incorrect.add("2");incorrect.add("TWO");incorrect.add("THREE");
			
 		}		
		

		
		
		boolean first = false, second = false, wrong = false, all = false;
		//System.out.println("What");
		
		for (int i = 0; i < correct1.size(); i++){
			//System.out.println("look for " + correct1.get(i));
			if (input.contains(correct1.get(i))){
				if (findTag(input,correct1.get(i)).startsWith("+")){	
					//System.out.println("first true");
					first = true;
				}
				else if (findTag(input,correct1.get(i)).startsWith("-")){
					//System.out.println("first wrong");
					wrong = true;
				}
				else {
					//System.out.println("first not");
					first = false;
				}
			}
		}
		
		for (int i = 0; i < correct2.size(); i++){
			if (input.contains(correct2.get(i))){
				if (findTag(input,correct2.get(i)).startsWith("+")){	
					System.out.println("second true");			
					second = true;
				}
				else if (findTag(input,correct2.get(i)).startsWith("-")){
					System.out.println("second wrong");
					wrong = true;
				}
				else {
					System.out.println("second not");
					second = false;
				}
			}
		}
		for (int i = 0; i < incorrect.size(); i++){
			if (input.contains(incorrect.get(i))){
				if (findTag(input,incorrect.get(i)).startsWith("+")){				
					wrong = true;
				}
			}
		}
		
		if(findTag(input,"ALL THE").startsWith("+")|| findTag(input,"ALL OF").startsWith("+")
				|| findTag(input,"THEM ALL").startsWith("+") ||
		findTag(input,"EVERY").startsWith("+") || findTag(input,"EACH").startsWith("+")){
			if (!(input.contains("THAT") || input.contains("WHO"))){
				all = true;
			}
		}
		

	
		if(first && second){
			correction = "Exactly.";
		}
		
		else if((first || second) && !all && !(first && second)){
			correction = "Well, you're close!";
		}
		else {
			correction = story.wrong;
		}
		
		if (first && !all){
			if (story.context==ContextType.VOWELEVENNUMBER) correction = correction + " You are right, you have to check the card with the E.";
			if (story.context==ContextType.BOARD_GAME_DEVELOPMENT) correction = correction + " You are right, you have to check the marker with the sheep.";
			if (story.context==ContextType.CAT_FLU) correction = correction + " Of course, the pregnant mother has to be checked.";
			if (story.context==ContextType.WEDDING_CARDS) correction = correction + " Your choice of the cards with the rose was correct - this one has to be checked.";
			if (story.context==ContextType.TOWELS) correction = correction + " You are right, you have to check room 303, where the towels are on the floor.";
			if (story.context==ContextType.BIRDS) correction = correction + " Yes, you have to check the bird 01 with the spots to see if it lives on the ground.";

		
		}
		else {
			if (story.context==ContextType.VOWELEVENNUMBER) correction = correction + " You absolutely have to check the card with the E.";
			if (story.context==ContextType.BOARD_GAME_DEVELOPMENT) correction = correction + " You absolutely have to check the marker with the sheep.";
			if (story.context==ContextType.CAT_FLU) correction = correction + " The pregnant mother definitely has to be checked.";
			if (story.context==ContextType.WEDDING_CARDS) correction = correction + " The cards with the roses has to be checked.";
			if (story.context==ContextType.TOWELS) correction = correction + " You absolutely have to check room 303, where the towels are on the floor.";
			if (story.context==ContextType.BIRDS) correction = correction + " You definitely have to check the bird 01 with the spots to see if it lives on the ground.";			
		}
		if (second && !all) {
			if (story.context==ContextType.VOWELEVENNUMBER)	correction = correction + " And also the card with the 7, that's true!";	
			if (story.context==ContextType.BOARD_GAME_DEVELOPMENT) correction = correction + " You also have to check the marker with the four coins, that's correct.";
			if (story.context==ContextType.CAT_FLU) correction = correction + " You definitely should also check the animal that was not vaccinated and could be pregnant, too.";
			if (story.context==ContextType.WEDDING_CARDS) correction = correction + " And yes, you should also check the card of the young boy.";
			if (story.context==ContextType.TOWELS) correction = correction + " And yes, you should also have a look at room 305.";
			if (story.context==ContextType.BIRDS) correction = correction + " And of course, you have to check the bird 04 that lives in a tree.";			
		}
		else {
			if (story.context==ContextType.VOWELEVENNUMBER)	correction = correction + " But also the card with the seven!";		
			if (story.context==ContextType.BOARD_GAME_DEVELOPMENT) correction = correction + " Actually, you also have to check the marker with the four coins.";
			if (story.context==ContextType.CAT_FLU) correction = correction + " And you definitely should also check the animal that was not vaccinated and could be pregnant, too.";
			if (story.context==ContextType.WEDDING_CARDS) correction = correction + " Furthermore, you should also check the card of the young boy.";
			if (story.context==ContextType.TOWELS) correction = correction + " You should also have a look at room 305.";
			if (story.context==ContextType.BIRDS) correction = correction + " You have to check the bird 04 that lives in a tree, too.";

				
		}

		if (wrong) {
			if (story.context==ContextType.VOWELEVENNUMBER)	correction = correction + " Hower, the card with the consonant and the one with the even number are not relevant.";				
			if (story.context==ContextType.BOARD_GAME_DEVELOPMENT) correction = correction + " The other three markers are not relevant to the question.";
			if (story.context==ContextType.CAT_FLU) correction = correction + " The others don't have to be checked.";
			if (story.context==ContextType.WEDDING_CARDS) correction = correction + " The others don't have to be checked.";
			if (story.context==ContextType.TOWELS) correction = correction + " The other rooms don't need to be checked.";
			if (story.context==ContextType.BIRDS) correction = correction + " The other birds are not relevant.";

		
		}
		
		System.out.println("correction is: " + correction);
		store.setCorrection(story.context, correction);
		
		if(first && second && !wrong && !all){
			return InputType.CORRECT;
		}
		else if(!first && !second && !wrong && !all){
			return InputType.NO_IDEA;
		}
		else {
			return InputType.INCORRECT;
		}
		

		
		
	}


	private InputType parseEffective(String input, Story story) {
		boolean validity = false;
		input = input.toUpperCase();
		
		if(story.tags[0][0].toUpperCase().equals("EFFECTIVE")){
			//System.out.println("validity is true");
			validity = true;
		}
		if(story.tags[0][0].toUpperCase().equals("INEFFECTIVE")){
			//System.out.println("validity is false");
			validity = false;
		}
		int length = 63 + story.tags.length;
		String[][] NewArray = new String[length][2];			

		
		NewArray[0][0] = "YES";
		NewArray[1][0] = "IT DOES";
		NewArray[2][0] = "IT IS";
		NewArray[3][0] = "EFFECT";
		NewArray[4][0] = "POWERFUL";
		NewArray[5][0] = "SUFFICIENT";
		NewArray[6][0] = "POTENT";
		NewArray[7][0] = "EFFECTIVE";
		NewArray[8][0] = "ADEQUATE";
		NewArray[9][0] = "HELP";
		NewArray[10][0] = "WORK";
		NewArray[11][0] = "BETTER";
		NewArray[12][0] = "YEAH";
		NewArray[13][0] = "I GUESS";
		NewArray[14][0] = "THINK SO";
		NewArray[15][0] = "SURE";
		NewArray[16][0] = "DEFINITELY";
		NewArray[16][0] = "EXACT";
		NewArray[17][0] = "OF COURSE";
		NewArray[18][0] = "OBVIOUSLY";
		NewArray[19][0] = "INDEED";
		NewArray[20][0] = "PROBABLY";
		NewArray[21][0] = "USEFUL";
		NewArray[22][0] = "DIFFERENCE";
		NewArray[23][0] = "EVIDENCE";
		NewArray[24][0] = "GOOD";
		NewArray[25][0] = "BENEFICIAL";
		NewArray[26][0] = "ADVANTAGEOUS";
		NewArray[27][0] = "VALUABLE";
		NewArray[28][0] = "FAVORABLE";
		NewArray[29][0] = "CONFIRM";
		NewArray[30][0] = "INDIC";
		NewArray[31][0] = "SIGN";
		
		
		for(int i = 0; i < 32; i++){
			if (validity)	NewArray[i][1] = "CORRECT";
			else 	NewArray[i][1] = "INCORRECT";
		}
		
		NewArray[32][0] = "USELESS";
		NewArray[33][0] = "HARMFUL";
		NewArray[34][0] = "NO WAY";
		NewArray[35][0] = "IT DOESN'T";		
		NewArray[36][0] = "NOT NECESSAR";		
		NewArray[37][0] = "NOT AT ALL";
		NewArray[38][0] = "NO";
		NewArray[39][0] = "NOPE";
		NewArray[40][0] = "INCORRECT";
		NewArray[41][0] = "INEFFECTIVE";
		NewArray[42][0] = "UNABLE";
		NewArray[43][0] = "INCAPABLE";
		NewArray[44][0] = "WORTHLESS";
		NewArray[45][0] = "INEFFICIENT";
		NewArray[46][0] = "INEFFECTIVE";
		NewArray[47][0] = "FRUITLESS";
		NewArray[48][0] = "INADEQUATE";	
		NewArray[49][0] = "INSUFFICIENT";	
		NewArray[50][0] = "WEAK";
		NewArray[51][0] = "WRONG";
		NewArray[51][0] = "BAD";
		NewArray[51][0] = "WORSE";
		NewArray[51][0] = "DAMAGING";
		NewArray[52][0] = "SURELY NOT";
		NewArray[53][0] = "DEFINITLY NOT";
		NewArray[54][0] = "OF COURSE NOT";
		NewArray[55][0] = "OBVIOUSLY NOT";
		NewArray[56][0] = "IS NOT";
		NewArray[57][0] = "CAN'T";
		NewArray[58][0] = "DOES NOT";
		NewArray[59][0] = "DOESN'T";
		NewArray[59][0] = "CONTRADIC";
		NewArray[60][0] = "INCONSISTEN";
		NewArray[61][0] = "DISCREPAN";
		NewArray[62][0] = "UNSCREPAN";

		for(int i = 32; i < 63; i++){
			if (validity) 	NewArray[i][1] = "INCORRECT";
			else NewArray[i][1] = "CORRECT";
		}
		
		int k = 0;
		for(int i = 63; i < (63	+story.tags.length); i++){
			NewArray[i] = story.tags[k];
			//System.out.println("added at position " + i + ": " + NewArray[i][0] + ", " + NewArray[i][1]);
			k++;
		}
		
		
		story.tags = NewArray;

		return understand(input,story);
		
		

		
		
		
		
	}
	
	
	private InputType parseSyllog(String input, Story story) {

		boolean validity = false;
		input = input.toUpperCase();
		
		if(story.tags[0][0].toUpperCase().equals("TRUE")){
			validity = true;
		}
		if(story.tags[0][0].toUpperCase().equals("FALSE")){
			validity = false;
		}

		int length = 37 + story.tags.length;
		String[][] NewArray = new String[length][2];			

		
		NewArray[0][0] = "YES";
		NewArray[1][0] = "IT DOES";
		NewArray[2][0] = "DOES FOLLOW";
		NewArray[3][0] = "CAN CONCLUDE";
		NewArray[4][0] = "VALID";
		NewArray[5][0] = "IS LOGIC";
		NewArray[6][0] = "INDEED";
		NewArray[7][0] = "PROBABLY";
		NewArray[8][0] = "I GUESS";
		NewArray[9][0] = "I THINK SO";
		NewArray[10][0] = "NATURALLY";
		NewArray[11][0] = "YEA";
		NewArray[12][0] = "TRUE";
		NewArray[13][0] = "CORRECT";
		NewArray[14][0] = "RIGHT";
		NewArray[15][0] = "SURE";
		NewArray[16][0] = "DEFINITELY";
		NewArray[16][0] = "EXACT";
		NewArray[17][0] = "OF COURSE";
		NewArray[18][0] = "OBVIOUSLY";
		
		
		for(int i = 0; i < 19; i++){
			if (validity)	NewArray[i][1] = "CORRECT";
			else 	NewArray[i][1] = "INCORRECT";
		}
		
		NewArray[19][0] = "NOT AT ALL";
		NewArray[20][0] = "NO";
		NewArray[21][0] = "NOPE";
		NewArray[22][0] = "INCORRECT";
		NewArray[23][0] = "WRONG";
		NewArray[24][0] = "SURELY NOT";
		NewArray[25][0] = "DEFINITLY NOT";
		NewArray[26][0] = "OF COURSE NOT";
		NewArray[27][0] = "OBVIOUSLY NOT";
		NewArray[28][0] = "CAN'T";
		NewArray[29][0] = "CAN NOT";
		NewArray[30][0] = "DOES NOT FOLLOW";
		NewArray[31][0] = "DOESN'T FOLLOW";
		NewArray[32][0] = "CAN NOT CONCLUDE";
		NewArray[33][0] = "CAN'T CONCLUDE";
		NewArray[34][0] = "NO WAY";
		NewArray[35][0] = "IT DOESN'T";		
		NewArray[36][0] = "NOT NECESSAR";		
		

		for(int i = 19; i < 37; i++){
			if (validity) 	NewArray[i][1] = "INCORRECT";
			else NewArray[i][1] = "CORRECT";
		}
		
		int k = 0;
		for(int i = 37; i < (37+story.tags.length); i++){
			NewArray[i] = story.tags[k];
			k++;
		}
		
		story.tags = NewArray;

		return understand(input,story);
		
		
	}


	private InputType parseWrong(String input, Story story) {
		input = input.toUpperCase();
		
		if ((input.contains("WHY") || input.contains("HOW") || input.contains("WHAT")) && input.contains("?")){
			return InputType.WHY;
		}
		return InputType.NO_IDEA;
	}


	private InputType parseRight(String input, Story story) {
	
		input = input.toUpperCase();
		
		if ((input.contains("WHY") || input.contains("HOW") || input.contains("WHAT")) && input.contains("?")){
			return InputType.WHY;
		}
		if (input.contains("GREAT") || input.contains("AWESOME") || input.contains("GOOD")
				 || input.contains("OF COURSE") || input.contains("YES") || input.contains("KNEW IT") ){
			return InputType.CORRECT;
		}
		
		return InputType.NO_IDEA;
	}


	private InputType parseIntro(String input, Story story) {
		System.out.println("ParseIntro!");
		InputType type;
		input = input.toUpperCase();
		
		if(story.intro.equals("Do you like Harry Potter (by J.K. Rowling)?")){
			type = ParseAgreement(input,true);
			if(type == InputType.CORRECT) return InputType.ANSWER1;
			else if (type == InputType.INCORRECT)	return InputType.ANSWER2;
			
			else if (findTag(input,"LOVE").startsWith("+") || findTag(input,"LIKE").startsWith("+") ||
					findTag(input,"ADORE").startsWith("+") || findTag(input,"FAVORITE").startsWith("+") ||
					findTag(input,"BEST").startsWith("+") || findTag(input,"GOOD").startsWith("+") || 
					findTag(input,"APPRECIATE").startsWith("+")){
				return InputType.ANSWER1;
			}
			
			else if  (findTag(input,"HATE").startsWith("+") || findTag(input,"LIKE").startsWith("-") ||
					findTag(input,"CAN'T STAND").startsWith("+") || findTag(input,"BORING").startsWith("+") ||
					findTag(input,"BAD").startsWith("+") || findTag(input,"NOT REALLY").startsWith("+") || 
					findTag(input,"ANNOY").startsWith("+")){
				return InputType.ANSWER2;
			}
			else {
				return InputType.NO_IDEA;
			}
		}
		

		if(story.intro.equals("Do you like to play video games?")){
			type = ParseAgreement(input,true);

			if (type == InputType.CORRECT) 	{
				////System.out.println("yes");
				store.video(1);
				eval.video = 1;
				return InputType.ANSWER1;
			}
			else if (type == InputType.INCORRECT) {
				//System.out.println("no");
				store.video(2); 
				eval.video = 2;
				return InputType.ANSWER2;
			}
			else if (findTag(input,"LOVE").startsWith("+") || findTag(input,"LIKE").startsWith("+") ||
					findTag(input,"ADORE").startsWith("+") || findTag(input,"ENJOY").startsWith("+")){
				return InputType.ANSWER1;
			}
			
			else if  (findTag(input,"HATE").startsWith("+") || findTag(input,"LIKE").startsWith("-") ||
					findTag(input,"CAN'T STAND").startsWith("+") || findTag(input,"BORING").startsWith("+") ||
					findTag(input,"BAD").startsWith("+") || findTag(input,"NOT REALLY").startsWith("+") || 
					findTag(input,"ANNOY").startsWith("+")){
				return InputType.ANSWER2;
			}
			else {
				eval.video = 3;
				return InputType.NO_IDEA;
			}
		}
		
		
		
		if(story.intro.equals("Do you like board games?")){
			type = ParseAgreement(input,true);

			if (type == InputType.CORRECT) 	{
				store.games(1);
				eval.games = 1;
				return InputType.ANSWER1;
			}
			else if (type == InputType.INCORRECT) {
				store.games(2); 
				eval.games = 2;
				return InputType.ANSWER2;
			}
			else if (findTag(input,"LOVE").startsWith("+") || findTag(input,"LIKE").startsWith("+") ||
					findTag(input,"ADORE").startsWith("+") || findTag(input,"ENJOY").startsWith("+")){
				return InputType.ANSWER1;
			}
			
			else if  (findTag(input,"HATE").startsWith("+") || findTag(input,"LIKE").startsWith("-") ||
					findTag(input,"CAN'T STAND").startsWith("+") || findTag(input,"BORING").startsWith("+") ||
					findTag(input,"BAD").startsWith("+") || findTag(input,"NOT REALLY").startsWith("+") || 
					findTag(input,"ANNOY").startsWith("+")){
				return InputType.ANSWER2;
			}
			else {
				eval.games = 3;
				return InputType.NO_IDEA;
			}
			
		}
		
			
		
		
		
		if(story.intro.equals("Is there any city you would like to travel to one day?")){
			
			String city = findCity(input);
			//System.out.println("found City " + city);
			if(city != null && !city.equals("")){
				
				String start = city.substring(0,1);
				String end = city.substring(1,city.length());
				city = start.toUpperCase() + end.toLowerCase();
				
				store.city(city);
				eval.city = city;
				
				return InputType.ANSWER1;
			}
			
			else {
				

				store.city("");
				eval.city = "Tokyo";

				return InputType.ANSWER2;
			}

			
		}

		if (story.intro.equals("Are you more of a cats person? Or do you prefer dogs?") || story.intro.equals("Are you more of a cat person? Or do you prefer dogs?")){
			type = ParseAgreement(input,true);

			if ((input.contains("CAT") && !(input.contains("DON'T LIKE")||input.contains("DONT LIKE")||input.contains("DESPISE")||input.contains("HATE")))||
					input.contains("MEOW")||input.contains("MWAOH")||input.contains("FORMER")||input.contains("PURR")||input.contains("KITT")) 	{
				store.catdog(1);
				eval.catdog = 1;
				return InputType.ANSWER1;
			}
			else if ((input.contains("DOG") && !(input.contains("DON'T LIKE")||input.contains("DONT LIKE")||input.contains("DESPISE")||input.contains("HATE")))||
					input.contains("WOOF")||input.contains("RUFF")||input.contains("LATTER")||input.contains("PUPP")||input.contains("HOUND")) 	{
				store.catdog(2);
				eval.catdog = 2;
				return InputType.ANSWER2;
			}
			else {
				eval.catdog = 3;
				store.catdog(3);
				return InputType.NO_IDEA;
			}
			
		}
		
		if (story.intro.equals("Have you ever been in a hospital?")){
			type = ParseAgreement(input,true);
			

			if (type == InputType.CORRECT) 	{
				eval.hospital = 1;
				return InputType.ANSWER1;
			}
			else if (type == InputType.INCORRECT) {
				eval.hospital = 2;
				return InputType.ANSWER2;
			}
			else if (findTag(input,"BROKE").startsWith("+")
					|| findTag(input,"SICK").startsWith("+")
					|| findTag(input,"ONCE").startsWith("+")
					|| findTag(input,"OFTEN").startsWith("+")
					|| findTag(input,"MANY TIMES").startsWith("+")
					|| findTag(input,"TWICE").startsWith("+")
					|| findTag(input,"ACCIDENT").startsWith("+")){

				eval.hospital = 1;
				return InputType.ANSWER1;
				
			}
			else if (findTag(input,"HEALTHY").startsWith("+")
					|| findTag(input,"NEED").startsWith("-")
					|| findTag(input,"NEVER").startsWith("+")
					|| findTag(input,"HAVE").startsWith("-")){

				eval.hospital = 2;
				return InputType.ANSWER2;
				
			}
			else {
				eval.hospital = 3;
				return InputType.NO_IDEA;
			}
			
		}
		
		if (story.intro.equals("Are you good in biology?")){
			type = ParseAgreement(input,true);
			
			//System.out.println(input);
			if (type == InputType.CORRECT) 	{
				eval.biology = 1;
				return InputType.ANSWER1;
			}
			else if (type == InputType.INCORRECT) {
				eval.biology = 2;
				return InputType.ANSWER2;
			}
			else if (findTag(input,"BIOLOGY").startsWith("+") ||
					findTag(input,"FAVORITE").startsWith("+") ||
			(findTag(input,"STUDY").startsWith("+") && ((input.contains("IT") || input.contains("BIOLOGY")))) ||
			(findTag(input,"PREFER").startsWith("+") && !input.contains("BIOLOGY"))					
					){
				eval.biology = 1;
				return InputType.ANSWER1;
			}
			else if (findTag(input,"BIOLOGY").startsWith("-") ||
					findTag(input,"FAVORITY").startsWith("-")) {
				eval.biology = 2;
				return InputType.ANSWER2;
			}
			
			
			else {
				eval.biology = 3;
				return InputType.NO_IDEA;
			}
			
		}
		
		if (story.intro.equals("Do you like gambling?")){
			type = ParseAgreement(input,true);

			if (type == InputType.CORRECT) 	{
				store.gambling(1);
				eval.gambling = 1;
				return InputType.ANSWER1;
			}
			else if (type == InputType.INCORRECT) {
				store.gambling(2); 
				eval.gambling = 2;
				return InputType.ANSWER2;
			}
			else {
				eval.gambling = 3;
				return InputType.NO_IDEA;
			}
			
		}
		
		
		if (story.context == ContextType.SPORTS_TICKET){
			//System.out.println("Sports ticket");
			type = ParseAgreement(input,true);
			if (type == InputType.CORRECT) return InputType.ANSWER1;
			if (type == InputType.INCORRECT) return InputType.ANSWER2;
			else return InputType.NO_IDEA;
			
		}
		
		if (story.context == ContextType.POPCORN){
			if (input.contains("SWEET") && findTag(input,"SWEET").startsWith("+")){
				store.popcorn(1);
				return InputType.ANSWER1;
			}
			if (input.contains("SUGAR") && findTag(input,"SUGAR").startsWith("+")){
				store.popcorn(1);
				return InputType.ANSWER1;
			}
			if (input.contains("SALT") && findTag(input,"SALT").startsWith("+")){
				store.popcorn(2);
				return InputType.ANSWER2;
			}
			if (input.contains("SWEET") && findTag(input,"SWEET").startsWith("-")){
				store.popcorn(2);
				return InputType.ANSWER2;
			}
			if (input.contains("SUGAR") && findTag(input,"SUGAR").startsWith("-")){
				store.popcorn(2);
				return InputType.ANSWER2;
			}
			if (input.contains("SALT") && findTag(input,"SALT").startsWith("-")){
				store.popcorn(1);
				return InputType.ANSWER1;
			}
			return InputType.NO_IDEA;
		}
		
		switch(story.intro) {

		case "What kind of games do find more fascinating? Board Games and Tabletops, or eSports like Starcraft or League of Legends?" : {
			if (input.contains("SPORT")||input.contains("ELECTRONIC")||input.contains("VIDEO")||input.contains("CRAFT")||input.contains("LEAGUE")||input.contains("COUNTER")||input.contains("DOTA")||input.contains("FIFA") ){
				eval.video = 1;
				store.video(1);
				return InputType.ANSWER1;

			}
			else if (input.contains("BOARD")||input.contains("TABLETOP")){
				eval.games = 1;
				return InputType.ANSWER2;
			}
			else return InputType.NO_IDEA;
			
		}
		
		
		case "Do you have a favorite restaurant?" : {
			type = ParseAgreement(input,true);
			if (type == InputType.CORRECT) return InputType.ANSWER1;
			if (type == InputType.INCORRECT) return InputType.ANSWER2;
			else return InputType.NO_IDEA;
		
		}
		default:	return InputType.NO_IDEA;
		
		}
	}


	private String findCity(String input) {
		//System.out.println("findCity");
		Vector<String> intros = new Vector<String>(5,1);
		input = input.toUpperCase();
		input = input.substring(1, input.length()-1);
		
	
		
		
		if(input.equals("YES") || input.equals("YEAH")|| input.equals("SURE")||
				input.equals("YES.") || input.equals("YEAH.")|| input.equals("SURE.")){
			return "";
		}
		
	String in = input;
		
		if(input.startsWith("~")){
			in = input.substring(1,input.length());
		}
		if(in.endsWith("~")){
			in = in.substring(0,in.length()-1);
		}
		if(!in.contains(" ") && !in.contains(",")){
			
			String city = "" + in.charAt(0);
			city = city.toUpperCase();
			city = city + in.substring(1,in.length());
			return city;
		}
		
		intros.addElement("see");
		intros.addElement("go to");
		intros.addElement("travel to");
		intros.addElement("visit");
		intros.addElement("check out");
		intros.addElement("live in");
		intros.addElement("time in");
		intros.addElement("days in");
		intros.addElement("yes,");
		intros.addElement("yeah,");
		intros.addElement("oh yeah,");
		intros.addElement("well,");
		intros.addElement("actually");
		intros.addElement("oh yes,");
		intros.addElement("well,");
		intros.addElement("holiday in");
		intros.addElement("vacation in");
		String city = "";
		String after,intro,pre = null;
		input = input.toUpperCase();
		
		
		
		if (input.startsWith("NO") || input.startsWith("I DON'T") || input.startsWith("I DONT")|| input.startsWith("I DO NOT")){
			return null;
		}
		
		if (input.startsWith("YES,")) input = input.substring(5,input.length());
		if (input.startsWith("YEAH,")) input = input.substring(6,input.length());
		if (input.startsWith("WELL,")) input = input.substring(6,input.length());
		if (input.startsWith(" ")) input = input.substring(1,input.length());
		//System.out.println(input);
		
		if (!input.contains(" ")){
			city = input.substring(0, input.length());
			
		}
		else {
			for (int i = 0; i < intros.size(); i++){
				if(input.contains(intros.elementAt(i).toUpperCase())){
					intro = intros.elementAt(i).toUpperCase();
					after = input.substring(input.indexOf(intro)+intro.length(),input.length());
					
					
					if(after.startsWith(" ")){
						after = after.substring(1,after.length());
					}
					if(after.contains("NEW")){
						pre = "NEW ";
						after = after.substring(4,after.length());
					}
					
					if (after.contains(" ")){
						
						after = after.substring(0, after.indexOf(" "));
						
					}
					if (after.contains(".")){
						after = after.substring(0, after.indexOf("."));
					}if (after.contains("!")){
						after = after.substring(0, after.indexOf("!"));
					}
					
					
					city = after;
					if (pre != null){
						city = pre + after;
					}
				}
			}			
		}
		//System.out.println("Now: " + city);
		
		while (city.endsWith(" ") || city.endsWith("!") || city.endsWith(".")){
				city = city.substring(0,city.length()-1);
		}
		
		if (city.contains("YES") || city.contains("ACTUALLY") || city.contains("YEAH") ||city.contains("NO")|| city.contains("NOPE")|| city.contains("NOT")){
			return null;
		}
		
		//System.out.println("this: " + city);
		
		
		return city;
	}


	private InputType ParseNumber(String input, boolean b) {
		
		if (input.toUpperCase().contains("FIVE")|| input.toUpperCase().contains("5"))
					return InputType.SMALL;
		if (input.toUpperCase().contains("TEN")|| input.toUpperCase().contains("10"))
					return InputType.MEDIUM;
		if (input.toUpperCase().contains("TWENTY")|| input.toUpperCase().contains("20"))
					return InputType.LARGE;
			
		
		return InputType.NO_IDEA;
	
	}


	public double parseSure(String input) {
		input = input.toUpperCase();
		

		Vector<Double> guesses = lookForStatistics(input);
		
		if(guesses.isEmpty()) {
			guesses = lookForStatistics(input.substring(0, input.length()-1) + "%~");			
		}
		
		if(guesses.isEmpty()) {
			guesses = lookForKeywords(input);
			//if (!guesses.isEmpty()) 
				//System.out.println("found keyword");
		}
		
 		if (guesses.size() > 0){
 			
 			double n = guesses.firstElement()/100;

 			//System.out.println("Confidence: " + n);
 			
 	 		if (input.contains("THAT")&&(input.contains("I'M WRONG")||input.contains("IM WRONG")||
 	 				input.contains("INCORRECT")||input.contains("HAVE NO CLUE")||input.contains("HAVE NO IDEA")||
 	 				input.contains("MISLEAD")||input.contains("AT FAULT")||input.contains("MISTAKE"))){
 	 					n = 1 - n;

 	 		 			//System.out.println("no, " + n);
 	 		}
 	 		return n;
 		}
 			
 		
 		

		if (findTag(input,"DON'T KNOW").startsWith("+")) return 0.5;
		if (findTag(input,"NO IDEA").startsWith("+")) return 0.5; 
		if (findTag(input,"NO CLUE").startsWith("+")) return 0.5;
		if (findTag(input,"DON'T REALLY KNOW").startsWith("+")) return 0.5;
 			
		
		
		else return -1;
	}



}
