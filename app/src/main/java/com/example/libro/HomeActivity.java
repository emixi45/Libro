package com.example.libro;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private String usuario;
    private Toolbar _toolbar;
    private RecyclerView rvLibros;
    private LibrosAdapter adapter;
    private String LIBRO = "Libro";


    private ActivityResultLauncher<Intent> starForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==Activity.RESULT_OK){
                        Libro nuevoLibro = (Libro) result.getData().getSerializableExtra(LIBRO);
                        if (nuevoLibro != null){
                            agregarNuevoLibroAdapter(nuevoLibro);
                        }
                    }
                }
            }
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario = bundle.getString("USUARIO");
        }
        setupToolbar();
        setupUI();
        setupAdapter();
    }
    private void setupUI(){
        rvLibros = findViewById(R.id.rvLibros);


    }
    private void setupToolbar(){
      _toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(_toolbar);
      getSupportActionBar().setTitle("mis libros");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.item_agregar){
            goToAgregarLibro();
        }
        return super.onOptionsItemSelected(item);
    }
    private void goToAgregarLibro(){
        starForResult.launch(new Intent(HomeActivity.this,AgregarLibroActivity.class));
    }

    private void setupAdapter(){
            adapter = new LibrosAdapter(new LibrosAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Libro libro) {
                    Toast.makeText(getApplicationContext(),libro.getNombre(),Toast.LENGTH_LONG).show();

                }
            });
            rvLibros.setAdapter(adapter);
            adapter.setLibroList(getLibro());

    }

    private List<Libro> getLibro(){
        return new ArrayList<Libro>(){{
           add(new Libro(1,"Harry Potter","J.K.Rowling"));
           add(new Libro(2,"Game of Thrones","George Martin"));
           add(new Libro(3,"Maze Runner","James Dasher"));

        }};
    }

    private void agregarNuevoLibroAdapter(Libro nuevoLibro){
        List<Libro> libroList = adapter.getLibroList();
        nuevoLibro.setId(libroList.size() + 1);
        libroList.add(nuevoLibro);
        adapter.setLibroList(libroList);

    }
}