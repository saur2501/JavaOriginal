package interfacesAdt;
import interfaces.Dictionary;

public interface AbstractGraph extends Dictionary{
	//Generic
	int numVertices();			//=size();
	int numEdges();	
	//Retrieve
		//Query
	Boolean isAdjacent( Object v1,Object v2);			//Determine if there is an edge from one vertex to another.
	Object position(Object vertex);			//Vertex = element and edge = Relationship
	Object[] vertices();
	Object[] edges();
	
	//Insert Relationship
	void insert_edge( int a, int b);		//Add an edge between two vertices.
	void swap(Object v1, Object v2);
	void replaceElement(Object position, Object element);
    
    int adjacent( int a );				//Return an iterator which walks through the adjacent vertices.
    int degree(int a);					//degree of vertex
    void remove( int a, int b);				//remove an edge
}
