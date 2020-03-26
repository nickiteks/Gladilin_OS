
public class Flow {
	private int id;
	private int workTime;
	private int givenTime;
	public Flow(int id, int workTime , int givenTime) {
		this.id =id;
		this.workTime = workTime;
		this.givenTime = givenTime;
	}
	public int getId() {
		return id;
	}
	public int getWorkTime() {
		return workTime;
	}	
}
