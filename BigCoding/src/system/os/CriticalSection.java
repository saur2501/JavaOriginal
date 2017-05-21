package system.os;

import java.util.LinkedList;

public class CriticalSection {
	static LinkedList<String> crunchifyList = new LinkedList<String>();

	static boolean[] flag = new boolean[2];
	static private int turn;

	static class Process extends Thread {
		private int mypid,otherspid;
		public Process(int i) {
			this.mypid = i;
			if(i==0) {
				this.otherspid = 1;
				CriticalSection.turn = 1;
			}
			else{
				this.otherspid = 0;
				CriticalSection.turn = 0;
			}
		}

		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {}
			flag[mypid] = true;
			System.out.println(mypid + ": I plan enter CS");
            turn = otherspid;
            System.out.println(mypid + ": Let the other be first");
            while(flag[otherspid] && turn==otherspid) {System.out.println(mypid + ": wait iff other proc in turn want to enter.");}
            System.out.println(mypid + ": I am in CRITICAL SECTION");
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
            System.out.println(mypid + ": I am out and not waiting for CS");
            flag[mypid] = false;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Process(1).start();
		new Process(0).start();
		Thread.sleep(3000);
	}
}