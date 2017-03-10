package javaConcepts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandomStuff {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hi There!!");
		generateValuesForFieldName("Hi", 10);
	}
	private static final Random rand = new Random();
	public static Object generateValuesForFieldName(String fieldname, Integer fieldtype) throws Exception {
		String dType = "DATETIME";

		switch (dType) {

		case "DATETIME":
			System.out.println("Now Epoch = " + getCurrentDateTimeOffsetInLong());

		case "UUID":
			System.out.println("Random UUID = " + getUUID());

		case "BOOLEAN":
			System.out.println("Random Boolean = " + generateRandomBooleanValue());

		case "TINYINT":
			System.out.println("Random TinyInt = " + generateRandomTinyIntValue());

		case "SMALLINT":
			System.out.println("Random SmallInt = " + generateRandomSmallIntValue());

		case "INT":
			System.out.println("Random Int = " + generateRandomIntValue());

		case "BIGINT":
			System.out.println("Random BigInt = " + generateRandomBigIntValue(Long.MAX_VALUE));
			
		case "NULLINT":
			System.out.println("Random NullInt = " + generateNullIntValue());
			
		case "FLOAT":
			System.out.println("Random FloatValue = " + generateRandomFloatValue());
			
		case "DOUBLE":
			System.out.println("Random Double = " + generateRandomDoubleValue());
			
		case "STRING":
			System.out.println("Random Varchar = " + generateVarcharData());
			
		case "DATE":
			System.out.println("Random Date = " + generateCurrentDateInLong());
			
		case "BYTES":
			System.out.println("Random Bytes = " + generateRandomBytes());
			
		case "INETADDRESS":
			System.out.println("Random InetAddress = " + generateInetAddress());

		default:
			try {
				throw new Exception("Unknown field type for fieldname\"" + fieldname + "\"");
			} catch (Exception e) {
				System.exit(0);
			}
			break;
		}
		return null;
	}

	private static String generateInetAddress() {
		Random r = new Random();
		return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
	}

	public long generateReversedTimeStamp() {
		Date date = new Date();
		Long offset = generateRandomBigIntValue(999999L);
		long lDate = date.getTime() + offset;
		return (Long.MAX_VALUE - lDate);
	}

	public static Long generateRandomBigIntValue(long range) {
		Long rndLong = nextLong(range);
		return rndLong;
	}
	
	public static long nextLong(long range) {
		 // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = (rand.nextLong() << 1) >>> 1;
		      val = bits % range;
		   } while (bits-val+(range-1) < 0L);
		   return val;
	}
	
	public static String getCurrentDateTimeOffsetInLong() {
		System.out.println("Drill!");
		Date date = new Date();
		Long offset = generateRandomBigIntValue(999999L);
		Long lDate = date.getTime() + offset;

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getDefault());

		return lDate.toString();
	}

	public static UUID getUUID() {
		System.out.println("Chill!");
		return UUID.randomUUID();
	}

	public static boolean generateRandomBooleanValue() {
		boolean rndBool = rand.nextBoolean();
		return rndBool;
	}

	public static short generateRandomTinyIntValue() {
		short rndSmallInt = (short) rand.nextInt(127);
		return rndSmallInt;
	}

	public static short generateRandomSmallIntValue() {
		short rndSmallInt = (short) rand.nextInt(Short.MAX_VALUE + 1);
		return rndSmallInt;
	}

	public static int generateRandomIntValue() {
		int rndInt = rand.nextInt(Integer.MAX_VALUE);
		int minVal = 0;
		return rndInt - minVal;
	}

	public static short generateNullIntValue() {
		return 0;
	}

	private static Float generateRandomFloatValue() {
		float rndFloat = rand.nextFloat();
		byte b1 = (byte) rand.nextInt(Byte.MAX_VALUE + 1);
		byte b2 = 0;
		return rndFloat + b1 - b2;
	}

	public static Double generateRandomDoubleValue() {
		double rndDouble = rand.nextDouble();
		byte b1 = (byte) rand.nextInt(Byte.MAX_VALUE + 1);
		byte b2 = 0;
		return rndDouble + b1 - b2;
	}

	public static String generateVarcharData() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static String generateCurrentDateInLong() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			throw e;
		}
		Long date = dateWithoutTime.getTime();
		return date.toString();
	}

	private static byte[] generateRandomBytes() {
		byte[] b = new byte[60];
		new Random().nextBytes(b);
		return b;
	}

}
