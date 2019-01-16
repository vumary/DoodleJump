import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Lose {
	
	protected int x, y;		//location 
	protected String color;
	protected JLabel img;	
	
	
	public Lose(String filename) {
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+filename);
		img = new JLabel(ghost); //connect 
		
		//bound img to the object

		x = 0;
		y = 0;
		img.setBounds(x, y, 600, 600);
		
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

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}


	public String toString() {
		return "Block";
	}


	public int getShift() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}


