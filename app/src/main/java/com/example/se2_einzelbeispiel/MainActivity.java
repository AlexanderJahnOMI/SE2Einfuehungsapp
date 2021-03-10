package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private Button abschicken;
    private EditText matrEingabefeld;
    private TextView ergebnisAusgeben;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abschicken = (Button) findViewById(R.id.sendServerButton);
        matrEingabefeld = (EditText) findViewById(R.id.matrEingabefeld);
        ergebnisAusgeben = (TextView) findViewById(R.id.ergebnisAusgeben);

        abschicken.setOnClickListener(this::onClick);
    }


    public void onClick(View v) {
        SubActivity subActivity = new SubActivity();

        Thread thread = new Thread(subActivity);

        thread.start();

        try {
            thread.join();
        }catch (InterruptedException ie) {
            ergebnisAusgeben.setText("Fehler");
        }

        ergebnisAusgeben.setText(subActivity.getMatrNr());
    }




    public class SubActivity extends Thread {

        private String matrNr = matrEingabefeld.getText().toString();

        public void run(){
            try {
                Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                outToServer.writeBytes(matrNr+ '\n');

                setMatrNr(inFromServer.readLine());

                clientSocket.close();

            } catch (Exception e){
                ergebnisAusgeben.setText(e.toString());
            }

        }

        public String getMatrNr (){
            return this.matrNr;
        }

        public void setMatrNr(String matrNr){
            this.matrNr = matrNr;
        }

    }






}