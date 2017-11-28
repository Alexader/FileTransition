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
				portNum = "13";         // default port number
			System.out.println("what is the file name you want to get:\n");
			String fileName = (br.readLine()).trim();
			while(fileName.length()==0) {
				System.out.println("you are not entering anything");
				fileName = (br.readLine()).trim();
			}
			//发送一个request个服务器
			InetAddress hostAddress = InetAddress.getByName(hostName);
			int serverPort = Integer.parseInt(portNum);
			//1000是客户端用来发送数据的端口
			ClientDatagramSocket mySocket = new ClientDatagramSocket(2450);
			mySocket.sendRequest(hostAddress, serverPort, fileName);
			//等待服务器的响应，接受文件
			//默认文件存储在E:\\saved文件夹中
			mySocket.receiveFile(hostName, portNum, fileName);
			mySocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
