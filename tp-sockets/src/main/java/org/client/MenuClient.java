package org.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuClient {
    private Client client;

    public MenuClient() {
        this.client = new Client();
    }

    public void Menu() throws InterruptedException, IOException {
        boolean connected = false;
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        Integer x;

        do {
            printMenu(connected);
            try {
                x = Integer.parseInt(keyboardReader.readLine());
            } catch (Exception e) {
                x = 3;
            }
            switch (x) {
                case 0:
                    connected = false;
                    break;
                case 1:
                    if (connected)
                        client.stopConnection();
                    connected = getConnection();
                    break;
                case 2:
                    if (connected) {
                        client.chat();
                        connected = false;
                    } else {
                        invalidInputMessage();
                    }
                    break;
                default:
                    invalidInputMessage();
                    break;
            }
        } while ((x != 0));
        System.out.println("Saliendo de la Aplicacion...");
        if (connected) {
            client.stopConnection();
        }
        Thread.sleep(2 * 1000);
    }

    private void printMenu(boolean connected) {
        System.out.println("MENU CLIENTE");
        System.out.println("1-Crear o modificar conexion");
        if (connected) {
            System.out.println("2-Ingresar al chat con el servidor");
        }
        System.out.println("0-Salir");
    }

    public boolean getConnection() {
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        boolean connectionSuccess = false;
        boolean tryConnect = true;
        boolean inputError = false;
        int port = 0;
        String ip ="";
        String x="";

        while (tryConnect && !connectionSuccess) {
            System.out.println("Ingresar IP");
            try {
                ip = keyboardReader.readLine();
                if (!CheckIpFormat(ip)) {
                    System.out.println("Formato de Ip incorrecto");
                    inputError = true;
                }
            }
            catch  (Exception e) {
                System.out.println("Formato de puerto incorrecto");
                inputError=true;
            }

            if(!inputError) {
                System.out.println("Ingresar Puerto");
                try {
                    port = Integer.parseInt(keyboardReader.readLine());
                } catch (Exception e) {
                    System.out.println("Formato de puerto incorrecto");
                    inputError = true;
                }
            }

            if(!inputError) {
                try {
                    connectionSuccess = client.startConnection(ip, port);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            if (!connectionSuccess || inputError) {
                System.out.println("Quiere intentar realizar la conexion nuevamente? ingrese 's' para confirmar");
                try {
                    x = keyboardReader.readLine();
                } catch (Exception ex) {}

                if (!x.equals("s")) {
                    tryConnect = false;
                }
            }
        }

        return connectionSuccess;
    }

    private boolean CheckIpFormat(String ip) {
        String pattern = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ip);
        if (!m.find())
            return false;
        return true;
    }

    public void invalidInputMessage() throws InterruptedException {
        System.out.println("Opcion invalida, intente nuevamente...");
        Thread.sleep(2 * 1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
