package dsa.ds;
import java.util.NoSuchElementException;

@SuppressWarnings("hiding")
public class MyDoublyLinkedList<Object> {
	private Node head;
	private Node tail;
	private int size;
	public MyDoublyLinkedList() {
		size = 0;
	}
	    /**
	     * this class keeps track of each element information
	     * @author java2novice
	     *
	     */
	private class Node {
		Object element;
	    Node next;
	    Node prev;
	    public Node(Object element, Node next, Node prev) {
	    	this.element = element;
	        this.next = next;
	        this.prev = prev;
	    }
	}
	    /**
	 * returns the size of the linked list
	 * @return
	 */
	public int size() { return size; }
	 
	/**
	 * return whether the list is empty or not
	 * @return
	 */
	public boolean isEmpty() { return size == 0; }
	     
	    /**
	     * adds element at the starting of the linked list
	     * @param element
	 */
	public void addFirst(Object element) {
	    Node tmp = new Node(element, head, null);
	    if(head != null ) {head.prev = tmp;}
	    head = tmp;
	    if(tail == null) { tail = tmp;}
	    size++;
	    System.out.println("adding: "+element);
	}
	 
	    /**
	     * adds element at the end of the linked list
	     * @param element
	     */
	public void addLast(Object element) {
	     
	    Node tmp = new Node(element, null, tail);
	    if(tail != null) {tail.next = tmp;}
	    tail = tmp;
	    if(head == null) { head = tmp;}
	    size++;
	    System.out.println("adding: "+element);
	}
	     
	    /**
	     * this method walks forward through the linked list
	 */
	public void iterateForward(){
	     
	    System.out.println("iterating forward..");
	    Node tmp = head;
	    while(tmp != null){
	        System.out.println(tmp.element);
	        tmp = tmp.next;
	    }
	}
	     
	    /**
	     * this method walks backward through the linked list
	     */
	public void iterateBackward(){
	     
	    System.out.println("iterating backword..");
	    Node tmp = tail;
	    while(tmp != null){
	        System.out.println(tmp.element);
	        tmp = tmp.prev;
	    }
	}
	     
	    /**
	     * this method removes element from the start of the linked list
	     * @return
	     */
	public Object removeFirst() {
	    if (size == 0) throw new NoSuchElementException();
	    Node tmp = head;
	    head = head.next;
	    head.prev = null;
	    size--;
	    System.out.println("deleted: "+tmp.element);
	    return tmp.element;
	}
	     
	    /**
	     * this method removes element from the end of the linked list
	     * @return
	     */
	public Object removeLast() {
	    if (size == 0) throw new NoSuchElementException();
	    Node tmp = tail;
	    tail = tail.prev;
	    tail.next = null;
	    size--;
	    System.out.println("deleted: "+tmp.element);
	    return tmp.element;
	}
	public void execute(){
		MyDoublyLinkedList<Integer> dll = new MyDoublyLinkedList<Integer>();
		dll.addFirst(10);
		dll.addFirst(34);
		dll.addLast(56);
		dll.addLast(364);
		dll.iterateForward();
		dll.removeFirst();
		dll.removeLast();
		dll.iterateBackward();
	}
}