import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String args[]) throws IOException {

        // declaration block
        InetAddress address = InetAddress.getLocalHost();// gets localhost
        Socket s1 = null;
        String line;// message
        BufferedReader br = null;
        BufferedReader is = null;// inputstream
        PrintWriter os = null;// outputstream
        String response;

        try {//instead of "trow"
            s1 = new Socket(address, 1234);
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            os = new PrintWriter(s1.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : " + address);
        System.out.println("Enter Data to echo Server ( Enter \"//exit\" to end):");

        try {
            line = br.readLine();// reads whole line
            while (line.compareTo("//exit") != 0) {// option: compareIgnoreCase()
                os.println(line);// writes to output
                os.flush();// resets output
                response = is.readLine();
                System.out.println("Server Response : " + response);// just a display for user
                line = br.readLine();

            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read Error");
        } finally {

            // redundant. works fine without it
            is.close();
            os.close();
            br.close();
            s1.close();
            System.out.println("Connection Closed");

        }

    }
}