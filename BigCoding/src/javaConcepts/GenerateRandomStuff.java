package javaConcepts;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandomStuff {

	public String toString() {
		return "RecordRandom";
	}

	public Object generateValuesForFieldName(String fieldName, Integer fieldType) throws Exception {

		String dType = "DATETIME";
		Random random = new Random();
		
		switch (dType) {

		case "DATETIME":
			Date date = new Date();
			Long offset = nextLong(999999L);
			Long lDate = date.getTime() - offset;

			return lDate.toString();

		case "UUID":
			return UUID.randomUUID();

		case "BOOLEAN":
			return random.nextBoolean();

		case "TINYINT":
			return (byte) random.nextInt(Byte.MAX_VALUE);

		case "SMALLINT":
			short rndSmallInt = (short) random.nextInt(Short.MAX_VALUE);
			return rndSmallInt;

		case "INT":
			int rndInt = random.nextInt(Integer.MAX_VALUE);
			int minVal = 0;
			return rndInt - minVal;
			
		case "FLOATNORMAL":
			float mean = 25; float sd = 2;
			float resultFloat = (float) (mean + (new Random().nextGaussian()) * sd);
			BigDecimal bdf = new BigDecimal(resultFloat);
			bdf = bdf.setScale(1, BigDecimal.ROUND_HALF_EVEN);
			return bdf.floatValue();

		case "BIGINT":
			Long rndLong = nextLong(999999L);
			return rndLong;

		case "NULLINT":
			return 0;

		case "FLOAT":
			return (byte) random.nextInt(Byte.MAX_VALUE + 1) + random.nextFloat();

		case "DOUBLE":
			return (byte) random.nextInt(Byte.MAX_VALUE + 1) + random.nextDouble();

		case "STRING":
			return RandomStringUtils.randomAlphanumeric(10);
		
		case "RANDOMSELECTSTRING":
			String[] valuesArray = {"Hi","There","Godji","Restful"};
			List<String> values = Arrays.asList(valuesArray);
			String stringAtRandomIndex = values.get(new Random().nextInt(values.size()));
			return stringAtRandomIndex;

		case "DATE":
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			/*try {
				dateWithoutTime = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				throw e;
			}
			Long date = dateWithoutTime.getTime();
				
			return dateWithoutTime.toString();
			return sdf.format(new Date());*/	
			date = new Date();
			offset = nextLong(99999999999L);
			lDate = date.getTime() - offset;
			return sdf.format(new Date(lDate));

		case "BYTES":
			byte[] b = new byte[60];
			random.nextBytes(b);
			return b;
			
		case "INETADDRESS":
			return random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
		case "":
			

		default:
			try {
				throw new Exception("Unknown field type for fieldname\"" + fieldName + "\"");
			} catch (Exception e) {
				System.exit(0);
			}
		}
		return null;
	}

	public long generateReversedTimeStamp() {
		Date date = new Date();
		Long offset = nextLong(999999L);
		long lDate = date.getTime() + offset;
		return (Long.MAX_VALUE - lDate);
	}
	
	public static long nextLong(long range) {
		 // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = ((new Random()).nextLong() << 1) >>> 1;
		      val = bits % range;
		   } while (bits-val+(range-1) < 0L);
		   return val;
	}
}
