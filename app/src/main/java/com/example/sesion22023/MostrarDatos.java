package com.example.sesion22023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MostrarDatos extends AppCompatActivity {

    private TextView nombreAlumnoTextView;
    private TextView nombreEscuelaTextView;
    private TextView nombreCarreraTextView;
    private TextView costoCarreraTextView;
    private TextView tipoCarnetTextView;
    private TextView montoAdicionalTextView;
    private TextView numeroCuotasTextView;
    private TextView montoPensionTextView;
    private TextView totalPagarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        nombreAlumnoTextView = findViewById(R.id.nombreAlumno);
        nombreEscuelaTextView = findViewById(R.id.nombreEscuela);
        nombreCarreraTextView = findViewById(R.id.nombreCarrera);
        costoCarreraTextView = findViewById(R.id.costoCarrera);
        tipoCarnetTextView = findViewById(R.id.tipoCarnet);
        montoAdicionalTextView = findViewById(R.id.montoAdicional);
        numeroCuotasTextView = findViewById(R.id.numeroCuotas);
        montoPensionTextView = findViewById(R.id.montoPension);
        totalPagarTextView = findViewById(R.id.totalPagar);


        Intent intent = getIntent();
        String nombreAlumno = intent.getStringExtra("nombreAlumno");
        String escuelaSeleccionada = intent.getStringExtra("escuelaSeleccionada");
        String carreraSeleccionada = intent.getStringExtra("carreraSeleccionada");
        String costoCarreraStr = intent.getStringExtra("costoCarrera");
        boolean carnetBiblioteca = intent.getBooleanExtra("checkBoxBiblioteca", false);
        boolean carnetMedioPasaje = intent.getBooleanExtra("checkBoxMedioPasaje", false);
        int cuotas = intent.getIntExtra("numeroCuotas", 4);
        String pensionStr = intent.getStringExtra("pension");
        String totalPagarStr = intent.getStringExtra("totalPagar");


        // Establecer los valores en los TextViews
        nombreAlumnoTextView.setText(nombreAlumno);
        nombreEscuelaTextView.setText(escuelaSeleccionada);
        nombreCarreraTextView.setText(carreraSeleccionada);
        costoCarreraTextView.setText("S/. " + costoCarreraStr);
        if(carnetBiblioteca){
            tipoCarnetTextView.setText("Carnet Biblioteca");
            montoAdicionalTextView.setText("S/. 25");
        }else if (carnetMedioPasaje){
            tipoCarnetTextView.setText("Carnet Medio Pasaje");
            montoAdicionalTextView.setText("S/. 22");
        } else {
            tipoCarnetTextView.setText("--------------------");
            montoAdicionalTextView.setText("--------------------");
        }
        numeroCuotasTextView.setText(String.valueOf(cuotas));
        montoPensionTextView.setText("S/. " + pensionStr);
        totalPagarTextView.setText("S/. " + totalPagarStr);
    }

    public void volverAMatricular(View vista){
        Intent miIntent = new Intent(this, MainActivity.class);
        startActivity(miIntent);
    }
}
