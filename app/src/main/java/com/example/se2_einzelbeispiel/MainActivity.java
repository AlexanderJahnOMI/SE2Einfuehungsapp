package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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


    public void onClick(View view) {
        Communication subActivity = new Communication(matrEingabefeld.getText().toString(), "se2-isys.aau.at", 53212);

        subActivity.start();

        try {
            subActivity.join();

            ergebnisAusgeben.setText(subActivity.getMessage());
        }catch (InterruptedException ie) {
            ergebnisAusgeben.setText("Something happend.");
        }

    }








}