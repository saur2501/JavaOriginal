package dsa.algo.dp;

import java.util.*;

public class KnapsackDP 
{
	Scanner sc = new Scanner(System.in);
    int max(int a, int b) 
    { 
        return (a > b)? a : b; 
    }
    int knapSack(int W, int wt[], int val[], int n)
    {
        int i, w;
        int [][]K = new int[n+1][W+1];
 
	   // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if (wt[i-1] <= w)
                    K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }
        return K[n][W];
    }
 
    public void main(String args[])
    {
        System.out.println("Enter the number of items: ");
        int n = sc.nextInt();
        System.out.println("Enter the items weights: ");
        int []wt = new int[n];
        for(int i=0; i<n; i++)
            wt[i] = sc.nextInt();
 
        System.out.println("Enter the items values: ");
        int []val = new int[n];
        for(int i=0; i<n; i++)
            val[i] = sc.nextInt();
 
        System.out.println("Enter the maximum capacity: ");
        int W = sc.nextInt();
 
        System.out.println("The maximum value that can be put in a knapsack of capacity W is: " + knapSack(W, wt, val, n));
    }
}


/*
 * 
 //Neater version made in siemens exam
import java.util.*;
public class Knapsack {
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		System.out.println("Enter numbers");
		n = scan.nextInt();
		int capacity;
		System.out.println("Enter Capacity");
		capacity = scan.nextInt();
		int cost[]= new int[n+1];
		System.out.println("Enter Costs");
		for(int i=1;i<n+1;i++)
			cost[i]=scan.nextInt();
		System.out.println("Enter Weights");
		int weight[] = new int[n+1];
		for(int i=1;i<n+1;i++)
			weight[i]=scan.nextInt();		
		int maxValue = knapsack(n,capacity,cost,weight);
		System.out.println(maxValue);
	}
	public static int knapsack(int n, int capacity, int cost[],int weight[]){
		int arr[][] = new int[n+1][capacity+1];
		int pi[][] = new int[n+1][capacity+1];
		for(int i=0;i<n+1;i++)
			arr[i][0]=0;
		for(int j=0;j<capacity+1;j++)
			arr[0][j]=0;
		for(int i = 1;i<n+1;i++){
			for(int j=1;j<capacity+1;j++){
				if(j-weight[i] >= 0){
					if(arr[i-1][j] > arr[i-1][j-weight[i]]+cost[i]){
						arr[i][j] = arr[i-1][j];
						pi[i][j] = 0;
					}
					else{
						arr[i][j] = arr[i-1][j-weight[i]]+cost[i];
						pi[i][j] = 1;
					}
				}
				else
					arr[i][j] = arr[i-1][j];
			}				
		}
		int i = n;
		int j = capacity;
		while(i!=0 && j!=0){
			if(pi[i][j] == 1){
				System.out.println("Included:"+i+"(cost:"+cost[i]+"Weight:"+weight[i]+")");
				j = j - weight[i];
				i--;
			}
			else if(pi[i][j] == 0){
				i--;
			}
		}
		
		return arr[n][capacity];
	}
	public static int max(int a, int b){
		return (a>b?a:b);
	}
}
*/