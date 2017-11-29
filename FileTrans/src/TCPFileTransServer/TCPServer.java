package TCPFileTransServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private static int MAX_LEN = 2048;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ServerSocket myServer = new ServerSocket(13);
		try {
			System.out.println("Server is ready.\n");
			while(true) {
				System.out.println("Waiting for a connection...");
				//blocking
				Socket socket = myServer.accept();
				System.out.println("connected with client");
				BufferedReader bfInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String uri = bfInput.readLine();
				System.out.println("client querying for file:"+uri
						+" and it will be sent to client soon");
				TCPServer server = new TCPServer();
				server.sendFile(socket, uri);
				//you have to close this socket, otherwise your client side will block.
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			myServer.close();
		}
	}
	private void sendFile(Socket socket,String fileName) throws IOException {
		//you can change your uri folder
		String filePath = "e:\\uri\\" + fileName;
		System.out.printf("'%s'", filePath);
		FileInputStream inputFile = new FileInputStream(filePath);
		//get the port to send bytes
		DataOutputStream outClient = new DataOutputStream(socket.getOutputStream());
		byte[] sendBuffer = new byte[MAX_LEN];
		int len;
		while((len = inputFile.read(sendBuffer))!=-1) {
			outClient.write(sendBuffer, 0, len);
		}
		System.out.println("file sending finished");
		inputFile.close();
	}

}
