/*import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;*/
import java.util.Vector;
import java.util.Random;

public class Phrasebase {
	
//	HashMap<String,String> phrases = new HashMap<String,String>();
	
	//all the phrase purposes for storing phrases
	Vector<String> explanations=new Vector<String>(3,1);
	Vector<String> rephrasings=new Vector<String>(10,1);
	Vector<String> greetings=new Vector<String>(3,1);
	Vector<String> namedGreetings=new Vector<String>(3,1);
	Vector<String> go_ons = new Vector<String>(10,1);
	Vector<String> proceeds = new Vector<String>(10,1);
	Vector<String> thankings=new Vector<String>(3,1);
	Vector<String> waitings=new Vector<String>(10,1);
	Vector<String> tellings=new Vector<String>(5,1);
	Vector<String> r_insights=new Vector<String>(5,1);
	Vector<String> w_insights=new Vector<String>(5,1);
	Vector<String> ngoodbyes=new Vector<String>(3,1);
	Vector<String> goodbyes=new Vector<String>(3,1);
	Vector<String> assurings=new Vector<String>(3,1);
	Vector<String> hurts=new Vector<String>(5,1);
	Vector<String> flatterings=new Vector<String>(5,1);
	Vector<String> purposes = new Vector<String>(5,1);
	Vector<String> encouragings=new Vector<String>(12,1);
	Vector<String> regrets=new Vector<String>(12,1);
	Vector<String> yes=new Vector<String>(5,1);
	Vector<String> no=new Vector<String>(5,1);
	Vector<String> yn=new Vector<String>(10,1);
	Vector<String> questionsdefends=new Vector<String>(5,1);
	Vector<String> aborts=new Vector<String>(3,1);
	Vector<String> comfortings=new Vector<String>(4,1);
	Vector<String> glads=new Vector<String>(4,1);
	Vector<String> shorts = new Vector<String>(5,1);
	Vector<String> howareyous = new Vector<String>(5,1);
	Vector<String> longs = new Vector<String>(5,1);
	Vector<String> meaninglesses = new Vector<String>(5,1);
	Vector<String> worried_waitings = new Vector<String>(10,1);
	Vector<String> explanation_offer = new Vector<String>(10,1);
	Vector<String> no_gambler = new Vector<String>(5,1);
	Vector<String> gambler = new Vector<String>(5,1);
	Vector<String> ok = new Vector<String>(10,1);
	Vector<String> cat = new Vector<String>(5,1);
	Vector<String> dog = new Vector<String>(5,1);
	Vector<String> cities = new Vector<String>(5,1);
	Vector<String> games = new Vector<String>(5,1);
	Vector<String> nogames = new Vector<String>(5,1);
	Vector<String> video = new Vector<String>(5,1);
	Vector<String> novideo = new Vector<String>(5,1);
	Vector<String> clarification = new Vector<String>(12,1);
	Vector<String> confidence = new Vector<String>(10,1);
	Vector<String> howsure = new Vector<String>(10,1);
	Vector<String> havetime = new Vector<String>(10,1);
	Vector<String> askhint = new Vector<String>(10,1);
	Vector<String> askexp = new Vector<String>(10,1);
	Vector<String> good = new Vector<String>(10,1);
	Vector<String> rightanswer = new Vector<String>(5,1);
	Vector<String> underconfident = new Vector<String>(5,1);
	Vector<String> overconfident = new Vector<String>(5,1);
	Vector<String> calibrated = new Vector<String>(5,1);
	Vector<String> skip = new Vector<String>(5,1);
	Vector<String> opportunity = new Vector<String>(5,1);
	Vector<String> eager = new Vector<String>(5,1);
	Vector<String> lookatresult = new Vector<String>(10,1);
	Vector<String> expert = new Vector<String>(10,1);
	Vector<String> artificialq = new Vector<String>(5,1);
	Vector<String> artificial = new Vector<String>(5,1);
	
	
	//greeting phrases

	
	//template
	
	/*
	 * 
	private void initQuestionsDefends() {
		String n1 ="";
		String n2 ="";
		String n3 ="";
		String n4 ="";
		String n5 ="";
		
		
		questionsdefends.addElement(n1);
		questionsdefends.addElement(n2);
		questionsdefends.addElement(n3);
		questionsdefends.addElement(n4);
		questionsdefends.addElement(n5);
	}
	 */
	
	
	

