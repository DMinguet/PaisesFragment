package com.example.paisesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentListado extends Fragment {
    public interface IOnAttachListener {
        Pais[] getPaises();
    }

    private Pais[] paises;
    private IPaisListener listener;

    public FragmentListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorPaises adaptadorPaises = new AdaptadorPaises(getContext(), paises, listener);
        RecyclerView rvListado = view.findViewById(R.id.rvListado);
        rvListado.setHasFixedSize(true);
        rvListado.setAdapter(adaptadorPaises);
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IPaisListener) context;
        IOnAttachListener attachListener = (IOnAttachListener) context;
        paises = attachListener.getPaises();
    }
}
