package com.project.incidenciascr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<IncidenceElement> {

    Context mCtx;
    int resource;
    List<IncidenceElement> listaIncidentes;

    public CustomAdapter(Context mCtx, int resource, List<IncidenceElement> listaIncidentes){
        super(mCtx, resource, listaIncidentes);

        this.mCtx = mCtx;
        this.resource = resource;
        this.listaIncidentes = listaIncidentes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource, null);

        TextView nombreIncidente = view.findViewById(R.id.txt_incidente_nombre);
        TextView estado = view.findViewById(R.id.txt_estado_lista);
        ImageView img = view.findViewById(R.id.img_lista);

        IncidenceElement incidente = listaIncidentes.get(position);

        nombreIncidente.setText((incidente.getNombreIncidente()));
        estado.setText(incidente.getEstado());
        img.setImageDrawable(mCtx.getResources().getDrawable(incidente.getImage()));

        view.findViewById(R.id.btn_desactivar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
