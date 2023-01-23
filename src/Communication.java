import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Communication {
    private User user;
    private ArrayList<Communication> list;
    private Socket socket;
    private Input input;
    private Output output;

    public Communication(User user, Socket socket, ArrayList<Communication> list) {
        this.user = user;
        this.input = new Input(socket, this);
        this.input.start();
        this.output = new Output(socket);
        this.output.start();
        this.list = list;
        senMessage(user.getName() + " dołączył do czatu");
    }


    public User getUser() {
        return user;
    }
    public void senMessage(String message) {
        for (int i = 0; i < list.size(); i++) {
            if (message.equals("EXIT")) {
                output.sendMessage(message);
                Main.close(this.user.getName(), list);
            }
            if (!list.get(i).getUser().equals(this.user)) {
                message = "[" + this.user.getName() + "] " + message;
                list.get(i).getOutput().sendMessage(message);
            }
        }
    }

    public Output getOutput() {
        return output;
    }

}
