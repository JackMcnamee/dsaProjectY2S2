package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class Parser 
{
	
	//instance variables
	private static final String ignoreWordsFile = "ignorewords.txt";
	
	private Set<String> ignoreWords = new TreeSet<>();
	
	public Parser() {
		
	}
	
	public void parse(InputStream in) throws Exception 
	{
		BufferedReader userFile = new BufferedReader(new InputStreamReader(in));
		String next = null;
		
		while((next = userFile.readLine())!=null) 
		{
			String[] words = next.split(" ");
			for(String word:words) {
				if(!ignoreWords.contains(word)) 
				{
					updateMap(word);
				}
			}
		} 
		
		sortMap();
		in.close();
	}
	
	// O(log n)
	public void parseIgnoreFile() throws IOException 
	{
		BufferedReader ignoreFile = new BufferedReader(new FileReader(ignoreWordsFile));
		String next = null;

		while ((next = ignoreFile.readLine()) != null) 
		{
			if (next.isEmpty()) 
			{
				continue;
			}
			
			ignoreWords.add(next.toLowerCase());
		}
		
		ignoreFile.close();
	}
	
	private Map<String, Integer> wordMap = new TreeMap<>();
	
	public void updateMap(String word) 
	{	
		int frequency;
		
		if(wordMap.containsKey(word)) 
		{
			frequency = wordMap.get(word);
			wordMap.put(word, ++frequency);
		}
		else 
		{
			wordMap.put(word,  1);
		}
			
	} 
	
	private Queue<WordFrequency> q = new PriorityQueue<>();
	
	public void sortMap() 
	{
		Set<String> keys = wordMap.keySet(); // O(1)
		
		for(String key : keys) 
		{
			q.offer(new WordFrequency(key, wordMap.get(key))); // O(log n)
		}	
	}
	
	public Queue<WordFrequency> getWordQueue() 
	{
		return q;
	}
}
