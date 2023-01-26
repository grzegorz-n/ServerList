import java.net.Socket;
import java.util.ArrayList;

public class Communication {
    private User user;
    private ArrayList<Communication> list;
    private Input input;
    private Output output;

    public Communication(User user, Socket socket, ArrayList<Communication> list) {
        this.user = user;
        this.input = new Input(socket, this);
        this.input.start();
        this.output = new Output(socket);
        this.output.start();
        this.list = list;
        senMessage("[7] [Wszyscy] " + user.getName() + " dołączył do czatu");
    }


    public User getUser() {
        return user;
    }
    public void senMessage(String message) {
        if (message.equals("EXIT")) {
            output.sendMessage("[7] [Wszyscy]" + message);
            Main.close(this.user.getName(), list);
            sendList();
        } else {
            String name = message.substring(5, Integer.parseInt(message.substring(1,2)) + 5);
            if (name.equals("Wszyscy")) {
                message = message.substring(14);
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).getUser().equals(this.user)) {
                        String message2 = "[MESSAGE]" + "[" + this.user.getName() + "] " + message;
                        list.get(i).getOutput().sendMessage(message2);
                    }
                }
            } else {
                message = message.substring(Integer.parseInt(message.substring(1,2)) + 7);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUser().getName().equals(name)) {
                        String message2 = "[MESSAGE]" + "[" + this.user.getName() + "] " + message;
                        list.get(i).getOutput().sendMessage(message2);
                    }
                }
            }
        }
    }
    public Output getOutput() {
        return output;
    }

    public void sendList() {
        for (int i = 0; i < list.size(); i++) {
            String textList = "[LIST]";
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getUser().getName() != list.get(j).getUser().getName()) {
                    textList = textList + "[" + list.get(j).getUser().getName() + "]";
                }
            }
            list.get(i).getOutput().sendMessage(textList);
        }
    }

}
