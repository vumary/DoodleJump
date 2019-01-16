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
	String stationaryBlockimg = "block.png";
	String boostedBlockimg = "boosted.png";
	String blueBlockimg = "blueblock.png";
	String actualBackgroundimg = "bg.png";
	String coolBlockimg = "coolblock40w53h.png";
	String playerimg = "wilsonn.png";
	String groundimg = "";
	String monsterimg = "monster-ConvertImage.png";
	String gameOverimg = "gameOver3.jpg";
	
	boolean won = false;		//if the player won this variable will be set to true, else it will be false
	boolean falling = false;
	boolean start = true;
	boolean monsterFalling = false;
	
	int boostDist;
	int monsterBoostDist;
	
	//flags for movement
	boolean left = false;
	boolean right = false;
	
	Lose lose = new Lose(gameOverimg);
	Player player = new Player(playerimg);
	Background actualBackground = new Background(actualBackgroundimg, 100);
	Ground ground = new Ground(groundimg, 0);
	
	ArrayList<Block> blocks = new ArrayList<Block>(); //all blocks
	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<Block> blueBlocks = new ArrayList<Block>(); //list of only blueBalls, loop through this to update
	
	
	//change values later
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
	}//end of paint method - put code above for anything dealing with drawing -
	
	
	
	Font font = new Font ("Courier New", 1, 50);
	
	int buffer = 12;
	int dTime = 0;
	int acc = 2;

	/* do not draw here */
	public void update() {
/**
 * if (carx - (deltaTime * speed) > 90) {
            carx -= (deltaTime * speed);
    } else {
      carx = 90;
    }
 */
		if(blocks.get(200).getY() > player.getY() || blocks.get(1).getY() < player.getY()) {
			showScreen();
		}
		
		//rectangle representation of the player
		Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		
		for(int i=1; i<blocks.size(); i++) { //don't include bg
			
			//rectangle representation of all stationary blocks
			Rectangle obstacle = new Rectangle(blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getWidth(), blocks.get(i).getHeight());
			
			if(playerRect.intersects(obstacle)&&(boostDist < 0)&&(player.getY()+player.getHeight()-buffer<=blocks.get(i).getY())) {
				//detected overlap between obstacles
				//start = false;
				boostDist = blocks.get(i).getBoost(); //getting the boost of each object in the list
				System.out.println(i + "\t type:\t" + blocks.get(i).toString());
				dTime = 0; //reset fake timer
				if(blocks.get(i).toString().equals("CoolBlock")) {
					//check if it's cool to switch back and forth
					player.switchImg();
					buffer = 20;
					System.out.println("yo you bumped into a cool block, asshate");
				}

			}
//			
		}
		
		for(int i=1; i<monsters.size(); i++) { //don't include bg
				
			//rectangle representation of all monsters
			Rectangle obstacle = new Rectangle(monsters.get(i).getX(), monsters.get(i).getY(), monsters.get(i).getWidth(), monsters.get(i).getHeight());
			
			if(playerRect.intersects(obstacle)&&(boostDist < 0)&&(player.getY()+player.getHeight()-buffer<=monsters.get(i).getY())) {
				//detected overlap between obstacles
				//start = false;
				monsterFalling = true;
				
				monsterBoostDist = monsters.get(i).getBoost(); //getting the boost of each object in the list
				System.out.println(i + "\t type:\t" + monsters.get(i).toString());
				//dTime = 0; //reset fake timer

			}
			
		}
		
		/*
		Rectangle groundObstacle = new Rectangle(ground.getX(), ground.getY(), ground.getWidth(), ground.getHeight());
		
		if(playerRect.intersects(groundObstacle)) {
			System.out.println("LOSER");
		}
		*/
	
		dTime += 1;
		System.out.println(dTime);
		boostDist -= 1 + dTime/80;
		monsterBoostDist += 1 + dTime/80;
		
		/*
		 * 
		 * boost up
		 * 
		 * */
		
		
		if(boostDist >= 0 && monsterFalling == false) {
			Block a = blocks.get(0); //move the background
			a.moveDown();
			for(int j=1; j<blocks.size(); j++) {
				Block temp = blocks.get(j);
				temp.setVel((int) (1.35*temp.getInitVel()));
				if (temp.getVel() - (dTime/100) > 0) { //can't have negative upward v
					temp.incVel(- 1-(dTime/10)); //decrease more and more for more time spent in air
					temp.moveDown();
					//actualBackground.moveDown();
					if(player.isCat()) {
						//if it's on double;
						temp.moveDown(); 
					}
				}
				
			}
			
			for(int j=1; j<monsters.size(); j++) {
				Monster temp = monsters.get(j);
				temp.setVel((int) (1.35*temp.getInitVel()));
				if (temp.getVel() - (dTime/100) > 0) { //can't have negative upward v
					temp.incVel(- 1-(dTime/10)); //decrease more and more for more time spent in air
					temp.moveDown();
					//actualBackground.moveDown();
					if(player.isCat()) {
						//if it's on double;
						temp.moveDown(); 
					}
				}
				
			}
		}
		
		/*
		 * 
		 * boost down
		 * 
		 */
		
		if(boostDist < 0 && monsterFalling == false) {
			Block a = blocks.get(0); //move the background
			a.moveUp();

			for(int j=1; j<blocks.size(); j++) {
				Block temp = blocks.get(j);
				if (temp.getVel() + (dTime/100) < 1.15*temp.getInitVel()) {
					temp.incVel((dTime/50)); //increase, more and more for more time
				}
				else {
					temp.setVel((int) (1.15*temp.getInitVel())); //terminal velocity
				}
				
				temp.moveUp();
				if(player.isCat()) {
					//if it's on double;
					temp.moveUp();
				}
				//actualBackground.moveUp();
				temp.reset();
			}
			
			for(int j=1; j<monsters.size(); j++) {
				Block temp = monsters.get(j);
				if (temp.getVel() + (dTime/100) < 1.15*temp.getInitVel()) {
					temp.incVel((dTime/50)); //increase, more and more for more time
				}
				else {
					temp.setVel((int) (1.15*temp.getInitVel())); //terminal velocity
				}
				
				temp.moveUp();
				if(player.isCat()) {
					//if it's on double;
					temp.moveUp();
				}
				//actualBackground.moveUp();
				temp.reset();
			}
		}
		
		if(monsterBoostDist >= 0) {
			monsterFalling = false;
		}
		
		if(monsterBoostDist < 0) {
			Block a = blocks.get(0);
			a.moveUp();
			
			for(int j=1; j<blocks.size(); j++) {
				Block temp = blocks.get(j);
				if (temp.getVel() + (dTime/100) < 1.15*temp.getInitVel()) {
					temp.incVel((dTime/50)); //increase, more and more for more time
				}
				else {
					temp.setVel((int) (1.15*temp.getInitVel())); //terminal velocity
				}
				
				temp.moveUp();
				if(player.isCat()) {
					//if it's on double;
					temp.moveUp();
				}
				//actualBackground.moveUp();
				temp.reset();
			}
			
			for(int j=1; j<monsters.size(); j++) {
				Block temp = monsters.get(j);
				if (temp.getVel() + (dTime/100) < 1.15*temp.getInitVel()) {
					temp.incVel((dTime/50)); //increase, more and more for more time
				}
				else {
					temp.setVel((int) (1.15*temp.getInitVel())); //terminal velocity
				}
				
				temp.moveUp();
				if(player.isCat()) {
					//if it's on double;
					temp.moveUp();
				}
				//actualBackground.moveUp();
				temp.reset();
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
			
			if(i.getX() >= i.getShift() + i.getInitialX()) {
				//right half, so move left
				i.setLeft(true);
			}
			
			if(i.getX()<= i.getInitialX() - i.getShift()) {
				i.setLeft(false);
			}
			
			if(i.getLeft()) {
				//i.moveLeft();
				
				i.moveLeft();
				
			}
			
			if(!(i.getLeft())) {
				
				i.moveRight();
			}

		}
		

		
	}//end of update method - put code above for any updates on variable
		
	
	private void showScreen() {
		JFrame f2 = new JFrame();
		f2.setTitle("Click Em");
		f2.setSize(screen_width, screen_height);
		f2.getContentPane().setBackground(Color.black);
		String src2 = new File("").getAbsolutePath()+"/src/"; //path to image setup
	//	ImageIcon backg = new ImageIcon(src+bg);    //setups icon image
		ImageIcon winner2 = new ImageIcon(src2+wn);    //setups icon image
		//background = new JLabel(backg2);
	//	background.setBounds(0,0,600,600); //set location and size of icon
		win= new JLabel(winner2);
		win.setBounds(0,0,600,600);
		win.setVisible(false);
		f2.add(win);
		f2.setResizable(false);
		f2.setLayout(null);
		f2.addKeyListener(this);
		f2.addMouseMotionListener(this);
		
		f2.add(lose.getImg());
		f2.setVisible(true);		
	}


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
		
		//Add Player Character (img)
		f.add(player.getImg());
		//instantiating blocks
		int y = 5000;

		for(int i =-0; i<50; i++) {
			
			Monster newMonster;
			newMonster = new Monster(monsterimg, y);
			
			monsters.add(newMonster);
			f.add(newMonster.getImg());
			
			for(int j = 0; j < 4; j++) {
				Block newBlock;
				
				double check = Math.random(); //0 to 1; if it's greater than 0.9 spawn a boosted block
				if(check>= 0.92) {
					newBlock = new CoolBlock(coolBlockimg, y);
				}
				else if(check>=0.8) {
					newBlock = new BlueBalls(blueBlockimg, (int)(Math.random() * 600), y);
					blueBlocks.add(newBlock);
					
				}
				else if(check>=0.65) {
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
		}
		
		
		blocks.add(0, actualBackground);
		//blocks.add(1, ground);
		
		//adding background to blocks
		f.add(actualBackground.getImg());
		
		//adding ground
		f.add(ground.getImg());

		
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