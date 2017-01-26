package algo.dac;

import java.util.*;

public class SortingRoutines{
	private int[] array;
	private int[] actualArray;
	private Scanner sc = new Scanner(System.in);
	public SortingRoutines(){
		actualArray = new int[]{1,2,5,4,3};
		array = Arrays.copyOf(actualArray, actualArray.length);
	}
	public SortingRoutines(int array[]){
		this.actualArray = array;
		this.array = Arrays.copyOf(actualArray, actualArray.length);
	}
	public void resetArray(int[] array){
		this.array = Arrays.copyOf(actualArray, actualArray.length);
	}
	  public void insertionSort(int n){
		  for (int i = 1; i < n; i++){
			  int j = i;
			  int B = array[i];
			  while ((j > 0) && (array[j-1] > B)){
				  array[j] = array[j-1];
				  j--;
			  }
			  array[j] = B;
		  }
	  }
	  public void mergeSort(int low, int high){
		  if (low < high) {
		      // Get the index of the element which is in the middle
		      int middle = low + (high - low) / 2;
		      // Sort the left side of the array
		      mergeSort(low, middle);
		      // Sort the right side of the array
		      mergeSort(middle + 1, high);
		      // Combine them both
		      merge(low, middle, high);
		    }
	  }
	  private void merge(int low, int middle, int high) {
		  	int helper[] = new int[array.length];
		    // Copy both parts into the helper array
		    for (int i = low; i <= high; i++) {
		      helper[i] = array[i];
		    }

		    int i = low;
		    int j = middle + 1;
		    int k = low;
		    // Copy the smallest values from either the left or the right side back
		    // to the original array
		    while (i <= middle && j <= high) {
		      if (helper[i] <= helper[j]) {
		        array[k] = helper[i];
		        i++;
		      } else {
		        array[k] = helper[j];
		        j++;
		      }
		      k++;
		    }
		    // Copy the rest of the left side of the array into the target array
		    while (i <= middle) {
		      array[k] = helper[i];
		      k++;
		      i++;
		    }
	  	}
	    public void quickSort(int low, int high) 
	    {
	        int i = low, j = high;
	        int temp;
	        int pivot = array[(low + high) / 2];
	        /** partition **/
	        while (i <= j) {
	            while (array[i] < pivot)
	                i++;
	            while (array[j] > pivot)
	                j--;
	            if (i <= j) {
	                /** swap **/
	                temp = array[i];
	                array[i] = array[j];
	                array[j] = temp;
	                i++;
	                j--;
	            }
	        }
	        /** recursively sort lower half **/
	        if (low < j)
	            quickSort(low, j);
	        /** recursively sort upper half **/
	        if (i < high)
	            quickSort(i, high);
	    }
	    public void hashingSort() 
	    {
	        int maxValue = array[getMaxIndex()];
	        int[] Bucket = new int[maxValue + 1];
	        for (int i = 0; i < array.length; i++)
	            Bucket[array[i]]++;
	        int outPos = 0;
	        for (int i = 0; i < Bucket.length; i++)
	            for (int j = 0; j < Bucket[i]; j++)
	                array[outPos++] = i;
	    }
		public int getMaxIndex() {
			int max = 0;
			for(int i = 1; i < array.length; i++)
				if(array[i] > array[max])
					max = i;
			return max;
		}
		public void bucketSort(List<Integer> listNum, int digits){
			//int kdigit = countDigits(array[getMaxIndex()]);
			int number;
			//List<Integer> buckets[] = new ArrayList<Integer>()[];			//array of collection not possible
			List<List<Integer>> buckets = new ArrayList<List<Integer>>(10);
			/*for(List<Integer> list : buckets){
				list = new ArrayList<Integer>();
			}*/
			for(int i=0;i<10;i++)
				buckets.add(new ArrayList<Integer>());
			for(int num : listNum){
				number = (int) ((num/(Math.pow(10,(digits-1))))%10);									//getting kdigit of every number
				buckets.get(number).add(num);
			}
			for(List<Integer> list : buckets){
				if(list.size() == 0)
					continue;
				if(list.size() == 1)
					System.out.print(list.get(0)+" ");
				else
					bucketSort(list, digits-1);
			}
		}
		public int countDigits(int number) {
			int i = 0;
			while(number != 0){
				number /= 10;
				i++;
			}
			return i;
		}
		public void execute() {
			int i;						//j,k;
			  System.out.print("\nSorting Routines\n==========\n1. Insertion Sort (AP)- Induction thru AP (adding 1)\n2. Merge Sort (GP)- Induction thru GP(tournament growth)\n3. Quick Sort (Intelligent Division (on 1) solving part of problem)\n4. Bucket Sort (Intelligent Subdivisions (in buckets) that solve part of problem (Exploiting properties of numbers))\nChoose Sorting Algo: ");
			  int code3 = sc.nextInt();
			  while(code3 != -1){
				  //resetArray(this.array);
				  array = Arrays.copyOf(this.actualArray, this.actualArray.length);
				  System.out.println("Values Before the sort:");  
				  for(i = 0; i < array.length; i++)
					  System.out.print( array[i]+"  ");
				  System.out.println();
				  switch(code3){
				  case 1:
					  insertionSort(array.length);
					  System.out.println("After Insertion Sort :");
					  break;
				  case 2:
					  mergeSort(0, array.length - 1);
					  System.out.println("After Merge Sort : ");  
					  break;
				  case 3:
					  quickSort(0,array.length - 1);
					  System.out.println("After Quick Sort : ");
					  break;
				  case 4:
					  List<Integer> list = new ArrayList<Integer>();
					  for(i=0;i<array.length;i++)
					  	list.add(array[i]);
					  bucketSort(list, countDigits(array[getMaxIndex()]));
					  break;
				  case 5:
					  hashingSort();
					  System.out.println("After Hashing : ");
					  break;
				  default:
					  break;
			  }
				  for(i = 0; i <array.length; i++)
					  System.out.print(array[i]+"  ");
				  System.out.println();
				  System.out.print("\nSorting Routines\n==========\n1. Insertion Sort (AP)- Induction thru AP (adding 1)\n2. Merge Sort (GP)- Induction thru GP(tournament growth)\n3. Quick Sort (Intelligent Division (on 1) solving part of problem)\n4. Bucket Sort (Intelligent Subdivisions (in buckets) that solve part of problem (Exploiting properties of numbers))\nChoose Sorting Algo: ");
				  code3 = sc.nextInt();
			  }
		}
}