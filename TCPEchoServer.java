import java.net.*; // for Socket, ServerSocket, and InetAddress
import java.io.*; // for IOException and Input/OutputStream

public class TCPEchoServer {

    private static final int BUFSIZE = 32; // Size of receive buffer
    
    public static void main(String[] args) throws IOException {

        if (args.length != 1) // Test for correct number of args
         throw new IllegalArgumentException("Parameter(s): <Port>");

        int serverPort = Integer.parseInt(args[0]);

        // Create a server socket to accept client connection requests
        ServerSocket serverSocket = new ServerSocket(serverPort);

        int recvMsgSize; // Size of received message
        byte[] recvBuf = new byte[BUFSIZE]; // Receive buffer

        while (true) { // Run forever, accepting and servicing connections
            Socket clientSocket = serverSocket.accept(); // Get client connection
            SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
            System.out.println("Handling client at " + clientAddress);

            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            // Receive until client closes connection, indicated by -1 return
            while ((recvMsgSize = in.read(recvBuf)) != -1)
                out.write(recvBuf, 0, recvMsgSize);
            
            clientSocket.close(); // Close the socket. We are done with this client.
        }
    }
}
