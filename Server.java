import java.io.*;
import java.net.*;

public class Server
{
    public static final int PORT = 2221;
    
    public static void main(String[] args) throws IOException
    { 
        new Server().runServer();
    }
    
    public void runServer() throws IOException
    {         
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is up and waiting for connection...");
        while(true)
        {
        Socket socket = serverSocket.accept();
        new ServerThread(socket).start();    
    }
}
}