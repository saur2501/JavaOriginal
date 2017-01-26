package algo.dac;

public class InversionPairs {
	private int[] arr;
	public InversionPairs(int[] arr){
		this.arr = arr;
	}
	public int inversionPair(int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			int i1 = inversionPair(low, mid);
			int i2 = inversionPair(mid+1,high);
			int i3 = countMerge(low,mid,high);
			return i1 + i2 + i3;
		}
		return 0;
	}
	public int countMerge(int low, int mid, int high){
		int i = low, j = mid+1, k = 0;
		int count = 0;
		int result[] = new int[high-low+1];
		while(i <= mid && j <= high){
			if(arr[i] < arr[j])
				result[k++] = arr[i++];
			else{
				result[k++] = arr[j++];
				count += (j-i-1);
			}
		}
		while(i<=mid)
			result[k++] = arr[i++];
		while(j<=high)
			result[k++] = arr[j++];
		return count;
	}
}
