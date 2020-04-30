
public class Page {
	
	private int startAdress;
	private int endAdress;
	private int usage;
	public Page(int startAdress , int endAdress) {
		this.endAdress = endAdress;
		this.startAdress = startAdress;		
	}	
	public void setUsage(int usage) {
		this.usage = usage;		
	}
	public int getUsages() {
		return usage;
	}
	public int getStartAdress(){
		return startAdress;
	}
	public int getEndAdress() {
		return endAdress;
	}
}
