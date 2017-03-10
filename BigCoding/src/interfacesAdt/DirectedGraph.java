package interfacesAdt;

public interface DirectedGraph {
	//Generic (element specific)
	int degree(Object v);
	int indegree(Object v);
	int outdegree(Object v);
	//Retrieve
	//vertex to edges
	Object[] directedEdges(Object vertex);
	Object[] undirectedEdges(Object vertex);
	Object[] incidentEdges(Object v);
	Object[] inincidentEdges(Object v);
	Object[] outincidentEdges(Object v);
	/*
	 * Difference from incidentEdges?
	Object[] adjacentVertices(Object v);
	Object[] inAdjacentVertices(Object v);
	Object[] outAdjacentVertices(Object v);*/
		//edges to vertices
	Boolean areAdjacent(Object v1, Object v2);
	Boolean isDirected(Object edge);
	Object opposite(Object vertex, Object edge);	//other end of edge
	Object[] endVertices(Object edge);	//returns vertices
	Object origin(Object edge);				//tail of an edge
	Object destination(Object edge);		//head of an edge
	//insert and update
	void insertVertex(Object v);		//=add()
	void insertDirectedEdge(Object v1, Object v2, Object edge);
		//direction related
	void setDirectionFrom(Object edge, Object vertex);
	void setDirectionTo(Object edge, Object vertex);
	void reverseDirection(Object edge);
	void makeUndirected(Object edge);
}
