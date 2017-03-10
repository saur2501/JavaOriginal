package interfacesAdt;

public interface Map {
	Boolean makeMap();
	int size();
	Boolean empty();
	void insert( int key, int value );		//Inserting a new key-object pair into the container
	Boolean member( int key );					//Determining if a key is in the container (membership)
	int access( int key );					//Access and/or modify the object associated with a key
	void remove( int key );						//Removing an object from the container
	void clear();							//Removing all objects from (clearing) the container
}
