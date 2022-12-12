package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ListClient {

    public static void main(String[] args) throws Exception{
        
        if (args.length != 4) {
            System.out.println("Incorrect number of arguments");
            System.out.println("No. should be 3");
        }
        //Get the list
        Integer nums = Integer.parseInt(args[0]);
        //Get the list range
        Integer range = Integer.parseInt(args[1]);
        //Get the host
        String host = args[2];
        //Get the port
        Integer port = Integer.parseInt(args[3]);

        //Create new socket to the server
        Socket clientSocket = new Socket(host, port);
        System.out.printf("Connected to %s: %d on %d\n", host, port, clientSocket.getPort());

        //Opens the output stream
        OutputStream os = clientSocket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeUTF(String.format("%d %d", nums, range));
        dos.flush();
        

        //Opens the input stream
        InputStream is = clientSocket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String fromServer = dis.readUTF();
        System.out.println(fromServer);

        String [] fromServerSplit = fromServer.split(":");
        Integer sum = 0;
        for (int i = 0; i<nums; i++) {
            sum += Integer.parseInt(fromServerSplit[i]);
        }

        System.out.println("Average: " + sum/nums);
        
        



        clientSocket.close();
        


    }



}