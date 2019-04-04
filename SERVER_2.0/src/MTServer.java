import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MTServer {
    public static void main(String args[]) {


        Socket s1;
        Socket s2;
        ServerSocket ss1 = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening...");
        try {
            ss1 = new ServerSocket(1234);
            ss2 = new ServerSocket(5678);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            try {
                s1 = ss1.accept();
                s2 = ss2.accept();
                System.out.println("Connection established");
                ServerThread st1 = new ServerThread(s1, s2);
                ServerThread st2 = new ServerThread(s2, s1);
                st1.start();
                st2.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }

}