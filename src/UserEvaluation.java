
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;


public class UserEvaluation {
	
	
	//user infos and stats
	String name = "";
	int amount;
	int number;
	String logfile = "";
	
	Vector<Double> calibration = new Vector<Double>(10,1);
	
	int[] correct = new int[10];
	int[] incorrect = new int[10];
	int totalc = 0;
	int totali = 0;
	Evaluation[] evaluations;
	Logfiler logfiler;
	public int gambling = 0; //0 undefined, 1 yes, 2 no, 3 no idea
	public int catdog = 0; //0 undefined, 1 cat, 2 dog, 3 no idea
	public String city = "";
	public int games = 0; //0 undefined, 1 yes, 2 no
	public int video = 0; //0 undefined, 1 yes, 2 no
	public int hospital =0;//0 undefined, 1 yes, 2 no, 3 no idea
	public int biology = 0;//0 undefined, 1 yes, 2 no, 3 no idea
	
	
	public int getcorrect(){
		return totalc;
	}
	
	public int getincorrect(){
		return totali;
	}
	
	public void increment_r(int i) {
		System.out.println("increment once at type " + i);
		correct[i] = correct[i] + 1;
		totalc++;
		//increment here if the user scored something, meaning he got an answer right
	/*
		switch (i) {
        case 0:  formal++;
				System.out.println("(Debug: got formal right)");
                 break;
        case 1: rhetorical++;
				System.out.println("(Debug: got rhetorical right)");
        		break;
        case 2: statistical++;
				System.out.println("(Debug: got statistical right)");
				break;
        case 3: consistency++;
				System.out.println("(Debug: got consistency right)");
        		break;
        case 4: evidence++;
				System.out.println("(Debug: got evidence right)");
        		break;
		default: 
			break;
		}*/
		//System.out.println(formal + " " + rhetorical + " " + statistical + " " + consistency + " " + evidence);
//		System.out.println(nformal + " " + nrhetorical + " " + nstatistical + " " + nconsistency + " " + nevidence);
		
	}

	
	public void increment_w(int i) {
		incorrect[i] = incorrect[i] + 1;
		totali = totali++;
		System.out.println("decrement once at type " + i);/*
		switch (i) {
        case 0:  nformal++;
                 break;
        case 1: nrhetorical++;
        		break;
        case 2: nstatistical++;
				break;
        case 3: nconsistency++;
        		break;
        case 4: nevidence++;
        		break;
		default: 
			break;
		}
*/
		//System.out.println(formal + " " + rhetorical + " " + statistical + " " + consistency + " " + evidence);
//		System.out.println(nformal + " " + nrhetorical + " " + nstatistical + " " + nconsistency + " " + nevidence);
	}
	
