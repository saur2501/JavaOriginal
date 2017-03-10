package interfaces;

public interface OrderedDictionary extends Dictionary{
	//aka Linear Ordering (Ordered relationship on a criteria- sequence on entry or size )
	Boolean isFirst();
	Boolean isLast();
	//query methods (retrieve)
	Object first();
	Object last();
	Object before(Object o);	//can be index integer or pointer
	Object elementAtRank(Object index);
	Object after(Object o);
	void swapElements(Object o1, Object o2);
	//insert and update methods
	
	void insertFirst(Object element);
	void insertBefore(Object i, Object element);
	void replaceElement(Object i, Object element);
	void replaceElementAtRank(Object rank, Object element);
	void insertAtRank(Object rank, Object element);
	void insertAfter(Object i, Object element);
	void insertLast(Object element);
	//delete inherited
	Object removeAtRank(Object rank);
}
