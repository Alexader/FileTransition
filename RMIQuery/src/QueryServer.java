import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class QueryServer {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try {
            System.out.println("Enter the RMIregistry port number:");
            portNum = (br.readLine()).trim();
            int RMIPortNum = Integer.parseInt(portNum);
            //目录服务器和实现远程对象的服务器是同一个
            startRegistry(RMIPortNum);
            QueryImpl exportObj = new QueryImpl();
            registryURL = "rmi://localhost:" + portNum + "/query";
            //将远程调用的方法接口和服务器上的具体实现代码绑定起来
            Naming.rebind(registryURL, exportObj);
            System.out.println("Server registered.  Registry currently contains:");
            listRegistry(registryURL);
            System.out.println("Query Server ready.");
        } catch (Exception e) {
            System.out.println("Exception in QueryServer.main:" + e);
        }
    }

    // This method starts a RMI registry on the local host, if it
    // does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException {
    	Registry registry;
        try {
          registry = LocateRegistry.getRegistry(RMIPortNum);
          registry.list(); // This call will throw an exception
                           // if the registry does not already exist
        } catch (RemoteException e) {
          // No valid registry at that port.
          System.out.println("RMI registry cannot be located at port " + RMIPortNum);
          registry = LocateRegistry.createRegistry(RMIPortNum);
          System.out.println("RMI registry created at port " + RMIPortNum);
        }
      } // end startRegistry

      // This method lists the names registered with a Registry object
    private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {
        System.out.println("Registry " + registryURL + " contains: ");
        String[] names = Naming.list(registryURL);
        for (int i = 0; i < names.length; i++)
          System.out.println(names[i]);
      } //end listRegistry
}

  