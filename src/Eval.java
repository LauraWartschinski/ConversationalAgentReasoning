
public class Eval {

	public Story story;
	public boolean asked = false;
	
	public InputType input;
	public String answer;
	
	public Eval(Story e_story, boolean e_asked, InputType e_input, String e_answer) {
		story = e_story;
		asked = e_asked;
		input = e_input;
		answer = e_answer;
	}
	
}
