import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        PrintStream output;
        BufferedReader input;
        Socket socket;
        String name;
        ArrayList<Communication> list = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(5000);
//            Czekam na nowego klienta
            while (true) {
                socket = serverSocket.accept();
                output = new PrintStream(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                name = input.readLine();
                write(name, list, output, socket, input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write (String name, ArrayList<Communication> list, PrintStream output, Socket socket, BufferedReader input) throws IOException {
        boolean value = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getName().equals(name)) {
                value = false;
            }
        }
        if (value) {
            User user = new User(name);
            output.println("ADDED");
            output.flush();
            Communication communication = new Communication(user, socket, list);
            list.add(communication);
            communication.sendList();
        } else {
            output.println("EXIST");
            output.flush();
            socket.close();
            output.close();
            input.close();
        }
    }
    public static void close(String name, ArrayList<Communication> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getName().equals(name)) {
                list.remove(i);
            }
        }
    }
}