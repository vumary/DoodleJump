import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block {
	
	private int boost;	//how much this block boosts the player up
	private int x, y;		//location (random)
	private String color;
	private int width, height;	
	private JLabel img;	
	
	public Block(String filename, int x, int y, int width, int height) {
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+filename);
		img = new JLabel(ghost); //connect 
		
		//bound img to the object
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		img.setBounds(x, y, width, height);
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
