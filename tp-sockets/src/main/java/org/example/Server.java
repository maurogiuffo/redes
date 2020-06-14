package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private static final int serverPort= 3000;

    public void start(int port) {
        System.out.println("servidor iniciado, esperando clientes");
        try {
            serverSocket = new ServerSocket(port);
            while (true)
                new ClientHandler(serverSocket.accept()).start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String closeConnectionCharacter= "x";

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                System.out.println("cliente conectado");
                System.out.println("ingrese mensaje + enter, x + enter para desconectar");

                while ((inputLine = in.readLine()) != null ) {
                    if (closeConnectionCharacter.equals(inputLine)) {
                        System.out.println("el cliente termino la conexion");
                        //mando saludo al cliente
                        out.println("bye");
                        break;
                    }

                    System.out.println("cliente dice : "+ inputLine);

                    BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
                    String message="";
                    message = keyboardReader.readLine();

                    if (closeConnectionCharacter.equals(message)) {
                        out.println(message);
                        break;
                    }

                    out.println(message);

                    System.out.println("Esperando respuesta del Cliente");
                }

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(serverPort);
    }

}






/*

 class Server2 {


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 3000;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        try {
            // se inicia el servidor
            serverSocket = new ServerSocket(port);

            while(true)
            {
                //espera
                clientSocket = serverSocket.accept();
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

                String message= dataInputStream.readUTF();


            }
        }
        catch (Exception e)
        {
        }




    }
}*/