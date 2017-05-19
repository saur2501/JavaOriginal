package system.os;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Node{
    int key;
    int value;
    Node pre;
    Node next;
 
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
    public String toString() {
    	return key + " " + value;
    }
}

class LRUCache {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    Node head=null;
    Node end=null;
 
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
 
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        System.out.println(map.keySet());
        System.out.println(Arrays.toString(new ArrayList(map.values()).toArray()));
        return -1;
    }
 
    public void remove(Node n){
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            end = n.pre;
        }
 
    }
 
    public void setHead(Node n){
        n.next = head;
        n.pre = null;
 
        if(head!=null)
            head.pre = n;
 
        head = n;
 
        if(end ==null)
            end = head;
    }
 
    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        }else{
            Node created = new Node(key, value);
            if(map.size()>=capacity){
                map.remove(end.key);
                remove(end);
                setHead(created);
 
            }else{
                setHead(created);
            }    
 
            map.put(key, created);
        }
    }
}
public class LRUBufferCache {
	public static void main(String[] args) {
    	LRUCache lruCache = new LRUCache(3);
    	lruCache.set(1,4);
    	lruCache.set(2,3);
    	lruCache.set(3,2);
    	lruCache.set(4,1);
    	lruCache.set(1,3);
    	lruCache.get(2);
    	lruCache.get(1);
    	lruCache.set(1,3);
    	lruCache.get(1);
    }
}