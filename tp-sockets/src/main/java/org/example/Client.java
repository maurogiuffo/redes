package org.example;

import java.io.*;
import java.net.*;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static String closeConnectionCharacter= "x";

    public boolean startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return true;
        } catch (IOException e) {
            System.out.println("No se pudo conectar. " + e.getMessage());
        }

        return false;
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
        }

    }



    public static void main(String[] args) throws IOException
    {

        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        String message="";

        Client client = new Client();

        if(!client.startConnection("127.0.0.1", 3000))
            return;


        System.out.println("ingrese mensaje + enter, x + enter para desconectar");
        while((message = keyboardReader.readLine()) != null)
        {
            System.out.println(message);
            String messageFromserver= client.sendMessage(message);

            if (closeConnectionCharacter.equals(messageFromserver)) {
                System.out.println("el servidor termino la conexion");
                break;
            }
            System.out.println("el servidor dice: "+ messageFromserver);
            if (closeConnectionCharacter.equals(message)) {
                break;
            }
        }
        client.stopConnection();
    }


}
