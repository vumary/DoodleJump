import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player {
	
	private int x, y;
	private int dir;			
	private final int width, height;	
	private JLabel img;	
	int score =0; 

	//add constructor that takes in a file name
	public Player(String filename) {
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+filename);
		img = new JLabel(ghost); //connect 
		
		//bound img to the object
		width = 60;
		height = 60;
		x = 600/2 - width/2;
		y = 400;
		img.setBounds(x, y, width, height);
	}
	
	//return frog to original spot
	public void reset() {
		x = 600/2 - width/2;
		y = 500;
		img.setBounds(x, y, width, height);
	}
	
	//move object its call on to the left
	public void moveLeft() {
		x-=7;
		if(x < 0) {
			x=600+width;
		} 
		//moving object should update its img
		img.setBounds(x, y, width, height);
	}
	
	//move object its call on to the right
	public void moveRight() {
		
		x+=7;
		
		if(x > 600) {
			x=-width;
		}
		
		img.setBounds(x, y, width, height);
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getWidth() {
		return width;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
	
	


}
