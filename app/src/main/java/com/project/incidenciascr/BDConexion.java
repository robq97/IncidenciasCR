package com.project.incidenciascr;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
                "distrito text not null, direccion text, clave text not null, codigo_correo text, estado text)");

        //db.execSQL("ALTER TABLE Cuenta ADD COLUMN codigo_correo text;");

        db.execSQL("CREATE TABLE Incidencia(id int primary key, categoria text not null, entidad text not null, " +
                "provincia text not null, canton text not null, distrito text not null, direccion text," +
                " detalle text, longitud text, latitud text, estado text, cedula text)");

        /*
        db.execSQL("CREATE TABLE Cuenta_Incidencia(cedula int primary key, id_incidencia int primary key, " +
                "FOREIGN KEY (cedula) REFERENCES Usuario(cedula), FOREIGN KEY (id_incidencia) REFERENCES Incidencia(id))");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insertImage(String x, Integer i){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream((x));
            byte[] imgByte = new byte[fs.available()];
            fs.read(imgByte);
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", i);
            contentValues.put("img", imgByte);
            db.insert("Incidencia", null, contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
