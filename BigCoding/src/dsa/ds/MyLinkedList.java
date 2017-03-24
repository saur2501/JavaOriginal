package dsa.ds;

public class MyLinkedList<Object> {
	private Node<Object> head;
    private Node<Object> tail;     
    public void add(Object element){
        Node<Object> nd = new Node<Object>();
        nd.setValue(element);
        System.out.println("Adding: "+element);
        /**
         * check if the list is empty
         */
        if(head == null){
            //since there is only one element, both head and
            //tail points to the same object.
            head = nd;
            tail = nd;
        } else {
            //set current tail next link to new node
            tail.setNextRef(nd);
            //set tail as newly created node
            tail = nd;
        }
    }
     
    public void addAfter(Object element, Object after){
         
        Node<Object> tmp = head;
        Node<Object> refNode = null;
        System.out.println("Traversing to all nodes..");
        /**
         * Traverse till given element
         */
        while(true){
            if(tmp == null){
                break;
            }
            if(tmp.compareTo(after) == 0){
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if(refNode != null){
            //add element after the target node
            Node<Object> nd = new Node<Object>();
            nd.setValue(element);
            nd.setNextRef(tmp.getNextRef());
            if(tmp == tail){
                tail = nd;
            }
            tmp.setNextRef(nd);
             
        } else {
            System.out.println("Unable to find the given element...");
        }
    }
     
    public void deleteFront(){
         
        if(head == null){
            System.out.println("Underflow...");
        }
        Node<Object> tmp = head;
        head = tmp.getNextRef();
        if(head == null){
            tail = null;
        }
        System.out.println("Deleted: "+tmp.getValue());
    }
     
    public void deleteAfter(Object after){
         
        Node<Object> tmp = head;
        Node<Object> refNode = null;
        System.out.println("Traversing to all nodes..");
        /**
         * Traverse till given element
         */
        while(true){
            if(tmp == null){
                break;
            }
            if(tmp.compareTo(after) == 0){
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if(refNode != null){
            tmp = refNode.getNextRef();
            refNode.setNextRef(tmp.getNextRef());
            if(refNode.getNextRef() == null){
                tail = refNode;
            }
            System.out.println("Deleted: "+tmp.getValue());
        } else {
            System.out.println("Unable to find the given element...");
        }
    }
     
    public void traverse(){
         
        Node<Object> tmp = head;
        while(true){
            if(tmp == null){
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.getNextRef();
        }
    }
    public void execute(){
		MyLinkedList<Integer> sl = new MyLinkedList<Integer>();
        sl.add(3);
        sl.add(32);
        sl.add(54);
        sl.add(89);
        sl.addAfter(76, 54);
        sl.deleteFront();
        sl.deleteAfter(76);
        sl.traverse();
	}
}
	 
	class Node<Object> implements Comparable<Object> {
	     
	    private Object value;
	    private Node<Object> nextRef;
	     
	    public Object getValue() {
	        return value;
	    }
	    public void setValue(Object value) {
	        this.value = value;
	    }
	    public Node<Object> getNextRef() {
	        return nextRef;
	    }
	    public void setNextRef(Node<Object> ref) {
	        this.nextRef = ref;
	    }
		@Override
		public int compareTo(Object arg) {
			if(arg == this.value){
	            return 0;
	        } else {
	            return 1;
	        }
		}
	}