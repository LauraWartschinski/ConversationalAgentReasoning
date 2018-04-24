import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Stories {
	//lists to story the stories
    List<Story> formals = new ArrayList<Story>();
    List<Story> rhetoricals = new ArrayList<Story>();
    List<Story> mathematicals = new ArrayList<Story>();
    List<Story> propositionals = new ArrayList<Story>();
    List<Story> statisticals = new ArrayList<Story>();
    List<Story> consistency = new ArrayList<Story>();
    List<Story> evidence = new ArrayList<Story>();
    

	//method for checking if there are stories left in this topic
	boolean storiesLeft(int topic){
		switch (topic) {
	        case 0:  return !formals.isEmpty(); 
	        case 1:  return !rhetoricals.isEmpty();
	        case 2:  return !mathematicals.isEmpty();
	        case 3:  return !propositionals.isEmpty();
	        case 4:  return !statisticals.isEmpty();
	        case 5:  return !consistency.isEmpty();
	        case 6:  return !evidence.isEmpty();
			default: System.out.println("no number of a type of stories!"); return false;
		}
		
	}

	//get a story out of a chosen topic and delete it there
	Story getStory(int topic){
		Story story = null;
		Random rand = new Random();
		int index = (rand.nextInt(100)+1);
				
		
		switch (topic) {
	        case 0:  story = formals.get(index%formals.size());
	        		 formals.remove(index%formals.size());
	        		 break;
	        case 1:  story = rhetoricals.get(index%rhetoricals.size());
	        		rhetoricals.remove(index%rhetoricals.size());
   		 				break;
	        case 2:  story = statisticals.get(index%statisticals.size());
    				statisticals.remove(index%statisticals.size());
    				break;
	        case 3:  story = consistency.get(index%consistency.size());
	        		consistency.remove(index%consistency.size());
	        		break;
	        case 4:  story = evidence.get(index%evidence.size());
    				evidence.remove(index%consistency.size());
    				break;
	        default: System.out.println("no number of a type of stories!");  break;
		}
		
		
		return story;
	}
	
	public Stories()
	{
	
		
		//initialize all the stories 
				
		
		
		
		
		
		// old stories, delete?
		
		
		/*
		*/ 
		
	}
}
