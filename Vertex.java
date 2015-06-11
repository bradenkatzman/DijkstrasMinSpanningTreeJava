//****************************************************
//Vertex Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************

import java.math.BigDecimal;
import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>
{
	public String cityName; //used as key for hashmap
	public ArrayList<Edge> adj; //edges which connect adjacent vertices
	public BigDecimal dist;
	public Vertex path;
	public int xcoord;
	public int ycoord;

	public Vertex(String cityName)
	{
		this.cityName = cityName;
		adj = new ArrayList<Edge>();
	}

	//compareTo method for BinaryHeap to compare vertices
	public int compareTo(Vertex v)
	{
		return dist.compareTo(v.dist);
	}
}