package TCPFileTransServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPClient {
	private Socket socket;
	
	public TCPClient(String host,int port) {
		try {
			this.socket = new Socket(host, port);
			this.socket.setSoTimeout(100000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				portNum = "13";        // default port number
			System.out.println("what is the file name you want to get:\n");
			String fileName = (br.readLine()).trim();
			while(fileName.length()==0) {
				System.out.println("you are not entering anything");
				fileName = (br.readLine()).trim();
			}
			//set up the connection-oriented socket 
			TCPClient client = new TCPClient(hostName, Integer.parseInt(portNum));
			//using already setup socket to send message
			client.sendMessage(fileName);
			System.out.println("send uri to server finished");
			client.receiveFile(fileName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void sendMessage(String fileName) {
		try {
			BufferedWriter bfOutputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//you have to add a '\n', otherwise bufferedReader won't stop readline() and blocking 
			bfOutputStream.write(fileName+'\n');
			bfOutputStream.flush();
			System.out.println("message send");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void receiveFile(String fileName) {
		try {
			String filePath = "e:\\saved\\"+fileName;
			FileOutputStream fileOut = new FileOutputStream(filePath);
			DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
			int len;
			byte[] buffer = new byte[2048];
			while((len=dataInputStream.read(buffer))!=-1) {
				fileOut.write(buffer,0,len);
			}
			System.out.println("receive file and stored at"+filePath);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
