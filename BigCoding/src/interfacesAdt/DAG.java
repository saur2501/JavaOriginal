package interfacesAdt;

public interface DAG {
	Boolean insert_edge( int a, int b );		//given 2 vertices make an edge; returns false if cycle created;;; if weighted graph then 1 more param for weight
    Boolean adjacent( int a, int b );	//are the 2 vertices adjacent?
    void adjacent( int a );		//Return an iterator which walks through the adjacent vertices.
    void remove( int a, int b);		//Remove an edge
	Boolean areConnected(int a, int b);		//is there a connected path?
	Boolean weaklyConnected();		//for all a,b, a connected to b or vv?
	int connected(int a,int b);		//path from a to b- thru an iterator returned
}
