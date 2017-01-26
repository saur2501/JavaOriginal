package interfacesAdt;

public interface Set {
	Boolean makeSet();
    int size();				//size of set
    Boolean empty();			//is set empty
    void insert( int a );		//insert element int ao set
    Boolean member( int a );		//is element a member of set?   
    void remove( int a );		//remove from set
    void clear();			//remove all elements from container
}
