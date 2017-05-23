package uncat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SwingingDoorDeadband {

	public static void main(String[] args) throws InterruptedException {
		Map<Integer, Integer> inputMap = new HashMap<>();
		Map<Integer, Integer> outputMap = new HashMap<>();
		Random random = new Random();
		int percentagePrecision = 5;
		int deviationPermitted = (1000-0)*percentagePrecision/100;
		int tMin = 3;
		int tMax = 5;
		double permittedSlopeTop = Double.MAX_VALUE;
		double permittedSlopeBottom = -999999;
		
		inputMap.put(0, random.nextInt(1000));
		outputMap.put(0, inputMap.get(0));
		int k = 1;
		long iterationsFromLastWrite = 0;
		System.out.println("currentReadValue\tlastWrittenValue\tslope\tpermittedSlopeTop\tpermittedSlopeBottom\tisPointWritable\tisPointWritten");
		for(int i=1;i<10000;i++) {
			inputMap.put(i, inputMap.get(i-1) + 25 - random.nextInt(50));
			iterationsFromLastWrite++;
			
			int lastWrittenValue = outputMap.get(k-1);
			int currentReadValue = inputMap.get(i);
			int valueChange = currentReadValue - lastWrittenValue;
			double slope = (double) valueChange / iterationsFromLastWrite;
			
			boolean isPointWritable = slope > permittedSlopeTop || slope < permittedSlopeBottom;
			boolean doesIterationsCrossLowerLimit = iterationsFromLastWrite > tMin;
			boolean doesIterationsCrossUpperLimit = iterationsFromLastWrite > tMax;
			
			System.out.print(currentReadValue + "\t" + lastWrittenValue + "\t" + slope + "\t" + permittedSlopeTop + "\t" + permittedSlopeBottom + "\t" + isPointWritable + "\t");
			
			if((isPointWritable && doesIterationsCrossLowerLimit) || doesIterationsCrossUpperLimit){
				outputMap.put(k++, inputMap.get(i-1));
				permittedSlopeTop = (double)(currentReadValue + deviationPermitted - inputMap.get(i-1)) / 1;
				permittedSlopeBottom = (double)(currentReadValue - deviationPermitted - inputMap.get(i-1)) / 1;
				iterationsFromLastWrite = 1;
				System.out.println("true");	//previous value is written
			} else {
				//update slope qualifier
				double slopeTop = (double)(valueChange + deviationPermitted) / iterationsFromLastWrite;
				double slopeBottom = (double) (valueChange - deviationPermitted) / iterationsFromLastWrite;
				permittedSlopeTop = minAbsolute(slopeTop, permittedSlopeTop);
				permittedSlopeBottom = minAbsolute(slopeBottom, permittedSlopeBottom);
				System.out.println("false");
			}
		}
		System.out.println(k);
	}

	private static double minAbsolute(double a, double b) {
		return (Math.abs(a) > Math.abs(b))? b : a;
	}

}
class Value {

	long timestamp;
	int value;
	public Value(long timestamp, int value) {
		this.timestamp = timestamp;
		this.value = value;
	}
	public Value(Value value) {
		this.timestamp = value.timestamp;
		this.value = value.value;
	}
	
}