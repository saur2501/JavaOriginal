package interfacesAdt;

public interface Partition {
	void set_union( int a, int b);			//Take the union of two partitions
    int find(int a);						//Return a representative of the partition containing an object
    int partitions();					//Return the number of partitions
    Boolean is_singleton(int a);				//Determining if an object is in in a partition of its own
}										//using disjoint sets
