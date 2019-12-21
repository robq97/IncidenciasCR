package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UpdateIncidence extends AppCompatActivity {

    public ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_incidence);

       /* listaIncidente = new ArrayList<>();

        listaIncidente.add(new IncidenceElement(R.drawable.email, "prueba", "activo"));
        listaIncidente.add(new IncidenceElement(R.drawable.map_icon, "prueba2", "activo2"));
        */
        list = (ListView)findViewById(R.id.list_incidentes);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_layout, listaIncidente());
        list.setAdapter(customAdapter);
    }

    public List<IncidenceElement> listaIncidente() {
        String cedula = ((Global) this.getApplication()).getCedula();
        List<IncidenceElement> listaIncidente = new ArrayList<>();
        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase db = cnn.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Incidencia WHERE cedula = ?", new String[] {cedula});

        if (cursor.moveToFirst()) {
            do {
                IncidenceElement incidente = new IncidenceElement(
                        R.drawable.email,
                        cursor.getString(1),
                        cursor.getString(10));
                listaIncidente.add(incidente);
            } while (cursor.moveToNext());
        }
        return listaIncidente;
    }
}
