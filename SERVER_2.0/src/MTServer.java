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
/*        try {
            ss1 = new ServerSocket(1234); // [kf] Creates a server socket, bound to the specified port.
            ss2 = new ServerSocket(5678);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }*/

        while (true) {
            try {
                ss1 = new ServerSocket(1234); // [kf] Creates a server socket, bound to the specified port.
                ss2 = new ServerSocket(5678);
                s1 = ss1.accept(); // [kf] Listens for a connection to be made to this socket and accepts it.
                s2 = ss2.accept();
                System.out.println("Connection established");
                ServerThread st1 = new ServerThread(s1, s2); // [kf] see custom class in this package
                ServerThread st2 = new ServerThread(s2, s1);
                st1.start();
                st2.start();
            } catch (Exception e) {
                    if (ss1 != null && !ss1.isClosed()) {
                        try {
                            ss1.close();
                            System.out.println("ss1 connection closed");
                        } catch (IOException f) {
                            f.printStackTrace(System.err);
                        }
                    } // end if
                        
                        if (ss2 != null && !ss2.isClosed()) {
                            try {
                                ss2.close();
                                System.out.println("ss2 connection closed");
                            } catch (IOException f) {
                                f.printStackTrace(System.err);
                            }
                    } // end if
                }

        } // end while
    } // end main
} // end class