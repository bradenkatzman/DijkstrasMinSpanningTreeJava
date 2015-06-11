//****************************************************
//Edge Class
//
//Author: Braden Katzman
//UNI: bmk2137
//
//Columbia University
//Data Structures and Algorithm Analysis PS5
//Fall 2014
//***************************************************

import java.math.BigDecimal;

public class Edge 
{
	public final String target;
	public final BigDecimal distance;

	public Edge(String target, BigDecimal distance)
	{
		this.target = target;
		this.distance = distance;
	}

}
