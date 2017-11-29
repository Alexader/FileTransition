
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionMysql {
	//
	public static DataSource getMysqlSource() {
		Properties properties = new Properties();
		MysqlDataSource mysqlDS = null;
		FileInputStream fileInputStream = null;
		try {
			mysqlDS = new MysqlDataSource();
			/* you have to set up your local mysql database first and fill
			* your database property.
			*/
			fileInputStream = new FileInputStream("db.property");
			properties.load(fileInputStream);
			mysqlDS.setUrl(properties.getProperty("MYSQL_DB_URL"));
			mysqlDS.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
			mysqlDS.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mysqlDS;
	}
}
