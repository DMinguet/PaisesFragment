package com.example.paisesfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.ViewHolder> {
    private final Context context;
    private final Pais[] paises;
    private final IPaisListener listener;

    public AdaptadorPaises(Context context, Pais[] paises, IPaisListener listener) {
        this.context = context;
        this.paises = paises;
        this.listener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);

        return new ViewHolder(itemView, context, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pais pais = paises[position];
        holder.bindPais(pais);
    }

    public int getItemCount() {
        if (paises == null) {
            return 0;
        } else {
            return paises.length;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView ivBandera;
        private final TextView tvNombre;
        private final TextView tvCapital;
        private final TextView tvPoblacion;
        private final Context context;
        private final IPaisListener listener;

        public ViewHolder(@NonNull View itemView, Context context, IPaisListener listener) {
            super(itemView);
            this.context = context;
            this.ivBandera = itemView.findViewById(R.id.ivBandera);
            this.tvNombre = itemView.findViewById(R.id.tvNombre);
            this.tvCapital = itemView.findViewById(R.id.tvCapital);
            this.tvPoblacion = itemView.findViewById(R.id.tvPoblacion);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindPais(Pais pais) {
            try {
                String nombreBandera = "_"+pais.getCodigo().toLowerCase();
                int resID = context.getResources().getIdentifier(nombreBandera, "drawable", context.getPackageName());

                if(resID != 0) {
                    ivBandera.setImageResource(resID);
                } else {
                    nombreBandera = "_onu";
                    resID = context.getResources().getIdentifier(nombreBandera, "drawable", context.getPackageName());
                    ivBandera.setImageResource(resID);
                }

            } catch (Exception e) {

            }
            tvNombre.setText(pais.getNombre());
            tvCapital.setText(pais.getCapital());
            tvPoblacion.setText(String.valueOf(pais.getPoblacion()));
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onPaisSeleccionado(getAdapterPosition());
            }
        }
    }
}
