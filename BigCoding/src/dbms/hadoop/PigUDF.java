package dbms.hadoop;

import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.WrappedIOException;

//The class name is undesirable as UDF- better UPPER
public class PigUDF extends EvalFunc<String> {
	public String exec(Tuple input) throws IOException {	//whenever call UPPER- exec will be executed- only tuple at a time.
		if(input == null || input.size() == 0)
			return null;
		try {
			String str = (String) input.get(0);
			return str.toUpperCase();
		} catch(Exception e) {
			throw WrappedIOException.wrap("Caught exception processing input row ", e);
		}
	}
}