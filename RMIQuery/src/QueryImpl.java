import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;

import javax.sql.DataSource;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

/**
 * This class implements the remote interface 
 *    HelloInterface.
 */

public class QueryImpl extends UnicastRemoteObject implements QueryInterface {
	static Connection connection = null;
	static PreparedStatement statement = null;
	static DataSource dSource = null;
    //远程对象方法的具体实现
	//constructor can't be left out, because RemoteException have to be thrown by constructor
    public QueryImpl() throws RemoteException {
        super();
        //dSource is used to manage connection pool
        dSource = ConnectionMysql.getMysqlSource();
    }

    public int queryStudentAge(String name) throws RemoteException {
        // something like to query a database
        try {
        	connection = dSource.getConnection();
        	System.out.print("connection database done");
        	//prepareStatement is sql injection proof
        	statement = connection.prepareStatement("select age from student where name=?");
        	statement.setString(1, name);
			ResultSet rSet = statement.executeQuery();
			rSet.next();
			int age = rSet.getInt(1);
			System.out.println("query done");
			return age;
        } catch(Exception io) {
            System.out.println("can't open database");
            return -1;
        }
    }
    public float queryStudentGrade(String name) throws RemoteException{
        //query database alike
        try {
        	connection = dSource.getConnection();
			System.out.println("connecting database done");
            statement = connection.prepareStatement("select grade from student where name=?");
            statement.setString(1, name);
            ResultSet rSet = statement.executeQuery();
            rSet.next();
            float grade = rSet.getFloat(1);
            System.out.println("query done");
            return grade;
        } catch(Exception io) {
            System.out.println("can't open database");
            return -1;
        }
    }
    public String queryTeacherSex(String name) {
    	try {
        	connection = dSource.getConnection();
			System.out.println("connecting database done");
            statement = connection.prepareStatement("select sex from teacher where name=?");
            statement.setString(1, name);
            ResultSet rSet = statement.executeQuery();
            rSet.next();
            String sex = rSet.getString(1);
            System.out.println("query done");
            return sex;
        } catch(Exception io) {
            System.out.println("can't open database");
            return "-1";
        }
    }
    public int queryTeacherAge(String name) {
    	try {
        	connection = dSource.getConnection();
			System.out.println("connecting database done");
            statement = connection.prepareStatement("select age from teacher where name=?");
            statement.setString(1, name);
            ResultSet rSet = statement.executeQuery();
            rSet.next();
            int age = rSet.getInt(1);
            System.out.println("query done");
            return age;
        } catch(Exception io) {
            System.out.println("can't open database");
            return -1;
        }
    }
} // end class