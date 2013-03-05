package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Box {

	int x,y, width,height, gravity;
	int top, bottom, left, right;
	boolean pickedUp;
	Image boxPic;
	
	public Box() 
	{
		InputStream Box = getClass().getResourceAsStream("Box.png");
		try {
			boxPic = ImageIO.read(Box);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		top = 0;
		left = 0;
		width = boxPic.getWidth(null);
		height = boxPic.getHeight(null);
		right = 700 - width;
		bottom = 700 - height - 30;// 30 is the ground image height
		x = right - 30 ;
		y = bottom;
		gravity = 2;
		pickedUp = false;		
	}
	
	// Can't carry while flying
	public void Drop()
	{
		pickedUp = false;
	}
	public void move()
	{
		if(pickedUp)
		{
			if(GameBoard.player.facingRight)
			{
				x = GameBoard.player.getX() + GameBoard.player.charWidth;
				y = GameBoard.player.getY() + (GameBoard.player.charHeight/3);
			}
			else
			{
				x = GameBoard.player.getX() - width;
				y = GameBoard.player.getY() + (GameBoard.player.charHeight/3);
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
				}
			}	
		}
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Image getImage()
	{
		return boxPic;
	}
	public boolean getPickedUp()
	{
		return pickedUp;
	}	

}
