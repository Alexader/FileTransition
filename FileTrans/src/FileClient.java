import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class FileClient {
	public static void main(String args[]) {
		InputStreamReader is = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(is);
		try {
			System.out.println("Welcome to the File client.\n" +
                    "What is the name of the server host?");
			String hostName = (br.readLine()).trim();
			if (hostName.length() == 0) // if user did not enter a name
				hostName = "localhost";  //   use the default host name
			System.out.println("What is the port number of the server host?");
			String portNum = (br.readLine()).trim();
			if (portNum.length() == 0)
				portNum = "1300";         // default port number
			System.out.println("what is the file name you want to get:\n");
			String fileName = (br.readLine()).trim();
			if(fileName.length()==0)
				fileName = "error.txt";// to inform user there is an error
			//发送一个request个服务器
			InetAddress hostAddress = InetAddress.getByName(hostName);
			int serverPort = Integer.parseInt(portNum);
			ClientDatagramSocket mySocket = new ClientDatagramSocket(1000);
			System.out.println("where do you want to store your file:\n");
			String savedPath = (br.readLine()).trim();
			mySocket.sendRequest(hostAddress, serverPort, savedPath);
			//等待服务器的响应，接受文件
			mySocket.receiveFile(hostName, portNum, fileName);
			mySocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
