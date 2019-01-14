import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
	
	protected int boost;	//how much this block boosts the player up
	protected int vel;	//velocity that it moves side to side
	protected int x, y;		//location (random)
	protected String color;
	protected final int width, height;	
	protected JLabel img;	
	
	public Block(String filename, int x, int y, int width, int height, int boost, int vel) {
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+filename);
		img = new JLabel(ghost); //connect 
		
		//bound img to the object
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.boost = boost;
		this.vel = vel;
		img.setBounds(x, y, width, height);
	}
	
	public boolean getLeft() {
		return false;
	}

	public void setLeft(boolean left) {
	}
	
	public int getInitialX() {
		return 0;
	}
	
	public void moveDown() {
		y+=5;
		img.setBounds(x, y, width, height);
		
	}
	
	public void moveUp() {
		y-=5;
		img.setBounds(x, y, width, height);
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public int getBoost() {
		return boost;
	}

	public void setBoost(int boost) {
		this.boost = boost;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	
	public int getHeight() {
		return height;
	}

	
	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}
	
	public void moveRight() {

}
	public String toString() {
		return "Block";
	}
	
}
