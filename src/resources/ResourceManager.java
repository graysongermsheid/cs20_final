package resources;

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

public class ResourceManager {
	
	private static HashMap<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();
	private static HashMap<String, Animation> animationMap = new HashMap<String, Animation>();
	private static HashMap<String, SpriteSheet> spriteSheetMap = new HashMap<String, SpriteSheet>();
	private static HashMap<String, SpriteFont> fontMap = new HashMap<String, SpriteFont>();

	public static void clearResources(){

		imageMap.clear();
		animationMap.clear();
		spriteSheetMap.clear();
		fontMap.clear();

	}

	public static BufferedImage getImage(String imageName){

		if (imageMap.get(imageName) == null){

			addImage(imageName);

		}

		return imageMap.get(imageName);

	}

	public static SpriteFont getFont(String fontName){

		if (fontMap.get(fontName) == null){

			addFont(fontName);

		}

		return fontMap.get(fontName);

	}

	public static Animation getNewAnimation(String sourceSpriteSheet, float delay){

		if (!hasSpriteSheet(sourceSpriteSheet)){

			return null;

		}

		return new Animation(getSpriteSheet(sourceSpriteSheet), delay);

	}

	public static Animation animationFromSheet(SpriteSheet source, float delay){

		return new Animation(source, delay);

	}

	public static boolean hasSpriteSheet(String sheetName){

		return (spriteSheetMap.get(sheetName) == null) ? false : true;

	}

	public static SpriteSheet getSpriteSheet(String sheetName){

		return spriteSheetMap.get(sheetName);

	}

	public static void createSpriteSheet(String sheetName, int frameWidth, int frameHeight){

		spriteSheetMap.put(sheetName, new SpriteSheet(sheetName, frameWidth, frameHeight));

	}

	private static void addFont(String fontName){

		if (spriteSheetMap.get(fontName) == null){

			int size = fontName.matches("//S*_large.png") ? 32 : 16;

			createSpriteSheet(fontName, size, size);

		}

		fontMap.put(fontName, new SpriteFont(getSpriteSheet(fontName)));

	}

	private static void addImage(String imageName){

		try {

			BufferedImage newImage = ImageIO.read(new File("src/content/" + imageName));
			imageMap.put(imageName, newImage);

		} catch (Exception e){

			System.out.println("ResourceManager could not load image <" + imageName + "> please make sure this file is a .png");

		}
	}

	private static void addAnimation(String animationName){

		Animation newAnimation = new Animation(getSpriteSheet(animationName), 1.0f);
		animationMap.put(animationName, newAnimation);

	}
}