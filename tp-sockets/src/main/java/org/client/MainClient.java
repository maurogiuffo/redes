package org.client;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        MenuClient menuClient = new MenuClient();
        menuClient.Menu();
    }
}