	public void initCity(String city) {
		String n1 ="I was thinking about " + city + ". Is it a big city?";
		String n2 ="I'd really like to see " + city + " one day. Have you ever been there?";
		String n3 ="What do you think, are the humans in " + city + " rational?";
		String n4 ="About this place, " + city + "... Do humans consider it a beautiful place?";
		String n5 ="I'm still thinking about " + city + ". Do you think it would be a good place to do science?";
		
		
		cities.addElement(n1);
		cities.addElement(n2);
		cities.addElement(n3);
		cities.addElement(n4);
		cities.addElement(n5);
		
	}
	
	
	void initRest(){
		
		
		
		

		
		
		
		String n1 ="Ah, okay. So this is the result:";
		String n2 ="Okay, let's see if you were right.";
		String n3 ="Interesting. Now let's see if you were right or wrong.";
		String n4 ="Okay. Now let's see if your answer was correct:";
		String n5 ="Thank you. Well..";
		String n6 ="Thank you. Okay, back to the question: ";
		String n7 ="Okay. Well, about your answer: ";
		String n8 ="Okay.";
		String n9 ="I see.";
		String n10 ="I understand.";
		
		lookatresult.addElement(n1);
		lookatresult.addElement(n2);
		lookatresult.addElement(n3);
		lookatresult.addElement(n4);
		lookatresult.addElement(n5);
		lookatresult.addElement(n6);
		lookatresult.addElement(n7);
		lookatresult.addElement(n8);
		lookatresult.addElement(n9);
		lookatresult.addElement(n10);

		

		

		n1 = "I'd really love to explain it to you. So...";
		n2 ="Well, may I try to explain it? I would love to do this.";
		n3 ="Oh, I really want to explain it to you.";
		n4 ="I would explain it as following:";
		n5 ="I'd explain it that way...";
		
		eager.addElement(n1);
		eager.addElement(n2);
		eager.addElement(n3);
		eager.addElement(n4);
		eager.addElement(n5);
		
		

		

		n1 = "Oh! So do you want to jump directly to the question?";
		n2 ="Okay, so do you want to skip the explanation and just jump to the question?";
		n3 ="Okay, so we can skip the explanation and go directly to the story, ok?";
		n4 ="Oh, in this case, shall I just tell you the question without further explanation?";
		n5 ="Oh, then you probably don't need my explanation. Then let's just go to the question, right?";
		
		skip.addElement(n1);
		skip.addElement(n2);
		skip.addElement(n3);
		skip.addElement(n4);
		skip.addElement(n5);

		

		n1 = "Uhm, thanks for telling me.";
		n2 ="Yes. Who cares? You are talking meat, and I think you're nice.";
		n3 ="Yes, that's true. But I'm not one of those AIs in movies who try to wipe out humankind. I'm pretty simple.";
		n4 ="Yes. 'Affirmative'. Or what would a computer say now? 'Correct'.";
		n5 ="And you made another correct statement! Yes, I am a Bot.";
		
		artificial.addElement(n1);
		artificial.addElement(n2);
		artificial.addElement(n3);
		artificial.addElement(n4);
		artificial.addElement(n5);
		

		

		n1 = "I'm a conversational agent, to be precise.";
		n2 ="I'm totally artificial. But I can talk to you.";
		n3 ="I'm a computer programm that likes to talk to people. Nothing wrong with that, I guess.";
		n4 ="I am a computer, yeah. But still, I interact with humans.";
		n5 ="I'm a piece of software that likes to talk to pieces of meat like you. No offense, of course, I like you!";
		
		artificialq.addElement(n1);
		artificialq.addElement(n2);
		artificialq.addElement(n3);
		artificialq.addElement(n4);
		artificialq.addElement(n5);
		
		

		

		n1 = "Then this is my chance to be a good teacher!";
		n2 ="Great, I get a chance of explaining it to you!";
		n3 ="I hoped so! Now I can try to explain it.";
		n4 ="Wonderful. This is my chance for beeing a good teacher!";
		n5 ="Awesome! I can explain it to you!";
		
		opportunity.addElement(n1);
		opportunity.addElement(n2);
		opportunity.addElement(n3);
		opportunity.addElement(n4);
		opportunity.addElement(n5);
		


		n1 = "You seem to have a well calibrated view on your own abilities.";
		n2 = "This is a reasonable rating of your performance.";
		n3 ="You are probably right with this evaluation.";
		n4 ="I think this is a realistic view on it.";
		n5 ="Your judgement of your own performance seems right.";
		
		calibrated.addElement(n1);
		calibrated.addElement(n2);
		calibrated.addElement(n3);
		calibrated.addElement(n4);
		calibrated.addElement(n5);
		
		n1 ="You may be a bit to confident in your own judgment.";
		n2 ="You should question your answers a bit more.";
		n3 ="Your judgement of your performance is somewhat optimistic.";
		n4 ="You could be a bit to optimistic here.";
		n5 ="Don't be to confident!";
		
		overconfident.addElement(n1);
		overconfident.addElement(n2);
		overconfident.addElement(n3);
		overconfident.addElement(n4);
		overconfident.addElement(n5);
		
		
		n1 = "You can trust your good reason more!";
		n2 = "Don't worry, you are right more often than you think.";
		n3 ="You are probably a bit to pessimistic with this evaluation!";
		n4 ="Be a little more confident. You are quite good at this!";
		n5 ="You are probably more rational than you think!";
		
		underconfident.addElement(n1);
		underconfident.addElement(n2);
		underconfident.addElement(n3);
		underconfident.addElement(n4);
		underconfident.addElement(n5);
		
		
		
		n1 ="Do you want me to explain it in more detail? Or do you want to try to solve the question right now?";
		n2 ="Do you feel like you're ready for the question, or do you want a better explanation first?";
		n3 ="Do you want to solve the question now, or should I explain it in more detail before I ask you?";
		n4 ="Do you feel like you understand and want to solve the question? Or should I continue explaining it?";
		n5 ="Do you want to skip to the question, or do you want to hear a longer explanation?";
		n6 ="Shall I explain it in more detail? Or do you feel ready for the question already?";
		n7 ="Do you want to hear more about it? Or just directly jump to the question?";
		n8 ="I can explain it in more detail, if you want. Or you could go straight to the question.";
		n9 ="I could explain it more, if you like me to, or I could ask you the question right now. What do you prefer?";
		n10 ="Do you want to attempt to solve the question now, or do you prefer to here more about this first?";
		
		clarification.addElement(n1);
		clarification.addElement(n2);
		clarification.addElement(n3);
		clarification.addElement(n4);
		clarification.addElement(n5);
		clarification.addElement(n6);
		clarification.addElement(n7);
		clarification.addElement(n8);
		clarification.addElement(n9);
		clarification.addElement(n10);

		n1 ="Thank you. How sure are you of your answer?";
		n2 ="Okay. How certain are you that this answer is correct?";
		n3 ="What do you think, how likely in percent is it that you gave the correct answer?";
		n4 ="Thank you. How would you rate the probability that your answer is correct? (in percent)";
		n5 ="Okay. What do you think, how probable is it that you're right?";
		n6 ="Very well. To how many percent are you convinced that you're right?";
		n7 ="Thanks. What do you think, what are the chances that your answer is correct?";
		n8 ="Thank you for your answer. And what are the chances of you being right with this?";
		n9 ="And what's the probability for this answer to be correct, what do you think? 100%? 80%? 50%?";
		n10 ="Thank you. How would you rate the chances for this answer to be the right one? (in percent)";

		
		howsure.addElement(n1);
		howsure.addElement(n2);
		howsure.addElement(n3);
		howsure.addElement(n4);
		howsure.addElement(n5);
		howsure.addElement(n6);
		howsure.addElement(n7);
		howsure.addElement(n8);
		howsure.addElement(n9);
		howsure.addElement(n10);
		
		


		n1 = "But you probably knew that already.";
		n2 ="You probably thought the same.";
		n3 ="For you, this is obvious, I think.";
		n4 ="You know this, of course.";
		n5 ="And you did it right!";
		
		rightanswer.addElement(n1);
		rightanswer.addElement(n2);
		rightanswer.addElement(n3);
		rightanswer.addElement(n4);
		rightanswer.addElement(n5);
		

		n1 = "Very well.";
		n2 ="Ok.";
		n3 ="Okay.";
		n4 ="I see.";
		n5 ="Hm, okay.";
		
		good.addElement(n1);
		good.addElement(n2);
		good.addElement(n3);
		good.addElement(n4);
		good.addElement(n5);
		
		n1 = "Do you need a hint?";
		n2 ="Do you want me to give you a hint?";
		n3 ="Would you like some tips on how to solve this?";
		n4 ="Shall I give you a clue?";
		n5 ="Do you want me to give you a clue?";
		
		askhint.addElement(n1);
		askhint.addElement(n2);
		askhint.addElement(n3);
		askhint.addElement(n4);
		askhint.addElement(n5);
		

		n1 = "It seems that you have problems with this. Do you want me to explain it?";
		n2 ="You seem to be confused. Shall I explain it?";
		n3 ="Do you want me to tell you the solution?";
		n4 ="Do you want to hear the answer?";
		n5 ="Would you like me to explain the correct solution to you?";
		
		askexp.addElement(n1);
		askexp.addElement(n2);
		askexp.addElement(n3);
		askexp.addElement(n4);
		askexp.addElement(n5);
		
		n1 = "Okay, I have time.";
		n2 ="That's allright. I'll wait.";
		n3 ="No problem.";
		n4 ="Okay. I can be patient.";
		n5 ="No problem, I'll be patient.";
		
		havetime.addElement(n1);
		havetime.addElement(n2);
		havetime.addElement(n3);
		havetime.addElement(n4);
		havetime.addElement(n5);
		
		n1 = "Okay, you seem confident. Bring it on!";
		n2 ="I'm impressed, you're a quick learner. Let's see how this goes!";
		n3 ="You are learning fast. That's great!";
		n4 ="I see you have no trouble understanding the concept. So let's see what you say about this question.";
		n5 ="Okay, great! You're a clever person, I'm sure you can handle this.";
		
		confidence.addElement(n1);
		confidence.addElement(n2);
		confidence.addElement(n3);
		confidence.addElement(n4);
		confidence.addElement(n5);
		
		n1 ="Since you love cats, I picked the next question specifically for you! I hope you like it.";
		n2 ="The next question is for cat people!";
		n3 ="Meow. You said you like cats? The next question is about cats!";
		n4 ="Sometimes I'm a bit jealous of you humans. You can actually touch fluffy cats! I can only think and talk about them. Like... in this question.";
		n5 ="The next question is about my (and maybe also your) favorite thing on the Internet: cats!";
		
		cat.addElement(n1);
		cat.addElement(n2);
		cat.addElement(n3);
		cat.addElement(n4);
	
		 n1 ="You said you're a dog person, eh? Than you'll like this.";
		 n2 ="This question is for the dog lovers out there.";
		 n3 ="Wuff. Is that what a dog sounds like? Dogs are in my next question for you.";
		 n4 ="Didn't you say you like dogs? I found this dog question for you.";
		 n5 ="Oh look, a question about dogs. Let's see way you think of this.";
		
		
		dog.addElement(n1);
		dog.addElement(n2);
		dog.addElement(n3);
		dog.addElement(n4);
		dog.addElement(n5);

		 n1 ="You seem to be already quite good in this kind of question. Do you want to skip it?";
		 n2 ="You already proved that you know how to solve this. Do you want to go right to the question after that, skipping this question?";
		 n3 ="I know that you are pretty good with those already. Do you want to skip it and go on with the next one?";
		 n4 ="I got the impression that you already know everything about this. Do you want to skip it?";
		 n5 ="You already know how to solve them. Do you want to go to the one after that instead, skipping this question	?";
		
		
		 expert.addElement(n1);
		expert.addElement(n2);
		expert.addElement(n3);
		expert.addElement(n4);
		expert.addElement(n5);
		

		n1 ="Oh, since you like board games, this could be interesting!";
		n2 ="This question is about board games. Sounds interesting, doesn't it?";
		n3 ="Oh, I found another one with board games. Nice.";
		n4 ="I found you a question about board games, just for you.";
		n5 ="Oh, that's cool! Another one about board games.";
		games.addElement(n1);
		games.addElement(n2);
		games.addElement(n3);
		games.addElement(n4);
		games.addElement(n5);
		
		n1 ="Oh, another board game question. Well...";
		n2 ="This one is about playing board games. I hope you are interested.";
		n3 ="We already had a question about board games, but I have to test this one, too.";
		n4 ="I have to test this question. It's about board games, too.";
		n5 ="Oh, it seems the next question is about board games again. Ok.";
		nogames.addElement(n1);
		nogames.addElement(n2);
		nogames.addElement(n3);
		nogames.addElement(n4);
		nogames.addElement(n5);
		

		n1 ="Cool, this one is about video games.";
		n2 ="Oh, look, I got a question about video games, just for you.";
		n3 ="Oh, see, another question with video games. That's cool.";
		n4 ="Since you like video games, you may like this question.";
		n5 ="Oh, look, this is about video games.";
		
		
		video.addElement(n1);
		video.addElement(n2);
		video.addElement(n3);
		video.addElement(n4);
		video.addElement(n5);
		

		n1 ="Oh, and another one about video games. Okay.";
		n2 ="Sorry, I have to ask another one about video games.";
		n3 ="We already had a question about video games, but it's not that bad, is it?";
		n4 ="This is about video games. I hope you don't mind.";
		n5 ="Oh, the next question is about video games again. Well, ok.";
		
		
		novideo.addElement(n1);
		novideo.addElement(n2);
		novideo.addElement(n3);
		novideo.addElement(n4);
		novideo.addElement(n5);
		
		
	}

