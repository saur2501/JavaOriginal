package dsa.ds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class GraphNodeDAG{
	int data;
	//Boolean visited = false;				//it is disastrous to have every node (in 2 lists types) hold their own visited boolean (can't do traversal)
											//visited gotta map to node keys!!
	public GraphNodeDAG(int data){
		this.data = data;
	}
}

public class DAG
{
    private Map<GraphNodeDAG, List<GraphNodeDAG>> adjacencyList;
    Boolean[] visited;
    public DAG(int v)
    {
    	GraphNodeDAG gnd = null;
        adjacencyList = new HashMap<GraphNodeDAG, List<GraphNodeDAG>>();
        visited = new Boolean[v+1];
        for (int i = 1; i <= v; i++){
        	gnd = new GraphNodeDAG(i);
            adjacencyList.put(gnd, new LinkedList<GraphNodeDAG>());
        }
    }
 
    public void setEdge(int from, int to)
    {
        if (to > adjacencyList.size() || from > adjacencyList.size()){
            System.out.println("The vertices does not exists");
            return;
        }
        /*
         * List<Integer> sls = adjacencyList.get(to);
         * sls.add(from);
         */
        Boolean cycle = findPath(to,from);
        if(cycle){
        	System.out.println("Cycle made. Can't add this edge.\nConsider removing some edges and then adding this new one.");
        	return;
        }
        GraphNodeDAG gndTemp = getNode(from);
        if(gndTemp == null){
        	System.out.println("Improper Vertex");
        	return;
        }
        List<GraphNodeDAG> dls = adjacencyList.get(gndTemp);
        GraphNodeDAG gnd = new GraphNodeDAG(to);
        dls.add(gnd);
    }
 
    private GraphNodeDAG getNode(int from) {
		for(GraphNodeDAG node : adjacencyList.keySet())
			if(node.data == from)
				return node;
		return null;
	}

	private Boolean findPath(int from, int to) {
    	Boolean found = dfsVariant(from, to);
    	for(GraphNodeDAG gnd : adjacencyList.keySet())
    		visited[gnd.data] = false;
    	return found;
	}

    Boolean dfsVariant(int from, int to)
    {
    	Boolean found = false;
		List<GraphNodeDAG> neighbours = getEdge(from);
		if(neighbours.isEmpty())
			return false;
	  	for (int i = 0; i < neighbours.size(); i++) {
	  		GraphNodeDAG node = neighbours.get(i);
	  		if(node!=null && !visited[node.data])
	  		{
	  			visited[node.data]=true;
	  			if(node.data == to)
	  				return true;
		  	    found = dfsVariant(node.data, to);
		  	    if(found)
		  	    	return true;
	  		}
	  	}
  		return false;
    }
	public List<GraphNodeDAG> getEdge(int to)
    {
        if (to > adjacencyList.size())
        {
            System.out.println("The vertices does not exists");
            return null;
        }
        for(GraphNodeDAG node : adjacencyList.keySet()){
        	if(node.data == to)
        		return adjacencyList.get(node);
        }
        return null;
    }
 
    public boolean checkDAG()
    {
        Integer count = 0;
        Iterator<GraphNodeDAG> iteratorI = this.adjacencyList.keySet().iterator();			//iterate over vertices
        Integer size = this.adjacencyList.size() - 1;
        while (iteratorI.hasNext())												//go thru all vertices
        {
        	GraphNodeDAG i = iteratorI.next();
            List<GraphNodeDAG> adjList = this.adjacencyList.get(i);				//get connected vertices list
            if (count == size)			//ever count becomes equal to size
            {
                return true;
            }
            if (adjList.size() == 0)			//no connected vertex
            {
                count++;
                System.out.println("Target Node - " + i);
                Iterator<GraphNodeDAG> iteratorJ = this.adjacencyList.keySet()
                        										.iterator();		//iterate over all vertices
                while (iteratorJ.hasNext())
                {
                	GraphNodeDAG j = iteratorJ.next();
                    List<GraphNodeDAG> li = this.adjacencyList.get(j);			//get connected vertices
                    if (li.contains(i))										//
                    {
                        li.remove(i);
                        System.out.println("Deleting edge between target node "
                                + i + " - " + j + " ");
                    }
                }
                this.adjacencyList.remove(i);
                iteratorI = this.adjacencyList.keySet().iterator();
            }
        }
        return false;
    }
 
    public void printGraph()
    {
        System.out.println("The Graph is: ");
        for (int i = 1; i <= this.adjacencyList.size(); i++)
        {
            List<GraphNodeDAG> edgeList = this.getEdge(i);
            if (edgeList.size() != 0)
            {
                System.out.print(i);
                for (int j = 0; j < edgeList.size(); j++)
                {
                    System.out.print(" -> " + edgeList.get(j).data);
                }
                System.out.println();
            }
        }
    }
	public void execute(){
		Scanner scan = new Scanner(System.in);
		DAG dag;
		int count = 0,from,to;
		System.out.println("Enter #Vertices");
		int v = scan.nextInt();
		System.out.println("Enter #Edges");
		int e = scan.nextInt();
		dag = new DAG(v);
		System.out.println("Enter the edges in graph");
		while(count <= e ){
			from = scan.nextInt();
			to = scan.nextInt();
			dag.setEdge(from, to);
			count++;
		}
		scan.close();
	}
}