//Charu Mishra (cm2jk)
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {

	// / imgs: default storage for the pictures of the bird
	private BufferedImage[] imgs;
	private double gravity;
	private double dy;
	private double dx;
	private double x;
	private double y;

	// TODO: add your own fields here
	private double vx;
	private double vy;
	private double mass;
	private int i;
	private Rectangle hitbox;
	

	/**
	 * Creates a bird object with the given image set
	 * 
	 * @param basename
	 *            should be "birdg" or "birdr" (assuming you use the provided
	 *            images)
	 * @param i
	 * @param y
	 * @param x
	 * @param i
	 */
	public Bird(String basename, double x, double y, int i) {
		// You may change this method if you wish, including adding
		// parameters if you want; however, the existing image code works as is.
		this.x = x;
		this.y = y;
		this.imgs = new BufferedImage[6];
		this.i = i;

		try {
			// 0-2: right-facing (folded, back, and forward wings)
			this.imgs[0] = ImageIO.read(new File(basename + ".png"));
			this.imgs[1] = ImageIO.read(new File(basename + "f.png"));
			this.imgs[2] = ImageIO.read(new File(basename + "b.png"));
			// 3-5: left-facing (folded, back, and forward wings)
			this.imgs[3] = Bird.makeFlipped(this.imgs[0]);
			this.imgs[4] = Bird.makeFlipped(this.imgs[1]);
			this.imgs[5] = Bird.makeFlipped(this.imgs[2]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		hitbox = new Rectangle((int) x, (int) y, hitboxWidth, hitboxHeight);

		this.vx = 0;
		this.vy = 0;
		this.mass = 1;

	}
	

	int hitboxWidth = 42;
	int hitboxHeight = 43;

	/**
	 * A helper method for flipping in image left-to-right into a mirror image.
	 * There is no reason to change this method.
	 * 
	 * @param original
	 *            The image to flip
	 * @return A left-right mirrored copy of the original image
	 */
	private static BufferedImage makeFlipped(BufferedImage original) {
		AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
		af.translate(-original.getWidth(), 0);
		BufferedImage ans = new BufferedImage(original.getWidth(),
				original.getHeight(), original.getType());
		Graphics2D g = (Graphics2D) ans.getGraphics();
		g.drawImage(original, af, null);
		return ans;
	}
	

	/**
	 * Draws this bird
	 * 
	 * @param g
	 *            the paintbrush to use for the drawing
	 */
	public void draw(Graphics g) {

//		int i = 3; // between 0 and 6, depending on facing and wing state
		double x = this.getX(); // where to center the picture
		double y = this.getY();

		// TODO: find the right x, y, and i instead of the examples given here

		g.drawImage(this.imgs[i], (int) x - this.imgs[i].getWidth() / 2,
				(int) y - this.imgs[i].getHeight() / 2, null);
	}
	public void faceLeft(){
		if(!(i>=3&&i<=5)){
			i = 3;
		}
	}
	public boolean facingLeft(){
		if(i>=3&&i<=5){
			return true;
		}else{
			return false;
		}
	}
	public boolean facingRight(){
		if(i>=0&&i<=2){
			return true;
		}else{
			return false;
		}
	}
	public void faceRight(){
		if(!(i>=0&&i<=2)){
			i = 0;
		}
	}
	public void flapBackward(){
		if(i==3){
			i=4;
		}else if(i==0){
			i=1;
		}
	}
	public void flapBack(){
		if(i==4){
			i=3;
		}else if(i==5){
			i=3;
		}else if(i==1){
			i=0;
		}else if(i==2){
			i=0;
		}
	}
	public void flapForward(){
		if(i==3){
			i=5;
		}else if(i==0){
			i=2;
		}
			
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void ifOutsideOf() {
		if (this.x < 10) {
			this.x = 10;
			this.vx = Math.abs(this.vx*0.5);
		}
		if (this.x > 780) {
			this.x = 780;
			this.vx = (-1) * Math.abs(this.vx*0.5);
		}
		if (this.y < 0) {
			this.y = 0;
			this.vy = Math.abs(this.vy*0.5);
		}
		if (this.y > 600) {
			this.y = 600;
			this.vx = (-1) * Math.abs(this.vx*0.5);
		}
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = 0.5;
	}

	public void applyForce(double fx, double fy, double dt) {
		double accx = fx / this.mass;
		double accy = fy / this.mass;
		double acx = accx * dt;
		double acy = accy * dt;
		vx += acx;
		vy += acy;

	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void applyDrag(double drag, double dt) {
		double fx = -this.vx * getSpeed() * drag;
		double fy = -this.vy * getSpeed() * drag;
		this.applyForce(fx, fy, dt);

	}

	public double getSpeed() {
		double speed = 0;
		speed = Math.sqrt(Math.pow(this.vx, .9) + Math.pow(this.vy, .9));
		return speed;
	}

	public void time(double dt) {
		double dx = this.vx * dt;
		double dy = this.vy * dt;
		this.x += dx;
		this.y += dy;
		this.vy += 1;//gravity
		this.vx *= 0.8;//drag
		this.vy *= 0.8;//drag
		
		hitbox.setLocation((int)x, (int)y);

	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}


}
