package ds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class NodeGraph
{
	int data;
	boolean visited;
	
	NodeGraph(int data)
	{
		this.data=data;
	}
}

public class GraphArray
{ 
	int adjacency_matrix[][];
	ArrayList<NodeGraph> nodes=new ArrayList<NodeGraph>();
	public GraphArray(int adjMatrix[][], int rows, int cols){
		 adjacency_matrix= new int [rows][cols];
		 adjacency_matrix = adjMatrix;
	}
	
	
	 // find neighbors of node using adjacency matrix
	 // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
	public ArrayList<NodeGraph> findNeighbours(NodeGraph x)
	{
		int nodeIndex=-1;
		ArrayList<NodeGraph> neighbours=new ArrayList<NodeGraph>();
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(x))
			{
				nodeIndex=i;
				break;
			}
		}
	
		if(nodeIndex!=-1)
		{
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if(adjacency_matrix[nodeIndex][j]==1){
					 neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}
	
	 public void clearVisitedFlags()
	 {
	  for (int i = 0; i < nodes.size(); i++) {
	   nodes.get(i).visited=false;
	  }
	 }
	 
	    // Recursive DFS
	 public  void dfs(NodeGraph node)
	 {
	  System.out.print(node.data + "\t");
	  ArrayList<NodeGraph> neighbours=findNeighbours(node);
	  for (int i = 0; i < neighbours.size(); i++) {
	   NodeGraph n=neighbours.get(i);
	   if(n!=null && !n.visited)
	   {
	    dfs(n);
	    n.visited=true;
	   }
	  }
	 }
	
	 // Iterative DFS using stack
	 public  void dfsUsingStack(NodeGraph node)
	 {
	  Stack<NodeGraph> stack=new  Stack<NodeGraph>();
	  stack.add(node);
	  node.visited=true;
	  while (!stack.isEmpty())
	  {
		  NodeGraph element=stack.pop();
		  System.out.print(element.data + "\t");
	
		  ArrayList<NodeGraph> neighbours=findNeighbours(element);
		  for (int i = 0; i < neighbours.size(); i++) {
			  NodeGraph n=neighbours.get(i);
			  if(n!=null && !n.visited)
			  {
				  stack.add(n);
				  n.visited=true;
			  }
		  }
	  }
	}

	 public void bfs(NodeGraph source)
	    {
		 	Queue<NodeGraph> queue = new LinkedList<NodeGraph>();
	        //int i;
	        NodeGraph element,i;
	 
	        source.visited = true;
	        queue.add(source);
	 
	        while (!queue.isEmpty())
	        {
	            element = queue.remove();
	            i = element;
	            System.out.print(i.data + "\t");
	            ArrayList<NodeGraph> neighbours = findNeighbours(element);
	            for(NodeGraph nodeGraph : neighbours){
	            	if(!nodeGraph.visited){
	            		queue.add(nodeGraph);
	            		nodeGraph.visited = true;
	            	}
	            }
	        }
	            /*while (i <= number_of_nodes)
	            {
	                if (adjacency_matrix[element][i] == 1 && visited[i] == 0)
	                {
	                    queue.add(i);
	                    visited[i] = 1;
	                }
	                i++;
	            }*/
	    }
	 public void execute(){
		 NodeGraph node40 =new NodeGraph(40);
		 NodeGraph node10 =new NodeGraph(10);
		 NodeGraph node20 =new NodeGraph(20);
		 NodeGraph node30 =new NodeGraph(30);
		 NodeGraph node60 =new NodeGraph(60);
		 NodeGraph node50 =new NodeGraph(50);
		 NodeGraph node70 =new NodeGraph(70);
		 int adjacency_matrix[][] = {
			    {0,1,1,0,0,0,0},  // Node 1: 40
			    {0,0,0,1,0,0,0},  // Node 2 :10
			    {0,1,0,1,1,1,0},  // Node 3: 20
			    {0,0,0,0,1,0,0},  // Node 4: 30
			    {0,0,0,0,0,0,1},  // Node 5: 60
			    {0,0,0,0,0,0,1},  // Node 6: 50
			    {0,0,0,0,0,0,0},  // Node 7: 70
		  };
		 GraphArray ga = new GraphArray(adjacency_matrix,7,7);
		 ga.nodes.add(node40);
		 ga.nodes.add(node10);
		 ga.nodes.add(node20);
		 ga.nodes.add(node30);
		 ga.nodes.add(node60);
		 ga.nodes.add(node50);
		 ga.nodes.add(node70);
		 System.out.println("The DFS traversal of the graph using stack ");
		 ga.dfsUsingStack(node40);
		 System.out.println();
		 ga.clearVisitedFlags();	  
		 System.out.println("The DFS traversal of the graph using recursion ");
		 ga.dfs(node40);
		 System.out.println();
		 ga.clearVisitedFlags();
		 System.out.println("BFS Now : ");
		 ga.bfs(node40);
		 System.out.println();
	 }
}