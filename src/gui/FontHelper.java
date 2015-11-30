package gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;

public class FontHelper {
	
	private Font font;
	private Color color;
	public static Graphics2D g2; // so we can get font metrics

	public FontHelper(String fontName, int fontSize){

		try {

			this.font = new Font(fontName, Font.PLAIN, fontSize);

		} catch (Exception e){

			e.printStackTrace();
			System.out.println("Could not load font: " + fontName);
			System.out.println("Are you sure you loaded it?");

		}

		this.color = Color.BLACK;

	}

	public void drawCenteredText(String text, int width, int height, Graphics2D g){

		Color c = g.getColor();

		g.setColor(color);
		g.setFont(font);

		FontMetrics m = g.getFontMetrics();

		int x = (width / m.stringWidth(text)) / 2;
		int y = (m.getAscent() + (height - (m.getAscent() + m.getDescent())) / 2);

		g.drawString(text, x, y);

		g.setColor(c);

	}

	public void drawCenteredY(String text, int x, int height, Graphics2D g){

		Color c = g.getColor();

		g.setColor(color);
		g.setFont(font);

		FontMetrics m = g.getFontMetrics();

		int y = (m.getAscent() + (height - (m.getAscent() + m.getDescent())) / 2);

		g.drawString(text, x, y);

		g.setColor(c);

	}

	public void drawCenteredX(String text, int width, int y, Graphics2D g){

		Color c = g.getColor();

		g.setColor(color);
		g.setFont(font);

		FontMetrics m = g.getFontMetrics();

		int x = (width / m.stringWidth(text)) / 2;

		g.drawString(text, x, y);

		g.setColor(c);

	}

	public void drawText(String text, int x, int y, Graphics2D g){

		Color c = g.getColor();

		g.setColor(color);
		g.setFont(font);

		g.drawString(text, x, y);

		g.setColor(c);

	}

	public void setColor(Color c){

		this.color = c;

	}

	public void setSize(int size){

		font = font.deriveFont(size);

	}

	public Dimension getStringSize(String s){

		FontMetrics m = g2.getFontMetrics();
		int width = m.stringWidth(s);
		int height = (int)font.createGlyphVector(m.getFontRenderContext(), s).getVisualBounds().getHeight();

		return new Dimension(width, height);

	}

	public Dimension getSize(){

		FontMetrics m = g2.getFontMetrics();
		int width = m.getMaxDescent();
		int height = m.getMaxAscent();

		return new Dimension(width, height);

	}
}