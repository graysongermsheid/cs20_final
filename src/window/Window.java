package window;

import java.awt.Dimension;
import javax.swing.JFrame;
import input.InputHandler;

public class Window extends JFrame{
	
	public static void main(String[] args){

		Window window = new Window();

	}

	public Window(){
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("~~~~~~~");

		GameCanvas canvas = new GameCanvas();
		this.add(canvas);
		canvas.setPreferredSize(new Dimension(640, 480));
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		canvas.createBuffer();

		//this.setResizable(false);

		canvas.run();
	}
}