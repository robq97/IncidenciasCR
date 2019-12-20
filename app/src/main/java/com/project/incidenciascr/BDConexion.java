package com.project.incidenciascr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.sqlite.SQLiteDatabase.*;

public class BDConexion extends SQLiteOpenHelper {

    public BDConexion(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        db.execSQL("CREATE TABLE Provincia(id INTEGER PRIMARY KEY, nombre text not null)");

        db.execSQL("CREATE TABLE Canton(id INTEGER PRIMARY KEY, nombre text not null)");

        db.execSQL("CREATE TABLE Distrito(id INTEGER PRIMARY KEY, nombre text not null)");
         */

        db.execSQL("CREATE TABLE Cuenta(cedula int primary key, nombre text not null, primer_apellido text not null," +
                "segundo_apellido text not null, correo_electronico text not null, tel_cel text not null, provincia text not null, canton text not null," +
                "distrito text not null, direccion text, clave text not null, codigo_correo text)");

        //db.execSQL("ALTER TABLE Cuenta ADD COLUMN codigo_correo text;");

        db.execSQL("CREATE TABLE Incidencia(id INTEGER PRIMARY KEY, categoria text not null, entidad text not null, " +
                "provincia text not null, canton text not null, distrito text not null, direccion text," +
                " detalle text, longitud text, latitud text)");

        /*
        db.execSQL("CREATE TABLE Cuenta_Incidencia(cedula int primary key, id_incidencia int primary key, " +
                "FOREIGN KEY (cedula) REFERENCES Usuario(cedula), FOREIGN KEY (id_incidencia) REFERENCES Incidencia(id))");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
