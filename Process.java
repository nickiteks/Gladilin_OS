import java.util.ArrayList;
import java.util.Random;

public class Process {
	private int id;
    private int givenTime;
	ArrayList<Flow> flows = new ArrayList<Flow>();
	Random rnd = new Random();
	public Process(int id , int givenTime) {	
		this.id =id;
		this.givenTime = givenTime;
		for (int i = 0; i < rnd.nextInt(5)+1; i++) {
			flows.add(new Flow( i , rnd.nextInt(2)+1 , createPriority()));
		}
	}
	
	public String print() {
		String str = "";
		for (int i = 0; i < flows.size(); i++) {
			str += flows.get(i).getId()+ " " + " with time-> " + flows.get(i).getWorkTime() + "sec  " + "with priority-> " + flows.get(i).getPriority()  + "\n";
		}
		return str;
	}	
	public int getGivenTime() {
		return givenTime;
	}
	
	public void DoFlow() {
		for (Flow flow : flows) {
			if(flow.getPriority().equals("high"))
				runFlow(flow);
		} 
		for (Flow flow : flows) {
			if(flow.getPriority().equals("middle"))
				runFlow(flow);
		}
		for (Flow flow : flows) {
			if(flow.getPriority().equals("low"))
				runFlow(flow);
		}		
	}
	
	public String createPriority(){

		switch (rnd.nextInt(2)+1) {
		case 1:
			 return "high";
		case 2:
			return "middle";
		case 3:
			return "low";
		
		}
		return null;
	}
	public void runFlow (Flow flow) {
		if(givenTime >= flow.getWorkTime()) {
			givenTime = givenTime -flow.getWorkTime();
			System.out.println("flow "+ flow.getId() + " is done time left-> " + givenTime);
		}
		else{
			System.out.println("not enough time for " + flow.getId());
		}
	}
}
