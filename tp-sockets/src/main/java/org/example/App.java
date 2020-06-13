package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server=new Server();
        server.start(6666);

        System.out.println( "Hello World!" );
    }
}
