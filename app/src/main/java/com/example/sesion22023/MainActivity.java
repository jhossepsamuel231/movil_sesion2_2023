package com.example.sesion22023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxCarnetBiblioteca;
    private CheckBox checkBoxCarnetMedioPasaje;
    private RadioGroup radioGroupCuotas;
    private Spinner spinnerCarrera;
    private Spinner spinnerEscuela;
    private Button btnCalcular;
    private EditText editTextTotalPagar;
    private EditText editTextCostoCarrera;
    private EditText editTextPension;
    private EditText nombreAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxCarnetBiblioteca = findViewById(R.id.checkBoxCarnetBiblioteca);
        checkBoxCarnetMedioPasaje = findViewById(R.id.checkBoxCarnetMedioPasaje);
        spinnerCarrera = findViewById(R.id.spinnerCarrera);
        spinnerEscuela = findViewById(R.id.spinnerEscuela);
        btnCalcular = findViewById(R.id.btnCalculate);
        editTextTotalPagar = findViewById(R.id.editTextTotalPagar);
        editTextCostoCarrera = findViewById(R.id.editTextCostoCarrera);
        editTextPension = findViewById(R.id.editTextPension);
        nombreAlumno = findViewById(R.id.nomAlumnos);


        // Configurando el listener para el spinner de escuela
        spinnerEscuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) { // Si se selecciona "Seleccione su escuela"
                    spinnerCarrera.setEnabled(false); // Deshabilita el spinner de carrera
                } else {
                    spinnerCarrera.setEnabled(true); // Habilita el spinner de carrera
                    updateCarreraSpinner(position - 1); // Resta 1 para ajustar con los índices del array
                }
                updateCarreraSpinner(position); // Actualiza el spinner de carrera independientemente de la selección
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada aquí
            }
        });


        // Configurando el listener para la CheckBox
        checkBoxCarnetBiblioteca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxCarnetMedioPasaje.setChecked(false); // Desmarca la otra CheckBox
                }
            }
        });
        checkBoxCarnetMedioPasaje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxCarnetBiblioteca.setChecked(false); // Desmarca la otra CheckBox
                }
            }
        });

        radioGroupCuotas = findViewById(R.id.radioGroupCuotas);

        radioGroupCuotas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Desmarcar todos los RadioButtons para que solo uno esté seleccionado
                RadioButton radioButton4 = findViewById(R.id.radioButton4);
                RadioButton radioButton5 = findViewById(R.id.radioButton5);
                RadioButton radioButton6 = findViewById(R.id.radioButton6);

                radioButton4.setChecked(false);
                radioButton5.setChecked(false);
                radioButton6.setChecked(false);

                // Marcar el RadioButton seleccionado
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedRadioButton.setChecked(true);
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carreraSeleccionada = spinnerCarrera.getSelectedItem().toString();
                // int costoCarrera = (carreraSeleccionada.equals("arquitectura")) ? 4000 : 3000;
                int costoCarrera = 0;
                if(carreraSeleccionada.equals("Sistemas")){
                    costoCarrera = 7000;
                } else if(carreraSeleccionada.equals("Civil")){
                    costoCarrera = 6500;
                } else if(carreraSeleccionada.equals("Arquitectura")){
                    costoCarrera = 7500;
                } else if(carreraSeleccionada.equals("Alimentarias")){
                    costoCarrera = 6000;
                } else if(carreraSeleccionada.equals("Nutricion Humana")){
                    costoCarrera = 6090;
                } else if(carreraSeleccionada.equals("Psicologia")){
                    costoCarrera = 5600;
                } else if(carreraSeleccionada.equals("Medicina")){
                    costoCarrera = 12000;
                } else if(carreraSeleccionada.equals("Ciencias de la Comunicación")){
                    costoCarrera = 7400;
                } else if(carreraSeleccionada.equals("Educación Fisica")){
                    costoCarrera = 4000;
                } else if(carreraSeleccionada.equals("Educación Musical y Artes")){
                    costoCarrera = 3700;
                }   else if(carreraSeleccionada.equals("Educación Inicial")){
                    costoCarrera = 3000;
                }

                editTextCostoCarrera.setText(String.valueOf(costoCarrera));

                int numeroCuotas = 4;
                int selectedRadioButtonId = radioGroupCuotas.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.radioButton5) {
                    numeroCuotas = 5;
                } else if (selectedRadioButtonId == R.id.radioButton6) {
                    numeroCuotas = 6;
                }

                int costoAdicional = 0;
                if (checkBoxCarnetBiblioteca.isChecked()) {
                    costoAdicional += 25;
                }
                if (checkBoxCarnetMedioPasaje.isChecked()) {
                    costoAdicional += 22;
                }

                double pension = ((costoCarrera + costoCarrera * 0.12) / numeroCuotas);
                editTextPension.setText(String.valueOf(pension));
                double totalPago = (((costoCarrera + costoCarrera * 0.12) + costoAdicional) / numeroCuotas);
                editTextTotalPagar.setText(String.format("%.2f", totalPago));
            }
        });


    }

    public void imprimirMatricula(View vista){
        Intent miIntent = new Intent(this, MostrarDatos.class);

        // Agregar los datos como extras al Intent
        miIntent.putExtra("nombreAlumno", nombreAlumno.getText().toString());
        miIntent.putExtra("escuelaSeleccionada", spinnerEscuela.getSelectedItem().toString());
        miIntent.putExtra("carreraSeleccionada", spinnerCarrera.getSelectedItem().toString());
        miIntent.putExtra("costoCarrera", editTextCostoCarrera.getText().toString());
        miIntent.putExtra("checkBoxBiblioteca", checkBoxCarnetBiblioteca.isChecked());
        miIntent.putExtra("checkBoxMedioPasaje", checkBoxCarnetMedioPasaje.isChecked());
        int selectedRadioButtonId = radioGroupCuotas.getCheckedRadioButtonId();
        int numeroCuotas = 4; // Valor predeterminado

        if (selectedRadioButtonId == R.id.radioButton5) {
            numeroCuotas = 5;
        } else if (selectedRadioButtonId == R.id.radioButton6) {
            numeroCuotas = 6;
        }
        miIntent.putExtra("numeroCuotas", numeroCuotas);
        miIntent.putExtra("pension", editTextPension.getText().toString());
        miIntent.putExtra("totalPagar", editTextTotalPagar.getText().toString());

        startActivity(miIntent);
    }

    private void updateCarreraSpinner(int escuelaPosition) {
        ArrayAdapter<CharSequence> adapter;

        switch (escuelaPosition) {
            case 1: // FIA
                adapter = ArrayAdapter.createFromResource(this, R.array.carrera_fia, android.R.layout.simple_spinner_item);
                break;
            case 2: // Ciencias de la Salud
                adapter = ArrayAdapter.createFromResource(this, R.array.carrera_salud, android.R.layout.simple_spinner_item);
                break;
            case 3: // Ciencias Humanas y Educación
                adapter = ArrayAdapter.createFromResource(this, R.array.carrera_educacion, android.R.layout.simple_spinner_item);
                break;
            default:
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
                break;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapter);
    }
}

