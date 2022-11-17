package com.example.paisesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment {
    public interface IOnAttachListener {
        Pais getPais();
    }

    private TextView tvId;
    private TextView tvNombreCiudad;
    private TextView tvPoblacionCiudad;
    private Pais pais;
    private AdaptadorPaises adaptadorPaises;

    public FragmentDetalle() {
        super(R.layout.fragment_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvId = view.findViewById(R.id.tvId);
        tvNombreCiudad = view.findViewById(R.id.tvNombreCiudad);
        tvPoblacionCiudad = view.findViewById(R.id.tvPoblacionCiudad);
        mostrarDetalle(pais);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        pais = attachListener.getPais();
    }

    public void mostrarDetalle(Pais pais) {
        requireActivity().setTitle("Datos de " + pais.getNombre());
        tvId.setText(pais.getCodigo());
        tvNombreCiudad.setText(pais.getCapital());
        tvPoblacionCiudad.setText(String.valueOf(pais.getPoblacion()));
    }
}
