package com.example.paisesfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IPaisListener, FragmentListado.IOnAttachListener, FragmentDetalle.IOnAttachListener {
    public static final String COUNTRIES_KEY = "com.example.paises";
    public static final String SELECTED_COUNTRY_KEY = "com.example.paisseleccionado";
    private FragmentDetalle frgDetalle;
    private boolean tabletLayout;
    private Pais[] paises;
    private Pais paisSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            paises = (Pais[]) savedInstanceState.getSerializable(COUNTRIES_KEY);
            paisSeleccionado = (Pais) savedInstanceState.getSerializable(SELECTED_COUNTRY_KEY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Países");

        tabletLayout = findViewById(R.id.frgDetalle) != null;
        if (tabletLayout) {
            FragmentManager manager = getSupportFragmentManager();
            frgDetalle = (FragmentDetalle) manager.findFragmentById(R.id.frgDetalle);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(COUNTRIES_KEY, paises);
        outState.putSerializable(SELECTED_COUNTRY_KEY, paisSeleccionado);
        super.onSaveInstanceState(outState);
    }

    public void loadData() {
        ParserPaises parserAlumnos = new ParserPaises(this);

        if (parserAlumnos.parse()) {
            paises = parserAlumnos.getPaises();
        } else {
            Toast.makeText(this, "Error al obtener los países", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Pais getPais() {
        if (paisSeleccionado == null) {
            paisSeleccionado = paises[0];
        }
        setTitle("Datos de " + paisSeleccionado.getNombre());

        return paisSeleccionado;
    }

    @Override
    public Pais[] getPaises() {
        if (paises == null) {
            loadData();
        }

        return paises;
    }

    @Override
    public void onPaisSeleccionado(int id) {
        paisSeleccionado = paises[id];
        if (tabletLayout) {
            frgDetalle.mostrarDetalle(paisSeleccionado);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.frgListado, FragmentDetalle.class, null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle("Países");
    }
}