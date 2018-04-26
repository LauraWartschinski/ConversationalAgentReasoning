# A Conversational Agent for the Improvement of Human Reasoning Skills

This is the project I did for my bachelor thesis in 2015/16 - A conversational agent (aka Chatbot) named "Liza" who talks to people in an attempt to teach them **Rationality**. Rationality in this case is defined by the absence of irrationial choices, in the form of some well-studied reasoning problems. The intervention of talking to the agent for some time improved performance on those tasks significantly more than just reading a textbook explanation about how they work (n=65). 

The Bot explains different topics and asks example questions, provides helpfuls hints and example solutions, and gives feedback. It reacts to user absence and a broad variety of different user-initiated dialogue, it assesses the confidence of the user as well as the correctness of the solutions, and gives further tips for improvement. The high believability of the agent as being "intelligent", despite being coded in a kind of old-fashioned way in Java, is achieved by incorporating very precise information about the dialogue context in every generated answer and never loosing track of what happens.

The persona of the (purely textual) agent is that of a "young", newly created computer program who is eager to be a good teacher, but is not completely perfected yet. Reasons for this personality choice are also described in detail in the thesis, as well as the architecture, experimental setup, evaluation etc.

![architecture](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/architecture.png)

![components](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/components.png)


More information about the general concept of reasoning and rationality can be found in the PDF version of my thesis. It received the best possible grade and I got a price for it, mostly because of the idea and scientific value, but I am not exactly proud of code itself.  It is not really prepared for the general public, not really documented, and some of the code is inelegant and not efficient. There are still some bugs. I rarely used comments at all. I guess the architecture is okay. I would require some cleanup, but I would rather move on with new projects than maintain old stuff that served its purpose.

I will just include some impressions. The snippets are taken from different parts of the conversation and do not come from the same teaching session, and are not neccessarily in that order. They just stand for themselves.

![Intro](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/onlineConversation.png)


#### Intro ####
![Intro](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/intro.png)

![Intro](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/intro2.png)

#### Explaining and Dialogue ####
![Explanation](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/explanation.png)

![Assessig confidence](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/confidence.png)

#### Smalltalk ####
![smalltalk](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/smalltalk2.png)

![smalltalk](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/smalltalk.png)

#### Reaction to absence ####
![reaction](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/reaction0.png)

![reaction](https://github.com/LauraWartschinski/ConversationalAgentReasoning/blob/master/images/reaction.png)

