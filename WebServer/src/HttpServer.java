import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer
{
   public static void main() {
	   ServerSocket serverSocket = new ServerSocket(8080);
	   try {
		   //block until client is connecting
		   Socket clientSocket =  serverSocket.accept();
		   
	   }
   }
}