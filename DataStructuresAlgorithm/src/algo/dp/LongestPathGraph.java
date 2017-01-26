package algo.dp;
/*Longest path from s to t in a DAG (with cycles there's no point)
 *Do DFS and reach till t
 *In the process remember the distance from s.
 *backtracking from t- remember how far you are from t.
 *update maximum distance to t on backtracking from another route to t. 
 *we can update PI value (forward from s and backward from t) 
 *to remember longest route to t from a given node thru LL kinda!
 *another route is tried, update distance from s 
 * */
 
public class LongestPathGraph {

}