	public String display () { 
		String answer = "I taught you about several different applications of reasoning, rational decision making, evaluating arguments and thinking"
				+ " logically.";
		if(totalc > 12){
			answer = answer + " You were a great human to teach. I think you are very reasonable and clever.";
		}
		if(totalc <= 12 && totali <= 12){
			answer = answer + " You were a great human to teach. I had a lot of fun!";
		}
		if(totali > 12){
			answer = answer + " It was not easy for me to teach a human being, but I hope you still learned something. Thank you again.";
		}
		
		/*
		String answer = "In this test, there were five categories: formal questions about your logical understanding,"
				+ " questions about rhetorical tactics and argumentative tricks, questions about your ability to reason "
				+ "about numbers and estimate probabilities, stories to test your ability to put the right weight on different pieces of evidence and "
				+ "shift your views accordingly, and questions about the consistency of your decisions and your ability to reason about the future.\n";
		
		if(formal == 1){
			answer = answer + "In the categorie 'formal', you got one question right";
		}
		else {
			answer = answer + "In the categorie 'formal', you got " + formal + " questions right";
		}
		if(nformal == 1){
			answer = answer + " and one question wrong.\n";
		}
		else {
			 answer = answer + " and "+ nformal + " questions wrong.\n";
		}
		if (nformal == 0 && formal > 0){
			answer = answer + "It seems that you know your way around about formal and logical expressions very well!\n";
			}
		
			
		if(rhetorical == 1){
			answer = answer + "In the second categorie, 'rhetorical', you got one question right";
		}
		else {
			answer = answer + "In the second categorie, 'rhetorical', you got " + rhetorical + " questions right";
		}
		if(nrhetorical == 1){
			answer = answer + " and one question wrong.\n";
		}
		else {
			 answer = answer + " and "+ nrhetorical + " questions wrong.\n";
		}
		if (nrhetorical == 0 && rhetorical > 0){
			answer = answer + "You don't get easily tricked by clever phrasings, I suppose!\n";
		}
			
		if(statistical == 1){
			answer = answer + "In the categorie 'statistical', you got one question right";
		}
		else {
			answer = answer + "In the categorie 'statistical', you got " + statistical + " questions right";
		}
		if(nstatistical == 1){
			answer = answer + " and one question wrong.\n";
		}
		else {
			 answer = answer + " and "+ nstatistical + " questions wrong.\n";
		}
		if (nstatistical == 0 && statistical > 0){
			answer = answer + "You are very good with numbers and probabilities!\n";
		}
		
		
		
		if(consistency == 1){
		answer = answer + "In the categorie 'consistency', you got one question right";
		}
		else {
		answer = answer + "In the categorie 'consistency', you got " + consistency + " questions right";
		}
		if(nconsistency == 1){
		answer = answer + " and one question wrong.\n";
		}
		else {
		 answer = answer + " and "+ nconsistency + " questions wrong.\n";
		}
		if (nconsistency == 0  && consistency > 0){
		answer = answer + "You are very consistent and good at planning!\n";
		}
		
		
		
		if(evidence == 1){
		answer = answer + "And finally, in the categorie 'evidence', you got one question right";
		}
		else {
		answer = answer + "And finally, in the categorie 'evidence', you got " + evidence + " questions right";
		}
		if(nevidence == 1){
		answer = answer + " and one question wrong.\n";
		}
		else {
		 answer = answer + " and "+ nevidence + " questions wrong.\n";
		}
		if (nevidence == 0 && evidence > 0){
		answer = answer + "You really know how to handle new pieces of information and integrate them in your world view!\n";
		}

		int right = evidence+statistical+formal+rhetorical+consistency;
		int wrong = nevidence+nstatistical+nformal+nrhetorical+nconsistency;
		answer = answer + "In general, I would say: ";
		
		if (amount == right){
			answer = answer + "This was absolute perfect! I am truly impressed.";
		}
		else if ((amount - right) <= Math.round(amount/5)){
			answer = answer + "You have done a quite good job. I think you are a rational person.";
		}

		else if ((amount - wrong) <= Math.round(amount/5)){
			answer = answer + "This wasn't good at all. You should really work on your rationality.";
		}
		else {
			answer = answer + "You have done okay, but there is always room for improvement!";
		}*/

		
		
return answer;
	}
	
	public String detailed_display() {
		/*
		String answer = "";
		
		
		
		String answer ="";
			//System.out.println(k);
		
		/*
		if(evaluations[k].asked){
			answer = answer + "The question number "+ (k+1) +" was: "+ evaluations[k].story.text.substring(0, 50) + " (...) " + evaluations[k].story.question + "\n";
			answer = answer + "         Your answer was: \"" + evaluations[k].answer.substring(1,evaluations[k].answer.length()-2) + "\"\n";
			
			if(k>1){
				if(evaluations[k-1].input == InputType.CORRECT && evaluations[k].input == InputType.CORRECT){
					answer = answer + "         This answer was also correct! \n";
				}
				if(evaluations[k-1].input == InputType.INCORRECT && evaluations[k].input == InputType.CORRECT){
					answer = answer + "         This time it was right! \n";
				}
				if(evaluations[k-1].input == InputType.INCORRECT && evaluations[k].input == InputType.INCORRECT){
					answer = answer + "         I'm afraid this was also not correct. \n";
				}
				if(evaluations[k-1].input == InputType.CORRECT && evaluations[k].input == InputType.INCORRECT){
					answer = answer + "         This one was not as good as the one before. I counted it as incorrect. \n";
				}
			}
			else {
				if(evaluations[k].input == InputType.CORRECT) answer = answer + "         This answer was evaluated as correct! \n";
				if(evaluations[k].input == InputType.INCORRECT) answer = answer + "         This answer was evaluated as incorrect.\n";
				if(evaluations[k].input == InputType.NO_CLUE) answer = answer + "         It seems that you didn't give an definite answer for this. \n";
				if(evaluations[k].input == InputType.NO_IDEA) answer = answer +  "         I was not able to understand your answer.\n";
			}
		}
			*/
		return "";
	}
	
	public UserEvaluation(String logfile)
	{
		this.logfile = logfile;
	}