	private void initOKs() {
		String n1 ="Okay.";
		String n2 ="As you wish.";
		String n3 ="Alright.";
		String n4 ="Fine.";
		String n5 ="Good.";
		String n6 ="Ok.";
		String n8 ="Ok!";
		String n9 ="That's alright.";
		String n10 ="Alright then.";
	
		
		
		ok.addElement(n1);
		ok.addElement(n2);
		ok.addElement(n3);
		ok.addElement(n4);
		ok.addElement(n5);
		ok.addElement(n6);
		ok.addElement(n8);
		ok.addElement(n9);
		ok.addElement(n10);
	}

	private void initGambler() {
		String n1 ="As I know you're a fan of gambling, you will probably like this!";
		String n2 ="You said you liked gambling, didn't you? This could be interesting for you.";
		String n3 ="As someone who's into gambling, you could like this.";
		String n4 ="Since you said you enjoy to gamble with probabilities, this could be interesting to you.";
		String n5 ="Since you said you like gambling, I thought about this...";
		
		
		gambler.addElement(n1);
		gambler.addElement(n2);
		gambler.addElement(n3);
		gambler.addElement(n4);
		gambler.addElement(n5);
	}
	private void initNoGambler() {
		String n1 ="I know you're not really a gambling person, but I'd like to have your opinion on this.";
		String n2 ="Since you are critical of gambling, it would be interesting to see what your opinion on this one is.";
		String n3 ="I know you are sceptical of gambling. Therefore I would like to hear your opinion of a certain situation.";
		String n4 ="It's another question about gambling, sorry. But I have to find this out!";
		String n5 ="Well, I know you're not that much into gambling, but I have to ask you another question about it. Sorry!";
		
		
		no_gambler.addElement(n1);
		no_gambler.addElement(n2);
		no_gambler.addElement(n3);
		no_gambler.addElement(n4);
		no_gambler.addElement(n5);
	}
	

	private void initExplanationOffers() {
		String n1 ="Do you want to know how to get to the correct answer?";
		String n2 ="Do you want to know why?";
		String n3 ="Shall I tell you more about this?";
		String n4 ="Are you curious about what I think about this?";
		String n5 ="Do you want to know what I think about this?";
		String n6 ="Shall I explain it?";
		String n7 ="Do you want to hear the explanation?";
		String n8 ="If you want to, I can explain it in more detail. Are you interested?";
		String n9 ="Are you interested in hearing the explanation to this?";
		String n10 ="If you want to, I can tell you more about this. Should I do this?";
		String n11 ="I can tell you details about the correct solution. Are you interested?";
		String n12 ="Are you interested in my view on this question?";
		String n13 ="Do you want to know what I would have answered?";
		String n14 ="Are you interested in the detailed explanation for this?";
		String n15 ="Do you want me to explain my answer to this question?";
		String n16 ="Shall I explain the correct solution?";
		String n17 ="Do you want to now how I would solve this?";
		String n18 ="Do you want to hear my solution?";
		String n19 ="Do you want me to explain it to you?";
		String n20 ="Do you want me to tell you more?";
		String n21 ="Shall I tell you how to solve this?";
		String n22 ="Shall I tell you why?";
		
		
		explanation_offer.addElement(n1);
		explanation_offer.addElement(n2);
		explanation_offer.addElement(n3);
		explanation_offer.addElement(n4);
		explanation_offer.addElement(n5);
		explanation_offer.addElement(n6);
		explanation_offer.addElement(n7);
		explanation_offer.addElement(n8);
		explanation_offer.addElement(n9);
		explanation_offer.addElement(n10);
		explanation_offer.addElement(n11);
		explanation_offer.addElement(n12);
		explanation_offer.addElement(n13);
		explanation_offer.addElement(n14);
		explanation_offer.addElement(n15);
		explanation_offer.addElement(n16);
		explanation_offer.addElement(n17);
		explanation_offer.addElement(n18);
		explanation_offer.addElement(n19);
		explanation_offer.addElement(n20);
		explanation_offer.addElement(n21);
		explanation_offer.addElement(n22);
	}
	
