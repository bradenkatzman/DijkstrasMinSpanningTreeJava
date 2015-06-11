//****************************************************
//DisplayWindow Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class DisplayWindow 
{
	public static MyComponent mc;
	public static JFrame frame;
	public static JPanel cityPanel;
	private static JPanel actionPanel;
	public static JComboBox comboBoxStart;
	public static JComboBox comboBoxDestination;
	private static JLabel departure;
	private static JLabel destination;
	private static JLabel distanceLabel;
	private static JLabel pathLabel;
	public static JTextField distance;
	public static JTextField path;
	private static JScrollPane scrollPane;

	public static void main(String[] args) 
	{	
		try
		{
			if (args.length == 2)
			{
				//creates files from command line args
				File citypairs = new File(args[0]);
				File citycoordinates = new File(args[1]);

				BuildMap map = new BuildMap();

				//passes files to map builder to construct map
				map.MapBuilder(citypairs, citycoordinates);

				//creates component based on size of largest x and y coordinates in file
				mc = new MyComponent(map.xcoordbound, map.ycoordbound);

				//creates new main frame
				frame = new JFrame();

				//set size of main frame to constant
				frame.setPreferredSize(new Dimension(1000, 1000));
				frame.setLayout(new BorderLayout());

				//creates new panel for city map
				cityPanel = new JPanel();
				cityPanel.setLayout(new FlowLayout());

				//adds map component to city panel
				cityPanel.add(mc);
				Border redLine = BorderFactory.createLineBorder(Color.RED);
				cityPanel.setBorder(redLine);

				//creates new panel for buttons
				actionPanel = new JPanel();
				actionPanel.setLayout(new GridLayout(3,4));
				Border blueLine = BorderFactory.createLineBorder(Color.BLUE);
				actionPanel.setBorder(blueLine);
				actionPanel.setPreferredSize(new Dimension(200, 100));

				String[] cities = new String[map.cities.keySet().size()];
				int i = 0;
				for (String key : map.cities.keySet())
				{
					cities[i] = map.cities.get(key).cityName;
					i++;
				}
				comboBoxStart = new JComboBox(cities);
				departure = new JLabel("Departure City");


				comboBoxDestination = new JComboBox(cities);
				destination = new JLabel("Desination city");

				distance = new JTextField("", 25);
				distanceLabel = new JLabel("Distance Traveled");

				path = new JTextField("", 25);
				pathLabel = new JLabel("Path Traveled");

				//add labels, combo boxes, and buttons to action panel
				actionPanel.add(departure);
				actionPanel.add(comboBoxStart);
				actionPanel.add(comboBoxDestination);
				actionPanel.add(destination);
				actionPanel.add(distanceLabel);
				actionPanel.add(distance);
				actionPanel.add(path);
				actionPanel.add(pathLabel);
				actionPanel.add(new Box(0)); //for formatting purposes
				actionPanel.add(makeButton("Compute Paths"));
				actionPanel.add(makeButton("Reset Map"));

				//creates new scrollPane that houses map component
				scrollPane = new JScrollPane(cityPanel);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setPreferredSize(new Dimension(800, 880));

				//adds action panel and scroll pane to main frame
				frame.add(actionPanel, BorderLayout.NORTH);
				frame.add(scrollPane, BorderLayout.SOUTH);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setResizable(false);
				frame.setVisible(true);

				//console tester
				for (String key : map.cities.keySet())
				{
					System.out.print(map.cities.get(key).cityName + " x " + map.cities.get(key).xcoord
							+ " y " + map.cities.get(key).ycoord + " : ");
					for (Edge e : map.cities.get(key).adj)
					{
						System.out.print(e.target + " (" + e.distance + ") ");
					}

					System.out.println("");
				}
				//end tester
			}
			else
			{
				System.out.println("Incorrect arguments -- "
						+ "rerun program with two file names as arguments");
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		finally
		{
			System.out.println("Program Terminated");
		}

	}

	//button maker method
	private static JButton makeButton(String s)
	{
		//creates button based on string parameter
		JButton button = new JButton(s);
		button.addActionListener(new MyActionListener());
		button.setActionCommand(s);

		return button;
	}
}