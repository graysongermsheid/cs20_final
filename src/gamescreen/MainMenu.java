package gamescreen;

import java.awt.Graphics2D;
import gui.*;
import resources.*;
import java.awt.Color;
import java.awt.Polygon;

public class MainMenu extends MenuScreen {

	public MainMenu(){

		

	}

	@Override
	public void loadResources(){

		super.loadResources();

		components = new GUIComponent[5];

		components[0] = new Button("Play Game", 512, 512, 256, 32){

			@Override
			public void performAction(){

				ScreenManager.switchCurrentScreen(new Game());

			}
		};
		components[1] = new Button("Mute", 512, 556, 256, 32){

			@Override
			public void performAction(){

				ResourceManager.MUTE = !ResourceManager.MUTE;

			}
		};
		components[2] = new Button("Quit", 512, 644, 256, 32){
			
			@Override
			public void performAction(){
				
				System.exit(0);
				
			}
			
		};
		components[3] = new Button("Help", 512, 600, 256, 32){
			
			@Override
			public void performAction(){
				
				((TextBox)components[4]).show();
				
			}
			
		};
		components[4] = new TextBox("help.txt", 448, 316, 384, 176);

	}

	@Override
	public void draw(Graphics2D g){

		g.drawImage(ResourceManager.getImage("title_banner.png"), 256, 64, null);
		super.draw(g);
		
	}

	@Override
	public String getTitle(){

		return "Main Menu";

	}
}