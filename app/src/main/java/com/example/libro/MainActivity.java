package com.example.libro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String USUARIO="USUARIO";
    private EditText user;
    private EditText pass;
    private Button btnLogin;
    private String usuario;
    private TextView tyc;
    private CheckBox checkbox;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getPreferences(MODE_PRIVATE);

        setupUI();
    }

    private void setupUI(){
        user= findViewById(R.id.e_usser);
        pass= findViewById(R.id.e_pass);
        btnLogin= findViewById(R.id.btn_login);
        usuario = user.getText().toString();
        tyc = findViewById(R.id.tyc_content);
        checkbox = findViewById(R.id.checkbox);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String password = pass.getText().toString();
                if (usuario.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this,"completar los datos faltantes",Toast.LENGTH_LONG).show();
                }else {
                    /*intent aca para que la sesion no se grave
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                    finish();*/
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                    finish();
                    guardarSharedPref(usuario);

                }

            }
        });
        tyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TycActivity.class);
                startActivity(intent);
            }
        });
        cargarSharedPref();
    }

    private void cargarSharedPref(){
        /*cargar las opciones de intent para que quede guardado

        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.putExtra("usuario",usuario);
        startActivity(intent);
        finish();*/
        String usuario = preferences.getString(USUARIO,"");

        user.setText(usuario);
        if (usuario  != null){
            checkbox.setChecked(true);
        }else {
            checkbox.setChecked(false);
        }
    }

    private void guardarSharedPref(String usuario) {
        if (checkbox.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USUARIO, usuario);
            editor.apply();
        }
    }
}