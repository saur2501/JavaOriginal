package dbms.hadoop;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

//The class name is undesirable as UDF- better FullForm
public class HiveUDF extends UDF {
	private Text result = new Text();

	public Text evaluate(Text str) {
		if (str == null) {
			return null;
		}
		String s1 = str.toString(), s2;
		if (s1.matches("M")) {
			s2 = "MALE";
		} else {
			s2 = "FEMALE";
		}
		result.set(s2);
		return result;
	}
}