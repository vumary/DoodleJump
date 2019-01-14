
public class BlueBalls extends Block{
	
	private int initialX;
	private boolean left;

	public BlueBalls(String filename, int x, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, x, y, 50, 20, 130, 0);
		initialX = x;
	}
	
	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public void moveUp() {
		y-=5;
		img.setBounds(x, y, width, height);
	}
	public void moveDown() {
		y += 5;
		img.setBounds(x, y, width, height);
	}
	public void moveLeft() {
		x -= 5;
		img.setBounds(x, y, width, height);
	}
	public void moveRight() {
		x += 5;
		img.setBounds(x, y, width, height);

	}
	public String toString() {
		return "Blueee BAls";
	}
}