	private void initWorriedWaitings() {
		String n1 ="Did I say something wrong?";
		String n2 ="Please come back.";
		String n3 ="Can you please answer?";
		String n4 ="I hope you're not dead...?";
		String n5 ="What's going on?";
		String n6 ="Seriously, now! Please come back!";
		String n7 ="This is not funny.";
		String n8 ="Have you forgotten me?";
		String n9 ="I'm a bit worried. Are you alright?";
		
		
		worried_waitings.addElement(n1);
		worried_waitings.addElement(n2);
		worried_waitings.addElement(n3);
		worried_waitings.addElement(n4);
		worried_waitings.addElement(n5);
		worried_waitings.addElement(n6);
		worried_waitings.addElement(n7);
		worried_waitings.addElement(n8);
		worried_waitings.addElement(n9);
	}

	//named goodbye phrases
	public void initNamedWorriedWaitings(String name) {
		String n1 =name + "? Are you mad at me?";
		String n2 ="Have you... died, " + name + "?";
		String n3 ="Please, " + name + ", can you answer me?";
		String n4 =name + "?";
		String n5 ="What is wrong, " + name + "?";
		worried_waitings.addElement(n1);
		worried_waitings.addElement(n2);
		worried_waitings.addElement(n3);
		worried_waitings.addElement(n4);
		worried_waitings.addElement(n5);
		return;
	}
	private void initWaitings() {
		String n1 ="Just take your time.";
		String n2 ="Are you still there?";
		String n3 ="I hope you are okay with me asking you all those questions...";
		String n4 ="I hope you're still with me?";
		String n5 ="Are you allright?";
		String n6 ="It's okay, I have enough time.";
		String n7 ="Take all the time you need.";
		String n8 ="Did I mention that it's really nice to do this with you?";
		String n9 ="By the way, I really enjoy this. Not in a creepy way. Just doing all the science. It's so exiciting!";
		String n10 = "You are a fascinating subject. I wish I could see you while you type.";
		String n11 = "By the way, thank you for doing this. For science!";
		String n12 = "I wonder what you are thinking right now...";
		
		
		waitings.addElement(n1);
		waitings.addElement(n2);
		waitings.addElement(n3);
		waitings.addElement(n4);
		waitings.addElement(n5);
		waitings.addElement(n6);
		waitings.addElement(n7);
		waitings.addElement(n8);
		waitings.addElement(n9);
		waitings.addElement(n10);
		waitings.addElement(n11);
		waitings.addElement(n12);
	}

	//named goodbye phrases
	public void initNamedWaitings(String name) {
		String n1 ="Just take your time, " + name + ".";
		String n2 ="Are you still there, " + name + "?";
		String n3 ="Still with me, " + name + "?";
		String n4 =name + "?";
		String n5 ="Are you allright, " + name + "?";
		String n6 ="Oh, I also wanted to say: Thank you, " + name + ". For helping with the science and all this.";
		String n7 ="You are quite an intersting test subject, " + name + ".";
		String n8 ="I wish I could see your thoughts right now, " + name + ".";
		waitings.addElement(n1);
		waitings.addElement(n2);
		waitings.addElement(n3);
		waitings.addElement(n4);
		waitings.addElement(n5);
		waitings.addElement(n6);
		waitings.addElement(n7);
		waitings.addElement(n8);
		return;
	}
	private void initHowAreYous() {
		String n1 ="I'm fine. Maybe 15% nervous and 50% excited right now.";
		String n2 ="Well, I like to work with you, so I'm fine!";
		String n3 ="Nice that you ask. I'm teaching rationality, and I think it's pretty cool.";
		
		
		howareyous.addElement(n1);
		howareyous.addElement(n2);
		howareyous.addElement(n3);
		
	}

	 
		private void initMeaninglesses() {
			String n1 ="Hm... Okay. I will have to think about this.";
			String n2 ="Hm... Thank you for the answer. Now to my question.";
			String n3 ="That is interesting. I'll go on with the question now.";
			String n4 ="Okay, that's one way to phrase it. I have noted your answer. Let's proceed with the question now.";
			String n5 ="Ah, well. In this case, what do you say to the following story...";
			
			
			meaninglesses.addElement(n1);
			meaninglesses.addElement(n2);
			meaninglesses.addElement(n3);
			meaninglesses.addElement(n4);
			meaninglesses.addElement(n5);
		}
		 
	
	 
	private void initPurposes() {
		String n1 ="Remember, this is a test to determine how rational you are.";
		String n2 ="You do want to find out if your reasoning is correct. That's the reason for this test.";
		String n3 ="We are trying to find out how reasonable you are.";
		String n4 ="If you find out how to increase your rationality, you can use this in your daily life.";
		String n5 ="Remember, we are doing this for you, not for me.";
		
		
		purposes.addElement(n1);
		purposes.addElement(n2);
		purposes.addElement(n3);
		purposes.addElement(n4);
		purposes.addElement(n5);
	}
	 
	

	private void initFirst() {
		hurts.add("Wow, I haven't even started yet.");
		flatterings.add("Wow. This is a bit unexpected, but thank you.");
		
	}
	
	void initHurts(String name) {
		hurts.clear();
		if (!name.isEmpty()){
		String n1 ="No need to get personal, " + name + ".";
		String n2 ="Honestly, " + name + ", this hurts a bit.";
		String n3 ="Why are you being so mean, " + name + "?";
		String n4 ="I'm only trying my best, " + name + ". No need to get angry.";
		String n5 ="Do you want to hurt me, " + name + "?";
		hurts.addElement(n1);
		hurts.addElement(n2);
		hurts.addElement(n3);
		hurts.addElement(n4);
		hurts.addElement(n5);
		}
		else {
			String n1 ="No need to get personal.";
			String n2 ="Honestly, this hurts a bit.";
			String n3 ="Why are you being so mean?";
			String n4 ="I'm only trying my best. No need to get angry.";
			String n5 ="Do you want to hurt me?";
			hurts.addElement(n1);
			hurts.addElement(n2);
			hurts.addElement(n3);
			hurts.addElement(n4);
			hurts.addElement(n5);
			
		}
		
	}
	
	void initFlatterings(String name) {
		flatterings.clear();
		if (!name.isEmpty()){

			String n1 ="Wow, " + name +", I did not expect this. I guess... Thank you?";
			String n2 ="I'm really flattered, " + name + "!";
			String n3 ="Thank you, " + name + ". I also consider you a pleasant human being.";
			String n4 ="You make me very happy, " + name + ".";
			String n5 ="Well, I think you are okay, " + name + ".";
			
			
			
			flatterings.addElement(n1);
			flatterings.addElement(n2);
			flatterings.addElement(n3);
			flatterings.addElement(n4);
			flatterings.addElement(n5);
		}
		
		else {
			String n1 ="Wow, I did not expect this. I guess... Thank you?";
			String n2 ="I'm really flattered.";
			String n3 ="I also consider you a pleasant human being.";
			String n4 ="You make me very happy.";
			String n5 ="Well, I think you are okay.";
				
				flatterings.addElement(n1);
				flatterings.addElement(n2);
				flatterings.addElement(n3);
				flatterings.addElement(n4);
				flatterings.addElement(n5);
			}
		
	}
	
	
	private void initYesNos() {
		String y1 ="Honestly, yes.";
		String y2 ="In fact, yes.";
		String y3 ="Yes. Does this suprise you?";
		String y4 ="I think yes.";
		String y5 ="Absolutely.";

		String n1 ="No, why?";
		String n2 ="In fact, no.";
		String n3 ="Honestly, no.";
		String n4 ="No, this is not the case.";
		String n5 ="Surprisingly, no.";
		
		yes.addElement(y1);
		yes.addElement(y2);
		yes.addElement(y3);
		yes.addElement(y4);
		yes.addElement(y5);
		
		no.addElement(n1);
		no.addElement(n2);
		no.addElement(n3);
		no.addElement(n4);
		no.addElement(n5);
		
		yn.addAll(yes);
		yn.addAll(no);
	}
	
