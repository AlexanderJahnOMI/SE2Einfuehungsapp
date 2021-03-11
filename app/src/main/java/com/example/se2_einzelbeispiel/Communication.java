package com.example.se2_einzelbeispiel;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class Communication extends Thread {

    private String message;
    private String host;
    private int port;

    /**
     * constructor
     * @param message is the message which get send to the server and after the server answers its the answer from server
     * @param host server host name
     * @param port which port should be used
     */
    public Communication(String message, String host, int port){
        this.message = message;
        this.host = host;
        this.port = port;
    }

    /**
     * gets started when a method creates an Communication object and uses method start()
     * communicates with the server
     */
    public void run(){
        try {
            Socket clientSocket = new Socket(host, port);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(this.message + '\n');

            this.message = (inFromServer.readLine());

            clientSocket.close();

        } catch (Exception e){
            this.message = e.toString();
        }

    }

    /**
     * getter and setter to get and set message
     * getMessage should be used to get the answer from server
     */
    public String getMessage (){
        return this.message;
    }

    private void setMessage(String message){
        this.message = message;
    }

}
