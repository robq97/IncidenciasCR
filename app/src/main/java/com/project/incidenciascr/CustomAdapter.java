package com.project.incidenciascr;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    public static LayoutInflater inflater;
    private UpdateIncidence updIncidence;
    private ImageView  imageView;
    private TextView textViewNombreIncidente;
    private TextView textViewEstadoIncidente;

    int[] img = {
            R.drawable.email
    };

    String[] nombres= {
            "Nombre Temp"
    };

    String[] desc={
            "Desc Temp"
    };

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_layout, updIncidence.list, false);
        //LayoutInflater.from(updIncidence).inflate(R.layout.custom_layout,null,false);
        //imageView=(ImageView)view.findViewById(R.id.img_lista);
        textViewNombreIncidente = (TextView)view.findViewById(R.id.txt_incidente_nombre);
        textViewEstadoIncidente = (TextView)view.findViewById(R.id.txt_estado_lista);

        //imageView.setImageResource(img[position]);
        textViewNombreIncidente.setText(nombres[position]);
        textViewEstadoIncidente.setText(desc[position]);
        return view;
    }
}
