package javaConcepts.self;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class Array {
public static void main(String[] args) {	//main ctrl+space
	int[] a = {1,2,3};
	int b[] = new int[5];
	int [] c = new int[]{1,2,3};
	System.out.println("nums = " + Arrays.toString(a));
	int [][]ma = new int[3][];
	ma[0] = a;
	ma[1] = new int[4];
	ma[2] = new int[]{4,5};
	int[][]ma2 = {{22,3}, {1,2,3},c,null};
	for (int[] is : ma2) {
		for (int i = 0; i < is.length; ++i) {
			System.out.println(is[i]);
		}
	}
	List<Integer> list = Arrays.asList(ArrayUtils.toObject(a));
}
}
