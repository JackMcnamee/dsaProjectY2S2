package ie.gmit.sw;

import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Runner 
{
	public static void main(String[] args) throws Exception 
	{
		Menu menu = new Menu();
		menu.showMenu();
		
		Parser p = new Parser();
		p.parseIgnoreFile();
		
		p.parse(new FileInputStream(menu.getUserFileName()));	
		
		Queue<WordFrequency> q = p.getWordQueue();
		
		WordCloudImage img = new WordCloudImage();
		img.drawString(q, menu.getMaxWords());
		
		ImageIO.write(img.getImage(), "png", new File(menu.getUserImageName()));
	}
}
