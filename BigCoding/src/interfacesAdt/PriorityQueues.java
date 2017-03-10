package interfacesAdt;

public interface PriorityQueues {
	void push( Object element, int priority);		//value, priority
	Object extremum();					//minimum or maximum
	void pop();
}
