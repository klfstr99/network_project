
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //socket declaration
    private static ServerSocket server;
    //any port. they should match
    private static int port = 1234;
    private static boolean trigger = true;
    volatile private static String message;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        while (trigger) {
            System.out.println("Listening mode...");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi " + message);
            ois.close();
            oos.close();
            socket.close();
            if (message.equalsIgnoreCase("//exit")) trigger = false;
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }

}
