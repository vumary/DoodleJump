public class Boosted extends Block {
	
	public Boosted(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 600), y, 50, 20, 100, 0);
	}
	
	//change boost or something
	public void moveUp() {
		y-=8;
		img.setBounds(x, y, width, height);
	}
	public void moveDown() {
		y+=8;
		img.setBounds(x, y, width, height);
	}
	public String toString() {
		return "Boosted";
	}
}
