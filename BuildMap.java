//****************************************************
//BuildMap Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BuildMap 
{	
	public static HashMap<String, Vertex> cities;
	public static int xcoordbound = 0;
	public static int ycoordbound = 0;

	public void MapBuilder(File file, File file1) throws IOException, ParseException
	{
		cities = new HashMap<String, Vertex>();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;

		while ((line = reader.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(line, " ");

			while (st.hasMoreTokens())
			{
				String city = st.nextToken();
				String destination = st.nextToken();

				//creates vertices based on first two tokens
				Vertex v = new Vertex(city);
				Vertex x = new Vertex(destination);

				//creates variable for third token --> edge weight
				BigDecimal edgeWeight = new BigDecimal(st.nextToken());
				Edge e = new Edge(destination, edgeWeight);
				Edge e1 = new Edge(city, edgeWeight);

				//checks if city and/or destination are in hashmap, add vertices and edges accordingly
				if (cities.containsKey(city) && cities.containsKey(destination))
				{
					cities.get(city).adj.add(e);
					cities.get(destination).adj.add(e1);
				}
				else if (cities.containsKey(city) && !cities.containsKey(destination))
				{
					cities.put(destination, x);
					cities.get(city).adj.add(e);
					cities.get(destination).adj.add(e1);
				}
				else if (!cities.containsKey(city) && cities.containsKey(destination))
				{
					cities.put(city, v);
					cities.get(city).adj.add(e);
					cities.get(destination).adj.add(e1);
				}
				else
				{
					cities.put(city, v);
					cities.put(destination, x);

					cities.get(city).adj.add(e);
					cities.get(destination).adj.add(e1);
				}
			}	
		}	
		reader.close();
		coordinates(file1);
	}

	private void coordinates(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;

		while ((line = reader.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(line, " ");

			while (st.hasMoreTokens())
			{
				String city = st.nextToken();

				if (cities.containsKey(city))
				{
					cities.get(city).xcoord = Integer.parseInt(st.nextToken());
					cities.get(city).ycoord = Integer.parseInt(st.nextToken());
				}
			}
		}

		//finds largest x and y coordinates for panel size
		for (String key : cities.keySet())
		{
			if (cities.get(key).xcoord > xcoordbound)
			{
				xcoordbound = cities.get(key).xcoord;
			}
			if (cities.get(key).ycoord > ycoordbound)
			{
				ycoordbound = cities.get(key).ycoord;
			}
		}
		System.out.println("Max - x: " + xcoordbound + ", max - y: " + ycoordbound);
		reader.close();
	}
}	