import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*
;
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	
	//properties of this class - the panel that shows up
	int screen_width 	= 600;
	int screen_height 	= 600;
	int max_vals = 200;
	int size = 30;
	int g = 0;
	String bg = "";
	JLabel background;
	String wn = "win.png";
	JLabel win;
	String playerimg = "wilsonn.png";
	String stationaryBlockimg = "block.png";
	String boostedBlockimg = "boosted.png";
	String blueBlockimg = "blueblock.png";
	String actualBackgroundimg = "bg.png";
	
	boolean won = false;		//if the player won this variable will be set to true, else it will be false
	boolean falling = false;
	boolean start = true;
	
	int boostDist;
	
	//flags for movement
	boolean left = false;
	boolean right = false;
	
	Player player = new Player(playerimg);
	Background actualBackground = new Background(actualBackgroundimg, 100);
	
	ArrayList<Block> blocks = new ArrayList<Block>(); //all blocks
	ArrayList<Block> blueBlocks = new ArrayList<Block>(); //list of only blueBalls, loop through this to update
	
	
	//change values later
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
	}//end of paint method - put code above for anything dealing with drawing -
	
	
	
	Font font = new Font ("Courier New", 1, 50);
	
	/* do not draw here */
	public void update() {

		//rectangle representation of the player
		Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		
		for(int i=1; i<blocks.size(); i++) { //don't include bg
			
			//rectangle representation of all stationary blocks
			Rectangle obstacle = new Rectangle(blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(1).getWidth(), blocks.get(1).getHeight());

			if(playerRect.intersects(obstacle)&&(boostDist < 0)&&(player.getY()+player.getHeight()-8<=blocks.get(i).getY())) {
				//detected overlap between obstacles
				start = false;
				boostDist = blocks.get(i).getBoost();
				System.out.println(i + "\t type:\t" + blocks.get(i).toString());

			}
			
		}
		
		
		boostDist--;
		
		/*
		 * 
		 * boost up
		 * 
		 * */
		
		if(boostDist > 0) {
			for(int j=0; j<blocks.size(); j++) {
				Block temp = blocks.get(j);
				temp.moveDown();
				//actualBackground.moveDown();
				
			}
		}
		
		
		/*
		 * 
		 * boost down
		 * 
		 */
		
		if(boostDist < 0) {
			for(int j=0; j<blocks.size(); j++) {
				Block temp = blocks.get(j);
				temp.moveUp();
				//actualBackground.moveUp();
			}
		}
		
		if(player.getY()<150&&player.getX()<350&&player.getX()>250) {
			won = true;
			win.setVisible(won);
		}
		
		//lateral movement of player
		if(left) {
			player.moveLeft();
		}
		if(right) {
			player.moveRight();
		}
		
		//move blueBalls
		for(Block i : blueBlocks) {
			
			if(i.getX() >= 100 + i.getInitialX()) {
				//right half, so move left
				i.setLeft(true);
			}
			
			if(i.getX()<= i.getInitialX() - 100) {
				i.setLeft(false);
			}
			
			if(i.getLeft()) {
				i.moveLeft();
			}
			
			if(!(i.getLeft())) {
				i.moveRight();
			}

		}
		
	}//end of update method - put code above for any updates on variable
		
	
	//==================code above ===========================
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	public Driver(){
		
		JFrame f = new JFrame();
		f.setTitle("Click Em");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.black);
		String src = new File("").getAbsolutePath()+"/src/"; //path to image setup
	//	ImageIcon backg = new ImageIcon(src+bg);    //setups icon image
		ImageIcon winner = new ImageIcon(src+wn);    //setups icon image
		//background = new JLabel(backg);
	//	background.setBounds(0,0,600,600); //set location and size of icon
		win= new JLabel(winner);
		win.setBounds(0,0,600,600);
		win.setVisible(false);
		f.add(win);
		f.setResizable(false);
		f.setLayout(null);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		
		JLabel scoreLabel = new JLabel("score: "+player.getScore());
		scoreLabel = new JLabel("score: "+player.getScore());
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBounds(50,70,100,50);
		
		f.add(scoreLabel);
		
		//Add Frog Character (img)
		f.add(player.getImg());
		//instantiating blocks
		int y = 5000;

		for(int i =-0; i<200; i++) {
			
			Block newBlock;
			double check = Math.random(); //0 to 1; if it's greater than 0.9 spawn a boosted block
			if(check>=0.8) {
				newBlock = new BlueBalls(blueBlockimg, (int)(Math.random() * 600), y);
				blueBlocks.add(newBlock);
				
			}
			else if(check>=0.6) {
				//spawn a boosted block
				 newBlock = new Boosted(boostedBlockimg, y);
			}
			else {
				 newBlock = new Stationary(stationaryBlockimg, y);
			}
			blocks.add(newBlock);
			y -= 100;
			
			//add to frame
			f.add(newBlock.getImg());
		}
		
		
		blocks.add(0, actualBackground);
		
		//adding background to blocks
		f.add(actualBackground.getImg());

		
		//add background after
		//f.add(background);
		
		
		
		
		//end creating objects
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==39){ //39 is right
			right = true;
		}
		
		if(e.getKeyCode()==37){ //37 is left
			left = true;
		}
		update();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==39){ //39 is right
			right = false;
		}
		
		if(e.getKeyCode()==37){ //37 is left
			left = false;
		}
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
 
	public void reset(){
	
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}