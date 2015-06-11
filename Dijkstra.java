import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra 
{
	BuildMap map = new BuildMap();
	public static BinaryHeap<Vertex> heap;
	public static ArrayList<Vertex> shortestPath;

	public void dijkstra(Vertex start)
	{	
		//make all distances infinite
		for (String key : map.cities.keySet())
		{
			map.cities.get(key).dist = BigDecimal.valueOf(Double.MAX_VALUE);
		}

		//create heap that can hold all cities
		heap = new BinaryHeap<Vertex>(map.cities.keySet().size());
		
		//make starting vertex distance = 0
		start.dist = BigDecimal.ZERO;
		heap.insert(start);

		//while there are elements in the heap
		while (!heap.isEmpty())
		{
			Vertex u = heap.deleteMin();

			//loop through vertex u's adjacency list
			for (Edge e : u.adj)
			{
				Vertex v = map.cities.get(e.target);

				//add distance of u and each edge distance
				BigDecimal distanceThroughU = u.dist.add(e.distance);

				//if distance through u is less than v's total distance
				if (distanceThroughU.compareTo(v.dist) < 0)
				{
					//update v's distance, add u to v's path, insert vertex v into heap
					v.dist = distanceThroughU;
					v.path = u;
					heap.insert(v);
				}
			}
		}
		heap.makeEmpty(); //clear heap
	}

	public ArrayList<Vertex> shortestPathTo(Vertex destination)
	{
		//starts at given destination, traces back path to start
		shortestPath = new ArrayList<Vertex>();
		for (Vertex v = destination; v != null; v = v.path)
		{
			//add each vertex to path
			shortestPath.add(v);
		}

		//reverse order of arraylist so it starts at departure and ends at destination
		Collections.reverse(shortestPath);
		return shortestPath;
	}
}