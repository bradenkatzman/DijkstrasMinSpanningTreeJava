//****************************************************
//PathComponent Class
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

public class PathComponent extends JComponent
{

	public PathComponent(int xcoordbound, int ycoordbound)
	{
		setPreferredSize(new Dimension(xcoordbound + 150, ycoordbound + 150));
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		drawPath(g2);
	}

	public void drawPath(Graphics2D g2)
	{
		Dijkstra d = new Dijkstra();
		BuildMap map = new BuildMap();

		Font font = new Font( "Monospaced", Font.BOLD, 20); 
		g2.setFont(font);

		//build shortest path, from array list created by dijkstra's
		for (int i = 0; i < d.shortestPath.size() - 1; i++)
		{			
			g2.drawString(d.shortestPath.get(i).cityName, d.shortestPath.get(i).xcoord + 50,
					(map.ycoordbound - d.shortestPath.get(i).ycoord) + 50);

			g2.draw(new Ellipse2D.Double(d.shortestPath.get(i).xcoord + 50,
					(map.ycoordbound - d.shortestPath.get(i).ycoord) + 50, 10, 10));

			g2.drawLine(d.shortestPath.get(i).xcoord + 50, (map.ycoordbound - d.shortestPath.get(i).ycoord) + 50,
					d.shortestPath.get(i+1).xcoord + 50, (map.ycoordbound - d.shortestPath.get(i+1).ycoord) + 50);

		}

		//final city and city name
		g2.draw(new Ellipse2D.Double(d.shortestPath.get(d.shortestPath.size() - 1).xcoord + 50,
				(map.ycoordbound - d.shortestPath.get(d.shortestPath.size() - 1).ycoord) + 50, 10, 10));

		g2.drawString(d.shortestPath.get(d.shortestPath.size() - 1).cityName, 
				d.shortestPath.get(d.shortestPath.size() - 1).xcoord + 50,
				(map.ycoordbound - d.shortestPath.get(d.shortestPath.size() - 1).ycoord) + 50);
	}
}