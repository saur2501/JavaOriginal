package javaConcepts.self;

public class GenericsUsage {
	   // generic method printArray

	   public static void main(String args[]) {
	      // Create arrays of Integer, Double and Character
	      Integer[] intArray = { 1, 2, 3, 4, 5 };
	      Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
	      Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

	      //generic methods
	      System.out.println("Array integerArray contains:");
	      MaximumTest.printArray(intArray);   // pass an Integer array

	      System.out.println("\nArray doubleArray contains:");
	      MaximumTest.printArray(doubleArray);   // pass a Double array

	      System.out.println("\nArray characterArray contains:");
	      MaximumTest.printArray(charArray);   // pass a Character array
	      
	      //bounded generic methods
	      System.out.printf("Max of %d, %d and %d is %d\n\n", 
	         3, 4, 5, MaximumTest.maximum( 3, 4, 5 ));

	      System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
	         6.6, 8.8, 7.7, MaximumTest.maximum( 6.6, 8.8, 7.7 ));

	      System.out.printf("Max of %s, %s and %s is %s\n","pear",
	         "apple", "orange", MaximumTest.maximum("pear", "apple", "orange"));
	      
	      //class generic
	      Box<Integer> integerBox = new Box<Integer>();
	      Box<String> stringBox = new Box<String>();
	    
	      integerBox.add(new Integer(10));
	      stringBox.add(new String("Hello World"));

	      System.out.printf("Integer Value :%d\n\n", integerBox.get());
	      System.out.printf("String Value :%s\n", stringBox.get());
   }
}

class MaximumTest {
   // determines the largest of three Comparable objects
	public static < E > void printArray( E[] inputArray ) {
      // Display array elements
      for(E element : inputArray) {
         System.out.printf("%s ", element);
      }
      System.out.println();
   }
	
   public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
      T max = x;   // assume x is initially the largest
      
      if(y.compareTo(max) > 0) {
         max = y;   // y is the largest so far
      }
      
      if(z.compareTo(max) > 0) {
         max = z;   // z is the largest now                 
      }
      return max;   // returns the largest object   
   }
}

class Box<T> {
   private T t;

   public void add(T t) {
      this.t = t;
   }

   public T get() {
      return t;
   }
}