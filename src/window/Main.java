package window;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import input.InputHandler;

public class Main extends JFrame{
	
	public static void main(String[] args){

		Main window = new Main();

	}

	public Main(){
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Cool Title - The Game");

		Screen screen = new Screen();
		this.add(screen);
		screen.setPreferredSize(new Dimension(1280, 720));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		screen.createBuffer();

		screen.run();
	}
}