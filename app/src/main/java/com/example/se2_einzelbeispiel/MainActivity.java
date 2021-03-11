package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * sendServerButton is needed to start method calculateStudyBeginning
     * asciiButton is needed to start method calculateAscii
     * matrEingabefeld gets the value which is tipped in by the user. this value is needed in method calculateStuyBeginning as well in calculateAscii. values can only be numberic
     * ergebnisAusgeben shows the user the results of the calculations
     */
    private Button sendServerButton;
    private Button asciiButton;
    private EditText matrEingabefeld;
    private TextView ergebnisAusgeben;

    /**
     * starts when the app gets started and waits until a button was clicked
     * when a button gets clicked onClick gets called
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendServerButton = (Button) findViewById(R.id.sendServerButton);
        asciiButton = (Button) findViewById(R.id.asciiButton);
        matrEingabefeld = (EditText) findViewById(R.id.matrEingabefeld);
        ergebnisAusgeben = (TextView) findViewById(R.id.ergebnisAusgeben);

        sendServerButton.setOnClickListener(this);
        asciiButton.setOnClickListener(this);
    }

    /**
     * finds out which button was pressed and starts the correct method
     * onClick is called by onCreate
     * @param view saves the button which was clicked
     */
    public void onClick(View view) {
        if (view == sendServerButton){
            calculateStudyBeginning();
        } else if (view == asciiButton){
            calculateAscii();
        }
    }

    /**
     * starts the connection to the server and waits until an answer arrives
     * the answer shows up on the app
     */
    public void calculateStudyBeginning(){
        Communication subActivity = new Communication(matrEingabefeld.getText().toString(), "se2-isys.aau.at", 53212);

        subActivity.start();

        try {
            subActivity.join();

            ergebnisAusgeben.setText(subActivity.getMessage());
        }catch (InterruptedException ie) {
            ergebnisAusgeben.setText("Something happened.");
        }
    }

    /**
     * replaces every second number of matrEingabefeld with an ASCII charakter and shows up the result on the app
     */
    public void calculateAscii(){
        char [] arr = matrEingabefeld.getText().toString().toCharArray();
        String buildingItUpAgain = "";

        for (int i = 0; i < arr.length; i++){
            if ((i+1) % 2 == 0){
                int charValue = 122;
                if (arr[i] != '0'){
                    charValue = 96 + Integer.parseInt(String.valueOf(arr[i]));
                }
                buildingItUpAgain += (char) charValue;
            } else {
                buildingItUpAgain += arr[i];
            }
        }

        ergebnisAusgeben.setText(buildingItUpAgain);
    }

}