//****************************************************
//MyActionListener Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener
{
	private static DisplayWindow w = new DisplayWindow();
	private static BuildMap map = new BuildMap();

	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		
		//if compute paths button is pressed
		if (action.equals("Compute Paths"))
		{
			Dijkstra d = new Dijkstra();
			
			//run dijkstra's algorithm with starting city as combo box selection
			d.dijkstra(map.cities.get(w.comboBoxStart.getSelectedItem()));
			String path = "";
			
			//continually concatenate string to print path to text field
			for (Vertex v : d.shortestPathTo(map.cities.get(w.comboBoxDestination.getSelectedItem())))
			{
				if (path.equals(""))
				{
					path = v.cityName;
				}
				else
				{
					path = path + " - " + v.cityName;
				}
			}
			
			w.path.setText(path);

			w.distance.setText(map.cities.get(w.comboBoxDestination.getSelectedItem()).dist.toString());

			//remove map component from city panel, replace with path component
			PathComponent pc = new PathComponent(map.xcoordbound, map.ycoordbound);
			w.cityPanel.setVisible(false);
			w.cityPanel.removeAll();
			w.cityPanel.add(pc);
			w.cityPanel.setVisible(true);
		}
		else if (action.equals("Reset Map"))
		{
			//remove path component from city panel, replace with map component, reset text fields
			w.cityPanel.setVisible(false);
			w.cityPanel.removeAll();
			w.cityPanel.add(w.mc);
			w.distance.setText("");
			w.path.setText("");
			w.cityPanel.setVisible(true);
		}
	}
}
