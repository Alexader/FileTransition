import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface 
 *    HelloInterface.
 * @author M. L. Liu
 */

public class QueryImpl extends UnicastRemoteObject implements QueryInterface {
    //远程对象方法的具体实现
    public QueryImpl() throws RemoteException {
        super();
    }

    public int queryAge(String name) throws RemoteException {
        // something like to query a database
        String age;
        BufferedReader bf = null;
        try {
            FileReader fileReader = new FileReader("E:\\index.txt");
            bf = new BufferedReader(fileReader);
            if(bf.readLine()!=null) {
                age = bf.readLine();
                return Integer.parseInt(age);
            }
            else return -1;
        } catch(IOException io) {
            System.out.println("can't open file or read file");
        } finally {
            bf.close();
        }
    }
    public float queryGrade(String name) throws RemoteException{
        //query database alike
        try {
            String age;
            FileReader fileReader = new FileReader("E:\\index.txt");
            BufferedReader bf = new BufferedReader(fileReader);
            if(bf.readLine()!=null) {
                age = bf.readLine();
                return Integer.parseInt(age);
            }
            else return -1;
            
        } catch(IOException io) {
            System.out.println("can't open file or read file");
            return -1;
        }
    }
} // end class