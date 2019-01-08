public class Stationary extends Block {
	
	/*
	 	int x = (int)(Math.random() * 600);
		int width = 50;
		int height = 20;
	 */
	
	public Stationary(String filename, int y) {
		super(filename, (int)(Math.random() * 600), y, 50, 20, 100, 0);
	}
}
