import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Server {
    void process() throws IOException {
        Socket socket = new Socket("Socket", 80);// Set up receive socket
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        boolean done = false;
        while (!done) {
            byte messageType = dis.readByte();

            switch (messageType) {
                case 1: // Type A
                    System.out.println("Message A: " + dis.readUTF());
                    break;
                case 2: // Type B
                    System.out.println("Message B: " + dis.readUTF());
                    break;
                case 3: // Type C
                    System.out.println("Message C [1]: " + dis.readUTF());
                    System.out.println("Message C [2]: " + dis.readUTF());
                    break;
                default:
                    done = true;
            }
        }

        dis.close();
    }
}
