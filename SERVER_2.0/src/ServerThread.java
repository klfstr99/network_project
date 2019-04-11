import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread extends Thread { // [kf] Allocates new Thread objects

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket s1;
    Socket s2;

    public ServerThread(Socket s, Socket s2) {
        s1 = s;
        this.s2 = s2;
    }

    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            os = new PrintWriter(s2.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = is.readLine();
            while (line.compareTo("//exit") != 0) {

                os.println(line);
                os.flush();
                System.out.println("Response to Client  :  " + line);
                line = is.readLine();
            }
        } catch (IOException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s1 != null) {
                    s1.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}