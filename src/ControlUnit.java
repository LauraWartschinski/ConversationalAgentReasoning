import java.util.Random;
import java.util.Vector;

// the main executive of the bot
public class ControlUnit {
	
	 // enum for storing the state of the program
	public enum ProgramState {
	    INTRO, EVALUATION, FEEDBACK, FINISH, SETUP, ERROR
	}

	//every control unit owns those helper units
	int a;
	static ProgramState state = ProgramState.INTRO; 
	static boolean real = true; //if the stories are chosen at random
	static int amount; // how many stories shall be asked in total
	static int number; // number of stories per topic to ask (at least);
	static Logfiler logfiler = new Logfiler();
	static UserEvaluation ueval;
	static Parser parser;
	static Storystore sstore;
	Phrasebase pbase = new Phrasebase();
	String logfile = "";
	UI ui;
	int in_a_row = 0;
	boolean in_a_rowed = false;
	
	Vector<Story> collectstories = new Vector<Story>(20,1);			
	Vector<String> leftTopics = new Vector<String>(5,1);
	
	static boolean[] explained = {false,false,false,false,false,false,false,false,false,false};
	static int[] good = {0,0,0,0,0,0,0,0,0,0};
	
	// empty constructor
	public ControlUnit(int amount, Storystore store, String logfile){
		

		String logfile2 = logfile.substring(0, logfile.length()-4) + "txt";
		
		ui = new UI(real,logfile); 
		ueval = new UserEvaluation(logfile2);
		parser = new Parser(ueval);
		sstore = store;
		
		leftTopics.add("base_rate"); 
		leftTopics.add("sunk_cost"); 
		leftTopics.add("sample_size"); 
		leftTopics.add("gamblers_fallacy");
		leftTopics.add("belief_syllogistic");
		leftTopics.add("covariation_detection");
		leftTopics.add("selection_task");
	}
	
	private String askForever (int i, String question) {
		return askForever(i,question,true);
		
	}
	
	private String askForever(int i, String question, boolean questionasked) {
		//System.out.println("ask forever was called");
		String answer = "";
		boolean answered = false;
		int time = 0;
		String in = "";
		boolean stuff = false;
		if (question.length() > 0){
			ui.tell(question);
		}
		while(!answered){
			//System.out.println("time " + time);
			time++;
			in = ui.listen_timeout(i);
			
			if  (in.length() != 0){
				answered = true;
			}
			else {

				
				if(time < 5){
						answer = pbase.getReply(ReplyType.WAITING,0); // get one of the greetings
						ui.tell(answer);
						stuff = true;
					}

				if(time >= 5 && time < 8){
						answer = pbase.getReply(ReplyType.WORRIED_WAITING,0); // get one of the greetings
						ui.tell(answer);
						stuff = true;
					}
				if(time >= 8 && time < 12){
					continue;
				}
				if(time >= 12){
					ui.tell("It seems that you are a bit distracted right now. Or maybe dead. ... ...I hope you are not dead.");
					ui.tell("But because it's been a while since your last answer, I have to ask you: ");
					if(askbreak()){
						time = 0;
						ui.tell("Okay, so what I said was: " + question);
					}
					else {
						String goodbye = pbase.getReply(ReplyType.GOODBYE, 0); //get a general goodbye message
						ui.tell(goodbye); 
						ui.goodbye();						
					}
				}
			}

		}
		
		//System.out.println("Answer : " + in);
		//some answer occured
		//System.out.println(stuff);
		Vector<InputType>reply = parser.parseInput(in, ContextType.AGREEMENT, null);
		for(int i1 = 0; i1 < reply.size(); i1++){
			//System.out.println("TAG: " + reply.get(i1).name());
		}

		if(reply.contains(InputType.WAIT)){
			ui.tell(pbase.getReply(ReplyType.TIME, 0));
			return askForever(60,"");
		}
		/*boolean reaction = reactToGenerals(reply);
		
		if(reaction) {
			return askForever(i,"Anyways... " + question);
		}*/
		
		if((in.contains("I AM") || in.contains("YES") || in.contains("OF COURSE") || in.contains("YEAH") ||
				in.contains("DON'T WORRY")|| in.contains("SURE") || in.contains("THANK") || reply.contains(InputType.CORRECT)) && stuff){
			//could also be the answer to what we said while waiting
			
			if(time > 1) ui.tell("Oh, it's nice that you're still with me!");
			
			ui.tell("Was this already the answer to my question?");
			if(question.length() > 2){
				ui.tell("I asked: '" + question +"'. Did you answer this just now?");
			}
			
			String input = ui.listen();
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
			if (reply.contains(InputType.CORRECT)) return in;
			else {
				ui.tell("What is your answer, then?");
				return askForever(i,"");
			}
		}
		
		

		
		return in;
	}





