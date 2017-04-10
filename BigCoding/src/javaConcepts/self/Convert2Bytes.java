package javaConcepts.self;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.google.common.primitives.Longs;

public class Convert2Bytes {

	public static void main(String[] args) {
		convert2Bytes();
	}

	private static byte[] convert2Bytes() {
		String dataType = "LONG";
		Object fieldDataValue = 12312434L;
		byte[] bytes = new byte[10];
		ByteBuffer byteBuffer;
		switch(dataType) {
		case "DATE":
			return Longs.toByteArray((long) fieldDataValue);	
			//Longs.fromByteArray(numberByte)
		case "DATETIME":
			return Longs.toByteArray((long) fieldDataValue);
		case "DOUBLE":
		    ByteBuffer.wrap(bytes).putDouble((double) fieldDataValue);
		    return bytes;
		    //return ByteBuffer.wrap(bytes).getDouble();
		case "BOOLEAN":
			if((boolean) fieldDataValue) 
				bytes[0] = 1;
			else bytes[0] = 0;
			return bytes;
		case "TINYINT":
			byteBuffer = ByteBuffer.allocate(Byte.BYTES);
			byteBuffer.putInt((byte) fieldDataValue); 
		    return byteBuffer.array();
		case "SMALLINT":
			byteBuffer = ByteBuffer.allocate(Short.BYTES);
			byteBuffer.putInt((short) fieldDataValue); 
		    return byteBuffer.array();
		case "INT":
			byteBuffer = ByteBuffer.allocate(4); 
			byteBuffer.putInt((int) fieldDataValue); 
		    return byteBuffer.array();
		    //Ints.toByteArray(0xAABBCCDD);
		case "BIGINT":
			byteBuffer = ByteBuffer.allocate(Long.BYTES);
			byteBuffer.putLong((long) fieldDataValue);
		    return byteBuffer.array();
		    /*ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		    buffer.put(bytes);
		    buffer.flip();//need flip 
		    return buffer.getLong();*/
		    
		case "FLOAT":
			byteBuffer = ByteBuffer.allocate(Float.BYTES);
			byteBuffer.putFloat((float) fieldDataValue);
			return byteBuffer.array();
		case "STRING":
			return ((String) fieldDataValue).getBytes(Charset.forName("UTF-8"));
		default:
			return fieldDataValue.toString().getBytes(Charset.forName("UTF-8"));
			//using dataoutputstream
			//Bytes.readAsInt(arg0, arg1, arg2);
		}
	}

}
