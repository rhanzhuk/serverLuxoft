package server.root;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    //TODO like https://gist.github.com/Rooman/ccf78918f884ae354580cf9ebd70142b

    private String path;  //= "/resources/webapp/";
    private int port;
    private Socket socket;

    public Socket connector() {
        //Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            socket = serverSocket.accept();
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            //TODO response to console msg
        }
        return null;
    }

    public void serviceServer(int port) throws IOException {

        //TODO поправить на относительный путь
        //TODO fix что бы сервер отрабатывал не один раз
        String from = "/Users/ruslanganzhuk/Desktop/luxoft2/Server/serverLuxoft/serverLuxoft/src" + requestPathParser(controller());
        String content = readContent(from);
        //System.out.println("Content:" + content);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("sending response:");
        bufferedWriter.write("HTTP/1.1 200 OK\n");
        bufferedWriter.write("\n");
        bufferedWriter.write(content);
        bufferedWriter.flush();

    }


    public String controller() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        List<String> list = new ArrayList<>();

        String message;
        while (!(message = bufferedReader.readLine()).isEmpty()) {
            list.add(message);
            System.out.println(message);
        }
        String result = list.get(0);

        return result;
    }

    private String readContent(String from) {
        String result = null;
        try (InputStream inputStream = new FileInputStream(from);) {
            StringBuilder content = new StringBuilder();
            byte[] buffer = new byte[1024];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, count));
            }
            result = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void setWebAppPath(String path) {
        this.path = path;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        connector();
        serviceServer(this.port);

    }

    private String requestPathParser(String request) {
        String[] arrayString = request.split("\\s");
        String result = arrayString[1];
        System.out.println("Result pars:" + result);

        return result;
    }

}
