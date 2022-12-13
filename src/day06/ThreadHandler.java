package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ThreadHandler implements Runnable{
private Socket socket;

public ThreadHandler (Socket socket){
    this.socket = socket;
}






    @Override
    public void run (){
            try{
                  //Opens input stream    
                  InputStream is = socket.getInputStream();
                  BufferedInputStream bis = new BufferedInputStream(is);
                  DataInputStream dis = new DataInputStream(bis);
                  
                  
                  String [] split = dis.readUTF().split(" ");
      
                  OutputStream os = socket.getOutputStream();
                  BufferedOutputStream bos = new BufferedOutputStream(os);
                  DataOutputStream dos = new DataOutputStream(bos);
      
                  List <Integer> numList = new LinkedList<>();
                  //Random number generator
                  Random rand = new SecureRandom();
                  for (Integer i = 0; i < Integer.parseInt(split[0]); i++){
                      numList.add(rand.nextInt(Integer.parseInt(split[1])));
                  } // For loop ends here (numList has been filled with loop)
      
                  String response = numList.stream() //to convert List <Integer> to String
                      .map(v -> v.toString())
                      .collect(Collectors.joining(":"));
          
                  dos.writeUTF(response);
                  dos.flush();
                  
                  socket.close();

                } catch (Exception e){
                    e.printStackTrace();
                }


    }
}




    







    