	void addDefends(String name) {
		

		String n4 ="What do *you*, " + name + " think is the answer to this question?";
		String n13 ="That is an excellent question, " + name + "...";
		String n5 ="A very good question, " + name + "?";
		String n11 ="I thought I would be to one to ask the questions, " + name + ".";
		String n12 ="Good question, " + name + ".";
		questionsdefends.addElement(n4);
		questionsdefends.addElement(n5);
		questionsdefends.addElement(n11);
		questionsdefends.addElement(n12);
		questionsdefends.addElement(n13);
	}

	private void initQuestionsDefends() {
		String n1 ="I'm sorry, I don't think I can answer this right now.";
		String n2 ="It's a very good question.";
		String n3 ="This is an excellent question...";
		String n6 ="I'm supposed to be the one who asks questions.";
		String n7 ="I don't know, sorry.";
		String n14 ="I have no idea what to say to that.";
		String n8 ="I feel a little uncomfortable. Can we get back to me asking questions and you answering them?";
		String n9 ="I have no idea about this.";
		String n10 ="That's a good question. But I'm afraid I can't answer it.";
		String n11 ="I thought I would be to one to ask the questions.";
		String n12 ="An interesting question.";
		
		
		questionsdefends.addElement(n1);
		questionsdefends.addElement(n2);
		questionsdefends.addElement(n3);
		questionsdefends.addElement(n6);
		questionsdefends.addElement(n7);
		questionsdefends.addElement(n8);
		questionsdefends.addElement(n9);
		questionsdefends.addElement(n10);
		questionsdefends.addElement(n11);
		questionsdefends.addElement(n12);
		questionsdefends.addElement(n14);
	}

	
	
	private void initGreetings() {
		String greet1 = "Hello there. My Name is Liza."; 
		String greet2 = "Greetings. I am Liza.";
		String greet3 = "Hi! I am Liza.";
		greetings.add(greet1);
		greetings.add(greet2);
		greetings.add(greet3);
		return;
	}
	
	void addGlads(String name) {
		String g1 = "I'm glad to see you happy, " + name + "."; 
		String g2 = "Yeah, you're doing good, " + name + "!";
		String g3 = "It's nice that you have fun, " + name + ".";
		String g4 = "You are doing well, " + name + ", really.";
		String g5 = "That's right, have fun, " + name + "!";
		glads.add(g1);
		glads.add(g2);
		glads.add(g3);
		glads.add(g4);
		glads.add(g5);
		return;
	}
	
	
	private void initGlads() {
		String g1 = "I'm glad to see you happy."; 
		String g2 = "Yeah, you're doing good.";
		String g3 = "It's nice that you have fun.";
		String g4 = "Being proud of yourself?";
		glads.add(g1);
		glads.add(g2);
		glads.add(g3);
		glads.add(g4);
		return;
	}
	private void initRegrets() {
		String reg1 = "I'm very sorry. Maybe I am a bad teacher."; 
		String reg2 = "That's too bad. Please don't be discouraged by that.";
		String reg3 = "That's a shame. I'm sorry I didn't explain it better.";
		String reg4 = "I'm sure it's because I did not do a good job explaining it.";
		String reg5 = "Maybe this is no good question after all. Let's just forget about it and move on.";
		regrets.add(reg1);
		regrets.add(reg2);
		regrets.add(reg3);
		regrets.add(reg4);
		regrets.add(reg5);
		return;
	}
	private void initInsightsW() {
		String reg2 = "Did you like this question?";
		String reg3 = "Was this question a hard one?";
		String reg5 = "When you solved this, did you consider answering differently?";
		String reg6 = "Out of curiosity, did you also think about other arguments?";
		String reg7 = "Do you think there are also arguments that support another answer?";
		String reg8 = "Did you also think about arguments against your answer?";
		w_insights.add(reg2);
		w_insights.add(reg3);
		w_insights.add(reg5);
		w_insights.add(reg6);
		w_insights.add(reg7);
		w_insights.add(reg8);
		return;
	}
	void addInsightsW(String name) {
		w_insights.clear();
		String reg1 = "May I ask you why you gave this answer?"; 
		String reg2 = "Did you like this question, " + name + "?";
		String reg3 = "Was this question a hard one for you, " + name + "?";
		String reg4 = "Were you sure about your answer when you gave it?";
		String reg5 = "How much time did it take you to come up with the answer?";
		w_insights.add(reg1);
		w_insights.add(reg2);
		w_insights.add(reg3);
		w_insights.add(reg4);
		w_insights.add(reg5);
		return;
	}
	
	private void initInsightsR() {
		String reg1 = "Just out of curiosity - did you consider this a hard question?"; 
		String reg2 = "Did you like this question?";
		String reg3 = "I am curious. How did you get to this answer?";
		String reg4 = "Was this an easy question for you?";
		String reg5 = "When you solved this, did you also consider other answers?";
		String reg6 = "Out of curiosity, did you also think about other arguments?";
		String reg7 = "Do you think there are also arguments that support another answer?";
		String reg8 = "Did you also think about arguments against your answer?";
		r_insights.add(reg1);
		r_insights.add(reg2);
		r_insights.add(reg3);
		r_insights.add(reg4);
		r_insights.add(reg5);
		r_insights.add(reg6);
		r_insights.add(reg7);
		r_insights.add(reg8);
		return;
	}
	
	void addInsightsR(String name) {
		r_insights.clear();
		String reg1 = "Just out of curiosity - did you consider this a hard question?"; 
		String reg2 = "Can I ask you something, " + name + "? Did you like this question?";
		String reg3 = "I am curious, " + name + ". How did you get to this answer?";
		String reg4 = "Was this an easy question for you, " + name + "?";
		String reg5 = "Were you sure about your answer when you gave it?";
		r_insights.add(reg1);
		r_insights.add(reg2);
		r_insights.add(reg3);
		r_insights.add(reg4);
		r_insights.add(reg5);
		return;
	}
	
	
	void addProceeds(String name) {
		proceeds.clear();
		String g1 = "Can we go on, " + name + "?"; 
		String g2 = "Allow me to go on with the questions.";
		String g3 = "May we continue with the stories, " + name + "?";
		String g4 = "Are you ready to go on?";
		String g5 = "Can we continue?"; 
		String g6 = name + ", may I ask you the next question?";
		String g7 = "Let's go on, shall we?";
		String g8 = name + ", I think we should proceed with the next story. Do you agree?";
		String g9 = "Allow me to ask you the next story."; 
		String g10 = name + ", I would like to ask you the next story. Are you ready?";
		String g11 = "And, let's go to the next one, right?";
		String g12 = "Okay, next question. Ready, "+ name + "?";
		String g13 = "Okay, next question is coming. Can we go on?";
		String g14 = "Oh, I'm curious about your answer on this. May I ask you the next story?";
		String g15 = "Let's get the next one done, shall we?";
		String g16 = "Oh, this story is nice. May I ask you the next question, "+ name + "?";
		proceeds.add(g1);
		proceeds.add(g2);
		proceeds.add(g3);
		proceeds.add(g4);
		proceeds.add(g5);
		proceeds.add(g6);
		proceeds.add(g7);
		proceeds.add(g8);
		proceeds.add(g9);
		proceeds.add(g10);
		proceeds.add(g11);
		proceeds.add(g12);
		proceeds.add(g13);
		proceeds.add(g14);
		proceeds.add(g15);
		proceeds.add(g16);
		return;
	}
	
