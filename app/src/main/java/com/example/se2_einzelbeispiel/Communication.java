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

    public Communication(String message, String host, int port){
            this.message = message;
            this.host = host;
            this.port = port;
    }

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

        public String getMessage (){
            return this.message;
        }

        private void setMessage(String message){
            this.message = message;
        }


}
