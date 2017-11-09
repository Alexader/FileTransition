
public class FiletransServer {
	public static void main(String[] args) {
	    int serverPort = 13;    // default port
	    if (args.length == 1)
	    	serverPort = Integer.parseInt(args[0]);
	    try {
			 //to get socket 
	    	 MyFileServerSocket serverSocket = new MyFileServerSocket(serverPort);
	    	 DatagramMessage request;
			 System.out.println("server is ready");
			 System.out.println("waiting for a connection");
	    	 while(true) {
				 request = serverSocket.receiveMessageAndSender();
				 System.out.println("Request received");
				 if(request.getUri()==null)
					 System.out.println("you have not choose a file");
				 if(request.getUri()=="q") break;
	    		 System.out.println("a client is connected\n file "+request.getUri()+"will be send to client");
				 serverSocket.sendFile(request.getAddress(), request.getPort(), request.getUri());
	    	 }
	    	 System.out.println("server exit");
	    	 serverSocket.close();
	    } // end try
		catch (Exception ex) {
	       System.out.println("There is a problem: " + ex);
		} // end catch
	  } //end main
}
