import java.util.ArrayList;
import java.util.Random;

public class Planner {
	
	Random rnd = new Random();
	ArrayList<Process> processes = new ArrayList<Process>();
	public Planner() {
		for (int i = 0; i < rnd.nextInt(3)+1 ; i++) {
			processes.add(new Process(i,1));
		}
		
	}	
	public void Work() {
		Info();
		for (int i = 0; i < processes.size() ; i++) {
			System.out.println("process "+ i + " started");
			processes.get(i).DoFlow();
		}
		
	}
	
	public void Info() {
		for (int i = 0; i < processes.size(); i++) {
			System.out.print("Process  " + i + " have flows->" + "\n" +  processes.get(i).print());
		}	
		System.out.println("--------------------------------------");
	}
}
