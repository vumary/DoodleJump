
public class BlueBalls extends Block{
	
	private int initialX;
	private boolean left;
	private int xV;
	private int shift;

	public BlueBalls(String filename, int x, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, x, y, 120, 26, 80, 8);
		initialX = x;
		xV = 8;
		shift = (int) (Math.random()*50 + 100);
	}
	
	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
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

	public void moveLeft() {
		x -= xV;
		img.setBounds(x, y, width, height);
	}
	public void moveRight() {
		x += xV;
		img.setBounds(x, y, width, height);

	}
	public String toString() {
		return "Blueee BAls";
	}
}
