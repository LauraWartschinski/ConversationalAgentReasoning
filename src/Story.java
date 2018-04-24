 
public class Story {

	String text;
	String question;
	String clarify; 
	ContextType context;
	int type;
	int construction;
	Story next;
	String intro;
	String intro1;
	String intro2;
	String right;
	String wrong;
	String explain;
	
	String[][] tags;
	public String hint;
	
	public Story(int qtype, String qtext, String qquestion, String qclarify, ContextType qcontext, String[][] qtags, int construct, Story s, String i, String i1, String i2, String r, String w, String e, String h){
		text = qtext;
		question = qquestion;
		context = qcontext;
		clarify = qclarify;
		type = qtype;
		tags = qtags;
		construction = construct;
		next = s;
		intro = i;
		intro1 = i1;
		intro2 = i2;
		right = r;
		wrong = w;
		explain = e;
		hint = h;
	}

	public void city(String city) {
		// TODO Auto-generated method stub
		
	}

}
