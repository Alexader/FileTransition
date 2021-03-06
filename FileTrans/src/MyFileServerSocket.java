import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramPacket;

public class MyFileServerSocket extends DatagramSocket {
	static final int MAX_LEN = 1024;

	MyFileServerSocket(int portNum) throws SocketException {
		super(portNum);
	}

	public void sendFile(InetAddress clientHost,  int clientPort, String fileName) throws IOException {
		//这里不知道为什么不能使用String filePath = "e:\\" + fileName;不然会得到找不到路径
		String filePath = "e:\\uri\\" + fileName;
		System.out.printf("'%s'", filePath);
		FileInputStream inputFile = new FileInputStream(filePath);
		byte[] sendBuffer = new byte[MAX_LEN];
		int len;
		DatagramPacket fileSender = null;
		while((len = inputFile.read(sendBuffer))!=-1) {
			fileSender = new DatagramPacket(sendBuffer, len, clientHost, clientPort);
			this.send(fileSender);
		}
		System.out.println("file sending finished");
		inputFile.close();
	}

	public String receiveUri() throws IOException {
		//该方法就只用管理得到的文件路径，便于在终端中打印
		byte[] receiveBuffer = new byte[MAX_LEN];
		DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
		this.receive(datagram);
		String uri = new String(receiveBuffer);
		return uri;
	} //end receiveMessage

	public DatagramMessage receiveMessageAndSender() throws IOException {
		byte[] receiveBuffer = new byte[MAX_LEN];
		//要求接受的请求大小不超过1kb，可以通过修改MAX_LEN修改缓冲区的大小
		DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
		this.receive(datagram);
		// create a DatagramMessage object
		DatagramMessage request = new DatagramMessage();
		//数据报中会记录发送方的IP和端口，传送过来的信息就是一个文件路径
		//trim is important, otherwise you will have invalid file path exception
		String uri = (new String(receiveBuffer)).trim();
		System.out.printf("'%s'", uri);
		request.putVal(uri, datagram.getAddress(), datagram.getPort());
		return request;
	} //end receiveMessage
}