	public void putStories(Vector<Story> collectstories) {
		for (int k = 0; k < collectstories.size(); k++){
			Evaluation evaluation = new Evaluation(collectstories.get(k),false,InputType.NO_IDEA," ");
			
			evaluations[k] = evaluation;
			
			//System.out.println("QUESTION: "+ evaluations[k].story.question);
			//System.out.println("ANSWER: \"" + evaluations[k].answer.substring(1) + "\"");
			//System.out.println(evaluations[k].input);
			//System.out.println();
		
		
		}
	
	}

public void evaluate(boolean clue, Story s, String input, boolean right) {
		
	//System.out.println("Evaluations: " + evaluations.length);
		
		boolean found = false;
		for (int i = 0; i < evaluations.length && !found; i++){
			
			if (evaluations[i].story == s){
				evaluations[i].answer = input;
				evaluations[i].asked = true;
				
				String text ="";
				if(clue){
					if (right){
						evaluations[i].input = InputType.CORRECT;
						
						if(s.text.length() < 31){
							 text = s.type + "\n"+ s.text + "\n" + input + "\n" + "CORRECT\n\n";
						}
						else {
							 text = s.type + "\n"+ s.text.substring(0,30) + "\n" + input + "\n" + "CORRECT\n\n";	
						}
						
						//System.out.println("Set as correct");
						
						/*
						logfiler.printLog(evaluations[i].story.text);
						logfiler.printLog(evaluations[i].story.question);
						logfiler.printLog(input+" - CORRECT");*/
					}
					else {
						evaluations[i].input = InputType.INCORRECT;
						if(s.text.length() < 31){
							 text = s.type + "\n"+ s.text + "\n" + input + "\n" + "INCORRECT\n\n";
						}
						else {
							 text = s.type + "\n"+ s.text.substring(0,30) + "\n" + input + "\n" + "INCORRECT\n\n";	
						}
						//System.out.println("Set as incorrect");
						/*
						logfiler.printLog(evaluations[i].story.text);
						logfiler.printLog(evaluations[i].story.question);
						logfiler.printLog(input+" - INCORRECT");*/
					}	
				}
				else {
					evaluations[i].input = InputType.NO_CLUE;
					if(s.text.length() < 31){
						 text = s.type + "\n"+ s.text + "\n" + input + "\n" + "NO CLUE\n\n";
					}
					else {
						 text = s.type + "\n"+ s.text.substring(0,30) + "\n" + input + "\n" + "NO CLUE\n\n";	
					}
/*
					logfiler.printLog(evaluations[i].story.text);
					logfiler.printLog(evaluations[i].story.question);
					logfiler.printLog(input+" - NOCLUE");*/
				}
				found = true;
				

			    byte data[] = text.getBytes();
			    Path p = Paths.get(logfile);

			    try (OutputStream out = new BufferedOutputStream(
			      Files.newOutputStream(p, CREATE, APPEND))) {
			      out.write(data, 0, data.length);
			    } catch (IOException x) {
			      System.err.println(x);
			    }
			}
			
		}
	}

