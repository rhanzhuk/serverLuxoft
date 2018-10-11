package server.root;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) {

        Server server = new Server();
        //server.setWebAppPath("resources/webapp");
        server.setPort(3000);
        try {
            server.start();

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}

/*
public class ServerTest {
    public static void main(String[] args) {
//        Server server = new Server();
//        server.setWebAppPath("resources/webapp");
//        server.setPort(3000);
//        server.start();
    }
}
 */