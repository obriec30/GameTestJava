package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Target {

	int x, y, width, height;
	boolean hit;
	Image target;
	InputStream Target = getClass().getResourceAsStream("Target.png");
	
	public Target(int xPos, int yPos) 
	{
		x = xPos;
		y = yPos;
		hit = false;
		try {
			target = ImageIO.read(Target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = target.getWidth(null);
		height = target.getHeight(null);
	}

}
