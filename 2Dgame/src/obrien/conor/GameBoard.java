package obrien.conor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


// where the game happens 
@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ActionListener, MouseListener {

	static Player player;
	static Box box;
	static Ledge l1, l2, l3, l4, l5, l6, l7,l8, l9, l10, l11, l12, l13;
	Image img,go;
	Timer time;
	int onLedge;
	boolean win;
	static Lever lever1, lever2;
	static Ladder ladder;
	static Bullet bullet;
	static Target target;
	static Gun gun;
	static PressurePlate pp;
	
	InputStream bg = getClass().getResourceAsStream("BackgroundAlt.png");
	InputStream GO = getClass().getResourceAsStream("GameOver.png");
	
	public GameBoard()
	{
		// entities
		player = new Player();
		box = new Box();
		lever1 = new Lever(175,555);
		lever2 = new Lever(75, 290 - lever1.height);
		l1 = new Ledge(350, 638);
		l2 = new Ledge(150, 605);
		l3 = new Ledge(0, 555); // lever activated
		l4 = new Ledge(200, 500);
		l5 = new Ledge(450, 470);
		l6 = new Ledge(450, 290);
		l7 = new Ledge(50, 290);
		l8 = new Ledge(250,290); // lever activated
		l9 = new Ledge(200, 235);
		l10 = new Ledge(450, 200);
		l11 = new Ledge(325, 145); // target activated
		l12 = new Ledge(500, 100);
		l13 = new Ledge(600, 100);
		ladder = new Ladder(500, 320);
		target = new Target(0,0);
		gun = new Gun();
		bullet = new Bullet();
		pp = new PressurePlate(570,96);
		// panel stuff
		setFocusable(true);
		addKeyListener(new AL());
		addMouseListener(this);
		try {
			img = ImageIO.read(bg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			go = ImageIO.read(GO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// clock
		time = new Timer(5, this);
		time.start();
		win = false;
	}

		// updater 
	public void actionPerformed(ActionEvent e)
	{
		player.move();
		box.move();
		gun.move();
		box.edges();
		gun.edges();
		player.edges();
		onLedge = l1.OnLedge();
		onLedge += l2.OnLedge();
		onLedge += l4.OnLedge();
		onLedge += l5.OnLedge();
		onLedge += l6.OnLedge();
		onLedge += l7.OnLedge();
		onLedge += l9.OnLedge();
		onLedge += l10.OnLedge();
		onLedge += l12.OnLedge();
		onLedge += l13.OnLedge();
		if(lever1.pulledState)
		{
			onLedge +=  l3.OnLedge();
		}
		if(lever2.pulledState)
		{
			onLedge += l8.OnLedge();
		}
		if(target.hit)
		{
			onLedge += l11.OnLedge();
		}
		if(onLedge > 0 )
		{
			player.onLedge = 0;
		}
		else
		{
			player.onLedge = 1;
		}
		if(bullet.fire)
		{
			bullet.Fire();
			bullet.Hit();
		}
		ladder.onLadder();
		win = pp.gameOver();

		repaint();
	}
	
	// draws things 
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if(win)
		{
			g2d.drawImage(go, 0, 0, null);
		}
		else
		{
			// things are in order of layers on screen 
			g2d.drawImage(img, 0, 0, null); // background
			g2d.drawImage(l1.ledge, l1.x, l1.y, null);
			g2d.drawImage(l2.ledge, l2.x, l2.y, null);
			g2d.drawImage(l4.ledge, l4.x, l4.y, null);
			g2d.drawImage(l5.ledge, l5.x, l5.y, null);
			g2d.drawImage(l6.ledge, l6.x, l6.y, null);
			g2d.drawImage(l7.ledge, l7.x, l7.y, null);
			g2d.drawImage(l9.ledge, l9.x, l9.y, null);
			g2d.drawImage(l10.ledge, l10.x, l10.y, null);
			g2d.drawImage(l12.ledge, l12.x, l12.y, null);
			g2d.drawImage(l13.ledge, l13.x, l13.y, null);
			g2d.drawImage(ladder.ladder, ladder.x, ladder.y, null);
			if(lever1.pulledState)
			{
				g2d.drawImage(l3.ledge, l3.x, l3.y, null);
			}
			if(lever2.pulledState)
			{
				g2d.drawImage(l8.ledge, l8.x, l8.y, null);
			}
			if(!target.hit)
			{
				g2d.drawImage(target.target, target.x, target.y, null);
			}
			else
			{
				g2d.drawImage(l11.ledge, l11.x, l11.y, null);
			}
			g2d.drawImage(player.getImage(), player.getX(), player.getY(),null);
			g2d.drawImage(pp.pressurePlate, pp.x, pp.y , null);
			g2d.drawImage(gun.gunPic, gun.x, gun.y, null);
			g2d.drawImage(box.getImage(), box.getX(), box.getY(), null);
			if(bullet.fire)
			{
				g2d.drawImage(bullet.bullet, bullet.x, bullet.y, null);
			}
			g2d.drawImage(lever1.lever, lever1.x, lever1.y, null);
			g2d.drawImage(lever2.lever, lever2.x, lever2.y, null);
		}
	}

	// handles key presses
	private class AL extends KeyAdapter
	{
		public void keyReleased(KeyEvent e)
		{
			player.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e)
		{
			player.keyPressed(e);
		}
	}
	// Mouse handlers all need to be here.

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		//player.shoot(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		player.shoot(arg0);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}