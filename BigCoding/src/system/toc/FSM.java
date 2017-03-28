package system.toc;

public class FSM {	
	public static void main(String[] args) {
		int startState = 1;	//make it enum/ class
		int state;
		state = startState;
		boolean continueTraversal = true;
		while(true) {
			//TODO- read input
			switch(state) {
			case 0:
				//as per input (trigger), decide next state and action
				state = 3;
				break;
			case 1:
				state = 2;
				break;
			case 2:
				state = 0;
				break;
			case 3:
				continueTraversal = false;
				break;
			default:
				break;
			}
			if(continueTraversal == false) {
				break;
			}
			System.out.print(">> ");
		}
	}
}

/*Current- Simple Implementation
Future- https://projects.spring.io/spring-statemachine/; 
Also NuSMV to be explored.
This is DFA can make it NFA also*/