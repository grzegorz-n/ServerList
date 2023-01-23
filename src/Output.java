import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Output extends Thread{
    Socket socket;
    PrintStream output;

    public Output(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            output = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        output.println(message);
        output.flush();
    }

}
