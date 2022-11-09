package com.example.empleados01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db),MODE_PRIVATE,null);

        Cursor cursor = myDB.rawQuery("select * from empleado",null);

        ArrayList<Empleado> lista = new ArrayList<Empleado>();

        while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String apellidos = cursor.getString(2);
            String telefono = cursor.getString(3);
            String email = cursor.getString(4);

            Log.v("empleado",nombre+ " "+apellidos+" "+telefono+ " "+ email);

            lista.add(new Empleado(id,nombre,apellidos,telefono,email));

        }
        RecyclerView rv = findViewById(R.id.lista_empleados);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        MiAdaptador adaptador = new MiAdaptador(lista);
        rv.setAdapter(adaptador);
    }
}