package algo.greedy;
/*This 1 is as follows-
 * Greedy Solution- find the largest profit giving job- give it a slot (for unit sized all of them)
 * Other probs are as follows:-
Find next largest, give it a slot- if filled, iteratively search to the left and go on.
if not unit sized, then find largest profit/time investment ratio and allot in the sequence- assuming that partially finished jobs give proportional rewards.
if reward for only finished ones- 2 cases- fixed jobs duration (start and end time)
	make a graph of racing condition between all jobs- use Dynamic programming.
	Or take converse of the conflict graph and then find the longest path from s to t
Maximum #jobs of a range of time.
Maximize coverage duration of jobs.

*/
//import java.util.*;

public class JobSequencing
{
	static int n; 
	public static int out(job jb[],int x)			//returns index of job which offers x profit
	{
		for(int i=0;i<n;++i)
			if(jb[i].profit==x)
				return i;
		return 0;
	}
	public void scheduling(){
		ActivitySelection as = new ActivitySelection();
		as.execute();
	}
	public void schedulingOnDeadline() {
		
	}
}
class ActivitySelection
{
    /* 
      Prints a maximum set of activities that can be done by a single
      person, one at a time.
      n   -->  Total number of activities
      s[] -->  An array that contains start time of all activities
      f[] -->  An array that contains finish time of all activities
    */
    public void printMaxActivities(int s[], int f[], int n)
    {
    int i, j;
    System.out.print("Following activities are selected : \n");
    												// The first activity always gets selected
    i = 0;
    System.out.print(i+" ");
    												// Consider rest of the activities
    /* 
    If this activity has start time greater than or
    equal to the finish time of previously selected
    activity, then select it
    */
    for (j = 1; j < n; j++)
    {
	     if (s[j] >= f[i])
	     {
	          System.out.print(j+" ");
	          i = j;
	     }
     }
    }
     
													// driver program to test above function
    public void execute()
    {
    int s[] =  {1, 3, 0, 5, 8, 5};
    int f[] =  {2, 4, 6, 7, 9, 9};
    int n = s.length;
        
    printMaxActivities(s, f, n);
    }
}