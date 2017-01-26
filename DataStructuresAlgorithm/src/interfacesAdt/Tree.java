package interfacesAdt;
import interfaces.OrderedDictionary;

public interface Tree extends OrderedDictionary{
	//Create
	void createTree();
	//Generic
	int size();
	int numberOfChildren(int a);
	void numberOfDescendents();
	int getHeight(int a);			//height of a node
	//Retrieve
	Boolean isEmpty();
	Boolean isRoot(Object index);				//given a position
	
	Object getRoot();
	Object getParent();
	Object[] getChildren();			//leftChild(p); rightChild(p); sibling(p);
	void elements();				//all elements
	void traversal(); 				//for all descendents
	void traverseToRoot(int a);		//Iterate through the ancestors back to the root node,
	//Update
	void add();
	void replaceElement();	//set position p with value e
	void swapElements();	//position p,q
}
