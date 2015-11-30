package window;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import input.InputHandler;

public class Window extends JFrame{
	
	public static void main(String[] args){

		Window window = new Window();

	}

	public Window(){
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Cool Title");

		Screen screen = new Screen();
		this.add(screen);
		screen.setPreferredSize(new Dimension(1024, 640));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		screen.createBuffer();

		screen.run();
	}
}