	public void setamount(int a, int n) {
		amount = a;
		number = n;
		//TODO this is only for the presentation mode!
		if (amount == 10) amount = 11;
		if (amount == 20) amount = 22;
		evaluations = new Evaluation[amount];
		//System.out.println("Setting the amount of the evaluations to " + amount);
	}

/*
	public String tips() {
		String answer ="";
		if(nformal > 0){
			answer = answer + "To improve your understanding of logic and correct conclusions, I could suggest you a simple introductory course on formal logic.";
			answer = answer + " You could read 'A Concise Introductino to Logic' by Patrick J. Hurley. If you prefer online material, "
					+ "you can find a nice course teaching the basic principles under http://philosophy.hku.hk/think/logic/whatislogic.php. \n"
					+ " In general, if you are not sure if a statement is formally valid, it often helps to insert variables like A, B and C for the expressions used."
					+ " Then try out if the conclusion makes sense even if you put other terms in for the variables. This is often helpful to unmask faulty logic.\n\n";
		}
		if(nrhetorical > 0){
			answer = answer + "If you struggling with rhetorical tricks, try the following: If you are having a discussion, viewing a tv debate or being whatever situation"
					+ " rhetorical tricks might be used on you, stop and think for a moment: Is this argument really directed at the actual question here, or is it"
					+ " trying to lead away from the original point of interest? Is it formulated in an objective, neutral way, or does it implicitely or explicitely"
					+ " question a certain group or opinion and shows it in a bad light? Does the argument make you feel uncomfortable, guilty, ashamed or offended?"
					+ " These are all warning signs for rhetorical fallacies being used on you. The best way to defend against them is to get to know them, so you can easily spot them in "
					+ "another persons argumentation. Read more about rhetorical fallacies e.g. on www.nizkor.org/features/fallacies/index.html. I also recommend "
					+ "the book 'How to win every argument (the use and abuse of logic) by Madsen Pirie. It offers advice and plenty of examples. If you want your results"
					+ " very quickly, do this 2 minute test on actual, recent arguments in presidential debates and try to debunk them: http://www.clearerthinking.org/#!the-2016-presidential-debates--subtitled/wt7g0\n\n";
		}

		if(nevidence > 0){
			answer = answer + "To integrate new evidence and adjust your worldview accordingly is a bit tricky, but with some practice it gets easier. You should always look at the theories "
					+ "that you use to explain the world to yourself and ask the following questions: Does this theory successfully predict what happens in actual life?"
					+ " And would this theory fail to predict it if the world was any different? Try to find a world view that stays plausible if and only if it is correct,"
					+ " to avoid believing in wrong or totally arbitrary things. I would recommend Eliezer Yudkowkys article on Bayes' Theorem (http://www.yudkowsky.net/rational/bayes) - "
					+ "at first, you might think it's just about a statistical proposition, but in fact, it teaches you a lot about how to update your beliefs about the world in the "
					+ "face of new evidence. The website clearerthinking.org offers a nice (11 minute) test on this to train to challenge your beliefs: http://programs.clearerthinking.org/challenge_your_deepest_beliefs.html"
					+ " and also this other one that will help you understanding evidence: http://programs.clearerthinking.org/question_of_evidence.html\n\n";
		}
		
		if(nconsistency > 0){
			answer = answer + "If you want to improve on how you plan for the future, there are a lot of tools out there that can help you to become better at planning. If you are likely to "
					+ "fall prey to the sunken cost fallacy and tending to look at past expenses too long, I recommend this tool from clearerthinking.org: http://programs.clearerthinking.org/sunk_costs.html,"
					+ " and if you feel unsure about what value you should assign to your time, maybe use this one: http://programs.clearerthinking.org/what_is_your_time_really_worth_to_you.html. In general,"
					+ " it may help you to remember two things: Make it plain to yourself what an action will really give or take (for example, how much time something will cost you or how much money you will get,"
					+ " but also sometimes how much fun something is etc.) and compare it with the alternatives. Make a list, if neccessary, and look at the raw facts."
					+ " And if you are planning for the future, always include a generous safety margin, be it time, money or every other ressource, if you think about how much something will cost or take.\n\n";
		}
 
		if(nstatistical > 0){
			answer = answer + "If you have difficulties with probabilities and maths, you have many options. You could start out by reading a simple and introductory book on probability theory or take "
					+ "a beginner's course on this topic. But in most daily applications, a lot can be won simply by taking pen and paper and writing it down. Note all the relevant numbers and how there are in "
					+ "connection with each other. Try to use an example to expalin the issue to yourself. Draw a graph or a chart visualizing the relationshipa of the pieces of information or e.g. the changing over time."
					+ " And, especially if you are dealing with probabilities, try to use an example with big but easy numbers (for example: assume one million people or balls or whatever the problem is about), and go on"
					+ " from there to see where it leads you. This is shown (for example by Fenton and Neil in 2010) to increase the probability of getting it right.\n\n";
		}
		
		if (answer ==""){
			answer = answer + "You already did great! Visit http://www.clearerthinking.org/ for more tips and exercices."
					+ " You could also take a look at the website of the Center for Applied Rationality (http://rationality.org/), it "
					+ "might interest you! Also, many texts and stories worth reading for people who try to understand their own thinking and"
					+ " become more rational can be found in the online community of LessWrong (http://lesswrong.com/). Have fun!\n\n";
		}
			
		return answer;
	}*/

	public void putCalibration(double c) {
		calibration.addElement(c);
	}
	
	public double getCalibration(){
		double c = 0;
		for(int i = 0; i < calibration.size(); i++){
			c = c + calibration.get(i);
		}
		
		c = c / calibration.size();
		
		return c;
	}

	public void printCalibration() {
		System.out.println("PrintCalibration");
		double c = getCalibration();
		String text = "Calibration: " + Double.toString(c);
		
	    byte data[] = text.getBytes();
	    String filename = logfile + "_calibration.txt";
	    Path p = Paths.get(filename);

	    try (OutputStream out = new BufferedOutputStream(
	      Files.newOutputStream(p, CREATE, APPEND))) {
	      out.write(data, 0, data.length);
	    } catch (IOException x) {
	      System.err.println(x);
	    }
		
		
	}
	
}
