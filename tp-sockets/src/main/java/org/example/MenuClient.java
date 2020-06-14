package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuClient {
    private Client client;

    public MenuClient(){
        this.client = new Client();
    }

    public void Menu() throws InterruptedException, IOException {
        boolean connectionStatus = false;
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        Integer x;

        do{
            System.out.println("MENU CLIENTE");
            System.out.println("1-Crear o modificar conexion");
            if(connectionStatus == true){
                System.out.println("2-Ingresar al chat con el servidor");
            }
            System.out.println("0-Salir");

            try {
                x = Integer.parseInt(keyboardReader.readLine());
            } catch (Exception e) {
                x = 3;
            }
            switch (x){
                case 0:
                    connectionStatus = false;
                    break;
                case 1:
                    if(connectionStatus == true){
                        client.stopConnection();
                    }
                    connectionStatus = getConnection();
                    break;
                case 2:
                    if(connectionStatus==true){
                        client.chat();
                        connectionStatus = false;
                    }
                    else{
                        invalidInputMessage();
                    }
                    break;
                default:
                    invalidInputMessage();
                    break;
            }
        }while ((x!=0));
        System.out.println("Saliendo de la Aplicacion...");
        if(connectionStatus == true){
            client.stopConnection();
        }
        Thread.sleep(2*1000);
    }

    public boolean getConnection(){
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        boolean connectionStatus = false;
        boolean flag = true;
        try{
            while(flag){
                System.out.println("Ingresar IP");
                String ip =  keyboardReader.readLine();
                System.out.println("Ingresar Puerto");
                int port;
                try {
                    port = Integer.parseInt(keyboardReader.readLine());
                } catch (Exception e) {
                    port = 0;
                }
                connectionStatus = client.startConnection(ip, port);
                if(connectionStatus == true){
                    flag = false;
                }else{
                    System.out.println("Quiere intentar realizar la conexion nuevamente? ingrese 's' para confirmar");
                    String x =  keyboardReader.readLine();
                    if(!x.equals("s")){
                        flag = false;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return connectionStatus;
    }

    public void invalidInputMessage() throws InterruptedException {
        System.out.println("Opcion invalida, intente nuevamente...");
        Thread.sleep(2*1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
