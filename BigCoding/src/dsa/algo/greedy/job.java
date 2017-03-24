package dsa.algo.greedy;

public class job
{
	int profit;  		 
	int deadline; 
	int visited;				//If job is selected
	job(){
		profit=0;
		deadline=0;
		visited=0;
	}
	public job(int x,int y,int z){
		profit=x;
		deadline=y;
		visited=z;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public int getVisited() {
		return visited;
	}
	public void setVisited(int visited) {
		this.visited = visited;
	}
	
}
