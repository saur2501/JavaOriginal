package interfaces;

public interface Dictionary {
	//aka No relation
	//generic methods
	int size();
	Boolean isEmpty();
	//create
	Object merge(Object o); 
	//retrieve
	void elements();
	//Insert and delete
	void add(Object element);
	void delete(Object index);	//remove
	Boolean clear();	//DeleteAllElements
}
//Implementations java.util.Map; .Dictionary;
//use arrays, LL, Hash Tables, Binary Tree (RB,AVL,B)
