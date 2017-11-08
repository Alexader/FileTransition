import java.rmi.*;
import java.io.*;

public class QueryClient {
    public static void main(String args[]) {
        try {
        	int RMIPort;    
            String hostName;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            hostName = (br.readLine()).trim();
            System.out.println("Enter the RMIregistry port number:");
            String portNum = (br.readLine()).trim();
            RMIPort = Integer.parseInt(portNum);
            String registryURL = 
               "rmi://" + hostName+ ":" + portNum + "/hello";
            //去注册中心查找需要调用的对象
            // find the remote object and cast it to an interface object
            QueryInterface h =
              (QueryInterface)Naming.lookup(registryURL);
            System.out.println("Lookup completed " );
            // invoke the remote method
            float grade = h.queryGrade("Jame");//远程方法调用
            System.out.println("HelloClient: " + grade);
         } // end try 
         catch (Exception e) {
            System.out.println("Exception in HelloClient: " + e);
         }
    }
}