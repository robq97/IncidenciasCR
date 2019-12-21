package com.project.incidenciascr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
                changeIncidenceState(position);
            }
        });

        return view;
    }

    private void changeIncidenceState(int i){

        BDConexion cnn = new BDConexion( this.mCtx,"Admin", null, 1);
        final SQLiteDatabase bd = cnn.getWritableDatabase();
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("¿Desea cambiar el estado de esta incidencia a inactiva?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String query = ("UPDATE Incidencia SET estado = 'activo'");
                    Cursor fila = bd.rawQuery(query, null);
                    fila.moveToFirst();
                    fila.close();
                } catch (Exception ex){
                    ex.getCause();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
