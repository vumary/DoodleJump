import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
	
	protected int boost;	//how much this block boosts the player up
	protected int vel;	//velocity that it moves side to side
	protected int x, y;		//location (random)
	protected int initialY;	//initial position of the block
	protected String color;
	protected int width, height;	
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
		this.initialY = y;
		this.boost = boost;
		this.vel = vel;
		img.setBounds(x, y, width, height);
	}
	
	public void moveDown() {
		y+=2;
		img.setBounds(x, y, width, height);
		
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

}
