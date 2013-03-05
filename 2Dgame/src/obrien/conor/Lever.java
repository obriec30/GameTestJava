package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Lever {
	
	int x,y, width, height;
	Image lever, leverP, leverNP;
	boolean pulledState;
	
	InputStream NotPulled = getClass().getResourceAsStream("NotPulled.png");
	InputStream Pulled = getClass().getResourceAsStream("Pulled.png");

	public Lever(int xPos, int yPos) 
	{
		pulledState = false;
		try {
			leverNP = ImageIO.read(NotPulled);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leverP = ImageIO.read(Pulled);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lever = leverNP;
		x = xPos;
		y = yPos;
		width = lever.getWidth(null);
		height = lever.getHeight(null);
		
	}
	
	public void pull()
	{
		int xdist = GameBoard.player.getX() - x;
		int ydist = GameBoard.player.getY() - y;
		if (!GameBoard.player.getFlying())
		{
			if(Math.abs(xdist) < width )
			{
				if(Math.abs(ydist)<GameBoard.player.charHeight)
				{
					if(!pulledState)
					{
						pulledState = true;
						lever = leverP;
					}
					else
					{
						pulledState = false;
						lever =  leverNP;
					}
				}
			}	
		}
	}

}
