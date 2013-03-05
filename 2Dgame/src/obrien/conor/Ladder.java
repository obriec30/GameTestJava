package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Ladder {

	Image ladder;
	int x, y, width, height;
	public Ladder(int xPos, int yPos) 
	{
		InputStream Ladder = getClass().getResourceAsStream("Ladder.png");
		x = xPos;
		y = yPos;
		try {
			ladder = ImageIO.read(Ladder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = ladder.getWidth(null);
		height = ladder.getHeight(null);
	}
	
	public void onLadder()
	{
		if(Math.abs(GameBoard.player.x - x) < GameBoard.player.charWidth)
		{
			if(GameBoard.player.y < (y + height) && GameBoard.player.y > (y - (GameBoard.player.charHeight + GameBoard.l1.height)))
			{
				GameBoard.player.onLadder = true;
				return;
			}
		}
	
		GameBoard.player.onLadder = false;
		
	}

}
