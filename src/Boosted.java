public class Boosted extends Block {
	
	public Boosted(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 600), y, 70, 28, 120, 8);
	}
	
	//change boost or something
	public String toString() {
		return "Boosted";
	}
}
