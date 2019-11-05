package ie.gmit.sw;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WordCloudImage 
{

	// instance variables
	private static final double maxFont = 50;
	private static final double minFont = 10;
	
	private BufferedImage image;
	
	public WordCloudImage() 
	{
		image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
	}

	public BufferedImage getImage() 
	{
		return image;
	}
	
	// draws text using java 2d api
	public void drawString(Queue<WordFrequency> q, int maxWords) 
	{
		// variables
		int i = 0;
		int count = 0;
		int max = 0;
		int min = 0;
		
		Graphics2D graphics = image.createGraphics(); 

		// bg colour
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 600, 300);
		
		WordFrequency wordFrequency = new WordFrequency();
		
		Iterator<WordFrequency> iterator = q.iterator(); 
		
		Random r = new Random();
		
		while (iterator.hasNext() && count < maxWords) 
		{ 
			i = iterator.next().getFrequency();
		
			if (i > max) {
				max = i;
			}
			
			if (i < min) {
				min = i;
			}
			
			while ((!q.isEmpty()) && count < maxWords)
			{  
				wordFrequency = q.poll();
				
				graphics.setColor(Color.GRAY); // color of word
				graphics.setFont(new Font(Font.SERIF, Font.BOLD, fontSize(wordFrequency.getFrequency(), min, max))); // font of word
				graphics.drawString(wordFrequency.getWord(), r.nextInt(500-100)+100, r.nextInt(250-50)+50); // renders word and finds location
				count++;
			}	
		}
		graphics.dispose();
	}
	
	// font size for word adjusted by frequency
	private int fontSize(double freq, int min, int max) 
	{
		return (int) Math.ceil((maxFont - minFont) * (freq - min) / (max - min) + minFont);
	}
}
