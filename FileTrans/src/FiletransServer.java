
public class FiletransServer {
	public static void main(String[] args) {
	    int serverPort = 13;    // default port
	    if (args.length == 1 )
	    	serverPort = Integer.parseInt(args[0]);
	    try {
			 //to get socket 
	    	 MyFileServerSocket serverSocket = new MyFileServerSocket(serverPort);
			 System.out.println("server is ready");
			 System.out.println("waiting for a connection");
	    	 while(true) {
				 DatagramMessage request = serverSocket.receiveMessageAndSender();
				 System.out.println("Request received");			 
	    		 System.out.println("a client is connected\n file"+request.getUri()+"will be send to client");
				 serverSocket.sendFile(request.getAddress(), request.getPort(), request.getUri());
				 serverSocket.close();
	    	 }
	    } // end try
		catch (Exception ex) {
	       System.out.println("There is a problem: " + ex);
		} // end catch
	  } //end main
}