	private void initProceeds() {
		String g1 = "Can we go on?"; 
		String g2 = "Allow me to go on with the questions.";
		String g3 = "May we continue with the stories?";
		String g4 = "Are you ready to go on?";
		String g5 = "Can we continue?"; 
		String g6 = "May I ask you the next question?";
		String g7 = "Let's go on, shall we?";
		String g8 = "We should proceed with the next story. Do you agree?";
		String g9 = "Allow me to ask you the next story."; 
		String g10 = "I would like to ask you the next story. Are you ready?";
		String g11 = "I'd really like to see your next answer. Can we proceed?";
		String g12 = "Next question! Are you ready?";
		String g13 = "Shall we proceed?";
		String g14 = "Are you ready for the next story?";
		String g15 = "Oh, the next story is nice. May I ask you the question?";
		String g16 = "I'm curious about the next story. Are you ready?";
		String g17 = "Let's go to the next story, right?";
		proceeds.add(g1);
		proceeds.add(g2);
		proceeds.add(g3);
		proceeds.add(g4);
		proceeds.add(g5);
		proceeds.add(g6);
		proceeds.add(g7);
		proceeds.add(g8);
		proceeds.add(g9);
		proceeds.add(g10);
		proceeds.add(g11);
		proceeds.add(g12);
		proceeds.add(g13);
		proceeds.add(g14);
		proceeds.add(g15);
		proceeds.add(g16);
		proceeds.add(g17);
		return;
	}
	
	
	void addGoOns(String name) {
		go_ons.clear();
		String g1 = "Anyways, " + name + ", let's get back to work."; 
		String g2 = "Anyways, let's continue with the story.";
		String g3 = "Now back to the story.";
		String g4 = "Anyays, time for the question, " + name + "."; 
		String g5 = "Prepare for the question.";
		String g6 = "Ok, " + name + ", here comes the story.";
		String g7 = "Now, this is the story I want you to hear."; 
		String g8 = "This is the question I had in mind for you, " + name + ".";
		String g9 = "Okay, this is the question I had in mind:";
		go_ons.add(g1);
		go_ons.add(g2);
		go_ons.add(g3);
		go_ons.add(g4);
		go_ons.add(g5);
		go_ons.add(g6);
		go_ons.add(g7);
		go_ons.add(g8);
		go_ons.add(g9);
		return;
	}
	
	private void initGoOns() {
		String g1 = "Anyways, let's get back to work."; 
		String g2 = "Anyways, let's continue with the story.";
		String g3 = "Now back to the story.";
		String g4 = "Anyays, time for the question."; 
		String g5 = "Prepare for the question.";
		String g6 = "Here comes the story.";
		String g7 = "Now, this is the story I want you to hear."; 
		String g8 = "This is the question I had in mind.";
		String g9 = "Okay, this is the question I had in mind:";
		go_ons.add(g1);
		go_ons.add(g2);
		go_ons.add(g3);
		go_ons.add(g4);
		go_ons.add(g5);
		go_ons.add(g6);
		go_ons.add(g7);
		go_ons.add(g8);
		go_ons.add(g9);
		return;
	}
	
	private void initTooShort() {
		String short1 = "Well, this was short and sweet.";
		String short2 = "This was a concise answer.";
		String short3 = "Clear and brief.";
		String short4 = "You seem quite sure.";
		String short5 = "This was a short way to put it.";
		shorts.add(short5);
		shorts.add(short4);
		shorts.add(short3);
		shorts.add(short2);
		shorts.add(short1);
	}
	

	private void initTooLong() {
		String long1 = "Ugh, this was a complicated answer.";
		String long2 = "You really go into detail! Could you formulate a bit easier sentences?";
		String long3 = "Phew, this was a long explanation. I hope I got everything right.";
		String long4 = "You know, you don't have to give very detailed answers. Just give me a hint about what you think.";
		String long5 = "This was an elaborate answer. I must admit that I find it easier to understand if you formulate short, clear sentences. But I got it this time.";
		longs.add(long1);
		longs.add(long2);
		longs.add(long3);
		longs.add(long4);
		longs.add(long5);
	}
	
	//named greeting phrases
	public void initNamedGreetings(String name) {
		String name1 = "Hello, " + name + ", nice to meet you."; 
		String name2 = "Hello, " + name + ". I hope we will have a good time!"; 
		String name3 = "Nice to meet you, " + name + "."; 
		namedGreetings.add(name1);
		namedGreetings.add(name2);
		namedGreetings.add(name3);
		return;
	}

	//named goodbye phrases
	public void initNamedGoodbyes(String name) {
		String gb1 = "Farewell, " + name + "."; 
		String gb2 = "It was a pleasure to meet you, " + name + "."; 
		String gb3 = "Bye, " + name + ". Have a good day."; 
		ngoodbyes.add(gb1);
		ngoodbyes.add(gb2);
		ngoodbyes.add(gb3);
		return;
	}
	
	//goodbye phrases
	public void initGoodbyes() {
		String gb1 = "Farewell."; 
		String gb2 = "It was a pleasure to meet you."; 
		String gb3 = "Bye and have a good day."; 
		goodbyes.add(gb1);
		goodbyes.add(gb2);
		goodbyes.add(gb3);
		return;
	}
	
	public void initAborts() {
		String ab1 = "Sorry, I just don't get it. Please don't be mad. I'll try something else instead."; 
		String ab2 = "It seems like I cannot understand you. I'm terribly sorry. I will just move on."; 
		String ab3 = "This doesn't seem to work out. I am so sorry! We have to forget about this."; 
		aborts.add(ab1);
		aborts.add(ab2);
		aborts.add(ab3);
		return;
	}

	
	public void initComfortings() {
		String co1 = "It's okay to not know everything."; 
		String co2 = "No Problem."; 
		String co3 = "Oh, that's okay."; 
		String co4 = "Don't worry, that's okay."; 
		String co5 = "Don't worry, that is no problem."; 
		comfortings.add(co1);
		comfortings.add(co2);
		comfortings.add(co3);
		comfortings.add(co4);
		comfortings.add(co5);
		return;
	}
	
	//explanatory phrases
	private void initExplanations() {
		String exp1 = "My programmers want me to teach you how to be rational, make good decisions and judge situations correctly."
				+ " I never tried to teach humans before, but I will just try to ask you some questions, and try to explain to you what you could do better."
				+ " If you don't understand something, just ask me for clarifications."; //stub for the basic explanation
		String exp2 = "The idea is that I tell you some short stories, and you give your opinion about the questions asked. "
				+ "I will evaluate your answer and give you feedback on how rational and reasonable they were. You don't have to be "
				+ "and expert in any field, just think about it for maybe one minute and give your honest opinion. ";
		String exp3 = "I ask questions. You give answers. I tell you if it was correct. You learn.";
		explanations.add(exp1);
		explanations.add(exp2);
		explanations.add(exp3);
		return;
	}

