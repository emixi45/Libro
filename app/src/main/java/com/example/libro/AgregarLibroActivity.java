package com.example.libro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarLibroActivity extends AppCompatActivity {
    private EditText etNombreLibro;
    private EditText etNombreAutor;
    private Button btnGuardar;
    private String Libro= "Libro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        setupUI();
    }

    private void setupUI(){
        etNombreLibro= findViewById(R.id.nombre_de_libro);
        etNombreAutor= findViewById(R.id.nombre_de_autor);
        btnGuardar = findViewById((R.id.btn_guardar));


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarLibro();
            }
        });
    }

    private void guardarLibro(){
        if (datosValidos()){
            Libro libro = new Libro();
            libro.setNombre(etNombreAutor.getText().toString());
            libro.setAutor(etNombreLibro.getText().toString());
            setResult(
                    Activity.RESULT_OK,new Intent().putExtra(Libro,libro)
            );
            finish();
        }
        else{
            Toast.makeText(AgregarLibroActivity.this,"Completar todos los datos",Toast.LENGTH_SHORT).show();
        }


    }
    private boolean datosValidos(){
        boolean validacion = true;

        if (etNombreLibro.getText().toString().isEmpty() || etNombreAutor.getText().toString().isEmpty()){
            validacion=false;
        }
        return validacion;
    }

}