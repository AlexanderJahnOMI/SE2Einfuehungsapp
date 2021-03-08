package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        // Die Variablen werden mit der ID ihres jeweiligen Buttons/Textfeldes belegt.
        abschicken = (Button) findViewById(R.id.sendServerButton);
        matrEingabefeld = (EditText) findViewById(R.id.matrEingabefeld);
        ergebnisAusgeben = (TextView) findViewById(R.id.ergebnisAusgeben);

        //Dem OnClickListener muss gesagt werden, dass er auf den Button achten soll
        abschicken.setOnClickListener(this::onClick);



    }


    // onClick: Wird aufgerufen, wenn auf einen Button geklickt wird.
    public void onClick(View view) {
        if (view == abschicken) {

            // Der Text muss in einen Integer geändert werden.
            Integer matrikelNummer;
            matrikelNummer = Integer.valueOf(matrEingabefeld.getText().toString());

            matrikelNummer = matrikelNummer+5;

            // Ergebnis der Berechnung wird ausgegeben.
            ergebnisAusgeben.setText(matrikelNummer.toString());

        }

    }



}