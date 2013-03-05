package obrien.conor;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Bullet {

	int x, y, dx, dy, startX, startY;
	boolean fire, hit;
	Image bullet;
	
	public Bullet()
	{
		InputStream Bullet = getClass().getResourceAsStream("Bullet.png");
		try {
			bullet = ImageIO.read(Bullet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void traj()
	{
		x = startX;
		y = startY;
		dy = GameBoard.player.bulletDy;
		dx = GameBoard.player.bulletDx;
		if ( startY > GameBoard.player.shooty)
		{
			dy = dy*(-1);
		}
		if( startX > GameBoard.player.shootx)
		{
			dx = dx * (-1);
		}
	}
	
	public void Fire()
	{
		if(fire)
		{
			x = x + dx;
			y = y + dy;
			hit = Hit();
			if(hit)	
			{
				fire = false;
			}
		}
	}
	
	public boolean Hit()
	{
		if(x < GameBoard.player.left || x > GameBoard.player.right)
		{
			return true;
		}
		if(y < GameBoard.player.top || y > GameBoard.player.bottom)
		{
			return true;
		}
		if(Math.abs(x - GameBoard.target.x) < GameBoard.target.width && Math.abs(y - GameBoard.target.y) < GameBoard.target.height)
		{
			GameBoard.target.hit = true; 
			return true;
		}
		return false;
	}

}
