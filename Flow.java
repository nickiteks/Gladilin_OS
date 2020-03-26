import java.time.Period;

public class Flow {
	private int id;
	private int workTime;
	private String priority;
	public Flow(int id, int workTime , String priority) {
		this.id =id;
		this.workTime = workTime;
		this.priority = priority;
	}
	public int getId() {
		return id;
	}
	public int getWorkTime() {
		return workTime;
	}	
	
	public String getPriority() {
		return priority;
	}
}
