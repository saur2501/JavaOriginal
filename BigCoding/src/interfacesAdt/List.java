package interfacesAdt;
import interfaces.OrderedDictionary;

public interface List extends OrderedDictionary{
	void sublist();
	void convertToListFromMap();	//map to list
	Boolean mergeLists();			//(list2 with list1
	void splitLinearList();
	void makeCopyOfList();		//CRUD- Create				
	Boolean contains(int value);	//Retrieve- CRUD
	Boolean containsAll();			//list1 = list2
	int getValueAtIndex(int index);
	int indexOf(int value);
	int lastIndexOf(int value);
	void sort();
	void add(int add);
	void addAll();		//from map, set, etc
	Boolean addNodeBeforeAfter(int value, Boolean before);	//Insert and Update- CRUD
	int remove(int index);				//Delete
	Boolean clear();
	int removeRange(int index1, int index2);
	void setValue(int index, int value);			//set() set value at index
}
