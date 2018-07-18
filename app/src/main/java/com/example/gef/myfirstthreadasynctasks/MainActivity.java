package com.example.gef.myfirstthreadasynctasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre = null;
    private TextView textViewResultat = null;
    private ProgressBar progressBar = null;
    private Button buttonValider = null;

    private long timeStampStart = 0;
    private static final String TAG = "GEF";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.saisie_nombre);
        textViewResultat = findViewById(R.id.resultat);
        progressBar = findViewById(R.id.progressbar);
        buttonValider = findViewById(R.id.bouton_valider);

        editTextNombre.setText("993960000099397");
    }


    public void onClickBoutonValider(View view) {

        textViewResultat.setText("");
        progressBar.setVisibility(view.VISIBLE);
        buttonValider.setVisibility(view.GONE);

        String saisie = editTextNombre.getText().toString();
        if(!TextUtils.isEmpty(saisie))
        {
            timeStampStart = new Date().getTime();

            MyAsyncTask myAsyncTask = new MyAsyncTask(this);
            myAsyncTask.execute(Long.parseLong(saisie));
        }
        else
        {
            Toast.makeText(this, R.string.main_erreur_saisie , Toast.LENGTH_LONG).show();
        }
    }

    public void updateProgression(Float value) {
        progressBar.setProgress((int)  (value * 100));

    }

    public void afficherResultat(Boolean resultat) {
        long timestampEnd = new Date().getTime();

        progressBar.setVisibility(View.GONE);
        buttonValider.setVisibility(View.VISIBLE);

        if( resultat == null){
            Log.i(TAG,"TEST");
            Toast.makeText(this,R.string.main_erreur_calcul,Toast.LENGTH_LONG).show();
        }
        else
        {
            if(resultat)
            {
                textViewResultat.setText(getString(R.string.main_resultat_positif , ( timestampEnd - timeStampStart ) + "ms"));
            }
            else
            {
                textViewResultat.setText(getString(R.string.main_resultat_negatif));
            }
        }
    }
}