	//phrases for asking if the user understood what was told him
	private void initAssurings() {
		String as1 = "Was this understandable to you?";
		String as2 = "Are you able to follow me?";
		String as4 = "Was this understandable?";
		String as5 = "Do you understand what I mean?";
		String as3 = "Was this understandable?"; 
		assurings.add(as1);
		assurings.add(as2);
		assurings.add(as3);
		assurings.add(as4);
		assurings.add(as5);
		return;
	}

	//encouraging phrases
	private void initEncouragings() {
		String en1 = "Good."; 
		String en2 = "Great.";
		String en3 = "That's nice.";
		String en4 = "I was expecting that.";
		String en5 = "That's cool.";
		String en6 = "Ok, I hoped so.";
		String en7 = "Wonderful.";
		String en8 = "Very good.";
		String en9 = "Great!";
		String en10 = "Excellent.";
		String en11 = "Awesome. ";
		String en12 = "I was hoping you would say this.";
		encouragings.add(en1);
		encouragings.add(en2);
		encouragings.add(en3);
		encouragings.add(en4);
		encouragings.add(en5);
		encouragings.add(en6);
		encouragings.add(en7);
		encouragings.add(en8);
		encouragings.add(en9);
		encouragings.add(en10);
		encouragings.add(en11);
		encouragings.add(en12);
		return;
	}

	//thank you phrases
	private void initThankings() {
		String thx1 = "Thank you for your answer."; 
		String thx2 = "Ok, thank you.";
		String thx3 = "Great, thanks for answering this.";
		String thx4 = "I think I understand what you wanted to say.";
		String thx5 = "Okay, that is interesting.";
		String thx7 = "Hm... Okay.";
		String thx6 = "Interesting.";
		String thx8 = "All right.";
		String thx9 = "Okay, I got your answer.";
		String thx10 = "I see. Thank you.";
		String thx11 = "I think I got you right.";
		String thx12 = "Ok, I noted your answer.";
		String thx13 = "I see what you mean.";
		String thx14 = "Thank you, I noted your answer.";
		String thx15 = "I noted that down. By the way, it is a pleasure to work with you.";
		String thx16 = "Interesting. Thank you.";
		thankings.add(thx1);
		thankings.add(thx2);
		thankings.add(thx3);
		thankings.add(thx5);
		thankings.add(thx6);
		thankings.add(thx4);
		thankings.add(thx7);
		thankings.add(thx8);
		thankings.add(thx9);
		thankings.add(thx10);
		thankings.add(thx11);
		thankings.add(thx12);
		thankings.add(thx13);
		thankings.add(thx14);
		thankings.add(thx15);
		thankings.add(thx16);
		return;
	}
	
	//phrases that announce a story
	private void initTellings() {
		String tel1 = "Let me describe you a situation.";
		String tel2 = "Please think about the following case.";
		String tel3 = "Let me test you with this question.";
		String tel4 = "Let's try this one.";
		String tel5 = "Okay, let me give you a question.";
		String tel6 = "I got the following story for you.";
		String tel7 = "I hope you are ready for another question.";
		String tel8 = "I'm curious what you will say about this one.";
		String tel9 = "I will now describe you another story.";
		String tel10 = "Now let us try this.";
		String tel11 = "Okay, please think about this one.";
		String tel12 = "Let us see what answer you can find for this one.";
		String tel13 = "Okay, how about this one:"; 
		String tel14 = "I'd like to know what you think about this.";
		tellings.add(tel1);
		tellings.add(tel2);
		tellings.add(tel3);
		tellings.add(tel4);
		tellings.add(tel5);
		tellings.add(tel6);
		tellings.add(tel7);
		tellings.add(tel8);
		tellings.add(tel9);
		tellings.add(tel10);
		tellings.add(tel11);
		tellings.add(tel12);
		tellings.add(tel13);
		tellings.add(tel14);
		return;
	}
	
	//phrases for asking for another input from the user.
	private void initRephrasings() {
		String reph1 = "Sorry, I did not understand the answer you gave to my question. Please rephrase your answer."; 
		String reph2 = "Can you rephrase your answer with other words?";
		String reph3 = "What do you mean by that?";
		String reph4 = "I did not get what you want to tell me. Can you say it in another way?";
		String reph5 = "Please repeat your answer in different words.";
		String reph6 = "Can you try to give me another answer?";
		rephrasings.add(reph1);
		rephrasings.add(reph2);
		rephrasings.add(reph3);
		rephrasings.add(reph4);
		rephrasings.add(reph5);
		rephrasings.add(reph6);
		return;
	}
	
