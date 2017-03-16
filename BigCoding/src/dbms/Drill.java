package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class Drill {
	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.drill.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:drill:drillbit=192.168.237.131:31010");
		stmt = con.createStatement();
		stmt.executeQuery("use hive");
		stmt.executeQuery("use tpcds");
		String query = prepareQuery("select count(*) from sales");
		stmt.executeQuery(query);
	}
	private static String prepareQuery(String string) throws Exception {
		//making query in line
		System.out.println("Received query for preparation is " + string);
		string = string.replaceAll("[\n\r]", " ");
		string = string.replaceAll("\t", " ");
		string = string.replaceAll("( )+"," ");
		string = string.trim();
		System.out.println("Inline Query is = " + string);
		
		//Fetch all DB names
		ResultSet rs;
		List<String> db = new ArrayList<>();
		try {
			rs = stmt.executeQuery("show databases");
			while(rs.next()) {
				db.add(rs.getString("SCHEMA_NAME"));
			}
		} catch (Exception e) {
			throw new Exception("Exception while running query for Drill" , e);
		}
		System.out.println("Databases inside are = " + Arrays.toString(db.toArray()));
		
		//replace dbname.tablename in query with hive.dbname.tablename 
		for(String dbName : db) {
			System.out.println("Considering " + dbName);
			if(dbName.startsWith("hive."))
				dbName = dbName.replaceAll("hive\\.", "");
			System.out.println("(Update) Considering " + dbName);
			String regex = "([\\,\\s\\.\\(])(" + dbName + "|" + dbName.toLowerCase() + ")\\.([\\w\\d_]+)";
			string = string.replaceAll(regex, "$1hive.$2.`$3`");
			System.out.println("Aftering Considering " + dbName + " String is " + string);
		}
		System.out.println("Query after hive prepending = " + string);
		
		//replace drill keywords (which are not in hive- and so are permitted as aliases) with quotes
		Properties prop = new Properties();
		prop.setProperty("DrillButHiveKeywords", "CURRENT_DEFAULT_TRANSFORM_GROUP,DETERMINISTIC,SIMILAR,LARGE,NATURAL,REGR_SLOPE,TINYINT,OCTET_LENGTH,ARE,MODULE,CORR,LOWER,CUME_DIST,NCLOB,CEILING,CASCADED,REGR_R2,BEGIN,INTERSECTION,RELEASE,ATOMIC,ABS,EXCEPT,VALUE,DEREF,AT,EVERY,REAL,LOCALTIMESTAMP,REPLACE,REF,REGR_COUNT,BLOB,EXPLAIN,ALLOCATE,MINUTE,ASENSITIVE,CHARACTER,DYNAMIC,COVAR_POP,COLLATE,SQLSTATE,CURRENT_ROLE,MODIFIES,END_EXEC,LANGUAGE,MULTISET,CHAR_LENGTH,SUBMULTISET,TIMEZONE_HOUR,POSITION,RESULT,REFERENCING,SQLWARNING,FILES,TRANSLATION,CHECK,CURRENT_PATH,MEMBER,SQRT,PARAMETER,SCROLL,REGR_INTERCEPT,TREAT,MONTH,CURRENT_USER,LAST_VALUE,SECOND,BIT,DISALLOW,CEIL,MOD,NATIONAL,CORRESPONDING,CYCLE,UNNEST,CURRENT_TRANSFORM_GROUP_FOR_TYPE,CONVERT,FIRST_VALUE,SAVEPOINT,REGR_AVGY,CURRENT_SCHEMA,REGR_AVGX,SHOW,SUBSTRING,SOME,PERCENTILE_CONT,MERGE,COALESCE,DEFAULT,LOCALTIME,TIME,TRANSLATE,UPPER,INOUT,CONNECT,GET,INDICATOR,PERCENTILE_DISC,METHOD,OFFSET,VARYING,FUSION,PREPARE,RECURSIVE,CONDITION,SPECIFICTYPE,LEADING,SYSTEM,HOLD,VAR_SAMP,WITHOUT,SPECIFIC,USE,CALLED,SESSION_USER,EXEC,DAY,TABLES,SENSITIVE,SCOPE,ALLOW,ASYMMETRIC,COLLECT,COVAR_SAMP,DENSE_RANK,ELEMENT,RETURNS,FREE,NEW,INSENSITIVE,SYSTEM_USER,MATCH,VARBINARY,NUMERIC,TRAILING,EXECUTE,GLOBAL,NCHAR,SQL,OPEN,SCHEMAS,WHENEVER,ROW_NUMBER,ESCAPE,CALL,SEARCH,DEFAULT_KW,DISCONNECT,PERCENT_RANK,LN,ANY,RANK,STATIC,CARDINALITY,DATABASES,TIMEZONE_MINUTE,TRIM,NORMALIZE,UNIQUE,YEAR,NULLIF,VAR_POP,EACH,IDENTITY,CURRENT_TIME,REGR_SXX,REGR_SXY,SYMMETRIC,DEC,EXP,JAR,CLOSE,STDDEV_POP,WIDTH_BUCKET,HOUR,NO,POWER,WITHIN,OVERLAPS,CHARACTER_LENGTH,DEALLOCATE,OLD,SQLEXCEPTION,CURRENT_CATALOG,UESCAPE,DECLARE,OVERLAY,RETURN,FILTER,UNKNOWN,CLOB,STDDEV_SAMP,MIN,MAX,AVG,SUM");
		String[] drillButHiveKeywords = prop.getProperty("DrillButHiveKeywords").split("\\,");
		HashSet<String> hs = new HashSet<>(Arrays.asList(drillButHiveKeywords));
		for(String s : hs){
			String regex = "([\\,\\s\\.\\(])(" + s + "|" + s.toLowerCase() + ")\\.([\\,\\s\\.\\)])";
			string = string.replaceAll(regex, "$1`$2`$3");
		}
		System.out.println("Query after enquoting unique drill keywords = " + string);
		
		//Replace aliases that start with digits with the ones in quotes
		string = string.replaceAll("(AS|as|As|aS)(\\s)+(\\d+)([\\w\\d_]+)", "$1 `$3$4`");								////string = string.replaceAll("([\\s\\,\\.]+)(\\d+)([_a-zA-Z][_a-zA-Z0-9]+)", "$1`$2$3`");
		System.out.println("Query after enquoting numeral aliases " + string);
		System.out.println("Prepared Query is = " + string);
		return string;
	}
}
