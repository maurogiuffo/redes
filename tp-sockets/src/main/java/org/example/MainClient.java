package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        MenuClient menuClient = new MenuClient();
        menuClient.Menu();
    }
}
