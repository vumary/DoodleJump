
public class Background extends Block{
	
	public Background(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, 0, y-4400, 600, 5000, 0, 0);
	}
	public void moveDown() {
		y+=1;
		img.setBounds(x, y, width, height);
		
	}
	
	public void moveUp() {
		y-=1;
		img.setBounds(x, y, width, height);
	}
	public String toString() {
		return "BGGGGGG";
	}
}
