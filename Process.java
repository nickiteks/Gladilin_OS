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
			flows.add(new Flow( i , rnd.nextInt(2)+1, givenTime));
		}
	}
	
	public String print() {
		String str = "";
		for (int i = 0; i < flows.size(); i++) {
			str += flows.get(i).getId()+ " " + " with time-> " + flows.get(i).getWorkTime() + "sec" + "\n";
		}
		return str;
	}	
	public void DoFlow() {
		for (int i = 0; i < flows.size(); i++) {
			if(givenTime >= flows.get(i).getWorkTime()) {
				givenTime = givenTime -flows.get(i).getWorkTime();
				System.out.println("flow "+ i + " is done time left-> " + givenTime);				
			}
			else{
				System.out.println("not enough time");
			}
		}		
	}
}
