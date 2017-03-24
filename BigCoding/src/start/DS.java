package start;
import java.util.Scanner;

import dsa.ds.AVLTree;
import dsa.ds.ArrayQueue;
import dsa.ds.ArrayStack;
import dsa.ds.BinaryTreeAsIs;
import dsa.ds.DAG;
import dsa.ds.GraphArray;
import dsa.ds.GraphLinkedList;
import dsa.ds.MinHeap;
import dsa.ds.MyArrayList;
import dsa.ds.MyDoublyLinkedList;
import dsa.ds.MyLinkedList;
import dsa.ds.RBTree;

public class DS {
	Scanner scan = new Scanner(System.in);
	public void execute() {
		System.out.print("\nDS\n===\n1. ArrayList\t2. LinkedList\t3. DoublyLinkedList\t4. AVL Trees\t5. RB Trees\t6. Min Heap\t7. Array Stack\t8. Array Queue\t9. Graph Array\t10. Unrefined Binary Trees\t11. Graph Linked List\t12. DAG\t-1. STOP\nEnter Your Choice : ");
		int code = scan.nextInt();
		while(code != -1){
			switch(code){
			case 1:
				MyArrayList myArrayList = new MyArrayList();
				myArrayList.execute();
				break;
			case 2:
				MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
				myLinkedList.execute();
				break;
			case 3:
				MyDoublyLinkedList<Integer> dll = new MyDoublyLinkedList<Integer>();
		        dll.execute();
		        break;
			case 4:
				AVLTree avlTree = new AVLTree();
				avlTree.execute();
				break;
			case 5:
				System.out.println("RB Trees");
				RBTree rbt = new RBTree(Integer.MIN_VALUE); 
				rbt.execute();
				break;
			case 6:
		        System.out.println("Min Heap");
		        MinHeap minHeap = new MinHeap(15);
		        minHeap.execute();
				break;
			case 7:
		        System.out.println("Array Stack");
		        System.out.print("Enter Size of Integer Stack : ");
		    	ArrayStack stk = new ArrayStack(10);
		    	stk.execute();
		        break;
			case 8:
				System.out.println("Array Queue Test\n");
		        System.out.println("Enter Size of Integer Queue ");
		        /* creating object of class arrayQueue */
		        new ArrayQueue(10).execute();
				break;
			case 9:
				System.out.println("GraphArray");
				int[][] arr = new int[5][10];
				new GraphArray(arr,7,7).execute();
				break;
			case 10:
				System.out.println("Unrefined Binary Tree");
				new BinaryTreeAsIs().run();
				break;
			case 11:
				System.out.println("GraphLinkedList");
				new GraphLinkedList(10).execute();
				break;
			case 12:
				System.out.println("DAG Implementation");
				new DAG(10).execute();
			default:
				break;
			}
			System.out.print("\nDS\n===\n1. ArrayList\t2. LinkedList\t3. DoublyLinkedList\t4. AVL Trees\t5. RB Trees\t6. Min Heap\t7. Array Stack\t8. Array Queue\t10. Unrefined Binary Trees\t11. Graph Linked List\t12. DAG\t-1. STOP\nEnter Your Choice : ");
			code = scan.nextInt();
		}
		//sc.close();
	}
}
