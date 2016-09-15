//Charu Mishra (cm2jk)
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class JoustScreen extends KeyAdapter implements ActionListener {

	/**
	 * A simple method to make the game runnable. You should not modify this
	 * main method: it should print out a list of extras you added and then say
	 * "new JoustScreen();" -- nothing more than that.
	 */
	public static void main(String[] args) {
		// add a list of all extras you did, such as
		 System.out.println("Gameover");
		 System.out.println("Bounce back if birds are not clearly above");
		 System.out.println("Flapping");
		new JoustScreen();
	}

	// DO NOT CHANGE the next four fields (the window and timer)
	private JFrame window; // the window itself
	private BufferedImage content; // the current game graphics
	private Graphics2D paintbrush; // for drawing things in the window
	private Timer gameTimer; // for keeping track of time passing
	// DO NOT CHANGE the previous four fields (the window and timer)
	private double x;
	private double y;
	

	// TODO: add your own fields here
	private Bird bird1;
	private Bird bird2;
	private Rectangle r;
	private Rectangle d;
	private Rectangle wall;
	private String msg; 
	private String msg2;
	private int score1;
	private int score2;
	private int b1count;
	private int b2count;
	

	public JoustScreen() {
		// DO NOT CHANGE the window, content, and paintbrush lines below
		this.window = new JFrame("Joust Clone");
		this.content = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		this.paintbrush = (Graphics2D) this.content.getGraphics();
		this.window.setContentPane(new JLabel(new ImageIcon(this.content)));
		this.window.pack();
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.addKeyListener(this);
		// DO NOT CHANGE the window, content, and paintbrush lines above

		// TODO: add anything else you might need (e.g., a couple of Bird
		// objects, some walls)
		bird1 = new Bird("birdr", 50, 100, 0);
		bird2 = new Bird("birdg", 750, 200, 3);
		r = new Rectangle(450, 300, 300, 40);
		d = new Rectangle(170, 100, 40, 400);
		wall = new Rectangle(0,600,800,20);
		
		score1 = 0;
		score2 = 0;
		msg = "";
		msg2="";

		// DO NOT CHANGE the next two lines nor add lines after them
		this.gameTimer = new Timer(20, this); // tick at 1000/20 fps
		this.gameTimer.start(); // and start ticking now
		// DO NOT CHANGE the previous two lines nor add lines after them
	}

	/**
	 * This method gets called each time a player presses a key. You can find
	 * out what key the pressed by comparing event.getKeyCode() with
	 * KeyEvent.VK_...
	 */
	public void keyPressed(KeyEvent event) {

		// TODO: handle the keys you want to use to run your game
		if (score1 < 10 && score2 < 10) {
			if (event.getKeyCode() == KeyEvent.VK_A) { // example
				bird1.applyForce(-10, -10, 1);
				if(bird1.facingRight()==true){
					bird1.flapForward();
				}else if(bird1.facingLeft()==true){
					bird1.flapBackward();
				}
				b1count=0;
				
			}
			if (event.getKeyCode() == KeyEvent.VK_S) { // example
				bird1.applyForce(10, -10, 1);
				if(bird1.facingRight()==true){
					bird1.flapBackward();
				}else if(bird1.facingLeft()==true){
					bird1.flapForward();
				}
				b1count=0;
			}
			if (event.getKeyCode() == KeyEvent.VK_K) { // example
				bird2.applyForce(-10, -10, 1);
				if(bird2.facingRight()==true){
					bird2.flapForward();
				}else if(bird2.facingLeft()==true){
					bird2.flapBackward();
				}
				b2count=0;
			}
			if (event.getKeyCode() == KeyEvent.VK_L) { // example
				bird2.applyForce(10, -10, 1);
				if(bird2.facingRight()==true){
					bird2.flapBackward();
				}else if(bird2.facingLeft()==true){
					bird2.flapForward();
				}
				b2count=0;
				
			}
		}
	}

	/**
	 * Java will call this every time the gameTimer ticks (50 times a second).
	 * If you want to stop the game, invoke this.gameTimer.stop() in this
	 * method.
	 */
	public void actionPerformed(ActionEvent event) {
		// DO NOT CHANGE the next four lines, and add nothing above them
		if (!this.window.isValid()) { // the "close window" button
			this.gameTimer.stop(); // should stop the timer
			return; // and stop doing anything else
		}
		// DO NOT CHANGE the previous four lines

		// TODO: add every-frame logic in here (gravity, momentum, collisions,
		// etc)
		if (score1 < 10 && score2 < 10) {
			bird1.time(2);
			bird2.time(2);
			bird1.ifOutsideOf();
			bird2.ifOutsideOf();

			if (bird1.getHitbox().intersects(bird2.getHitbox())) {
				bird1.setVx(-bird1.getVx());
				bird2.setVx(-bird2.getVx());
			}
			if (bird1.getHitbox().intersects(bird2.getHitbox())) {
				if (bird1.getHitbox().getY() < bird2.getHitbox().getY()
						- (3 * (bird2.getHitbox().getHeight() / 4))) {
					score1++;
					bird2.setY(0);
					Random rand = new Random();
					int randomNumber = rand.nextInt(800);
					bird2.setX(randomNumber);
				} else if (bird2.getHitbox().getY() < bird1.getHitbox().getY()
						- (3 * (bird1.getHitbox().getHeight() / 4))) {
					score2++;
					bird1.setY(0);
					Random rand = new Random();
					int randomNumber = rand.nextInt(800);
					bird1.setX(randomNumber);
				} else {
					bird1.setVx(-2 * bird1.getVx());
					bird2.setVx(-2 * bird2.getVx());
					if (bird1.getHitbox().getX() < bird2.getHitbox().getMinX()) {
						bird1.getHitbox().setLocation(
								(int) (bird1.getHitbox().getX() + 10),
								(int) bird1.getHitbox().getY());
					} else if (bird1.getHitbox().getX() > bird2.getHitbox()
							.getMaxX()) {
						bird2.getHitbox().setLocation(
								(int) (bird2.getHitbox().getX() + 10),
								(int) bird2.getHitbox().getY());
					}
				}
			}
			/*
			 * if (bird1.getHitbox().intersects(bird2.getHitbox())) { if
			 * (bird1.getHitbox().getY() < bird2.getHitbox().getY()) { score1++;
			 * bird2.setY(0); Random rand = new Random(); int randomNumber =
			 * rand.nextInt(800); bird2.setX(randomNumber); } else { score2++;
			 * bird1.setY(0); Random rand = new Random(); int randomNumber =
			 * rand.nextInt(800); bird1.setX(randomNumber); } }
			 */

			if (bird1.getHitbox().intersects(r)) {
				if (bird1.getHitbox().getY() < r.getMinY()) {
					bird1.setY(r.getMinY() - bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getMaxY() > r.getMaxY()) {
					bird1.setY(r.getMaxY() + bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getX() < r.getMinX()) {
					bird1.setX(r.getMinX() - bird1.getHitbox().getWidth());
					bird1.setVx(-bird1.getVx());
				} else if (bird1.getHitbox().getMaxX() > r.getMaxX()) {
					bird1.setX(r.getMaxX() + bird1.getHitbox().getHeight());
					bird1.setVx(-bird1.getVx());
				}
			}
			if (bird1.getHitbox().intersects(d)) {
				if (bird1.getHitbox().getY() < d.getMinY()) {
					bird1.setY(d.getMinY() - bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getMaxY() > d.getMaxY()) {
					bird1.setY(d.getMaxY() + bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getX() < d.getMinX()) {
					bird1.setX(d.getMinX() - bird1.getHitbox().getWidth());
					bird1.setVx(-bird1.getVx());
				} else if (bird1.getHitbox().getMaxX() > d.getMaxX()) {
					bird1.setX(d.getMaxX() + bird1.getHitbox().getHeight());
					bird1.setVx(-bird1.getVx());
				}
			}
			if (bird2.getHitbox().intersects(r)) {
				if (bird2.getHitbox().getY() < r.getMinY()) {
					bird2.setY(r.getMinY() - bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getMaxY() > r.getMaxY()) {
					bird2.setY(r.getMaxY() + bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getX() < r.getMinX()) {
					bird2.setX(r.getMinX() - bird2.getHitbox().getWidth());
					bird2.setVx(-bird2.getVx());
				} else if (bird2.getHitbox().getMaxX() > r.getMaxX()) {
					bird2.setX(r.getMaxX() + bird2.getHitbox().getHeight());
					bird2.setVx(-bird2.getVx());
				}
			}
			if (bird2.getHitbox().intersects(d)) {
				if (bird2.getHitbox().getY() < d.getMinY()) {
					bird2.setY(d.getMinY() - bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getMaxY() > d.getMaxY()) {
					bird2.setY(d.getMaxY() + bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getX() < d.getMinX()) {
					bird2.setX(d.getMinX() - bird2.getHitbox().getWidth());
					bird2.setVx(-bird2.getVx());
				} else if (bird2.getHitbox().getMaxX() > d.getMaxX()) {
					bird2.setX(d.getMaxX() + bird2.getHitbox().getHeight());
					bird2.setVx(-bird2.getVx());
				}
			}
			if (bird1.getHitbox().intersects(wall)) {
				if (bird1.getHitbox().getY() < wall.getMinY()) {
					bird1.setY(wall.getMinY() - bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getMaxY() > wall.getMaxY()) {
					bird1.setY(wall.getMaxY() + bird1.getHitbox().getHeight());
					bird1.setVy(-bird1.getVy());
				} else if (bird1.getHitbox().getX() < wall.getMinX()) {
					bird1.setX(wall.getMinX() - bird1.getHitbox().getWidth());
					bird1.setVx(-bird1.getVx());
				} else if (bird1.getHitbox().getMaxX() > wall.getMaxX()) {
					bird1.setX(wall.getMaxX() + bird1.getHitbox().getHeight());
					bird1.setVx(-bird1.getVx());
				}
			}
			if (bird2.getHitbox().intersects(wall)) {
				if (bird2.getHitbox().getY() < wall.getMinY()) {
					bird2.setY(wall.getMinY() - bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getMaxY() > wall.getMaxY()) {
					bird2.setY(wall.getMaxY() + bird2.getHitbox().getHeight());
					bird2.setVy(-bird2.getVy());
				} else if (bird2.getHitbox().getX() < wall.getMinX()) {
					bird2.setX(wall.getMinX() - bird2.getHitbox().getWidth());
					bird2.setVx(-bird2.getVx());
				} else if (bird2.getHitbox().getMaxX() > wall.getMaxX()) {
					bird2.setX(wall.getMaxX() + bird2.getHitbox().getHeight());
					bird2.setVx(-bird2.getVx());
				}
			}
			if(bird1.getX()<bird2.getX()){
				bird1.faceRight();
				bird2.faceLeft();
			}else{
				bird1.faceLeft();
				bird2.faceRight();
			}
			if(b1count==5){
				bird1.flapBack();
			}
			if(b2count==5){
				bird2.flapBack();
			}
		}
		

		// DO NOT CHANGE the next line; it must be last in this method
		this.refreshScreen(); // redraws the screen after things move
		// DO NOT CHANGE the above line; it must be last in this method
	}

	/**
	 * Re-draws the screen. You should erase the old image and draw a new one,
	 * but you should not change anything in this method (use actionPerformed
	 * instead if you need something to change).
	 */
	private void refreshScreen() {

		this.paintbrush.setColor(new Color(150, 210, 255)); // pale blue
		this.paintbrush.fillRect(0, 0, this.content.getWidth(),
				this.content.getHeight()); // erases the previous frame
		

		// TODO: replace the following example code with code that does
		// the right thing (i.e., draw the birds, walls, and score)

		// example bird drawing; replace with something sensible instead of
		// making a new bird every frame
		bird1.draw(this.paintbrush);
		this.paintbrush.setColor(Color.MAGENTA);
		bird2.draw(this.paintbrush);
		
		// example wall drawing; replace with something sensible instead of
		// making a new wall every frame
		// this.paintbrush.setColor(Color.YELLOW);
		// this.paintbrush.fill(new Rectangle(50,150,300,10));
		this.paintbrush.setColor(Color.YELLOW);
		this.paintbrush.fill(new Rectangle(450, 300, 300, 40));
		this.paintbrush.fill(new Rectangle(170, 100, 40, 400));
		
		// example text drawing, for scores and/or other messages
		if (score1 == 10) {
			msg = "GAMEOVER";
			msg2 = "Player 1 Wins!";
		} else if (score2 == 10) {
			msg = "GAMEOVER";
			msg2 = "Player 2 Wins!";
		} else {
			msg = "";
			msg2 = "";
		}
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		this.paintbrush.setFont(f);
		this.paintbrush.setColor(new Color(127, 0, 0)); // dark red
		this.paintbrush.drawString(Integer.toString(score1), 30, 30);
		this.paintbrush.setColor(new Color(0, 127, 0)); // dark green
		this.paintbrush.drawString(Integer.toString(score2), 760, 30);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 70);
		Rectangle2D a = f.getStringBounds(msg,
				this.paintbrush.getFontRenderContext());
		this.paintbrush.setFont(f);
		this.paintbrush.setColor(Color.BLUE);
		this.paintbrush.drawString(msg, 400 - (int) a.getWidth() / 2, 300);
		this.paintbrush.drawString(msg2, 370 - (int) a.getWidth() / 2, 400);
		b1count++;
		b2count++;
		// DO NOT CHANGE the next line; it must be last in this method
		this.window.repaint(); // displays the frame to the screen
		// DO NOT CHANGE the above line; it must be last in this method
	}


}
