package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Ledge {

	int x, y, width, height; 
	InputStream Ledge = getClass().getResourceAsStream("Ledge.png");
	Image ledge;
	
	public Ledge(int xPos, int yPos) 
	{
		try {
			ledge = ImageIO.read(Ledge);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		x = xPos;
		y = yPos;
		width = x + ledge.getWidth(null);
		height = ledge.getHeight(null);
	}

	public int OnLedge()
	{
		int feet = GameBoard.player.y + GameBoard.player.charHeight;
		if(Math.abs(feet - y) <= 1)
		{
			if((GameBoard.player.x + GameBoard.player.charWidth) > x && GameBoard.player.x < width)
			{
				return 1;
			}
		}
		return 0;		
	}
}
