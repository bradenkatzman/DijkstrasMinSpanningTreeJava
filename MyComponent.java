//****************************************************
//MyComponent Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyComponent extends JComponent
{
	public MyComponent(int xcoordbound, int ycoordbound)
	{
		setPreferredSize(new Dimension(xcoordbound + 150, ycoordbound + 150));
	}

	public void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		drawMap(g2);
	}

	public void drawMap(Graphics2D g2)
	{
		BuildMap map = new BuildMap();

		Font font = new Font( "Monospaced", Font.BOLD, 20); 
		g2.setFont(font);

		//loops through every city in hashmap
		for (String key : map.cities.keySet())
		{
			Vertex v = map.cities.get(key);

			//draws a city name and circle for every city
			g2.drawString(v.cityName, v.xcoord + 50, (map.ycoordbound - v.ycoord) + 50);
			g2.draw(new Ellipse2D.Double(v.xcoord + 50, (map.ycoordbound - v.ycoord) + 50, 10, 10));

			//loops through each edge in vertex v's adjacency list
			for (Edge e : v.adj)
			{
				//draws each edge
				Vertex m = map.cities.get(e.target);
				g2.drawLine(v.xcoord + 50, (map.ycoordbound - v.ycoord) + 50, m.xcoord + 50, (map.ycoordbound - m.ycoord) + 50);
			}
		}
	}
}