	private boolean askStory(Story story, boolean announce){
		//System.out.println("AskStory: ");
		Vector<InputType> reply = new Vector<InputType>(0,1);
		String input = "", answer;
		boolean answered = false;
		boolean correct = false;
		

		
		//anounce the story
		
		if (story == null){
			//System.out.println("Debug: NO STORY!");
			return false;
		}
		
		
		if (explained[story.type] == false){
			explain_first(story.type);
			explained[story.type] = true;
		}
		
	
		
		//intro handling
		if(story.intro.length() > 0){
			askIntro(story);
		}
		else {
			if (announce){
				answer = pbase.getReply(ReplyType.WILL_TELL, 0); 
				//ui.tell(answer); // tell it 
			}
		}
		
		
		if (state != ProgramState.FINISH){
			//actual story
			ui.tell(story.text); //tell the story
			ui.tell(story.question); //ask the question
			boolean explained = false;
			if(story.type == 0)		input = ui.listen_timeout(20);
			else if(story.type == 3)		input = ui.listen_timeout(15);
			else input = ui.listen_timeout(15);
			boolean cor = false;
			if (input.isEmpty()){
				input = waitAWhile(story);
			}						
			if(input.isEmpty()){
				input = offerHint(story);
			}
			if (input.isEmpty()){
				input = waitAWhile(story);
			}									
			if (input.isEmpty() && story.construction == 0){
				input = offerExplanation(story);
			}			
			if(input == "-") explained = true;
			
			if(input.isEmpty()){
				ui.tell(story.clarify);
				input = ui.listen_timeout(10);
			}
			
			if(!explained){
				boolean hinted = false;
				
				//System.out.println("We got input " + input);
				reply = parser.parseInput(input, story.context, story);		
	
				boolean reacted = reactToGenerals(reply,true);
				int tries = 1;
			
				while (!(reply.contains(InputType.CORRECT) || reply.contains(InputType.INCORRECT)
						|| reply.contains(InputType.CONTINUE) || reply.contains(InputType.ABORT))){
					
					//System.out.println("another round");
					
					if (input.length() > 100){
						answer = pbase.getReply(ReplyType.TOO_LONG, 0);
						ui.tell(answer);
					}
					
					//ASKED TO WAIT
					if(reply.contains(InputType.WAIT)){
						//System.out.println("we wait.");
						ui.tell(pbase.getReply(ReplyType.TIME,0));
						input = ui.listen();
						reply = parser.parseInput(input, story.context, story);		
						continue;
					}
					
					//ASKED FOR HINT
					if(reply.contains(InputType.HINT)){
						if(hinted){
							ui.tell("I already gave you a hint.");
							input = offerExplanation(story);
						}
						
						else{
							ui.tell(story.hint);
							hinted = true;
							input = askForever(12, story.clarify);
							reply = parser.parseInput(input, story.context, story);	
							if(reply.contains(InputType.WAIT)){
								ui.tell("Now, what do you think: " + story.clarify);
								input = ui.listen_timeout(15);
								reply = parser.parseInput(input, story.context, story);			
							}
						}
						continue;
					}
					
					//ASKED FOR EXPLANATION
					if(reply.contains(InputType.EXPLAIN)){
						explainStory(story,1);
						explained = true;
						break;
					}
					
						
					//SAYS HE HAS NO CLUE
					if(reply.contains(InputType.NO_CLUE)){
						
						
						String comfort = pbase.getReply(ReplyType.COMFORT,0);
						ui.tell(comfort);
						
						if(!hinted){
							input = offerHint(story);
							reply = parser.parseInput(input, story.context, story);		
							continue;
						}
						else {
							input = offerExplanation(story);
							reply = parser.parseInput(input, story.context, story);		
							explained = true;
							break;
						}
					}
					
					//GAVE VAGUE ANSWER
					double keyword = parser.getKeyword();
					if(reply.contains(InputType.ASK_FOR_PERCENT) && keyword > -1){
											
						input = askForever(6,"Okay, but what exactly do you mean by that... something like " + keyword + "%? Is this what you meant?");
						Vector<InputType> yesno = parser.parseInput(input, ContextType.AGREEMENT, story);
						reply =  parser.parseInput(input, story.context, story);
						if (yesno.contains(InputType.CORRECT)){
							input = "~" + keyword + "%~";
							reply = parser.parseInput(input, story.context, story);
						}
						else if (yesno.contains(InputType.INCORRECT) && reply.contains(InputType.NO_IDEA)){
							
							answer = pbase.getReply(ReplyType.REPHRASE, tries);  //get the request for rephrasing
							 // tell it to the user	
							tries++;
							input = askForever(10,answer);
							reply = parser.parseInput(input, story.context, story);
						}
					}
					
					//GAVE USEFUL ANSWER
					if (reply.contains(InputType.CORRECT) || reply.contains(InputType.INCORRECT)
							|| reply.contains(InputType.CONTINUE) ){
						
						break;
					}
					
					//NONE OF THOSE
					else {
						//System.out.println("reacted: " + reacted);
						if(tries > 4){
							//System.out.println("tries > 4");
							reply.add(InputType.ABORT);
						}
						else {
							
							
							if(reacted){
								answer = "My question is: "+story.clarify;
							}
							else {
								//System.out.println("we did not understand");
								answer = pbase.getReply(ReplyType.REPHRASE, tries);  //get the request for rephrasing
							}
							//System.out.println(answer);
							tries++;
							input = askForever(10,answer);
							reply = parser.parseInput(input, story.context, story);
							reactToGenerals(reply,true);
							continue;
						}
					}
				}
			
			
			/*
			if (input.length() < 6){
				answer = pbase.getReply(ReplyType.TOO_SHORT, 0);
				ui.tell(answer);
			}*/
			
			
			
			Random rand = new Random();
			int askHowSure = rand.nextInt(100)%2;
			if(askHowSure==1 && (reply.contains(InputType.CORRECT)||reply.contains(InputType.INCORRECT))){
				askHowSure(reply);
			}
			
			//System.out.println(reply.size());
			
			if (reply.contains(InputType.ABORT)){
				answer = pbase.getReply(ReplyType.ABORT, 0);
				ui.tell(answer);
				askToProceed();
				
			}			
			
			if (reply.contains(InputType.CORRECT) && !explained){
				//System.out.println("(Debug: Answer was evaluated as correct)");
				good[story.type] = good[story.type] + 1;
				in_a_row++;
				cor = true;
				ueval.increment_r(story.type);
				ueval.evaluate(true,story,input,true);
				answered = true;
				ui.tell(story.right); // tell it 
	
				input = ui.listen_timeout(1);
				reply = parser.parseInput(input, ContextType.RIGHT, story);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.RIGHT, story);
				}
				
				if (reply.contains(InputType.CORRECT)){
					answer = pbase.getReply(ReplyType.GLAD, 0); //get a comforting message
					ui.tell(answer);
				}
			}
			if (reply.contains(InputType.INCORRECT) && !explained){
				//System.out.println("(Debug: Answer was evaluated as incorrect)");
				good[story.type] = good[story.type] -1;
				in_a_row = 0;
				ueval.increment_w(story.type);
				ueval.evaluate(true,story,input,false);
				answered = true;
				ui.tell(story.wrong); // tell it 
				input = ui.listen_timeout(1);
				reply = parser.parseInput(input, ContextType.WRONG, story);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.WRONG, story);
				}

			}
			if (reply.contains(InputType.CONTINUE) && !explained){
				//System.out.println("(Debug: Stored the answer for later use, continuing with next question)");
				//ueval.evaluate(true,story,input,true);
				ui.tell("That is interesting. I will get back to this later."); // tell it 
			}

			
			
			if(answered && !explained){
				
				if (reply.contains(InputType.WHY) || reply.contains(InputType.EXPLAIN)){
					if(cor){
						explainStory(story,0);
					}
					else {
						explainStory(story,1);
					}
				}
				else {
					reactToGenerals(reply,true);
					answer = pbase.getReply(ReplyType.OFFER, 0);
					input = askForever(10,answer);
					reply = parser.parseInput(input, ContextType.AGREEMENT, null);
					
					
					if(reply.contains(InputType.WAIT)){
						input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
						reply = parser.parseInput(input, ContextType.AGREEMENT, null);
					}
					
					if (reply.contains(InputType.CORRECT) || reply.contains(InputType.WHY) || reply.contains(InputType.EXPLAIN)){
						if(cor){
							explainStory(story,0);
						}
						else {
							explainStory(story,1);
						}
					}
					else if (reply.contains(InputType.INCORRECT)){
						answer = pbase.getReply(ReplyType.OK,0);
						ui.tell(answer);
					}
					else if (reply.contains(InputType.NO_IDEA) && reply.size() == 1){
						answer = pbase.getReply(ReplyType.REPHRASE, tries);  //get the request for rephrasing
						answer = pbase.getReply(ReplyType.OFFER, 0);
						input = askForever(10,answer);
						reply = parser.parseInput(input, ContextType.AGREEMENT, null);

						if (reply.contains(InputType.CORRECT) || reply.contains(InputType.WHY) || reply.contains(InputType.EXPLAIN)){
							if(cor){
								explainStory(story,0);
							}
							else {
								explainStory(story,1);
							}
						}
						else if (reply.contains(InputType.INCORRECT)){
							answer = pbase.getReply(ReplyType.OK,0);
							ui.tell(answer);
						}
						else {
							ui.tell("Sorry, I don't know what you're trying to tell me.");
						}
					}
				}
			}
	//sometimes, ask for details to seem more human
				
				int n = rand.nextInt(100)%3;
				if (n == 0 && askHowSure != 1){
					if (answered && !correct){
						answer = pbase.getReply(ReplyType.INSIGHT_R, 0); 
						input = askForever(6,answer);
						reply = parser.parseInput(input, ContextType.AGREEMENT, null);
						reactToGenerals(reply,true);
						if(reply.contains(InputType.WAIT)){
							input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
							reply = parser.parseInput(input, ContextType.AGREEMENT, null);
							reactToGenerals(reply,true);

						}

						answer = pbase.getReply(ReplyType.THANKS, 0); //get a thank you message
						ui.tell(answer); // tell it 
					}
					if (answered && correct){
						answer = pbase.getReply(ReplyType.INSIGHT_W, 0); 
						input = askForever(6,answer);
						reply = parser.parseInput(input, ContextType.AGREEMENT, null);
						reactToGenerals(reply,true);
						if(reply.contains(InputType.WAIT)){
							input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
							reply = parser.parseInput(input, ContextType.AGREEMENT, null);
							reactToGenerals(reply,true);

						}
						answer = pbase.getReply(ReplyType.THANKS, 0); //get a thank you message
						ui.tell(answer); // tell it 
					}
				}
			}
		}
		
		return true; // return true to signal that finding a story and asking it succeded
		
}
	


	private String waitAWhile(Story story) {
		String input = "";
		String answer = pbase.getReply(ReplyType.WAITING,0); // get one of the waiting messages
		ui.tell(answer);
		if(story.type == 0)		input = ui.listen_timeout(25);
		else if(story.type == 3)		input = ui.listen_timeout(16);
		else input = ui.listen_timeout(13);
		Vector<InputType> reply = parser.parseInput(input, story.context, story);	
		Vector<InputType> yesno = parser.parseInput(input, ContextType.AGREEMENT, null);

		if (reply.contains(InputType.NO_IDEA) && !reply.contains(InputType.HINT)
				 && !reply.contains(InputType.EXPLAIN)
				 && !reply.contains(InputType.NO_CLUE)
				 
				){
			if(yesno.contains(InputType.CORRECT) || input.contains("THANK")){
			answer = pbase.getReply(ReplyType.GOOD,0);
			ui.tell(answer);
			input = ui.listen_timeout(10);			
			return input;		
			}
			return input;
		}
		return input;
	}
	
	private String worriedwaitAWhile(Story story) {
		String input = "";
		String answer = pbase.getReply(ReplyType.WORRIED_WAITING,0); // get one of the waiting messages
		ui.tell(answer);
		if(story.type == 0)		input = ui.listen_timeout(20);
		else if(story.type == 3)		input = ui.listen_timeout(15);
		else input = ui.listen_timeout(10);
		Vector<InputType> reply = parser.parseInput(input, story.context, story);	
		Vector<InputType> yesno = parser.parseInput(input, ContextType.AGREEMENT, null);

		if (reply.contains(InputType.NO_IDEA) && !reply.contains(InputType.HINT)
				 && !reply.contains(InputType.EXPLAIN)
				 && !reply.contains(InputType.NO_CLUE)
				 
				){
			if(yesno.contains(InputType.CORRECT) || input.contains("THANK")){
			ui.tell("Phew, it's nice that you're back.");
			input = ui.listen_timeout(10);			
			return input;		
			}
			return input;
		}
		return input;
	}

	private void askHowSure(Vector<InputType> reply) {
		String ask = pbase.getReply(ReplyType.HOW_SURE, 0);
		String input = askForever(8,ask);
		double sure = parser.parseSure(input);	
		if (sure > 100) sure = 100;
		
		reactToGenerals(parser.parseInput(input, ContextType.NOTHING, null),true);
		double calibration = 0;
		//System.out.println("SURE: " + sure);
		if (reply.contains(InputType.CORRECT) && sure > -1){
			calibration = sure;
		}
		if (reply.contains(InputType.INCORRECT)  && sure > -1){
			if(sure < 0.5) sure = 0.5;			
			calibration = 1 + (sure-0.5);
		
		}
		
		
		//System.out.println("CALIBRATION: " + calibration);
		
		if (sure != -1){
			
			if(calibration > 1.1){
				ui.tell(pbase.getReply(ReplyType.OVERCONFIDENT, 0));
			}
			else if(calibration < 0.9){
				ui.tell(pbase.getReply(ReplyType.UNDERCONFIDENT, 0));
			}
			else {
				ui.tell(pbase.getReply(ReplyType.CALIBRATED, 0));
			}
			
			ueval.putCalibration(calibration);
		}
		//ui.tell(pbase.getReply(ReplyType.THANKS, 0));
	
	}

	private String offerExplanation(Story story) {
		
		//offer to explain it
		String exp = pbase.getReply(ReplyType.EXP,0);
		ui.tell(exp);
		String input = ui.listen_timeout(10);

		Vector<InputType> reply = parser.parseInput(input, story.context, story);	
		Vector<InputType> yesno = parser.parseInput(input, ContextType.AGREEMENT, null);
		

		if (input.isEmpty()){
			ui.tell("Well, I gues that means no. " + pbase.getReply(ReplyType.OK, 0));
			return input;
		}
		
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, story.context, story);
			yesno = parser.parseInput(input, ContextType.AGREEMENT, null);	
		}
		
		if(reply.contains(InputType.NO_IDEA) && !reply.contains(InputType.HINT)
				 && !reply.contains(InputType.EXPLAIN)
				 && !reply.contains(InputType.NO_CLUE)
				){
			if (yesno.contains(InputType.CORRECT) || yesno.contains(InputType.WHY)| yesno.contains(InputType.EXPLAIN)){
				explainStory(story,1);
				return "-";
			}
			else if (yesno.contains(InputType.INCORRECT)){
				String answer = pbase.getReply(ReplyType.OK,0);
				ui.tell(answer + " My question was: ");
				input = askForever(10,story.clarify);
				return input;
			}
			else {
					ui.tell("I take that as a no.");
					input = askForever(10,story.clarify);	
					return input;
				
				}				 
		}
		else {
			return input;
		}
	
	}
		
	private String offerHint(Story story) {
		//System.out.println("offerHint()");
		String askhint = pbase.getReply(ReplyType.HINT, 0);
		ui.tell(askhint);
		String input = ui.listen_timeout(10);

		//parse the answer to the offer - that could also be an answer to the question!

		Vector<InputType> reply = parser.parseInput(input, story.context, story);	
		Vector<InputType> yesno = parser.parseInput(input, ContextType.AGREEMENT, null);
		
		if (input.isEmpty()){
			ui.tell("... No? " + pbase.getReply(ReplyType.OK, 0));
			return input;
		}
		
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, story.context, story);	
			yesno = parser.parseInput(input, ContextType.AGREEMENT, null);
			
		}
		
		//if no answer for the story was given
		if(reply.contains(InputType.NO_IDEA) && !reply.contains(InputType.HINT)
				 && !reply.contains(InputType.EXPLAIN)
				 && !reply.contains(InputType.NO_CLUE)
				){
			if (yesno.contains(InputType.CORRECT) || yesno.contains(InputType.WHY)){
				ui.tell(story.hint);
				if(story.type == 0)		input = ui.listen_timeout(20);
				else if(story.type == 3)		input = ui.listen_timeout(15);
				else input = ui.listen_timeout(15);
				return input;
			}
			else if (yesno.contains(InputType.INCORRECT)){
				String answer = pbase.getReply(ReplyType.OK,0);
				ui.tell(answer);
				if(story.type == 0)		input = ui.listen_timeout(20);
				else if(story.type == 3)		input = ui.listen_timeout(15);
				else input = ui.listen_timeout(15);
				return input;
			}
			else {
				ui.tell("I take that as a no. So what I wanted to know...");
				input = askForever(10,story.clarify);
				return input;
			}
		}
		else {
			return input;
		}
	}

	private boolean explain_type(int type, int number){
	boolean explain = false;
	
	if (number == 0){
		
		switch(type){
		case 0:		ui.tell("Do you already know a lot about conditional probabilities?"); break;
		case 1:		ui.tell("Are you already familiar with sample sizes and the regression to the mean?");break;
		case 2:		ui.tell("Do you know what the Gambler's Fallacy is?");break;
		case 3:		ui.tell("Are you an expert on formal logic?");break;
		case 4:		ui.tell("Are you familiar with the Sunk Cost Fallacy?");break;
		case 6:		ui.tell("Do you already know everything about syllogistic reasoning?");break;
		case 7: 	ui.tell("Are you an expert on statistics?");break;
		}
		
		String input = ui.listen();
		Vector<InputType> reply = parser.parseInput(input, ContextType.AGREEMENT, null);

		//System.out.println("number of reply types: " + reply.size());
		for (int i = 0; i < reply.size(); i++){
			//System.out.println(reply.get(i).name());
		}//todo
		reactToGenerals(reply, false);
		

		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);			
		}
		
		if (input.contains("I AM")){
			reply.add(InputType.CORRECT);
		}
		
		if(reply.contains(InputType.CORRECT) && !reply.contains(InputType.EXPLAIN) && !reply.contains(InputType.HINT) && !reply.contains(InputType.NO_CLUE)){
			//The user IS already familiar with the topic
			good[type] = good[type] + 1;
			ui.tell(pbase.getReply(ReplyType.SKIP, 0));
			input = ui.listen();
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
			reactToGenerals(reply,true);
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.AGREEMENT, null);			
			}
			
			if (reply.contains(InputType.CORRECT) && !reply.contains(InputType.EXPLAIN) && !reply.contains(InputType.HINT) && !reply.contains(InputType.NO_CLUE)){
				ui.tell(pbase.getReply(ReplyType.OK, 0));
				return true;
			}
			else if (reply.contains(InputType.INCORRECT) || reply.contains(InputType.EXPLAIN)|| reply.contains(InputType.WHATIS) || reply.contains(InputType.HINT)|| reply.contains(InputType.NO_CLUE)){
				//System.out.println("here");
				if(!reply.contains(InputType.WHATIS)) ui.tell(pbase.getReply(ReplyType.OPPORTUNITY_TO_EXPLAIN,0));
				else ui.tell("Oh, I can tell you!");
				explain = true;				
			}
			else {
				explain = true;
			}
			
			
		}
		else if ((reply.contains(InputType.INCORRECT) || reply.contains(InputType.EXPLAIN)|| reply.contains(InputType.WHATIS) || reply.contains(InputType.HINT)|| reply.contains(InputType.NO_CLUE)) && !explain){
			if(!reply.contains(InputType.WHATIS)) ui.tell(pbase.getReply(ReplyType.OPPORTUNITY_TO_EXPLAIN,0));
			else ui.tell("Oh, I can tell you!");
			
		}
		
		else {
			ui.tell(pbase.getReply(ReplyType.WANT_TO_EXPLAIN, 0));
			
		}
		
	
		
		
		
		switch(type){
			case 0:	ui.tell("The next problem is about solving problems with conditional probabilities. It's a bit mathematical, but don't worry."
					+ " People tend to ignore certain parts of statistical information because they confuse them."
					+ " But most of the time, this kind of problem is actually easy to solve! Just try to imagine it with a big number - let's say 10,000."
					+ " Then you look at how many of those 10,000 (whatever it is) fall into one category or another, and to how many a certain condition applies."
					+ " Use all the information you have. If you"
					+ " calculate with those populations, the outcome is equivalent to calculating probabilities, but it's much easier. This way,"
					+ " you can solve conditional probability problems without knowing any statistical rule."); break;
			case 1:	ui.tell("This is about what a sample can tell you about a whole population."
				+ " When you observe some events, very good or bad outcomes are most of the time not just due to some underlying quality,"
				+ " but also influenced by luck. The essence of the regression-toward-the-mean phenomenon is that events with extraordinary outcomes tend to be above"
				+ " average in some quality (e.g. skill of a person) <em>and</em> in luck and that only the skill portion is relevant to future performance."
				+ " If you have only a small sample from a population, those effects of random good or bad luck can have a strong influence on what the"
				+ " average looks like. But if you chose a very large sample, the random effects will eventually even out and the average will come closer"
				+ " to the true mean. insertimage:regression.png "); break;
			case 2:	ui.tell("Well, the next story is about one of my favorites: The gambler's fallacy. This happens when people observe a series of events, and if something happens"
					+ " more frequently than expected, they assume it will happen less often in the future (and vice versa). But if you look at independent"
					+ " random events, than the past can never influence the future."); break;
			case 3:	ui.tell("This time it's a task about logical rules of the form 'IF P, THEN Q'. You can imagine those like obligations: if some situation P arises,"
					+ " than some action Q must be done. There are four possible situations we could face: P happens, P doesn't happen, Q is done and Q is not done. "
					+ " Only in two of them the rule can be violated: When P occurs, it could be that Q is not done, and when Q is not done, it could be that P occured."
					+ " To see if the rule is being fulfilled, we have to check those two configurations. Many people forget about the second situation,"
					+ " because it doesn't directly jump to their mind like the first one."); break;
			case 4:	ui.tell("Prepare for the next story to be about: The sunk cost fallacy. It's about thinking like a rational strategist. Sunk costs are costs you paid"
					+ " in the past and can't get back anymore, no matter what you do. As a practical matter, sunk costs are not relevant with respect to any decision,"
					+ " because you can't change them anymore."
					+ " People tend to let themselves be affected by remorse about sunk costs and deliberately take worse options because of that - for example, a "
					+ " manager could argue to continue a failing project because there was already a lot of money invested in it."
					+ " You should avoid this, and just decide which of the achievable options is the best one."); break;
			/*case 5:	ui.tell("Oh, this is about outcome bias. It happens when people shift their view on the quality a decision based on information about the"
					+ " outcome. A typical case of 'Told you so!'. Obviously, if the information wasn't available before, you can't rate a decision"
					+ " based on it."); break;*/
			case 6:	ui.tell("This is about syllogistic reasoning, a form of reasoning that involves deciding whether a conclusion"
					+ " can properly be inferred from two or more statements. The question is if a conclusion follows logically from "
					+ " the premises. To be valid, a conclusion must <strong>always</strong> be true given its premises."
					+ " The question is <strong>not</strong> if you like the conclusion or if it seems realistic (many people confuse this!) - but only if it is logically valid."
					+ " If you can think of one way that the conclusion could be false when the premises are true, then it is invalid."
					+ " Drawing venn diagramms (the overlapping circles) sometimes helps with this.");break;
			case 7:ui.tell("To judge your environment correctly, it's crucial that you can determine whether a certain action"
					+ " is effective or not, and distinguish clear correlations from chance or statistical noise."
					+ " Most people just concentrate on what jumps to their eyes immediately. But this can be totally wrong. Instead, look closely at the given evidence and"
					+ " what it actually tells you."); break;
				
			default : //do nothing
			}
		}
		if (number == 1){
			switch(type){
			case 0:	ui.tell("How about an example... Let's say you have pieces of chocolate wrapped in golden and silver paper. insertimage:pralines1.png And you know that 10% contain nougat and"
					+ " the other 90% contain hazelnuts inside the chocolat.  <br> You know that 50% of the pieces with nuts are wrapped in gold paper, the others in silver. Also, 75% of the pieces with"
					+ " nougat are wrapped in gold. Now, given that you have a praline wrapped in gold paper - <strong>what is the probability that the gold praline contains nougat?</strong>"
					+ "...This seems a bit overwhelming, but let me show you how it's done. You just imagine 1000 chocolate pieces - yummie - and think about how many contain"
					+ " nougat. insertimage:pralines2.png As the base rate for containing nougat is 10%, it must be around 100 of them. Of those 100, 75% are wrapped in gold, so we have 75 with nougat in gold."
					+ " But we also have the other pralines with the nuts - 900 pieces. Of those, 50% are also wrapped in gold, making up for 450 nuts with gold wrappings. insertimage:pralines3.png In total, we have"
					+ " now 450+75 = 525 chocolate pieces with gold wrapping, and we know that 75 of them contain nougat, so the chance for a gold-wrapped praline to be a"
					+ " confection of nougat is 75/525 or around 14%."); break;
			case 1:	ui.tell("For example: having a date is"
						+ " somewhat like picking one of all the possible dating experiences. And eating in a restaurant is one sample of the population of"
						+ " meals in this restaurant. If the meal is exceptionally bad, it's probably because the chef is not very good <em>and</em> some accidents happened in the kitchen."
						+ " If the date is just in every way perfect, it's probably because the other person is amazing <em>and</em> you also by chance had a great day with good"
						+ " weather, or you were especially confident, or you picked exactly the right topics to talk about etc."
						+ " Extreme values are influenced by skill and luck. The more picks or tries from a population you have, the more "
						+ " the effect of good and bad luck will even out. If you go to the restaurant three more times, the average probably will be disappointing, but not an "
						+ " outright disaster anymore. <strong>The larger your sample is, the more it is representative of the actual qualities</strong>."); break;
			case 2:	ui.tell("Gambler's Fallacy can be explained easily with fair dice."
					+ " The probability for a throw of the fair, six-sided die to result in a 6 is always 1/6, regardless of how long you've been waiting for a six."
					+ " Some people think things like 'Oh, it was a six last time, so it won't come up again' or 'there has been no six in such a long time, it"
					+ " HAS to come up with a six now'. But this is not true. Outcomes from a chance setup are <strong>random</strong>, if and only if there are <strong>not influenced by the outcomes on previous trials.</strong>"
					+ " Probability doesn't care if the last ten throws showed some specific 'pattern'."
					+ " Chance does not try to look nice. Independent random events always have the same probability."); break;
			case 3:	ui.tell("Think about this example: You have to enforce a rule in a library that says that 'IF someone's ID card is from another university, "
					+ "THEN they have to have a special permit to use the library'. The condition P is 'ID from another university', the consequence Q is"
					+ " 'required to have special permit', and the whole rule is 'If P, then Q'. Which people do you have to check: a person"
					+ " comes from another university (P), a person from this university (not P), a person that has a special permit (Q), or a person that"
					+ " has no special permit (not Q)? The rule is broken if 'P' and 'not Q' occur. You have to check the person that is from another university,"
					+ " because if that person has no permit (not Q), than the rule is broken. But you also have to check the person with no special permit (not Q), "
					+ " because if they also happen to be from another university, the rule is still violated. To sum it up, if you want to make sure a rule"
					+ " about 'IF P THEN Q' is satisfied, you have to check both the 'P' and the 'not Q' cases." ); break;
			case 4:	ui.tell("Think about this: if you have purchased a nonrefundable ticket to a concert, and you are feeling ill, you might attend the concert anyway"
					+ " because you do not want the ticket to go to waste. However, the money spent to buy the ticket is sunk, and the cost of the ticket is entirely"
					+ " irrelevant, whether it cost $5 or $100. The only relevant consideration is whether you would derive more pleasure from attending the concert"
					+ " or staying home on the evening of the concert. You should not throw good money (or time) after bad, but just take whatever action"
					+ " has the best outcomes."); break;
			/*case 5:	ui.tell("One should evaluate decisions based on the knowledge that was available to the decision maker at that time. No decision maker can know"
					+ " what will happen in the future. It can be correct to take a calculated risk - say, a 90% chance of success under some circumstances - even though it can sometimes"
					+ " turn out badly. Vice versa, a ridiculus decision is not more justified or rational just because the decision maker came out unharmed because of"
					+ " a great deal of luck. You wouldn't consider balancing on a rope over a gaping abyss with closed eyes a good idea just because it worked once,"
					+ " and you wouldn't condemn a player who bet on a 90% success and than suffered misfortune."); break;*/
			case 6:	ui.tell("In logical conclusions, it's not about what seems realistical, but what follows logically."
					+ " When you decide that a syllogism is valid, you are saying <em>'If the premises are true, then the conclusion must be true.'</em>"
					+ " If you can find any counterexample where the premises are true but the conclusion is not, then it's not valid."
					+ " Look at the following example. Premise 1: All As are Bs. Premise 2: All Cs are Bs, too. Can you conclude that all Cs are As as well?"
					+ " Think about if you can"
					+ " find counterexamples. Maybe visualize it. In this case, if you replace A with chairs and B with furniture and C with beds, it becomes evident that the conclusion"
					+ " is not logically vald.  insertimage:venn.png But most importantly: don't follow your intuition, but just the clear logical rules in front of you."); break;
			case 7:ui.tell("For example, let's say you want to determine wheter a new diet works. 200 overweight people try it out and loose weight, 75 try it without success,"
					+ " 50 overweight people who don't try the diet loose weight, too, and 15 don't try it and have also no improvement."
					+ " Most people just see the 200 for whom it worked. insertimage:diet.png They say: 'Oh, more people who used the diet lost weight! It must be effective.'"
					+ " But - they <strong>fail to consider the control group</strong> who did not try the diet."
					+ " 50 of those 65 also lost weight (>75%), which is approximately the same chance that the people with the diet have (200/275)."
					+ " The probability of improvement is <strong>the same</strong> for people on the diet and people not on the diet, therefore it is <strong>completely ineffective</strong>."
					+ " Always remember to compare to the control group!"); break;
				
			default : //do nothing
			}
	
	
			
			
			Random rand = new Random();
			int proceed = rand.nextInt(100)%2;
			if(proceed==0 ){
				//askToProceed();
			}
			
			
			
			
			
		}
		return false;		
		
		
		
	
	
	}
	
	
	
	private void askToProceed() {
		String proceed = pbase.getReply(ReplyType.PROCEED,0);
		ui.tell(proceed);
				
		String answer = ui.listen_timeout(0);
		Vector<InputType> reply1 = parser.parseInput(answer, ContextType.GOON, null);
		Vector<InputType> reply = parser.parseInput(answer, ContextType.AGREEMENT, null);

		reactToGenerals(reply,true);
		
		if(reply.contains(InputType.STOP)){
			ui.tell("Oh... uhm... Maybe I misunderstood you.");
			if(!askbreak()) {
				abort();
			}
			else {
				ui.tell("Okay, let's continue!");
				return;
				
			}
			
		}
		
		if(reply.contains(InputType.WAIT)){
			answer = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(answer, ContextType.AGREEMENT, null);			
		}
		
		if(reply.contains(InputType.CORRECT) || reply1.contains(InputType.ANSWER1)){
			Random rand = new Random();
			int ok = (rand.nextInt(100)+1)%3; 
			if(ok%3 == 0) ui.tell(pbase.getReply(ReplyType.OK,0));
			return;
		}
		else if(reply.contains(InputType.INCORRECT)){
			answer = askForever(15,"Hm, I see... Do you still want to continue with the learning?");
			reply = parser.parseInput(answer, ContextType.AGREEMENT, null);
			if(reply.contains(InputType.WAIT)){
				answer = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(answer, ContextType.AGREEMENT, null);
			}				
			if(reply.contains(InputType.CORRECT)){
				ui.tell("Allright then.");
			}
			else {
				abort();
			}
		}
		else {
			reply = parser.parseInput(answer,ContextType.CLARIFICATION, null);
			if(reply.contains(InputType.ANSWER1)){
					ui.tell(pbase.getReply(ReplyType.OK,0));
					return;
			
			}
			else {
				ui.tell("I will just go on.");
				return;
			}

		
			
		}

	}

	private void explain_first(int type) {
		//System.out.println("explain_first");
		
		boolean knows_already = explain_type(type,0);		
		boolean something = false;
		
		if (knows_already){
			return;
		}
		
		String answer = pbase.getReply(ReplyType.CLARIFICATION, 0); 
		String input = askForever(12,answer);
		Vector<InputType> reply = parser.parseInput(input, ContextType.CLARIFICATION, null);
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.CLARIFICATION, null);			
		}
		
		
		if (reply.contains(InputType.NO_CLUE)){
			String comfort = pbase.getReply(ReplyType.COMFORT,0);
			ui.tell(comfort);
			something = true;
		}
		
		if (reply.contains(InputType.ANSWER1) || reply.contains(InputType.NO_CLUE)){
			explain_type(type,1);
			answer = pbase.getReply(ReplyType.WILL_TELL, 0); 
			ui.tell(answer);
			something = true;
			return;
		}
		else if (reply.contains(InputType.ANSWER2)){
			String confidence = pbase.getReply(ReplyType.CONFIDENCE,0);
			ui.tell(confidence);
			something = true;
			return;
		}
		else if  (reply.size() >= 2){
			reactToGenerals(reply,true);	
			something = true;
		}
		
		if(!something){
			
			
			
			
			ui.tell("Sorry, I did not understand.");
		}
		
		
		input = askForever(10,"Do you want more explanation, yes or no?");
		reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		}
		
		if (reply.contains(InputType.CORRECT)){		
			explain_type(type,1);
			answer = pbase.getReply(ReplyType.WILL_TELL, 0); 
			ui.tell(answer);
			return;
		}
		else if (reply.contains(InputType.INCORRECT)){
			String ok = pbase.getReply(ReplyType.OK,0);
			ui.tell(ok);
			return;
		}
		else {
			reply = parser.parseInput(input, ContextType.CLARIFICATION, null);
			if(reply.contains(InputType.ANSWER1)){	
				explain_type(type,1);
				answer = pbase.getReply(ReplyType.WILL_TELL, 0); 
				ui.tell(answer);
				return;
			}
			else if (reply.contains(InputType.ANSWER2)){
				String ok = pbase.getReply(ReplyType.OK,0);
				ui.tell(ok);
				return;
			}
			else {				
				ui.tell("Hm, I'm not very good at understanding what you're saying. Probably it's best if we just continue with the story and see how you can handle it.");
				return;
			}
		}
	
	}



	private void askIntro(Story story) {
		String answer;
		//System.out.println(story.intro);
		//System.out.println(ueval.games);
		
		if(story.intro.equals("Have you ever been in a hospital?") && ueval.hospital >0){

			if(ueval.hospital == 1){
				ui.tell("Since you've been in a hospital, you're practically a health expert to me. Let's see how you can handle this one.");
			}
			if(ueval.hospital == 2 || ueval.hospital == 3){
				ui.tell("Uh, another thing about humans and their health business. I'm glad I can't break a leg or get a cold. On the other hand,"
						+ " I can get a virus! Anyway, let's go to the question.");
			}			
			
			
		}

		else if(story.intro.equals("Are you good in biology?") && ueval.biology > 0){
			if(ueval.video == 1){
				ui.tell("Oh, this question is perfect for biologists (I guess)! Have fun!");
			}
			if(ueval.video == 2 || ueval.video == 3){
				
				ui.tell("Oh, yet another question about living things. I wish you were a biologist.");
			}			
		}
		
		else if(story.intro.equals("Do you like to play video games?") && ueval.video > 0){
			if(ueval.video == 1){
				answer = pbase.getReply(ReplyType.VIDEO, 0);
				ui.tell(answer);
			}
			if(ueval.video == 2 || ueval.video == 3){
				answer = pbase.getReply(ReplyType.NOVIDEO, 0);
				ui.tell(answer);
			}			
		}
		
		else if(story.intro.equals("Do you like board games?") && ueval.games > 0){
			if(ueval.games == 1){
				answer = pbase.getReply(ReplyType.GAMES, 0);
				ui.tell(answer);
			}
			if(ueval.games == 2 || ueval.games == 3){
				answer = pbase.getReply(ReplyType.NOGAMES, 0);
				ui.tell(answer);
			}
		}
		else if(story.intro.equals("Do you like gambling?") && ueval.gambling > 0){
			if(ueval.gambling == 1){
				answer = pbase.getReply(ReplyType.GAMBLER, 0);
				ui.tell(answer);
			}
			if(ueval.gambling == 2){
				answer = pbase.getReply(ReplyType.NOGAMBLER, 0);
				ui.tell(answer);
			}
		}
		else if(story.intro.contains("Or do you prefer dogs?") && ueval.catdog > 0){
			if(ueval.catdog == 1){
				answer = pbase.getReply(ReplyType.CAT, 0);
				ui.tell(answer);
			}
			if(ueval.catdog == 2){
				answer = pbase.getReply(ReplyType.DOG, 0);
				ui.tell(answer);
			}
		}
		else if(story.intro.equals("Is there any city you would like to travel to one day?") && !(ueval.city.equals(""))){
				pbase.initCity(ueval.city);
				answer = pbase.getReply(ReplyType.CITY, 0);
				askForever(10,answer);
				String reply = pbase.getReply(ReplyType.THANKS, 0);
				ui.tell(reply);
		}
		
		/*		else if (){
			
		}*/
		else {
			
			String input = askForever(12,story.intro);
			if (state == ProgramState.FINISH){
				goodbye();				
			}
			else {
				
				if (input.length() > 0){
					Vector<InputType> reply = parser.parseInput(input, ContextType.INTRO, story);	
					reactToGenerals(reply);			
					if(reply.contains(InputType.WAIT)){
						input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
						reply = parser.parseInput(input, ContextType.INTRO, null);
					}
					reactToGenerals(reply);			
					if(reply.contains(InputType.ANSWER1)){
						answer = story.intro1;
						ui.tell(answer); // tell it			
					}
					else if(reply.contains(InputType.ANSWER2)){
						answer = story.intro2;
						ui.tell(answer); // tell it			
					}
					else if(reply.contains(InputType.NO_IDEA)){
						answer = pbase.getReply(ReplyType.MEANINGLESS, 0);
						ui.tell(answer); // tell it			
					}
				}
				
				else {
	
					answer = pbase.getReply(ReplyType.WILL_TELL, 0); 
					ui.tell(answer); // tell it
					
				}
			}
		}
	}






	private boolean reactToGenerals(Vector<InputType> reply) {
		return reactToGenerals(reply,true);
		
	}

	private void explainStory(Story story, int i) {

		ui.tell(story.explain);
		
		if(i == 0){
			ui.tell(pbase.getReply(ReplyType.RIGHTANSWER, 0));
			return;
		}
		else {
			String answer = pbase.getReply(ReplyType.ASSURE, 0); // get a question about the understanding of the user
			ui.tell(answer); // ask it
			String input = askForever(12,"");
			Vector<InputType> reply = parser.parseInput(input, ContextType.EXPLANATION, null);
		
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.EXPLANATION, null);
			}
			
			
			boolean reacted = reactToGenerals(reply);
			if (reply.contains(InputType.CORRECT)){
				answer = pbase.getReply(ReplyType.ENCOURAGE, 0); 
				ui.tell(answer); 
			}
			if (reply.contains(InputType.INCORRECT)){
				answer = pbase.getReply(ReplyType.REGRET, 0); 
				ui.tell(answer); 
				explain_type(story.type,1);
				
			}
		}
		return;
	}





		
	//method for handling not context specific tags
	//those two given here are merely examples.
	private boolean reactToGenerals(Vector<InputType> reply, boolean questions) {
		boolean reacted = false;
		
		if (reply.contains(InputType.STOP)){
			ui.tell("Oh...! Well... I'm not sure if I got you right...");
			if(askbreak()){
				reacted = true;
			}
			else {
				abort();
				reacted = true;
				return reacted;
			}
			
		}
		
		if (reply.contains(InputType.HOW)){
			String purpose = pbase.getReply(ReplyType.HOW,0);
			reacted = true;
			ui.tell(purpose);
		}

		else if (reply.contains(InputType.PURPOSE)){
			String purpose = pbase.getReply(ReplyType.PURPOSE,0);
			reacted = true;
			ui.tell(purpose);
		}
		else if (reply.contains(InputType.ARTIFICIAL_Q)){
			String art = pbase.getReply(ReplyType.ARTIFICIAL_Q, 0);
			reacted = true;
			ui.tell(art);
		}
		else if (reply.contains(InputType.ARTIFICIAL_Q)){
			String art = pbase.getReply(ReplyType.ARTIFICIAL, 0);
			reacted = true;
			ui.tell(art);
		}
		
		else if (reply.contains(InputType.YES_NO)){
			String answer = pbase.getReply(ReplyType.YES_NO_RANDOM,0);
			reacted = true;
			ui.tell(answer);						
		}
		else if (reply.contains(InputType.BORING)){
			reacted = true;
			ui.tell("Well, I'm sorry for you. It is actually very interesting to me.");
								
		}
		if (reply.contains(InputType.COOL)){
			reacted = true;
			ui.tell("I'm happy you say that.");
		}
		if (reply.contains(InputType.SELFCRITIC)){
			reacted = true;
			ui.tell("Actually, you are very capable of learning! I like that about you.");
		}

		if (reply.contains(InputType.REQUEST_STORY)){
			ui.tell("Sorry, we have to stick to the order I calculated.");
			reacted = true;
		}
		
		if (reply.contains(InputType.THANKYOU)){
			reacted = true;
			ui.tell("You're welcome.");
		}
		
		if(reply.contains(InputType.FASTER)){
			reacted = true;
			int s = ui.getSpeed();
			if (s < 3){
				ui.tell("Okay, I will go faster.");
				ui.setSpeed(s+1);
			}
			else {
				ui.tell("I'm already going pretty fast.");
			}
		
		}

		if(reply.contains(InputType.SLOWER)){
			reacted = true;
			int s = ui.getSpeed();
			if (s > 0){
				ui.tell("Okay, I will slow down.");
				ui.setSpeed(s-1);
			}
			else {
				ui.tell("I'm already as slow as I can, sorry.");
			}
		
		}

		if (reply.contains(InputType.HATE)){
			reacted = true;
			String comfort = pbase.getReply(ReplyType.REPLY_HATE,0);
			ui.tell(comfort);
		}
		if (reply.contains(InputType.LOVE)){
			reacted = true;
			String comfort = pbase.getReply(ReplyType.REPLY_LOVE,0);
			ui.tell(comfort);
		}
		
		if(questions){
			if(reply.contains(InputType.QUESTION) && !(reply.contains(InputType.HINT )
				|| reply.contains(InputType.EXPLAIN) || reply.contains(InputType.HOW)
				|| reply.contains(InputType.PURPOSE) || reply.contains(InputType.SLOWER) || reply.contains(InputType.FASTER))){
				String answer = pbase.getReply(ReplyType.QUESTION_DEFENSE,0);
				reacted = true;
				ui.tell(answer);			
			}
		}
		return reacted;
	}
	
	
	//method for the initialization of the talk
	private void greet() {
		String name = "";
		String output;
		String input;
		
		Vector<InputType> reply;
		output = pbase.getReply(ReplyType.GREETING,0); // get one of the greetings
		ui.tell(output); //tell it to the user
		input = ui.listen(); // let the user answer 
		reply = parser.parseInput(input, ContextType.GREETING, null); // directly parse the answer

		reactToGenerals(reply); 
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.GREETING, null);
		}
		if (reply.contains(InputType.CORRECT) && !reply.contains(InputType.SELFCRITIC) && !reply.contains(InputType.BORING)){ // if a name was recognized, "CORRECT" signals this.
			name = parser.getString(); //if this is the case, the parser will have saved the information in Information
			ueval.name = name; // set the information in the user evaluation to store the name of the user
			//now init all the name specific message variants
			pbase.setName(ueval.name);
			output = pbase.getReply(ReplyType.NAMED_GREETING,0); // get a name-based reply
			ui.tell(output); //tell it to the user
		}
		if(reply.contains(InputType.INCORRECT)){
			ui.tell("What a weird name. Sorry.");
		}

		
		input = askForever(15,"You are a human, aren't you?");
		reply = parser.parseInput(input, ContextType.HUMAN, null);

		for (int i = 0; i < reply.size(); i++){
			//System.out.println(reply.get(i).name());
		}
		
		reactToGenerals(reply);
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.HUMAN, null);
		}
		if (reply.contains(InputType.WHY)){
			ui.tell("Because I need a human.");
		}
		if (reply.contains(InputType.ANSWER2)){
			ui.tell("Oh, really? In this case, please continue to behave like a human being. I need your help.");
		}
		else if (reply.contains(InputType.ANSWER1)){
			ui.tell("Hm... To be honest, I don't believe you. But you probably can still help me!");
		}
		
		else if (reply.contains(InputType.CORRECT)){
			ui.tell("Great! This means you can help me.");
		}
		
		else if (reply.contains(InputType.INCORRECT)){
			input = askForever(8,"Are you sure? What is the sum of two and three?");
			reply = parser.parseInput(input, ContextType.CAPTCHA, null);
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.CAPTCHA, null);
			}
			reactToGenerals(reply);
			if(reply.contains(InputType.CORRECT)){
				ui.tell("See! You solved the captcha. You are sufficiently human for my purposes.");
			}
			if(reply.contains(InputType.INCORRECT)){
				ui.tell("Very funny. Well, you are sufficiently human for my purposes.");
			}
			if(reply.contains(InputType.NO_IDEA)){
				ui.tell("Can we please agree that you said five?");
				ui.tell("Because I really need humans to help me.");
			}
		}
		else  {
			if(input.toUpperCase().contains("MAYBE")){
				ui.tell("It's very homo sapiens to say 'maybe'.");
			}
			ui.tell("Well, you seem sufficiently human to help me.");
		}
		
		input = ui.listen_timeout(10);
		reply = parser.parseInput(input, ContextType.HUMAN_HELP, null);
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.HUMAN_HELP, null);
		}
		if ((reply.contains(InputType.ANSWER1) || reply.contains(InputType.WHY))  && !reply.contains(InputType.FASTER) &&
				!reply.contains(InputType.SLOWER) ){
			ui.tell("Oh, I'm glad that you ask!");
		}
		if (reply.contains(InputType.ANSWER2)){
			ui.tell("Oh, but I really need some help!");
		}
		
		ui.tell("My programmers want me to teach you how to be rational, make good decisions and judge situations correctly.");
	
		
		input = askForever(12,"Do you want to be more rational?");

	
		reply = parser.parseInput(input, ContextType.WANT_RATIONAL, null);
			
		if(input.toUpperCase().contains("MAYBE")){
			ui.tell("Come on!");
		}
		
		reactToGenerals(reply); 
		
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.WANT_RATIONAL, null);
		}
		if (reply.contains(InputType.CORRECT)){
			ui.tell("Yeah, that's the spirit!");
		}

		else if (reply.contains(InputType.WHY)){
			ui.tell("Because being rational is necessary to solve problems and reach goals.");
		}
		else if (reply.contains(InputType.INCORRECT)){
			input = askForever(12,"Why would you think that? Rationality is just the ability to make good decisions. Do you want to be able to make good decisions?");
			reply = parser.parseInput(input, ContextType.WANT_RATIONAL, null);

			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.WANT_RATIONAL, null);
			}
			if (reply.contains(InputType.CORRECT))	ui.tell("I'm glad you say that!");
			else if  (reply.contains(InputType.INCORRECT)){
				abort();
			}
			
		}
		
		ui.tell("I will just try to ask you some questions, and try to explain to you what you could do better. If I do a bad job at explaning, just ask me, ok? I never taught humans before.");
		input = ui.listen_timeout(5);
		reply = parser.parseInput(input, ContextType.UNSURE, null);
		reactToGenerals(reply); 
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.UNSURE, null);
		}
		if (reply.contains(InputType.ANSWER1)){
			ui.tell("I hope you're right!");
		}
		if (reply.contains(InputType.WHY)){
			ui.tell("I'm fairly new software.");
		}
		ui.tell("I hope I will be a good teacher! I'm a bit nervous.");
		input = ui.listen_timeout(1);
		reply = parser.parseInput(input, ContextType.UNSURE, null);
		
		
		for (int i = 0; i < reply.size(); i++){
			//System.out.println(reply.get(i).name());
		}
		
		if(reply.contains(InputType.QUESTION) || reply.contains(InputType.PURPOSE)|| reply.contains(InputType.YES_NO)){
			ui.tell("Now you're making me even more nervous. Please let me teach you.");
			
		}
		else {
			reactToGenerals(reply); 
		}
		
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.UNSURE, null);
		}
		if (reply.contains(InputType.ANSWER1)){
			ui.tell("Thank you. It's nice that you say that.");
		}
		if (reply.contains(InputType.WHY)){
			ui.tell("Because it's the first time!");
		}
		
		ui.tell("So, let's see... the first thing I want you to know is that you don't have to be extremely intelligent to be"
				+ " rational. There are very intelligent people who do things that are not at all reasonable. The key to rational "
				+ "decisions is to know when not to follow your gut feelings, but to stop and actually think about the problem.");
		input = ui.listen_timeout(5);
		if (input.length() > 2){
			reply = parser.parseInput(input, ContextType.NOTHING, null);
			reactToGenerals(reply);
		}
		input = askForever(10,"Just to get used to the situation - what if I asked you a test question? Just to make sure I'm doing this right?");
		reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		reactToGenerals(reply); 
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		}
		if (reply.contains(InputType.CORRECT)){
			ui.tell("Okay, thank you.");
		}
		else if (reply.contains(InputType.INCORRECT)){
			ui.tell("I think I would nevertheless try the test question.");
		}
		else if (reply.contains(InputType.WHY)){
			ui.tell("Because it seems like a good idea!");
		}
		
		input = askForever(15,"This is my first question: Do people need to follow their gut feelings to make rational decisions?");
		//System.out.println("Answer was: " + input);
		reply = parser.parseInput(input, ContextType.GUT, null);
		boolean answered = false;
		while(!answered){
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.GUT, null);
			}
			
			if(reply.contains(InputType.CORRECT)){
				ui.tell("Amazing! I mean, it was easy, I know, but you did it. Very reasonable of you to say this!");
				ui.tell("Now I feel prepared for the actual teaching. This is very exciting.");
				answered = true;
			}
			else if(reply.contains(InputType.INCORRECT)){
				answered = true;
				ui.tell("Uhm... no. This is a bit awkward. Following you gut feelings means not to think about something, but just go with what feels right."
						+ " A lot of psychologists have shown that people tend to make a lot of mistakes when they make decisions that way.");
				input = askForever(12,"Is this clear to you now?");
				reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				}
				if (reply.contains(InputType.CORRECT)){
					ui.tell("Okay, thank you.");
				}
				else if (reply.contains(InputType.INCORRECT)){
					input = askForever(15,"Hm, I see... do you still want to continue?");
					if(reply.contains(InputType.WAIT)){
						input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
						reply = parser.parseInput(input, ContextType.AGREEMENT, null);
					}
					reply = parser.parseInput(input, ContextType.AGREEMENT, null);				
					if(reply.contains(InputType.CORRECT)){
						ui.tell("Allright then.");
					}
					else {
						state = ProgramState.ERROR;
					}
				}
			}
			else {
				String q = pbase.getReply(ReplyType.REPHRASE, 0);
				input = askForever(15,q);
				reply = parser.parseInput(input, ContextType.GUT, null);
			}
			
		}
		
		if (name.equals("")){
			
			ui.tell("One last thing before we start...");
			input = askForever(15,"What was your name again?");
			reply = parser.parseInput(input, ContextType.NAME, null); // directly parse the answer
			reactToGenerals(reply);
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.GREETING, null);
			}
			if (reply.contains(InputType.CORRECT)  && !reply.contains(InputType.SELFCRITIC) && !reply.contains(InputType.BORING)){ // if a name was recognized, "CORRECT" signals this.
				//System.out.println("Name");
				name = parser.getString(); //if this is the case, the parser will have saved the information in Information
				ueval.name = name; // set the information in the user evaluation to store the name of the user
				//now init all the name specific message variants
				pbase.setName(ueval.name);
				output = pbase.getReply(ReplyType.NAMED_GREETING,0); // get a name-based reply
				ui.tell(output); //tell it to the user
			}
			if(reply.contains(InputType.INCORRECT)){
				ui.tell("What a weird name. ");
			}

			if (reply.contains(InputType.WHY) || reply.contains(InputType.NO_IDEA)){
				ui.tell("Just curiosity. You don't have to tell me. Nevermind.");
			}
		}


		state = ProgramState.SETUP;
		return;
		
	}
	private void abort() {
		ui.tell("I can only teach you if you want to learn. If you don't, I will simply disconnect from this chat. So, last chance: Do you want to learn how to reason?");
		String input = ui.listen();
		Vector<InputType>reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		
		if (reply.contains(InputType.CORRECT)){
			ui.tell("Okay, thank you.");
			return;
		}
		else if (reply.contains(InputType.INCORRECT)){
			ui.tell("Well, then - goodbye.");
			ui.goodbye();
		}
				
		while(!(reply.contains(InputType.CORRECT) || (reply.contains(InputType.INCORRECT)))){
			
			ui.tell("Sorry? I did not understand. Do you want to learn?");
			input = ui.listen();
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);

			if (reply.contains(InputType.CORRECT)){
				ui.tell("Okay, thank you.");
				return;
			}
			if (reply.contains(InputType.INCORRECT)){
				ui.tell("Well, then - goodbye.");
				ui.goodbye();
				System.exit(0);
			}
			
		
		}
	}
		

	// this is our main controlling routine
	public static void main(String[] args)
	{
					

		String logfile = args[0];
		
		Storystore store = new Storystore();  
		ControlUnit cu = new ControlUnit(amount,store,logfile);
		
		
		store.readStories();
		parser.init(store);	
		//cu.greet(); 
		
		state = ProgramState.SETUP;
		amount = 14;
		number = 2;
		ControlUnit.ueval.setamount(amount, number);
		 
			if((amount / cu.leftTopics.size()) > number){
				cu.ui.tell("You can't have a total of " + amount + " questions in " + cu.leftTopics.size() + " topics  and not more than " + number + " question(s)"
						+ " per topic at the same time.");
				state = ProgramState.ERROR;
			}
			
		//System.out.println("Now fetch!");
		cu.fetchQuestions();
		//System.out.println("Now execute!");
		cu.executeQuestions();
		
	
		if(state == ProgramState.EVALUATION) state = ProgramState.FEEDBACK;
		
		if(ControlUnit.ueval.name.length() > 1){
			cu.ui.tell("Well, now we're done. Thank you for staying with me and doing this, "+ ControlUnit.ueval.name + ".");
		}
		else {
			cu.ui.tell("Well, now we're done. Thank you for staying with me and doing this.");	
		}
		
		
		if(state == ProgramState.FEEDBACK){
			cu.giveFeedback();	
			state = ProgramState.FINISH;
		}

		if(state == ProgramState.FINISH){		
			cu.goodbye();
		}
		if (state == ProgramState.ERROR){
			cu.error();
		}
		ControlUnit.logfiler.closeWriter();
		
	cu.ui.goodbye();
	
	return;
	
}





	private void executeQuestions() {	

		boolean skip = false;
		for (int k = 0; k < collectstories.size() && state == ProgramState.EVALUATION; k++){
			// ask story of storylist
			
			if((collectstories.size()-k) == 1 && real && k != 0){

				if (ueval.name.equals(""))	ui.tell("This is the last question. Please do your best!");
				else	ui.tell("Okay, "+ ueval.name + ", this ist the last question. Please do your best!");
			}
			//System.out.println("now ask the story");
	
			if(in_a_row == 3 && !in_a_rowed){
				ui.tell("You're really good at this, aren't you? You just solved three questions correctly in a row!");
				in_a_rowed = true;
			}
			
			if(ControlUnit.ueval.getcorrect() == 10){
				if (ueval.name.equals(""))	ui.tell("By the way, you do better than most test subjects on this!");
				else	ui.tell("By the way, "+ ueval.name + ", you do better than most test subjects on this!");
			}
			if(ControlUnit.ueval.getcorrect() == 15){
				ui.tell("Wow, you are really amazing. Probably the most rational human being I have ever met!");
			}
			if (ControlUnit.ueval.getincorrect() == 5){
				ui.tell("I'm sure you are learning a lot, aren't you?");
			}
			if (ControlUnit.ueval.getincorrect() == 10){
				if (ueval.name.equals(""))	ui.tell("It's very interesting to teach you, although I still have to learn a lot about communicating with humans.");
				else	ui.tell("It's very interesting to teach you, " + ueval.name + ", although I still have to learn a lot about communicating with humans.");
			}
			
			if (k == 4){
				
				if (ueval.name.equals(""))	ui.tell("Well, it's been a lot of fun teaching you so far.");
				else	ui.tell("Well, it's been a lot of fun teaching you so far, " + ueval.name + ".");
		
				String input = ui.listen_timeout(5);
				if (ControlUnit.ueval.getcorrect() > 0){
					ui.tell("I think I'm getting used to this!");
				}
				input = askForever(12,"Do you want me to speak faster? Or slower?");
				Vector<InputType> reply = parser.parseInput(input, ContextType.SPEED, null);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.SPEED, null);
				}
				if (reply.contains(InputType.ANSWER1)){
					ui.tell("Okay, I'll try to speak a little slower.");
					ui.setSpeed(0);
				}
				else if  (reply.contains(InputType.ANSWER2)){
					ui.setSpeed(2);
					ui.tell("I can do that!");	
				}
				else if (reply.contains(InputType.INCORRECT)){
					ui.tell("Okay, I just wanted to make sure.");
				}
				else {
					ui.tell("Well, I'm not sure what you said. I'll just keep my tempo.");
				}
			}
			
			if (k == 8){
				if (ueval.name.equals("")){
					ui.tell("You already did half of the questions!");
				}
				else {
					ui.tell("Wow, " + ueval.name + ", you already did half of the questions!");
				}
				
				String input = askForever(10,"How do you like learning about rationality so far?");
				Vector<InputType> reply = parser.parseInput(input, ContextType.LIKE, null);

				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.LIKE, null);
				}
				if(reply.contains(InputType.LOVE) && !reply.contains(InputType.HATE)){
					ui.tell("Wow! I ... I did not expect that. Just wow!");
					
				}
				if(!reply.contains(InputType.LOVE) && reply.contains(InputType.HATE)){
					ui.tell("Phew... humans can be very tough. I have to try even harder then...");
				}
				if(reply.contains(InputType.INTERESTING) && !reply.contains(InputType.HATE)){
					//interesting
					ui.tell("That's true! I find this whole experiment really fascinating.");
				}
				if(reply.contains(InputType.BORING)){
					ui.tell("But you get to learn about how to make your brain work better. I would really love"
							+ " to learn how to improve my code.");
					//boring
				}

				if(reply.contains(InputType.COOL) && !reply.contains(InputType.HATE)){
					if (ueval.name.equals(""))	ui.tell("Thank you, it's nice that you say that.");
					else	ui.tell("Thank you, " + ueval.name + ". It's so nice that you say that.");
					//awesome
				}

				if(reply.contains(InputType.BAD) && !reply.contains(InputType.LOVE)){
					if (ueval.name.equals(""))	ui.tell("Sorry. I will try to do better.");
					else	ui.tell("I'm sorry, " + ueval.name + ". I will try to do better.");
					//bad stuff
				}
				
				if(reply.contains(InputType.OK) && !reply.contains(InputType.LOVE)&& !reply.contains(InputType.HATE)
						&& !reply.contains(InputType.BORING)&& !reply.contains(InputType.INTERESTING)
						&& !reply.contains(InputType.COOL) && !reply.contains(InputType.BAD)){
					ui.tell("Well, it's something.");
					
				}
				
				if(reply.contains(InputType.HATE) || reply.contains(InputType.BAD) || reply.contains(InputType.BORING)){
					input = askForever(10,"What can I do better?");
					if (ueval.name.equals(""))	ui.tell("Well, I'll tell my programmers about this. Thank you for your feedback!");
					else	ui.tell("Well, I'll tell my programmers about this, " + ueval.name + ". Thank you for your feedback!");
				}
				
				if(reply.size() > 0)	ui.listen_timeout(5);
				else ui.tell("Hm, okay.");
				ui.tell("So, let's go on with the questions!");
			}
			
			if (k == 10){
				if (ControlUnit.ueval.getcorrect() > 4){
					ui.tell("I'm more and more convinced that I could be a pretty good teacher someday. What do you think?");
					ui.listen_timeout(5);
				}
				String input;
				
				if (ueval.name.equals("")) input = askForever(10, "Do you like talking to me?");
				else input = askForever(10, "Do you like talking to me, " + ueval.name + "?");				
				Vector <InputType> reply = parser.parseInput(input, ContextType.AGREEMENT, null);

				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				}
				if(reply.contains(InputType.CORRECT) || reply.contains(InputType.LOVE) || reply.contains(InputType.COOL)) ui.tell("Wow, that's so nice! I have to log this and show it to my programmers later.");
				else if(reply.contains(InputType.INCORRECT)) ui.tell("Well, I should have expected that.");
				else 		reactToGenerals(reply);

				input = ui.listen_timeout(3);
				reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				reactToGenerals(reply);
				
			}
			
			
			if (k == 10){
				if(ueval.getcorrect() > 5 && ueval.getcorrect() < 10){
					ui.tell("You already changed my view on humans. They're not as irrational as I thought!");
					ui.listen_timeout(1);

				}
				
				else if (ueval.getcorrect() >= 10){
					ui.tell("You already changed my view on humans. I never thought they could be so good at this!");
					ui.listen_timeout(1);

				}
				else {
					//ui.tell("I can see that you're eager to learn.");
				}
				
				String input = askForever(1,"I really hope you perform well on this small test afterwards. If you do, my"
						+ " programmers will be proud of me!");
				Vector<InputType> reply = parser.parseInput(input, ContextType.TEST, null);

				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.TEST, null);
				}
				if(reply.contains(InputType.ANSWER1)){
					ui.tell("They have to find out if I am good for teaching humans. So they do a five minute test"
							+ " before and after you talk to me and see if you learned anything.");
					
				}
				if(reply.contains(InputType.ANSWER2)){
					ui.tell("Teaching humans is my reasons to exist - of course I want to be good at it. I don't want to be useless!");
					
				}
				if(reply.contains(InputType.CORRECT)){
					ui.tell("Yeah, I really hope that you are right!");
				}
				if(reply.contains(InputType.INCORRECT)){
					ui.tell("You know what? I bet you can do it. Just concentrate on what you're doing and you'll show them how rational you are!");
				}
				
				if (k == 14){
					if(ueval.catdog == 1)
						ui.tell("By the way, you are really nice. I think if I was a cat, I'd like to be your pet.");
					else if(ueval.catdog == 2)
						ui.tell("By the way, you are really nice. I think if I was a dog, I'd like to be your pet.");
					else if (ueval.games == 1){
						ui.tell("By the way, you are really nice. If I was human, I'd love to have a gaming night with you.");
					}
					else if (ueval.video == 2){
						ui.tell("By the way, you are really nice. If I could execute this kind of software, I'd love to play games with you.");
					}
				}
				ui.listen_timeout(4);
				
			}

			Random rand = new Random();
			int announce = 0;
			if (k > 0 ){
				announce = (rand.nextInt(100)+1)%3;  
			}

			
			if(good[collectstories.get(k).type] > 1	){
				switch(collectstories.get(k).type){
				case 0:		ui.tell("The next story is about conditional probabilities."); break;
				case 1:		ui.tell("The next question is about regression to the mean.");break;
				case 2:		ui.tell("The next question is about the Gambler's Fallacy again.");break;
				case 3:		ui.tell("The next story is another one on formal logic.");break;
				case 4:		ui.tell("The next story is again about the Sunk Cost Fallacy.");break;
				case 6:		ui.tell("This story is about syllogistic reasoning.");break;
				case 7: 	ui.tell("The next story is another one about evaluating evidence.");break;
				}
				ui.tell(pbase.getReply(ReplyType.EXPERT, 0));
				String input = ui.listen();
				Vector<InputType> reply = parser.parseInput(input, ContextType.CLARIFICATION, null);
				Vector<InputType> reply2 = parser.parseInput(input, ContextType.AGREEMENT, null);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.CLARIFICATION, null);			
				}
				
				if (reply.contains(InputType.ANSWER1) || reply.contains(InputType.NO_CLUE) || reply2.contains(InputType.INCORRECT)){
					String ok = pbase.getReply(ReplyType.OK,0);
					ui.tell(ok);
				}
				else if (reply.contains(InputType.ANSWER2)||reply2.contains(InputType.CORRECT)){
					String ok = pbase.getReply(ReplyType.OK,0);
					ui.tell(ok);
					skip = true;
				}
				else {
					ui.tell("Hm... I'm not sure what you meant. Perhaps it's better to ask the question instead.");
				}
				
				
			}
			
			
			
			if(!skip){
				askStory(collectstories.get(k), (announce == 0));
				
				if((collectstories.size()-k > 1) && announce == 1){
					askToProceed();
				}
			}
		}			
	}


	private void maybe_askProceed() {
		String answer = pbase.getReply(ReplyType.PROCEED, 0); //ask if he can go on
		ui.tell(answer); // tell it 
		String input = ui.listen();	
		Vector<InputType> reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		reactToGenerals(reply);
		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		}
		if (reply.contains(InputType.INCORRECT)){
			ui.tell("Do you want to say that you want to end the questions?"); // tell it 
			input = ui.listen();
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				if (reply.contains(InputType.CORRECT)){
					state = ProgramState.FEEDBACK;
				}
		}
	
		
	}

	private void fetchQuestions() {
		Random rand = new Random();
		Story[] occupation = new Story[amount];
		boolean end = false;		
		int[] topiccount = new int[20]; // will be used to count how many stories of a topic have been gotten

		int n = 0;
		 while(collectstories.size() < amount && end == false){
			n++;
			
			if(occupation[collectstories.size()] != null){
				//int i = collectstories.size() +1;
				//System.out.print("Number: " + i + " ");
				//System.out.println("put something here!");
				Story s = occupation[collectstories.size()];
				//System.out.print("(2/2) ");
				//System.out.println(s.text.substring(0, 40) + "...");
				collectstories.add(s);
				//System.out.println("Added story (second part) \"" + s.text.substring(0,20) + "...\" of category " + s.type);
				
			}
			else {
					
				int topic = (rand.nextInt(100)+1)%11;  // chose topic at random


				if (n == 2 || n == 4 || n == 7 || n == 9)
					topic = 5;
				
				String chosen = "";
				switch(topic){
					case 0:	chosen = "base_rate"; break;
					case 1: chosen = "sample_size"; break;
					case 2: chosen = "gamblers_fallacy"; break;
					case 3: chosen = "selection_task"; break;
					case 4: chosen = "sunk_cost"; break;
					case 5: chosen = "outcome_bias"; break;
					case 6: chosen = "belief_syllogistic"; break;
					case 7: chosen = "covariation_detection"; break;
				}
				
				//System.out.println("chosen was : "+ chosen);
				
				if(!leftTopics.contains(chosen)){
					//System.out.println(chosen + " not in left topics");	
				}
				if (leftTopics.contains(chosen)){
					// as long as there are still questions in the topic left..
					//System.out.println("Try topic " + topic);
					//System.out.println("of this topic, we have already " + topiccount[topic]);
					
					if(topiccount[topic] < number) {
						// and we have less than five questions of this topic asked...
						if(sstore.storiesLeft(topic)){ 
							int i = collectstories.size() +1;
							Story s = sstore.getStory(topic); 
							//System.out.println("found story \"" + s.text.substring(0,20) + "...\" of category " + s.type);
							
							if (s.construction == 0){
								//System.out.println("is single story - can be added.\n");
								//System.out.println("Added story \"" + s.text.substring(0,4) + "...\" of category " + s.type);
								
								collectstories.add(s);
								//System.out.println("It is number: " + i + ".\n\n ");
								topiccount[topic]++; // we have now gotten one more question of this topic 
							}
							else {
								if (s.construction == 1){ // we got the first part of a two-part-story here.
									//System.out.println("It's a first part. Could be added.");
									if (amount - collectstories.size() > 3){ 
										collectstories.add(s);
										//System.out.println("Added story (first part) \"" + s.text.substring(0,20) + "...\" of category " + s.type);
										
										//System.out.print("It's story number " + i );
										//if(s.construction == 1)	System.out.print(" and Part 1/2.\n\n ");
										//System.out.println("total space: " + amount);
										int room = amount - collectstories.size() -2;
										//System.out.println("Left places between " + (i+2) + " and " + amount + ": " +room);
										for (int a = room*2; a > 0; a--){ // try for a while to find a place
											int secondplace = amount-(rand.nextInt(100)+1)%(room)-1;
											if (occupation[secondplace] == null){												
												//System.out.print("position " + secondplace + " for story\n\n");
												//System.out.println(s.next.text.substring(0, 20) + "(...)\n\n");
												occupation[secondplace] = s.next;
												topiccount[topic]++;
												a = 0;
											}
											else {
												//System.out.println("Place already taken.");
											}
										}
									}
								
									else {
										//System.out.println("But there's no room for the second part.\n");
										// we got no room to add the second part
										// do nothing
									}
								}
								if (s.construction == 2){ // we got the second part of a two-part-story here.
									//do nothing
									//System.out.println("is second part, don't add.\n");
								}
							}
						}
						else {
							leftTopics.remove(chosen); //and if that failed because there were no stories in the topic left: don't chose that topic again
						}
					}
					else {
						//System.out.println("Seems that we got enough of topic " + topic);
						leftTopics.remove(chosen); //if we have already asked enough, remove this
					}
				}
			}
		}
		//System.out.println("(Okay, we got " + collectstories.size() + " stories for you.)");		
		ControlUnit.ueval.putStories(collectstories);
		state = ProgramState.EVALUATION;
		 
		 
	}

		
	
		
		
	

	private void giveFeedback() {
		String feedback = ueval.display();
		ui.tell(feedback);

		ueval.printCalibration();
		String input = askForever(10, "Do you want me to tell you what you did well and what you can improve?");
		Vector<InputType> reply = parser.parseInput(input, ContextType.AGREEMENT, null);

		if(reply.contains(InputType.WAIT)){
			input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
			reply = parser.parseInput(input, ContextType.AGREEMENT, null);
		}
		
		if (reply.contains(InputType.CORRECT) && state == ProgramState.FEEDBACK){
			giveDetailedFeedback();
		}
		else {
			ui.tell(pbase.getReply(ReplyType.OK, 0));
		}
		/*	
			for (int i = 0; i < amount; i++){ 
				boolean explain = false;
				String detail = ueval.detailed_display(i);
				ui.tell(detail);
				if (ueval.evaluations[i].input == InputType.INCORRECT){
					ui.tell("Do you want to know why this was wrong?");
					input = getInput(ContextType.EXPLANATION, null); // get a meaning out of his answer
					reply = parser.parseInput(input, ContextType.EXPLANATION, null);		
					if (reply.contains(InputType.CORRECT) || reply.contains(InputType.WHY)){
						explain = true;
					}
				}
				else if (ueval.evaluations[i].story.explain.length() > 0){
					input = ui.listen();
					reply = parser.parseInput(input, ContextType.RIGHT, null);		
					if (reply.contains(InputType.WHY)){
						explain = true;
					}
				}
				if (explain) {
					ui.tell(ueval.evaluations[i].story.explain + "\n");
				}
			}	
		}
		ui.tell("Would you like to have some tips on improving your rationality?");
		input = getInput(ContextType.EXPLANATION, null); // get a meaning out of his answer
		reply = parser.parseInput(input, ContextType.EXPLANATION, null);
		if (reply.contains(InputType.CORRECT)){
			ui.tell(ueval.tips());
		}
		if (reply.contains(InputType.INCORRECT)){
		
		}
		
		*/

		
	}






	private void giveDetailedFeedback() {

		
		int already = 0;
		
		//base rate
		if(ueval.correct[0] > 1){
			already++;
			ui.tell("You handled the base rate problems very well.");
			ui.listen_timeout(1);
		}
		if (ueval.incorrect[0] > 1){
			ui.tell("It looks like you have some problems with the base rate questions.");
			ui.listen_timeout(1);
			ui.tell("Just remember: when dealing with probabilities, it's best to just take a sheet of paper, imagine 10,000 cases, and"
					+ " dividing those 10,000 into groups depending on what case they fall into. Then it's much easier to see.");
			ui.listen_timeout(1);
		}
		
		//sample size
		if(ueval.correct[1] > 1){
			if (already == 0){
				ui.tell("You seem to be very good with sample size problems. Great!");
				ui.listen_timeout(1);
			}
			if (already == 1){
				ui.tell("You're also very good with sample size problems. Great!");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[1] > 1){
			already = 0;
			ui.tell("Regarding sample size problems, you can improve your rationality by asking yourself: is this selection "
						+ "large enough to be representative? The more examples you have, the closer the average will be to the"
						+ " typical case. Greater numbers are more representative than small samples.");
			ui.listen_timeout(1);
		}

		//gambler's fallacy
		if(ueval.correct[2] > 1){
			if (already == 0){
				ui.tell("You did not get fooled by the Gambler's Fallacy, which is very good.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("The same goes for the Gambler's Fallacy, that failed to confuse you. Awesome!");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[2] > 1){
			already = 0;
			ui.tell("It seems like you sometimes were confused the Gambler's Fallacy. Always remember that independent random events have no memory!"
					+ " There is no pattern in those things. A die that is thrown has no idea what number the last die showed.");
			ui.listen_timeout(1);
		}

		//logical selection
		if(ueval.correct[3] > 1){
			if (already == 0){
				ui.tell("As far as I can tell, you're also quite good with logic, though. You performed very well at the selection task.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("Also, I have to add that you seem to be very good with logical selection tasks.");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[3] > 1){
			already = 0;
			ui.tell("When presented with a logical rule, always try to think of every possibility for every individual bit,"
					+ " and about what could violate the rule.");
			ui.listen_timeout(1);
		}

		//sunk cost
		if(ueval.correct[4] > 1){
			if (already == 0){
				ui.tell("You make pretty rational decisions regarding sunk costs.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("You also make very rational decisions regarding sunk costs.");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[4] > 1){
			already = 0;
			ui.tell("You should watch out to not make bad decisions because of sunk costs - it's not worth to throw more money or time into a "
					+ "lost cause, and you should not give up a better outcome just because you feel bad about a decision in the past.");
			ui.listen_timeout(1);
		}

		//outcome bias
		if(ueval.correct[5] > 1){
			if (already == 0){
				ui.tell("You are quite unaffected by the outcome bias.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("The same goes for the outcome bias, which you pretty much avoided sucessfully.");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[5] > 1){
			already = 0;
			ui.tell("Keep in mind to evaluate a decision with regard to what was known at the time the decision was made.");
			ui.listen_timeout(1);
		}


		//Syllogism
		if(ueval.correct[6] > 1){
			if (already == 0){
				ui.tell("You solved the syllogism I presented you quite successfully.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("And you solved the syllogisms I presented you, too.");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[6] > 1){
			already = 0;
			ui.tell("In logical questions, try not to think about how relatable or common the conclusion is, but if you can actually draw"
					+ " the conclusion from the premises. It helps if you exchange the sentences with other contents or with letters (All A are B etc.)");

			ui.listen_timeout(1);	
		}

		//Covariation
		if(ueval.correct[7] > 1){
			if (already == 0){
				ui.tell("And last but not least: You were exceptionally good at finding out which treatments and interventions were effective when I asked"
						+ " you to evaluate them.");
				ui.listen_timeout(1);
			}
			if (already == 2){
				ui.tell("And last but not least: You were also exceptionally good at finding out which treatments and interventions were effective when I asked"
						+ " you to evaluate them.");
				ui.listen_timeout(1);
			}
		}
		if (ueval.incorrect[7] > 1){
			already = 0;
			ui.tell("When evaluating the effectiveness of an intervention, an experiment or any kind of approach, keep in mind that the outcome has"
					+ " to be significantly better when the method is used (than when it's not used), and look out for the actual percentages!");

			ui.listen_timeout(1);	
		}

		
		//System.out.println("Calibration: " + ueval.getCalibration());
		if (ueval.getCalibration() > 1.2){
			ui.tell("Also, you seem somewhat overconfident about the correctness of your answers.");
		}
		else if (ueval.getCalibration() < 0.8) {
			ui.tell("Also, you should trust your good reason a bit more!");
		}
		else {
			ui.tell("You seem to have a good intuition about how sure you can be of your thoughts. Stick to that!");
		}
		
	}

	private void error() {

		ui.tell("Well, this is awkward."); 
		String goodbye = pbase.getReply(ReplyType.GOODBYE, 0); //get a general goodbye message
		ui.tell(goodbye); 

		ui.goodbye();
	}






	private void goodbye() {

		ui.tell("What I want you to keep in mind when making decisions is:"
				+ " think twice and not simply jump to the first conclusion that comes to mind, but actually think of what option actually most reasonable."
				+ " This can safe you from a lot of errors.");
		
	
		
		ui.tell("I hope you will do well on the test now, my human friend! Show them that I taught you well.");
		
		
		if(ueval.name.length() >0){
			String goodbye = pbase.getReply(ReplyType.NAMED_GOODBYE, 0); //get a named goodbye message
			ui.tell(goodbye); // tell it
		}
		else {
			String goodbye = pbase.getReply(ReplyType.GOODBYE, 0); //get a general goodbye message
			ui.tell(goodbye); 
		}
		
	}






	private boolean askbreak() {
		String input;
		Vector<InputType> reply;
		ui.tell("Do you want to continue with the questions?"); 

		while(true){
			input = ui.listen(); // let the user answer 
			//System.out.println(input);
			reply = parser.parseInput(input, ContextType.AGREEMENT, null); 
			if(reply.contains(InputType.WAIT)){
				input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
				reply = parser.parseInput(input, ContextType.AGREEMENT, null);
			}
			if (reply.contains(InputType.CORRECT)){ 
				ui.tell("Very good. We shall continue with the questions.");
				return true;
			}
			else if (reply.contains(InputType.INCORRECT)){ 
				ui.tell("I'm sorry. Did I do something wrong?");
				input = ui.listen(); // let the user answer 
				reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				if(reply.contains(InputType.WAIT)){
					input = askForever(60,pbase.getReply(ReplyType.TIME, 0));
					reply = parser.parseInput(input, ContextType.AGREEMENT, null);
				}
				if (reply.contains(InputType.CORRECT)){ 
					ui.tell("What did I do wrong?");
					ui.listen();
					ui.tell("Thank you very much for your feedback.");
					state = ProgramState.FEEDBACK;
					return false;
					
				}
				else if (reply.contains(InputType.INCORRECT)){ 
					ui.tell("Thats a relief. But was was the problem then?");
					input = ui.listen(); // let the user answer 
					ui.tell("Thank you very much for your feedback.");
					state = ProgramState.FEEDBACK;
					return false;
				}
				else {
					ui.tell("Please describe the reason why you want to quit.");
					input = ui.listen(); // let the user answer 
					ui.tell("Thank you very much for your feedback.");
					state = ProgramState.FEEDBACK;
					return false;
					
					
				}
				
			}
			
			else {
				ui.tell("Excuse me? I'm afraid I did not understand you."); 
				ui.tell("Do you want to continue with the questions?"); 
			}
		}
	}





}
