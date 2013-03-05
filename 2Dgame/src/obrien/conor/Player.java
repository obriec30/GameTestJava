package obrien.conor;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player {

	int x, y, dx ,dy;
	int shootx, shooty, bulletDy, bulletDx;
	int run;
	int top, bottom, left, right;	// edges
	int flying, gravity, onLedge;
	int charHeight, charWidth, jumpStart;
	boolean jumping, onLadder, holdingGun, facingRight;
	
	Image dude, dudeleft,duderight;
	InputStream rightPic = getClass().getResourceAsStream("guyRight.png");
	InputStream leftPic = getClass().getResourceAsStream("guyLeft.png");

	
	public Player()  
	{
		try {
			dudeleft = ImageIO.read(leftPic);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			duderight = ImageIO.read(rightPic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dude = duderight;
		top = 0;
		left = 0;
		right = 700 - dude.getWidth(null);
		bottom = 700 - dude.getHeight(null) - 30;
		charHeight = dude.getHeight(null);
		charWidth = dude.getWidth(null);
		x = left;
		y = bottom;	
		flying = 0;
		gravity = 2;
		onLedge = 1;
		jumping = false;
		run = 1;
		jumpStart = bottom;
		onLadder = false;
		holdingGun = false;
		facingRight = true;
	}
	public void edges()
	{
		if( y > bottom)
		{
			y = bottom;
		}
		if( x > right)
		{
			x = right;
		}
		if(y < top)
		{
			y = top;
		}
		if( x < left )
		{
			x = left;
		}
		
	}
	public void move()
	{
		if(jumping)
		{
			if(y > jumpStart - (5 + charHeight) && y > top)	
			{
				y -= 1;
			}
			else 
			{
				jumping = false;
			}
		}
		else if(onLadder)
		{
			y = y + dy;
		}
		else
		{	
			jumpStart = (bottom* onLedge)+(y - (y*onLedge)) ;
			y = y + (dy*flying)  + (gravity*onLedge);
		}
		x = x + (dx*run);
		
	}	
	public int getX()
	{
		return x;
	}	
	public int getY()
	{
		return y;
	}
	public boolean getFlying()
	{
		if(flying == 0)
		{
			return false;
		}
		else 
			return true;
	}
	public Image getImage()
	{
		return dude;
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D)
		{
			dx = 1;
			facingRight = true;
			dude = duderight;
		}
		if (key == KeyEvent.VK_A)
		{
			dx = -1;
			facingRight = false;
				dude = dudeleft;

		}
		if (key == KeyEvent.VK_W)
		{
			dy = -1;
		}
		if (key == KeyEvent.VK_S)
		{
			dy = 1;
		}
		if (key == KeyEvent.VK_SHIFT)
		{
			run = 2;
		}
		if (key == KeyEvent.VK_E)
		{
			if(GameBoard.box.getPickedUp())
			{
				GameBoard.box.Drop();
			}
			if(GameBoard.gun.pickedUp)
			{
				GameBoard.gun.Drop();
			}
			else
			{
				GameBoard.lever1.pull();
				GameBoard.lever2.pull();
				GameBoard.box.pickup();
				GameBoard.gun.pickup();
			}
		}
		if (key == KeyEvent.VK_F)
		{
			if(flying == 0) // turns flying on and gravity off
			{
				flying = 1;
				gravity = 0;
				GameBoard.box.Drop(); // drops box if starting to fly
				GameBoard.gun.Drop();
			}
			else // not using release because it's toggleable
			{
				flying = 0;
				gravity = 2;
			}
		}
		if(key == KeyEvent.VK_SPACE)
		{
			jumping = true;
		}
	}	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D)
		{
			dx = 0;
		}
		if (key == KeyEvent.VK_A)
		{
			dx = 0;
		}
		if (key == KeyEvent.VK_W)
		{
			dy = 0;
		}
		if (key == KeyEvent.VK_S)
		{
			dy = 0;
		}
		if (key == KeyEvent.VK_SHIFT)
		{
			run = 1;
		}
	}
	public void shoot(MouseEvent e)
	{	
		if(holdingGun)
		{
			shootx = e.getX();
			shooty = e.getY();
			bulletDx = (Math.abs(x - shootx)/50);
			bulletDy = (Math.abs(y - shooty)/50);
			
			System.out.println(shootx +"  " + shooty);
			GameBoard.bullet.startX = x;
			GameBoard.bullet.startY = y;
			GameBoard.bullet.traj();
			GameBoard.bullet.fire = true;
		}
	}
}