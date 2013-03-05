package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class PressurePlate {
	
	int x, y;
	Image pressurePlate;
	InputStream PP = getClass().getResourceAsStream("PressurePlate.png");

	public PressurePlate(int xPos, int yPos) 
	{
		x = xPos;
		y = yPos;
		try {
			pressurePlate = ImageIO.read(PP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean gameOver()
	{
		if(!GameBoard.box.pickedUp)
		{
			if(Math.abs((GameBoard.box.y + GameBoard.box.height) -y) <= 2)
			{
				if((GameBoard.box.x + GameBoard.box.width) > x)
				{
					GameBoard.box.gravity = 0;
					return true;
				}
			}
		}
		return false;
	}

}
