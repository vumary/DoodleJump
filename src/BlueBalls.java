
public class BlueBalls extends Block{
	
	public BlueBalls(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 500 + 75), y, 50, 20, 130, 0);
	}
	
	public void moveUp() {
		y-=5;
		x+=5;
		img.setBounds(x, y, width, height);
	}
	public void moveDown() {
		y+=5;
		x-=5;
		img.setBounds(x, y, width, height);
	}
}
