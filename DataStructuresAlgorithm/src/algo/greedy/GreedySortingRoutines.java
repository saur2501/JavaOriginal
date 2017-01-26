package algo.greedy;

import java.util.Arrays;

public class GreedySortingRoutines {
	//Problem Size is reduced by selecting the first piece we are sure will be there
	int array[];
	public GreedySortingRoutines(){}
	public GreedySortingRoutines(int[] arr){
		array = arr;
	}
	public int[] selectionSort(){
		int[] workingArray = Arrays.copyOf(array, array.length);
		int N = workingArray.length;
        for(int i=0;i<N;i++)
        {
            int small=workingArray[i];
            int pos=i;
            for(int j=i+1;j<N;j++)
            {
                if(workingArray[j]<small)
                {
                    small=workingArray[j];
                    pos=j;
                }
            }
            int temp=workingArray[pos];
            workingArray[pos]=workingArray[i];
            workingArray[i]=temp;
            //System.out.println("After pass "+(i+1));
            //Printing array after pass
            //System.out.println(Arrays.toString(array));
        }
        return workingArray;
	}
	public int[] bubbleSort(){
		int[] workingArray = Arrays.copyOf(array, array.length);
		for (int i = 0; i < workingArray.length; i++) {
            for (int j = 0; j < workingArray.length-i - 1; j++) {
                if (workingArray[j] > workingArray[j + 1]) {
                    int temp = workingArray[j];
                    workingArray[j] = workingArray[j + 1];
                    workingArray[j + 1] = temp;
                }
            }
            /*System.out.println("After pass "+(i+1));
            //printing array after every pass
            System.out.println(Arrays.toString(array));*/
        }
		return workingArray;
	}
}