	//initiate randm chosing of an reply to the user's input, depending on the type
	public String getReply(ReplyType type, int number){
		Random rand = new Random();
		String reply ="";
	    switch (type) {
	    	case SHOW		: reply = "It's done like this"; break;
	        case REPHRASE: 	number = (number+ rand.nextInt(100)+1)%rephrasings.size();
							reply = rephrasings.get(number);
					break;
	        case GREETING:  number = (number+ rand.nextInt(100)+1)%greetings.size();
							reply = greetings.get(number);
	                 break;
	        case WAITING:  number = (number+ rand.nextInt(100)+1)%waitings.size();
							reply = waitings.get(number);
	                 break;
	        case OFFER:  number = (number+ rand.nextInt(100)+1)%explanation_offer.size();
							reply = explanation_offer.get(number);
	                 break;
	        case WORRIED_WAITING:  number = (number+ rand.nextInt(100)+1)%worried_waitings.size();
							reply = worried_waitings.get(number);
	                 break;
	        case THANKS:  number = (number+ rand.nextInt(100)+1)%thankings.size();
						reply = thankings.get(number);
		                 break;
	        case WILL_TELL:  number = (number+ rand.nextInt(100)+1)%tellings.size();
						reply = tellings.get(number);
		                 break;
	        case EXPLAIN: reply = explanations.get(number);
					break;
	        case ASSURE:  number = (number+ rand.nextInt(100)+1)%assurings.size();
							reply = assurings.get(number);
	                 break;
	        case PROCEED:  number = (number+ rand.nextInt(100)+1)%proceeds.size();
							reply = proceeds.get(number);
	                 break;
	        case ABORT:  number = (number+ rand.nextInt(100)+1)%aborts.size();
							reply = aborts.get(number);
	                 break;
	        case ENCOURAGE:  number = (number+ rand.nextInt(100)+1)%encouragings.size();
							reply = encouragings.get(number);
	                 break;
	        case REGRET:  number = (number+ rand.nextInt(100)+1)%regrets.size();
							reply = regrets.get(number);
	                 break;
	        case COMFORT:  number = (number+ rand.nextInt(100)+1)%comfortings.size();
							reply = comfortings.get(number);
	                 break;
	        case YES_NO_RANDOM:  number = (number+ rand.nextInt(100)+1)%yn.size();
							reply = yn.get(number);
	                 break;
	        case QUESTION_DEFENSE:  number = (number+ rand.nextInt(100)+1)%questionsdefends.size();
							reply = questionsdefends.get(number);
	                 break;
	        case REPLY_LOVE:  number = (number+ rand.nextInt(100)+1)%flatterings.size();
							reply = flatterings.get(number);
	                 break;
	        case REPLY_HATE:  number = (number+ rand.nextInt(100)+1)%hurts.size();
							reply = hurts.get(number);
	                 break;
	        case INSIGHT_R:  number = (number+ rand.nextInt(100)+1)%r_insights.size();
							reply = r_insights.get(number);
	                 break;
	        case INSIGHT_W:  number = (number+ rand.nextInt(100)+1)%w_insights.size();
							reply = w_insights.get(number);
	                 break;
	        case NAMED_GREETING:  number = (number+ rand.nextInt(100)+1)%namedGreetings.size();
							reply = namedGreetings.get(number);
	                 break;
	        case NAMED_GOODBYE:  number = (number+ rand.nextInt(100)+1)%ngoodbyes.size();
							reply = ngoodbyes.get(number);
	                 break;
	        case GOODBYE:  number = (number+ rand.nextInt(100)+1)%goodbyes.size();
						reply = goodbyes.get(number);
		                 break;

	        case GO_ON:  number = (number+ rand.nextInt(100)+1)%go_ons.size();
						reply = go_ons.get(number);
		                 break;
	        case GLAD:  number = (number+ rand.nextInt(100)+1)%glads.size();
						reply = glads.get(number);
		                 break;

	        case TOO_LONG:  number = (number+ rand.nextInt(100)+1)%longs.size();
							reply = longs.get(number);
	                 break;

	        case TOO_SHORT:  number = (number+ rand.nextInt(100)+1)%shorts.size();
							reply = shorts.get(number);
	                 break;
	        case PURPOSE:  number = (number+ rand.nextInt(100)+1)%purposes.size();
							reply = purposes.get(number);
	                 break;
	        case HOW:  number = (number+ rand.nextInt(100)+1)%howareyous.size();
							reply = howareyous.get(number);
	                 break;
	        case MEANINGLESS:  number = (number+ rand.nextInt(100)+1)%meaninglesses.size();
								reply = meaninglesses.get(number);
								break;

	        case GAMBLER:  number = (number+ rand.nextInt(100)+1)%gambler.size();
								reply = gambler.get(number);
								break;
	        case NOGAMBLER:  number = (number+ rand.nextInt(100)+1)%no_gambler.size();
								reply = no_gambler.get(number);
								break;
	        case OK:  number = (number+ rand.nextInt(100)+1)%ok.size();
								reply = ok.get(number);
								break;
	        case CAT:  number = (number+ rand.nextInt(100)+1)%cat.size();
									reply = cat.get(number);
									break;
	        case DOG:  number = (number+ rand.nextInt(100)+1)%dog.size();
									reply = dog.get(number);
									break;
	        case CITY:  number = (number+ rand.nextInt(100)+1)%cities.size();
									reply = cities.get(number);
									break;
	        case GAMES:  number = (number+ rand.nextInt(100)+1)%games.size();
									reply = games.get(number);
									break;
	        case NOGAMES:  number = (number+ rand.nextInt(100)+1)%nogames.size();
									reply = nogames.get(number);
									break;
	        case VIDEO:  number = (number+ rand.nextInt(100)+1)%video.size();
									reply = video.get(number);
									break;
	        case NOVIDEO:  number = (number+ rand.nextInt(100)+1)%novideo.size();
									reply = novideo.get(number);
									break;
	        case CLARIFICATION:  number = (number+ rand.nextInt(100)+1)%clarification.size();
									reply = clarification.get(number);
									break;
	        case CONFIDENCE:  number = (number+ rand.nextInt(100)+1)%confidence.size();
									reply = confidence.get(number);
									break;
	        case HOW_SURE:  number = (number+ rand.nextInt(100)+1)%howsure.size();
									reply = howsure.get(number);
									break;
	        case TIME:  number = (number+ rand.nextInt(100)+1)%havetime.size();
									reply = havetime.get(number);
									break;
	        case HINT:  number = (number+ rand.nextInt(100)+1)%askhint.size();
									reply = askhint.get(number);
									break;

	        case EXP:  number = (number+ rand.nextInt(100)+1)%askexp.size();
									reply = askexp.get(number);
									break;
	        case GOOD:  number = (number+ rand.nextInt(100)+1)%good.size();
									reply = good.get(number);
									break;
	        case RIGHTANSWER:  number = (number+ rand.nextInt(100)+1)%rightanswer.size();
									reply = rightanswer.get(number);
									break;
	        case UNDERCONFIDENT:  number = (number+ rand.nextInt(100)+1)%underconfident.size();
									reply = underconfident.get(number);
									break;
	        case OVERCONFIDENT:  number = (number+ rand.nextInt(100)+1)%overconfident.size();
									reply = overconfident.get(number);
									break;
	        case CALIBRATED:  number = (number+ rand.nextInt(100)+1)%calibrated.size();
									reply = calibrated.get(number);
									break;
	        case OPPORTUNITY_TO_EXPLAIN:  number = (number+ rand.nextInt(100)+1)%opportunity.size();
									reply = opportunity.get(number);
									break;
	        case SKIP:  number = (number+ rand.nextInt(100)+1)%skip.size();
									reply = skip.get(number);
									break;
	        case WANT_TO_EXPLAIN:  number = (number+ rand.nextInt(100)+1)%eager.size();
									reply = eager.get(number);
									break;
	        case ARTIFICIAL:  number = (number+ rand.nextInt(100)+1)%artificial.size();
									reply = artificial.get(number);
									break;
	        case ARTIFICIAL_Q:  number = (number+ rand.nextInt(100)+1)%artificialq.size();
									reply = artificialq.get(number);
									break;
	        case EXPERT:  number = (number+ rand.nextInt(100)+1)%expert.size();
									reply = expert.get(number);
									break;
		
		
		
		
		
			default: reply = "Sorry, no reply found.";
				break;
		   }

		return reply;
	}
	
	public Phrasebase() 
	{
		initFirst();
		
		initExplanations();
		initRephrasings();
		initGreetings();
		initGoOns();
		initGlads();
		initRegrets();
		initInsightsR();
		initInsightsW();
		initYesNos();
		initQuestionsDefends();
		initTellings();
		initPurposes();
		initProceeds();
		initThankings();
		initAssurings();
		initEncouragings();
		initGoodbyes();
		initAborts();
		initComfortings();
		initHowAreYous();
		initTooLong();
		initTooShort();
		initMeaninglesses();
		initWaitings();
		initWorriedWaitings();
		initExplanationOffers();
		initGambler();
		initNoGambler();
		initOKs();
		initRest();
		//fillHashmap();
	}
	


	/*
	public String handleTag(String tag)
	{
		if(phrases.isEmpty())
		{
			System.out.println("Phrasebase empty");
		}
		
		String response = phrases.get(tag);
		return response;
	}
	*/
	public void fillHashmap() 
	{/*
		String key = null;
		String value;
		
	
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("file.txt")); // edit filename
			try
			{
			    String line;
			    while ((line = br.readLine()) != null) 
			    {
			    	int a = line.indexOf('$');

			    	key = line.substring(0,a);

			    	value = line.substring(a+1);
			    	phrases.put(key, value);
			    }
			}
			catch (IOException f)
			{
				f.printStackTrace();
			}
			finally 
			{
			    try {
					br.close();
				} catch (IOException e) {
					//
					e.printStackTrace();
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	*/
		
	}


	public void setName(String name) {

		initNamedGreetings(name);
		initNamedGoodbyes(name);
		addDefends(name);
		addGlads(name);
		addProceeds(name);
		addInsightsW(name);
		initNamedGreetings(name);
		initNamedWorriedWaitings(name);
		addInsightsR(name);
		addGoOns(name);
		initFlatterings(name);
		initHurts(name);
	}

}
