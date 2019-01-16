public class Stationary extends Block {
	
	public Stationary(String filename, int y) {
		
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 600), y, 80, 30, 80, 8);
		
	}
	public String toString() {
		return "Stationaryyyy";
	}
	
}
