package obrien.conor;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{

	
	// main windowy thing
	public GameWindow()
	{
		add(new GameBoard());
		setTitle("Games test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args)
	{
		new GameWindow();

	}

}
