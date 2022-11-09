package com.example.empleados01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db),MODE_PRIVATE,null);
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS empleado " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name VARCHAR(50)," +
                        "apellido VARCHAR(50)," +
                        "telefono VARCHAR(20)," +
                        "email VARCHAR(100))"
        );
    }

    public void nuevo_empleado(View v){
        Intent i = new Intent(this,SegundoActivity.class);

        startActivity(i);
    }
    public void listaEmpleado(View v){
        Intent i = new Intent(this,ListaActivity.class);
        startActivity(i);

    }
}