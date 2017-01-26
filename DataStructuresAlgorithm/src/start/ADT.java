package start;

import java.util.Scanner;

public class ADT {
	private Scanner sc = new Scanner(System.in);
	public void execute() {
		System.out.println("\nADT\n===\n1. No Relation\t2. Linear Ordering\t3. Hierarchical Ordering\t4. PartialOrdering\t5. Equivalence Relations\t6. Adjacency Relation\t7. WeakOrdering\t8. Vector Spaces\t9.Mathematical Types\t-1. STOP\nEnter Code : ");
		int code = sc.nextInt();
		while(code!=-1){
			switch(code){
			case 1:
				System.out.println("No Relation\n-------------");
				System.out.print("\n\t1. List\t2. Set\t3. Map\t-1.STOP\nTake a pick of Data Organization (view/ Main Memory Data Model)\t:\t");
				int code2 = sc.nextInt();
				while(code2 != -1){
					if(code2==1){
						System.out.println("LIST : Duplication Allowed, order maintained; many null elements allowed (Variants- LinkedList, ArrayList, Vector)");
						//List list = new List();	
					}
					if(code2 == 2){
						System.out.println("SET : Duplication not allowed, order lost; 1 null element allowed (variants- HashSet, LinkedHashSet and TreeSet (maintains sorted ordering))");
						//Set();
					}
					if(code2 ==3){
						System.out.println("MAP : may contain duplicate values but keys are always unique; Order not maintained; 1 null key allowed (many null values) (variant- TreeMap (maintains ordering))");
						//Map();
					}
					System.out.println("\n\t1. List\t2. Set\t3. Map\nTake a pick of Data Organization (view/ Main Memory Data Model)\t:\t");
					code2 = sc.nextInt();
				}
				break;
			case 2:
				System.out.println("Linear Ordering Relation\n-------------");
				System.out.println("\n\t1. List\t2. SortedList\t3. Stack\t4. Queue\n\t5. String\t6. PriorityQueue\t7. Deque\t-1. STOP\nTake a pick\t:\t");
				code2 = sc.nextInt();
				while(code2 != -1){
					if(code2 == 1)
						//list();
					if(code2 == 2)
						//sortedList();
					if(code2 == 3)
						//stack();
					if(code2 == 4)
						//queue();
					if(code2 == 5)
						//string();
					if(code2 == 6)
						//priorityQueue();
					if(code2 == 7)
						//deque();
					if(code2 == -1)
						break;
					System.out.println("\n\t1. List\t2. SortedList\t3. Stack\t4. Queue\n\t5. String\t-1. STOP\nTake a pick\t:\t");
					code2 = sc.nextInt();
				}
				break;
			case 3:
				System.out.println("Hierarchical Ordering relation\n-------------------");
				//tree();
				break;
			case 4:
				System.out.println("Partial Ordering relation\n-------------------");
				//dag();
				break;
			case 5:
				System.out.println("Equivalence relation\n-------------------");
				//partition();
				break;
			case 6:
				System.out.println("Adjacency relation\n-------------------");
				//abstractGraph();	//using dense matrix, array of linked list, sparse matrix
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			default:
				System.out.println("wrong input");
				break;
			}
			System.out.println("\nADT\n===\n1. No Relation\t2. Linear Ordering\t3. Hierarchical Ordering\t4. PartialOrdering\t5. Equivalence Relations\t6. Adjacency Relation\t7. WeakOrdering\t8. Vector Spaces\t9.Mathematical Types\t-1. STOP\nEnter Code : ");
			code = sc.nextInt();
		}
		//sc.close();
	}
}
