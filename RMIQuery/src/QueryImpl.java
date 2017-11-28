import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface 
 *    HelloInterface.
 */

public class QueryImpl extends UnicastRemoteObject implements QueryInterface {
    //远程对象方法的具体实现
	//constructor can't be left out, because RemoteException have to be thrown by constructor
    public QueryImpl() throws RemoteException {
        super();
    }

    public int queryAge(String name) throws RemoteException {
        // something like to query a database
        String age;
        BufferedReader bf = null;
        try {
        	// you have to got a index.txt record with a number in it in your computer
            FileReader fileReader = new FileReader("E:\\uri\\index.txt");
            bf = new BufferedReader(fileReader);
            if((age=bf.readLine())!=null) {
                bf.close();
                return Integer.parseInt(age);
            }
            else {
            	bf.close();
            	return -1;
            }  
        } catch(IOException io) {
            System.out.println("can't open file or read file");
            return -1;
        }
    }
    public float queryGrade(String name) throws RemoteException{
        //query database alike
    	BufferedReader bf;
    	String grade;
        try {
            FileReader fileReader = new FileReader("E:\\uri\\index.txt");
            bf = new BufferedReader(fileReader);
            if((grade=bf.readLine())!=null) {
                return Float.parseFloat(grade);
            }
            else return -1;
            
        } catch(IOException io) {
            System.out.println("can't open file or read file");
            return -1;
        }
    }
} // end class