package com.example.empleados01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TercerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        Intent i = getIntent();

        String nombre = i.getStringExtra("nombre");
        String apellido = i.getStringExtra("apellido");
        String telefono = i.getStringExtra("telefono");
        String email = i.getStringExtra("email");
        String id = i.getStringExtra("id");

        TextView txt_nombre = findViewById(R.id.textView18);
        TextView txt_apellido = findViewById(R.id.textView17);
        TextView txt_telefono = findViewById(R.id.textView20);
        TextView txt_email = findViewById(R.id.textView19);
        TextView txt_id = findViewById(R.id.textViewId);

        txt_nombre.setText(nombre);
        txt_apellido.setText(apellido);
        txt_telefono.setText(telefono);
        txt_email.setText(email);
        txt_id.setText(id);
    }
    public void guardar(View v){

        TextView txt_nombre = findViewById(R.id.textView18);
        TextView txt_apellido = findViewById(R.id.textView17);
        TextView txt_telefono = findViewById(R.id.textView20);
        TextView txt_email = findViewById(R.id.textView19);
        TextView txt_id = findViewById(R.id.textViewId);

        String nombre = txt_nombre.getText().toString();
        String apellido = txt_apellido.getText().toString();
        String telefono = txt_telefono.getText().toString();
        String  email = txt_email.getText().toString();
        String id = txt_id.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("name",nombre);
        cv.put("apellido",apellido);
        cv.put("telefono",telefono);
        cv.put("email",email);

        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db),MODE_PRIVATE,null);
        long respuesta = 0;
        String mensaje= "";
        if (id.equals("")) {
           respuesta= myDB.insert("empleado", null, cv);
            mensaje =  "Registro guardado";
        } else{
            respuesta = myDB.update("empleado",cv,"id=?",new String []{id});
            mensaje =  "Registro no guardado";
        }
        if (respuesta != -1) {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();


        }
    }
}