package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Gun {

	int x,y, width, gravity;
	int top, bottom, left, right;
	boolean pickedUp;
	Image gunPic, gunleft, gunright;
	InputStream gRight = getClass().getResourceAsStream("GunRight.png");
	InputStream gLeft = getClass().getResourceAsStream("GunLeft.png");
	
	
	public Gun() 
	{
		try {
			gunright =ImageIO.read(gRight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gunleft =ImageIO.read(gLeft);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gunPic = gunright;
		top = 0;
		left = 0;
		width = gunPic.getWidth(null);
		right = 700 - width;
		bottom = 700 - gunPic.getHeight(null) - 30;// 30 is the ground image height
		x = right - 30 ;
		y = bottom;
		gravity = 2;
		pickedUp = false;
	}
	public void Drop()
	{
		pickedUp = false;
		GameBoard.player.holdingGun = false;
	}
	public void move()
	{
		if(pickedUp)
		{
			if(GameBoard.player.facingRight)
			{
				x = GameBoard.player.getX() + GameBoard.player.charWidth;
				y = GameBoard.player.getY() + (GameBoard.player.charHeight/3);
				gunPic = gunright;
			}
			else
			{
				x = GameBoard.player.getX() - width;
				y = GameBoard.player.getY() + (GameBoard.player.charHeight/3);
				gunPic = gunleft;
			}
		}
		else
		{
			y = y + gravity;
		}
	}
	public void edges()
	{
		if( y > bottom)
		{
			y = bottom ;
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
	public void pickup()
	{
		int xdist = GameBoard.player.getX() - x;
		int ydist = GameBoard.player.getY() - y;
		if (!GameBoard.player.getFlying())
		{
			if(Math.abs(xdist) < width )
			{
				if(Math.abs(ydist)<GameBoard.player.charHeight)
				{
					pickedUp = true;
					GameBoard.player.holdingGun = true;
				}
			}	
		}
	}
	

}
