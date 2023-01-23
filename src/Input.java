import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input extends Thread {
    Socket socket;
    BufferedReader input;
    String message;
    Communication communication;

    public Input(Socket socket, Communication communication) {
        this.socket = socket;
        this.communication = communication;
    }
    public void run() {
        try {
            while (true) {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = input.readLine();
                communication.senMessage(message);
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
