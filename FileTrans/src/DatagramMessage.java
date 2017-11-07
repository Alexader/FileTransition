import java.net.*;

public class DatagramMessage{
   private String uri;
   private InetAddress senderAddress;
   private int senderPort;
   public void putVal(String uri, InetAddress addr, int port) {
      this.uri = uri;
      this.senderAddress = addr;
      this.senderPort = port;
   }
   public String getUri() {
      return this.uri;
   }
   public InetAddress getAddress( ) {
      return this.senderAddress;
   }
   public int getPort( ) {
      return this.senderPort;
   }
} // end class