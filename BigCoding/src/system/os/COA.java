package system.os;

public class COA {

	public static void main(String[] args) {

	}

}

class CPU {
	static class Microprocessor {
		String[] Microcontroller = {"Load 10= MAR=10, M(MAR)-> MBR-> AC", "..."};
	}
	public void runProgram() {
		System.out.println("The following is carried out by CU control signals");
		fetch();
		decode();
		execute();
	}

	private void fetch() {
		System.out.println("PC-> MAR (Configure Fetching)");
		System.out.println("M(MAR+BR)--RAM-->MBR (Instruction Loaded)");
		System.out.println("MBR-> IR (Configured IR for execute)");
		System.out.println("PC++ (PC ready for next instruction)");
	}

	private void decode() {
		System.out.println("Microcontroller(IR)-> control signals details from specified address");
		System.out.println("OR Decoder is used to generate streak of signals (pressing appropriate switches)");
	}

	private void execute() {
		System.out.println("Eg- Load 10- M(10)-> MBR-> AC (Control signals executed by Hardware)");
		System.out.println("Eg- ALU loaded with AC, add instruction in turn loads data into AC- ALU does its adder thing as CU directs");
	}
}