
public class Page {
	
	private int startAdress;
	private int endAdress;
	public Page(int startAdress , int endAdress) {
		this.endAdress = endAdress;
		this.startAdress = startAdress;
		
	}	
	
	public int getStartAdress(){
		return startAdress;
	}
	public int getEndAdress() {
		return endAdress;
	}
}
