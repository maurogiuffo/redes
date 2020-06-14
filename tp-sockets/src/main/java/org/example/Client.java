package org.example;

import java.io.*;
import java.net.*;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static String closeConnectionCharacter= "x";

    public boolean startConnection(String ip, int port) {
        boolean result = true;
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("No se pudo conectar. " + e.getMessage());
            result = false;
        }

        return result;
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            System.out.println("Esperando respuesta del Servidor");
            return in.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chat() throws IOException {
        boolean connectionStatus = true;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escribe mensaje y envialo al servidor con Enter o escribe 'x' para finalizar la conexion");
        while(connectionStatus)
        {
            System.out.println("Mensaje a enviar:");
            String message = keyboardReader.readLine();
            if(closeConnectionCharacter.equals(message)){
                System.out.println("Finalizando conexion...");
                connectionStatus = false;
            }
            System.out.println("Cliente : " + message);
            String messageFromserver= this.sendMessage(message);

            if (closeConnectionCharacter.equals(messageFromserver)) {
                System.out.println("el servidor termino la conexion");
                connectionStatus = false;
            }
            else {
                System.out.println("Servidor: "+ messageFromserver);
            }
        }
        this.stopConnection();
    }
}
