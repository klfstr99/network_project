import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    void process() throws IOException {
        Socket socket = new Socket(); // Create and connect the socket
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

// Send first message
        dos.writeByte(1);
        dos.writeUTF("This is the first type of message.");
        dos.flush(); // Send off the data

// Send the second message
        dos.writeByte(2);
        dos.writeUTF("This is the second type of message.");
        dos.flush(); // Send off the data

// Send the third message
        dos.writeByte(3);
        dos.writeUTF("This is the third type of message (Part 1).");
        dos.writeUTF("This is the third type of message (Part 2).");
        dos.flush(); // Send off the data

// Send the exit message
        dos.writeByte(-1);
        dos.flush();

        dos.close();
    }
}
