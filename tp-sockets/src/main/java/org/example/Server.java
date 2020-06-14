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
            Integer clientId = 1;

            while (true){
                new ClientHandler(serverSocket.accept(),clientId).start();
                clientId++;
            }



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
        private Integer ClientId;

        public ClientHandler(Socket socket, Integer id) {
            this.clientSocket = socket;
            this.ClientId = id;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                System.out.println("Cliente " + ClientId + " Conectado \n");
                System.out.println("Escribe mensaje y envialo al Cliente con Enter o escribe 'x' para finalizar la conexion");

                while ((inputLine = in.readLine()) != null ) {
                    if (closeConnectionCharacter.equals(inputLine)) {
                        System.out.println("Cliente " + ClientId +" termino la conexion");
                        //mando saludo al cliente
                        out.println("bye");
                        break;
                    }

                    System.out.println("Cliente " + ClientId +" dice : "+ inputLine);

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