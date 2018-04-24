import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//class for storing and manage all the stories

public class Storystore {
	//lists to story the stories
    static List<Story> base_rate = new ArrayList<Story>();
    static List<Story> sample_size = new ArrayList<Story>();
    static List<Story> gamblers_fallacy = new ArrayList<Story>();
    static List<Story> regression_mean = new ArrayList<Story>();
    static List<Story> sunk_cost = new ArrayList<Story>();
    static List<Story> outcome_bias = new ArrayList<Story>();
    static List<Story> belief_syllogistic = new ArrayList<Story>();
    static List<Story> covariation_detection = new ArrayList<Story>();
    static List<Story> argument_evaluation = new ArrayList<Story>();
    static List<Story> selection_task = new ArrayList<Story>();
    static List<Story> all = new ArrayList<Story>();
    
	//method for checking if there are stories left in this topic
	boolean storiesLeft(int topic){
		switch (topic) {

		
	        case 0:  return !base_rate.isEmpty(); 
	        case 1:  return !sample_size.isEmpty();
	        case 2:  return !gamblers_fallacy.isEmpty();
	        case 3:  return !selection_task.isEmpty();
	        case 4:  return !sunk_cost.isEmpty();
	        case 5:  return !outcome_bias.isEmpty();
	        case 6:  return !belief_syllogistic.isEmpty();
	        case 7:  return !covariation_detection.isEmpty();
			default: System.out.println("no number of a type of stories!"); return false;
		}
		
	}
	public void readStories() {
		
		
		String content ="";
		
		try {
			content = new String(Files.readAllBytes(Paths.get("questions.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(content.length() > 2){
			if(content.startsWith("//"))
				content = content.substring(2,content.length());
			else {
				content = content.substring(1,content.length());
				continue;
			}
			int end = content.indexOf('/');
			String story = content.substring(0,end);

			BuildUpStory(story);
			
			if(content.length() > end+3){		
				content = content.substring(end+3,content.length());
			}
			else {
				break;
			}
			
				
	}	
	
	
	}
	private void BuildUpStory(String story) {
		////System.out.println("building up");
		String followup = ""; String next = ""; String type = "";
		int f = 0;
		String description = "", question1 = "", question2 = ""; String intro = ""; String yesreaction = ""; String noreaction = ""; String correct = ""; String incorrect = ""; String explanation = "";
		String correctTags = ""; String incorrectTags = ""; String hint = "";

		ContextType CType = ContextType.Empty;
		int category = 0;
		category = Integer.parseInt(story.substring(0,1));
		story = story.substring(2,story.length());
		////System.out.println("C: " + category);
		
		description = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("Desc: " + description);

		question1 = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("q1: " + question1);
		
		question2 = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("q2: " + question2);
		
		type = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("T: " + type);

		switch(type) {
			case "one" : 	CType = ContextType.ONE; break;
			case "restaurant" : 	CType = ContextType.RESTAURANTS; break;
			case "baseball" : 	CType = ContextType.BASEBALL; break;
			case "ballet" : 	CType = ContextType.BALLET; break;
			case "ivy league" : 	CType = ContextType.IVY_LEAGUE; break;
			case "bad stunts" : 	CType = ContextType.BAD_STUNTS; break;
			case "cars" : 	CType = ContextType.CARS; break;
			case "teaching method": CType = ContextType.TEACHING_METHOD; break;
			case "best of five" : CType = ContextType.BEST_OF_FIVE; break;
			case "hospitals" : CType = ContextType.HOSPITALS; break;
			case "word count" : CType = ContextType.WORD_COUNT; break;
			case "mixed" : 		CType = ContextType.MIXED; break;
			case "slotting" : 		CType = ContextType.SLOTTING; break;
			case "universities" : 		CType = ContextType.UNIVERSITIES; break;
			case "pennies":				CType = ContextType.PENNIES; break;
			case "imperfections" : 		CType = ContextType.IMPERFECTIONS; break;
			
			case "globoma" : 			CType = ContextType.GLOBOMA; break;
			case "breast cancer" : 			CType = ContextType.BREAST_CANCER; break;
			case "cab" : 			CType = ContextType.CAB; break;
			case "police dog" : 			CType = ContextType.POLICE_DOG; break;
			case "storm" : 			CType = ContextType.STORM; break;
			
			case "marble tray" : 	CType = ContextType.MARBLE_TRAY; break;
			
			case "theatre ticket" : 	CType = ContextType.THEATRE_TICKET; break;
			case "coin toss":		CType = ContextType.COIN_TOSS; break;
			case "coin game":		CType = ContextType.COIN_GAME; break;
			case "slot gambler":		CType = ContextType.SLOT_GAMBLER; break;
			case "lottery":		CType = ContextType.LOTTERY; break;
			case "roulette":		CType = ContextType.ROULETTE; break;
			case "subway":		CType = ContextType.SUBWAY; break;
			case "board game":		CType = ContextType.BOARD_GAME; break;
			case "ravenclaw":		CType = ContextType.RAVENCLAW; break;
			case "green outfits":		CType = ContextType.GREEN_OUTFITS; break;
			
			case "outcome1a":		CType = ContextType.OUTCOME1A; break;
			case "outcome1b":		CType = ContextType.OUTCOME1B; break;
			case "outcome2a":		CType = ContextType.OUTCOME2A; break;
			case "outcome2b":		CType = ContextType.OUTCOME2B; break;
			case "outcome3a":		CType = ContextType.OUTCOME3A; break;
			case "outcome3b":		CType = ContextType.OUTCOME3B; break;
		
			case "syllog1":			CType = ContextType.SYLLOG1; break;
			case "syllog2":			CType = ContextType.SYLLOG2; break;
			case "syllog3":			CType = ContextType.SYLLOG3; break;
			case "syllog4":			CType = ContextType.SYLLOG4; break;
			case "syllog5":			CType = ContextType.SYLLOG5; break;
			case "syllog6":			CType = ContextType.SYLLOG6; break;
			case "syllog7":			CType = ContextType.SYLLOG7; break;
			case "syllog8":			CType = ContextType.SYLLOG8; break;
			case "syllog9":			CType = ContextType.SYLLOG9; break;
			
			case "context":			CType = ContextType.CONTEXT; break;
			
			case "drug":			CType = ContextType.DRUG; break;
			case "back pain":		CType = ContextType.BACK_PAIN; break;
			case "grades":		CType = ContextType.GRADES; break;
			case "glasses":		CType = ContextType.GLASSES; break;
			case "parrots":		CType = ContextType.PARROTS; break;
			case "tomato":		CType = ContextType.TOMATO; break;
			
			case "VowelEvenNumber": CType = ContextType.VOWELEVENNUMBER; break;
			case "board game dev" : 	CType = ContextType.BOARD_GAME_DEVELOPMENT; break;
			case "cat flu"			: 	CType = ContextType.CAT_FLU; break;
			case "wedding cards"    :	CType = ContextType.WEDDING_CARDS; break;
			case "towels"			:	CType = ContextType.TOWELS; break;
			case "birds"			:	CType = ContextType.BIRDS; break;

			
			case "pay tv":		CType = ContextType.PAY_TV; break;
			case "boring cinema": CType = ContextType.BORING_CINEMA; break;
			case "boring aunt": CType = ContextType.BORING_AUNT; break;
			case "theatre tickes": CType = ContextType.THEATRE_TICKET; break;
			case "sports ticket": CType = ContextType.SPORTS_TICKET; break;
			case "popcorn" :		CType = ContextType.POPCORN; break;
			case "university renovation": 	CType = ContextType.UNIVERSITY_RENOVATION; break;
			case "tuna sandwich":	CType = ContextType.TUNA_SANDWICH; break;
			case "wardrobe":		CType = ContextType.WARDROBE; break;
			case "software project":	CType=ContextType.SOFTWARE_PROJECT; break;
			default	: 			//System.out.println("no context recognized!");
		}

		correctTags = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("correct: " + correctTags);

		incorrectTags = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		
		String tags = correctTags + incorrectTags;
		int amount = tags.split("\\,",-1).length-1 + 2;
		if (correctTags.length() < 1) amount--;
		if (incorrectTags.length() < 1) amount--;
		////System.out.println("contains " + amount);
		
		String[][] TagArray = new String[amount][2];
		int i = 0;
		////System.out.println(correctTags.length());
		while(correctTags.length() >= 1){
			////System.out.println("Tag number " + i);
			if(!correctTags.contains(",")){
				////System.out.println(correctTags);
				TagArray[i][0] = correctTags.toUpperCase();
				TagArray[i][1] = "CORRECT";
				correctTags = "";
			}
			else {
				////System.out.println(correctTags.substring(0,correctTags.indexOf(',')));
				TagArray[i][0] = correctTags.substring(0,correctTags.indexOf(',')).toUpperCase();
				TagArray[i][1] = "CORRECT";
				correctTags = correctTags.substring(correctTags.indexOf(',')+1,correctTags.length());
			}
			i++;
		}
		
		while(incorrectTags.length() >= 1){
			////System.out.println("Tag number " + i);
			if(!incorrectTags.contains(",")){
				////System.out.println("last one: " + incorrectTags);
				TagArray[i][0] = incorrectTags.toUpperCase();
				TagArray[i][1] = "INCORRECT";
				incorrectTags = "";
			}
			else {
				////System.out.println(incorrectTags.substring(0,incorrectTags.indexOf(',')));
				TagArray[i][0] = incorrectTags.substring(0,incorrectTags.indexOf(',')).toUpperCase();
				TagArray[i][1] = "INCORRECT";
				incorrectTags = incorrectTags.substring(incorrectTags.indexOf(',')+1,incorrectTags.length());
			}
			i++;
		}

		followup = story.substring(0,story.indexOf(';'));
		////System.out.println("Followup? " + followup);
		story = story.substring(story.indexOf(';')+1,story.length());

		switch(followup) {
			case "no" : 	f = 0; break;
			case "first" :  f = 1; break;
			case "second" : f = 2; break;
			default		: 	f = 0; break;
		}

		next = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		
		Story nextStory;
		////System.out.println("Next one: " + next);
		if (next.equals("null")){
//			//System.out.println("no next one.");
			nextStory = null;
		}
		else {
			//System.out.println("The next story would start with " + next);
			nextStory = getSpecificStory(next); 
			
		}

		
		intro = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("Intro " + intro);

		yesreaction = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("yes - " + yesreaction);
		
		noreaction = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("no - " + noreaction);

		correct = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("correct - " + correct);

		incorrect = story.substring(0,story.indexOf(';'));
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("incorrect - " + incorrect);

		explanation = story.substring(0,story.indexOf(';'));
		//System.out.println(story);
		story = story.substring(story.indexOf(';')+1,story.length());
		////System.out.println("exp: " + explanation + "\n\n");
		
		
		//System.out.println(description.substring(0,10));
		hint = "[In this version, the hints are missing.]";
//		hint = story.substring(0,story.indexOf(';'));
		//story = story.substring(story.indexOf(';')+1,story.length());
	//	//System.out.println("hint: " + hint + "\n\n");
		
		//for something
		Story S = new Story(category,description,question1,question2,CType,TagArray, f,	nextStory, intro, yesreaction, noreaction, correct, incorrect, explanation,hint);

		all.add(S);
		switch (category) {
		

	        case 0:  base_rate.add(S);
	        		 break;
	        case 1:  sample_size.add(S);
   		 				break;
	        case 2:  gamblers_fallacy.add(S);
	        		break;
	        case 3:  selection_task.add(S);
    				break;

	        case 4: sunk_cost.add(S);
	        		break;

	        case 5:  outcome_bias.add(S);
	        		break;

	        case 6:  belief_syllogistic.add(S);
	        		break;

	        case 7:  covariation_detection.add(S);
	        		break;
	        		
			default: //System.out.println("No correct category");  break;
		}
		
	
		//System.out.println("The story starting with \"" + description.substring(0, 5) + "...\" was added to category " + category + ". \n");
		
	}
	//get a story out of a chosen topic and delete it there
	Story getStory(int topic){
		Story story = null;
		Random rand = new Random();
		int index = (rand.nextInt(100)+1);
				
		switch (topic) {
	        case 0:  story = base_rate.get(index%base_rate.size());
	        		base_rate.remove(index%base_rate.size());
	        		 break;
	        case 1:  story = sample_size.get(index%sample_size.size());
	        sample_size.remove(index%sample_size.size());
   		 				break;
	        case 2:  story = gamblers_fallacy.get(index%gamblers_fallacy.size());
	        gamblers_fallacy.remove(index%gamblers_fallacy.size());
	        		break;
	        case 3:  story = selection_task.get(index%selection_task.size());
	        selection_task.remove(index%selection_task.size());
	        		break;     		
	        case 4:  story = sunk_cost.get(index%sunk_cost.size());
	        sunk_cost.remove(index%sunk_cost.size());
	        		break;
	        case 5:  story = outcome_bias.get(index%outcome_bias.size());
	        outcome_bias.remove(index%outcome_bias.size());
	        		break;
	        case 6:  story = belief_syllogistic.get(index%belief_syllogistic.size());
	        belief_syllogistic.remove(index%belief_syllogistic.size());
	        		break;
	        case 7:  story = covariation_detection.get(index%covariation_detection.size());
	        covariation_detection.remove(index%covariation_detection.size());
	        		break;
			default: //System.out.println("no number of a type of stories!");  break;
		}
		
			
		return story;
	}
	
	public Storystore(){}
		
		public Story getSpecificStory(String text) {

			////System.out.println("looking for:\n"+text);
			for (int i = 0; i < all.size(); i++){
				////System.out.println("Found a text:\n"+all.get(i).text);
				if(all.get(i).text.equals(text)) return all.get(i);
			}
					
			//System.out.println("get specific story found nothing...!!!");
				return null;
		}
		
		
		public void boring_aunt(int i, Story next) {
			if (i == 2){
				//System.out.println("You clearly chose to continue");
				//chose to continue
				String[][] array = {
						{"QUIT","INCORRECT"},
						{"LEAVE","INCORRECT"},
						{"ELSE","INCORRECT"},
						{"SWITCH","INCORRECT"},
						{"OTHER","INCORRECT"},
						{"KEEP","CORRECT"},
						{"CONTINUE","CORRECT"},
						{"GO ON","CORRECT"},
						{"WATCH","CORRECT"},
						{"WASTE","CORRECT"},
						{"ALREADY","CORRECT"},
						{"SIT THROUGH","CORRECT"}
				};
				next.tags = array;
				
				
			}
			if (i == 1){
				//System.out.println("You chose to quit");
				//chose to quit
				String[][] array = {
						{"QUIT","CORRECT"},
						{"LEAVE","CORRECT"},
						{"ELSE","CORRECT"},
						{"SWITCH","CORRECT"},
						{"OTHER","CORRECT"},
						{"KEEP","INCORRECT"},
						{"CONTINUE","INCORRECT"},
						{"GO ON","INCORRECT"},
						{"WATCH","INCORRECT"},
						{"WASTE","INCORRECT"},
						{"ALREADY","INCORRECT"},
						{"SIT THROUGH","INCORRECT"}
				};
				next.tags = array;
				
			}
			return;
		}
		public void popcorn(int p) {
				for (int i = 0; i < all.size(); i++){
					if(all.get(i).context == ContextType.POPCORN){
						if (p == 2){
							all.get(i).text = "I wanted to tell you a story, actually. So you are at a movie theatre and bought a big bag of salty popcorn. The movie starts, and you're looking forward to enjoy it - but as soon as you start eating the popcorn, you notice that you bought the wrong flavour of popcorn, - it's sweet, terribly sweet. You don't really feel like eating it anymore. On the other hand, you paid a lot of money for the huge bag, and since you have no friends with you to share it with, it will go to waste.";
							}
						if (p == 1){
							all.get(i).text = "Well, there's this story I wanted to tell you. You are at a movie theatre and bought a big bag of sweet popcorn. The movie starts, and you're looking forward to enjoy it - but as soon as you start eating the popcorn, you notice that you bought the wrong flavour of popcorn, - in fact, it's extremely salty. You don't really feel like eating it anymore. On the other hand, you paid a lot of money for the huge bag, and since you have no friends with you to share it with, it will go to waste.";	
						}
					}/*
					if(all.get(i).context == ContextType.BORING_CINEMA){
						if (p == 2){
							all.get(i).text = "Imagine you are in another town and you have decided to go to a small local cinema. You buy a ticket and a bag of salty popcorn and make yourself comfortable, waiting for the movie. After a while, you notice that you're the only person in the room. The movie starts, and you try to enjoy it, but after roughly ten minutes you come to the conclusing that it's probably the most boring movie you've ever seen. The sun is shining outside and you could probably do a lot of things in the town that are more fun than watching this movie, but on the other hand, you already bought the ticket.";
						}
						if (p == 1){
							all.get(i).text = "Imagine you are in another town and you have decided to go to a small local cinema. You buy a ticket and a bag of sweet popcorn and make yourself comfortable, waiting for the movie. After a while, you notice that you're the only person in the room. The movie starts, and you try to enjoy it, but after roughly ten minutes you come to the conclusing that it's probably the most boring movie you've ever seen. The sun is shining outside and you could probably do a lot of things in the town that are more fun than watching this movie, but on the other hand, you already bought the ticket";
						}
						
					}
					*/
				}
				
				
			}
			



		public void gambling(int i) {
			if (i == 1){
				String text = "William and Susan are buying lottery tickets and choose their numbers. insertimage:lottery.png William chooses 6, 14, 22, 35, 38 and 40. Susan chooses 1, 2, 3, 4, 5 and 6. Regardless of how much money they will win, what do you think:";
				Story s = getSpecificStory(text);		
				if (s != null){
					s.text = "William and Susan, who also like playing with the odds, are buying lottery tickets and choose their numbers. insertimage:lottery.png William chooses 6, 14, 22, 35, 38 and 40. Susan chooses 1, 2, 3, 4, 5 and 6. Regardless of how much money they will win, what do you think:";
				}
				
				text = "Steve is in a casino and asks the dealer to put all his chips on red 20. insertimage:roulette.png The Dealer asks him if he is sure, because red 20 came up the last time. Steve is surprised, saying: 'Oh, I didn't know that. Thanks for saving me from this mistake! Please, put it on black 18 then.'";
				s = getSpecificStory(text);
				if (s != null){
					s.text = "A friend of you, Steve, is in a casino and asks the dealer to put all his chips on red 20. insertimage:roulette.png The Dealer asks him if he is sure, because red 20 came up the last time. Steve is surprised, saying: 'Oh, I didn't know that. Thanks for saving me from this mistake! Please, put it on black 18 then.'";
				}
				
				text = "James has drawn a ticket for a lottery where only the tickets with the highest three-digit numbers win prices. He bought his ticket early, but unfortunately, he got the number 003. He talks to two other people (out of several hundred participating in the lottery), who have also bought tickets right at the beginning, and they have the numbers 108 and 37, respectively. He suspects that the tickets may not have been properly mixed. A little bit upset, he looks for people who bought their tickets late, and finds three people that have a 348, a 719 and a 879. Now he is really upsets and goes to complain about the lottery not being fair.";
				s = getSpecificStory(text);		
				if (s != null){
					s.text = "James also likes to try his luck. He has drawn a ticket for a lottery where only the tickets with the highest three-digit numbers win prices. He bought his ticket early, but unfortunately, he got the number 003. He talks to two other people (out of several hundred participating in the lottery), who have also bought tickets right at the beginning, and they have the numbers 108 and 37, respectively. He suspects that the tickets may not have been properly mixed. A little bit upset, he looks for people who bought their tickets late, and finds three people that have a 348, a 719 and a 879. Now he is really upsets and goes to complain about the lottery not being fair.";
				}

				text = "Keith is traveling and stopping by a gas station where he sees two slotting machines. insertimage:slot.png He never gambled before, but he is curious. And old man is sitting there and tells him: 'There ain't no winning system for slot machines. It's all luck. But some machines are differently configured than others. You can trust me - the right one always makes you lose more than you win. I've played them for years.' After that, he leaves. Keith is intrigued and tries to play at the left machine, but he looses three times in a row. Frustrated, he tries the right machine for some minutes, ending up winning more than he lost. He concludes that the old man must have tried to trick him and the right one is actually more favorable than the right one.";
				s = getSpecificStory(text);		
				if (s != null){
					s.text = "You probably know slot machines, since you like gambling. Keith is traveling and stopping by a gas station where he sees two slotting machines. insertimage:slot.png He never gambled before, but he is curious. And old man is sitting there and tells him: 'There ain't no winning system for slot machines. It's all luck. But some machines are differently configured than others. You can trust me - the right one always makes you lose more than you win. I've played them for years.' After that, he leaves. Keith is intrigued and tries to play at the left machine, but he looses three times in a row. Frustrated, he tries the right machine for some minutes, ending up winning more than he lost. He concludes that the old man must have tried to trick him and the right one is actually more favorable than the right one.";
				}
				

				text = "Imagine that we are tossing a fair coin (a coin that has a 50-50 chance of coming up heads or tails) and it has just come up heads 5 times in a row. For the 6th toss, what do you think:";
				s = getSpecificStory(text);		
				if (s != null){
					s.question = "Is it more likely that head comes up, that tails comes up, or is both equally likely? As someone who like to gamble, this should be easy for you.";
				}

				text = "When playing slot machines, people win about 1 in every 10 times. insertimage:slot2.png It doesn't really depend on their ability, it's just a matter of luck (and there is no complicated programming implemented). Julie, however, has just won on her first three plays. Her friends are impressed - three times in a row! They gather around her and watch her, and to their amazement Julie wins the next one, too.";
				s = getSpecificStory(text);		
				if (s != null){
					s.question = "What are her chances now of winning again the next time she plays? (Which will be the 5th time) I'm sure you're the right person to solve this.";
				}

				text = "Assume you're playing a game where the gamemaster tosses a coin repeatedly and when a sequence comes up someone betted on, this person wins 50 dollars. The gamemaster will toss the coin six times, and each time, there's a 50% probability for the coin to show heads (H) or tails (T), respectively.";
				s = getSpecificStory(text);		
				if (s != null){
					s.question = "You want to win, so you try to bet on the sequence that is most likely to appear. On which sequence do you want to bet: A. HTHTTH, B. HHHTTT, or C. HHHHH? This should be easy for someone who likes gambling.";
				}

				text = "William and Susan are buying lottery tickets and choose their numbers. insertimage:lottery.png William chooses 6, 14, 22, 35, 38 and 40. Susan chooses 1, 2, 3, 4, 5 and 6. Regardless of how much money they will win, what do you think:";
				s = getSpecificStory(text);		
				if (s != null){
					s.question = "Whose numbers are more likely to be the winning numbers? (I'm sure, for you this is a no-brainer.)";
				}
				
				text = "Steve is in a casino and asks the dealer to put all his chips on red 20. insertimage:roulette.png The Dealer asks him if he is sure, because red 20 came up the last time. Steve is surprised, saying: 'Oh, I didn't know that. Thanks for saving me from this mistake! Please, put it on black 18 then.'";
				s = getSpecificStory(text);		
				if (s != null){
					s.question = "Since you're into that kind of games, you have to know: Is Steve better off now?";
				}
				
			}			
		}
		
		public void catdog(int i) {
			 //0 undefined, 1 cat, 2 dog, 3 no idea
			if(i == 1){
				String text = "A veterinarian tries out a new medicine for fungal infections in parrots. He gives the medicine to 8 of his small feathery patients, and in 6 of them, the infections go away after a week. He also observes 20 other parrots who don't get the new medicine, and 9 of them are also recovering after a week.";
				Story vet = getSpecificStory(text);		
				if (vet != null){
					vet.text = "A veterinarian tries out a new medicine for gum inflammation in cats. He gives the medicine to 8 of his small furry patients, and in 6 of them, the inflammation goes away after a week. He also observes 20 other cat who don't get the new medicine, and 9 of them are also recovering after a week.";
					vet.question = "Did he actually help the poor kittens with the new medicine?";
					vet.clarify = "Do you think the medicine was helping in treating the cats?";
					vet.wrong = "Oh, why would you think that? If you look closely, you see that the medicine did probably help the cats.";
					vet.explain = "In this case, just look at the proportions: Of the poor balls of fur who didn't get the medicine, 45% (9 out of 20) recovered. Of the cats who where treated with the new medicine, 75% (6 out of 8) recovered. This is a much better rate than without the medicine.";		
				}
				
			}
			
			if(i == 2){
				String text = "A veterinarian tries out a new medicine for fungal infections in parrots. He gives the medicine to 8 of his small feathery patients, and in 6 of them, the infections go away after a week. He also observes 20 other parrots who don't get the new medicine, and 9 of them are also recovering after a week.";
				Story vet = getSpecificStory(text);	
				if (vet != null){
					vet.text = "A veterinarian tries out a new medicine for gum inflammation in dogs. He gives the medicine to 8 of his furry patients, and in 6 of them, the inflammation goes away after a week. He also observes 20 other dogs who don't get the new medicine, and 9 of them are also recovering after a week.";
					vet.question = "Did he actually help the poor animals with the new medicine?";
					vet.clarify = "Do you think the medicine was helping in treating the dogs?";
					vet.wrong = "Oh, why would you think that? If you look closely, you see that the medicine did probably help the dogs.";
					vet.explain = "In this case, just look at the proportions: Of the poor balls of fur who didn't get the medicine, 45% (9 out of 20) recovered. Of the dogs who where treated with the new medicine, 75% (6 out of 8) recovered. This is a much better rate than without the medicine.";		
				}
				
				text = "For this, please imagine that you are working in an animal shelter. You have one of your regular meetings with a veterinarian and she tells you about a new cat flu that is spreading this summer. It's not harmful for adult cats, but kittens are in danger. The vet tells you the medical guidelines, including: If a female cat is pregnant, she has be vaccinated to protect her from the cat flu. In your shelter, you have right now the following cats: Max, who is a tomcat. Snowball, who is a female cat that is pregnant (and you don't know if her former owners let a vet vaccinate her). Meow, who is female and definitely not vaccinated, as she was picked up from the streets, and who could be pregnant or not. And Fluffypaws, whom you got vaccinated last week.insertimage:cats.png A vet can find out if a cat is pregnant and can also vaccinate them. Cats usually hate going to the vet, so you only want to put them into the stress if it's really necessary.";
				//System.out.println("looking for: " + text.substring(0,30));
				vet = getSpecificStory(text);
				if (vet != null){
					vet.text = "For this, please imagine that you are working in an animal shelter. You have one of your regular meetings with a veterinarian and she tells you about a new dog flu that is spreading this summer. It's not harmful for adult dogs, but puppies are in danger. The vet tells you the medical guidelines, including: \"If a female dog is pregnant, she has be vaccinated to protect her from the dog flu.\" In your shelter, you have right now the following dogs: Toto and Bisquit, who are male dogs. Bella, who is a dog lady that is pregnant (and you don't know if her former owners let a vet vaccinate her). Coco, who is female and definetely not vaccinated, as she was picked up from the streets, and who could be pregnant or not. And Princess, whom you got vaccinated last week. insertimage:dogs.png A vet can find out if a dog is pregnant and can also vaccinate them. Dogs usually hate going to the vet, so you only want to put them into the stress if it's really necessary.";
					vet.question = "Which dogs do you have to take to the vet?";
					vet.clarify = "Name each dog that has to be taken to the vet to ensure the medical guideline is fulfilled, but not more";
					vet.wrong = "I think you made a mistake.";
					vet.right = "Correct! You saved the dogs.";
					vet.explain = "You have to check Bella, because she is pregnant and should be vaccinated, and Coco, because she is not vaccinated and could be pregnant. insertimage:dogs2.png";
				}
			}
			
		}
		public void city(String city) {
			if (city == ""){
				city = "Tokyo";
			}
			
			String text = "insertimage:car.png A cab was involved in a hit-and-run accident at night. Two cab companies, the Green and the Blue, operate in the city in which the accident occurred. You are given the following facts: 85 percent of the cabs in the city are Green and 15 percent are Blue. A witness identified the cab as Blue. The court tested the reliability of the witness under the same circumstances that existed on the night of the accident and concluded that the witness correctly identified each of the two colors 80 percent of the time (20 percent of the time he mistook Green for Blue or Blue for Green).";
			Story s = getSpecificStory(text);
			if (s != null){
				s.text = "Well, this little crime story takes place there! insertimage:car.png  In the center of " + city + ", a cab was involved in a hit-and-run accident at night. Suppose that two cab companies, the Green and the Blue, operate in the region in which the accident occurred. You are given the following facts: 85 percent of the cabs in this part of " + city + " are Green and 15 percent are Blue. A witness identified the cab as Blue. The court tested the reliability of the witness under the same circumstances that existed on the night of the accident and concluded that the witness correctly identified each of the two colors 80 percent of the time (20 procent of the time he mistook Green for Blue or Blue for Green).";
			}
			else {
				//System.out.println("Story " + text.substring(0,20) + "not found.");
			}
		
		
			text = "Howard was a teacher in a junior high school in a community known for truancy and delinquency problems among its youth. insertimage:highschool.png Howard says of his experiences: Usually, in a class of 35 or so kids, 2 or 3 will pull some pretty bad stunts in the first week - they'll skip a day of class, get into a scuffle with another kid, or some such thing. When that kind of thing happens, I play it down and try to avoid calling attention to it. Usually, these kids turn out to be no worse than the others. By the end of the term you'll find they haven't pulled any more stunts than the others have. Howard reasons as follows: Some of these kids are headed toward a delinquent pattern of behavior. When they find out nobody is very impressed, they tend to settle down.";
			s = getSpecificStory(text);
			if (s != null){
				s.text = "Howard was a teacher in a junior high school in a poor part of " + city + " known for truancy and delinquency problems among its youth. insertimage:highschool.png Howard says of his experiences: Usually, in a class of 35 or so kids, 2 or 3 will pull some pretty bad stunts in the first week - they'll skip a day of class, get into a scuffle with another kid, or some such thing. When that kind of thing happens, I play it down and try to avoid calling attention to it. Usually, these kids turn out to be no worse than the others. By the end of the term you'll find they haven't pulled any more stunts than the others have. Howard reasons as follows: Some of these kids are headed toward a delinquent pattern of behavior. When they find out nobody is very impressed, they tend to settle down.";
				s.right = "Yes, I think you're right about the kids of " + city + ".";				
			}
			else {
				//System.out.println("Story " + text.substring(0,20) + "not found.");
			}
			
			
			text = "Chris lives near a subway station where every ten minutes a subway leaves for the city center. insertimage:subway.png The probability that he arrives and has to wait 1, 2, 3 etc. up to 10 minutes is each 10%, because he just leaves his apartment whenever he is ready and doesn't try to time it with the subway departures. Sometimes he is lucky, and sometimes he has to wait for a couple of minutes. It's Thursday, and this week, he had to wait 9 minutes on Monday, 8 minutes on Tuesday and 9 minutes on Wednesday. 'It just has to turn out better this time', Chris mumbles to himself as he sprints to the station.";
			s = getSpecificStory(text);
			if (s != null){
				s.text = "Chris lives near a subway station where every ten minutes a subway leaves for the city center of " + city + ". insertimage:subway.png The probability that he arrives and has to wait 1, 2, 3 etc. up to 10 minutes is each 10%, because he just leaves his apartment whenever he is ready and doesn't try to time it with the subway departures. Sometimes he is lucky, and sometimes he has to wait for a couple of minutes. It's Thursday, and this week, he had to wait 9 minutes on Monday, 8 minutes on Tuesday and 9 minutes on Wednesday. 'It just has to turn out better this time', Chris mumbles to himself as he sprints to the station.";
				s.wrong = "No, I'm afraid that's not correct. You missed the subway to the center of " + city + "today.";
				s.right = "You're right. Still, Chris will reach the center of "+ city + "not any faster, but at least you know why.";
				
			}
			else {
				//System.out.println("Story " + text.substring(0,20) + "not found.");
			}
			

			text = "insertimage:cinemasmall.png Imagine you are in another town and you have decided to go to a small local cinema. You buy a ticket and make yourself comfortable, waiting for the movie. After a while, you notice that you're the only person in the room. The movie starts, and you try to enjoy it, but after roughly ten minutes you come to the conclusion that it's probably the most boring movie you've ever seen. The sun is shining outside and you could probably do a lot of things in the town that are more fun than watching this movie, but on the other hand, you already bought the ticket.";
			s = getSpecificStory(text);
			if (s != null){
				s.text = "Imagine you are visiting " + city + " and you have decided to go to a small fancy cinema. insertimage:cinemasmall.png You buy a ticket and make yourself comfortable, waiting for the movie. After a while, you notice that you're the only person in the room. The movie starts, and you try to enjoy it, but after roughly ten minutes you come to the conclusing that it's probably the most boring movie you've ever seen. The sun is shining outside and you could probably do a lot of things in the town that are more fun than watching this movie, but on the other hand, you already bought the ticket.";
				
			}
			else {
				//System.out.println("Story " + text.substring(0,20) + "not found.");
			}
			
			
					
			
			
			
			
		}
		public void games(int i) {
			
		}
		public void video(int i) {
			
			
		}
		public void setCorrection(ContextType context, String correction) {
			
			for (int i = 0; i < all.size(); i++){
				if(all.get(i).context == context) {
					all.get(i).wrong = correction;
				}
			}
			
		}
		
	
}
