package com.example.empleados01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        Intent i = getIntent();

        String nombre= i.getStringExtra("nombre");
        EditText nombreET = findViewById(R.id.editTextNombre);
        nombreET.setText(nombre);
        String apellido= i.getStringExtra("apellido");
        EditText apellidoET = findViewById(R.id.editTextApellido);
        apellidoET.setText(apellido);
        String telefono= i.getStringExtra("telefono");
        EditText telefonoET = findViewById(R.id.editTextPhone);
        telefonoET.setText(telefono);
        String email= i.getStringExtra("email");
        EditText emailET = findViewById(R.id.editTextEmail);
        emailET.setText(email);

        String id= i.getStringExtra("id");
        EditText idET = findViewById(R.id.editTextId);
        idET.setText(id);

    }
    public void siguiente(View v){

        EditText nombre = findViewById(R.id.editTextNombre);
        EditText apellido = findViewById(R.id.editTextApellido);
        EditText edad = findViewById(R.id.editTextEmail);
        EditText telefono = findViewById(R.id.editTextPhone);
        EditText id = findViewById(R.id.editTextId);

        String var_nombre = nombre.getText().toString();
        String var_apellido = apellido.getText().toString();
        String var_email = edad.getText().toString();
        String var_telefono = telefono.getText().toString();
        String var_id = id.getText().toString();

        if (var_nombre.equals("") || var_nombre == null){
            nombre.requestFocus();
            nombre.setBackgroundTintList(getColorStateList(R.color.red));
            Toast.makeText(this,"campo obligatorio",Toast.LENGTH_SHORT).show();
        }
        else if (var_apellido.equals("") || var_apellido ==null){
            apellido.requestFocus();
            apellido.setBackgroundTintList(getColorStateList(R.color.red));
            Toast.makeText(this,"campo obligatorio",Toast.LENGTH_SHORT).show();
        }
        else if (var_telefono.equals("") || var_telefono == null){
            telefono.requestFocus();
            telefono.setBackgroundTintList(getColorStateList(R.color.red));
            Toast.makeText(this,"campo obligatorio",Toast.LENGTH_SHORT).show();
        }
        else if (var_email.equals("") || var_email == null){
            edad.requestFocus();
            edad.setBackgroundTintList(getColorStateList(R.color.red));
            Toast.makeText(this,"campo obligatorio",Toast.LENGTH_SHORT).show();
        }
        else {

            Intent i = new Intent(this, TercerActivity.class);
            i.putExtra("nombre", var_nombre);
            i.putExtra("apellido", var_apellido);
            i.putExtra("email", var_email);
            i.putExtra("telefono", var_telefono);
            i.putExtra("id", var_id);
            startActivity(i);
        }
    }
    public void cargarContacto(View v){
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);


        startActivityForResult(i,34);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==34){
            if (resultCode==RESULT_OK){
                Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
                Uri contactData = data.getData();

                Cursor c = getContentResolver().query(contactData,null,null,null,null);

                if (c.moveToFirst()){

                    int i = c.getColumnIndex(ContactsContract.Contacts._ID);
                    String id = c.getString(i);
                    int i2 = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                    int hp = Integer.parseInt(c.getString(i2));


                    String whereNameStruc = ContactsContract.Data.MIMETYPE+" = ? AND "+ ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ? ";
                    String [] whereNameParamsStruc = new String[] {ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,id};

                    Cursor nameCursorStruc = getContentResolver().query(ContactsContract.Data.CONTENT_URI,null,whereNameStruc,whereNameParamsStruc,null);
                    while (nameCursorStruc.moveToNext()){
                        int i1 = nameCursorStruc.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                        String given = nameCursorStruc.getString(i1);

                        int iDos = nameCursorStruc.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
                        String family = nameCursorStruc.getString(iDos);

                        EditText nombre = findViewById(R.id.editTextNombre);
                        nombre.setText(given);

                        EditText apellido = findViewById(R.id.editTextApellido);
                        apellido.setText(family);
                    }

                    if (hp > 0) {


                        String whereTelfStruc = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ";
                        String[] whereTelfParamsStruc = new String[]{ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, id};

                        Cursor nameTelfStruc = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereTelfStruc, whereTelfParamsStruc, null);
                        while (nameTelfStruc.moveToNext()) {

                            int i3 = nameTelfStruc.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String phone = nameTelfStruc.getString(i3);

                            EditText telefono = findViewById(R.id.editTextPhone);
                            telefono.setText(phone);
                        }
                    }

                    String whereEmailStruc = ContactsContract.Data.MIMETYPE + " = ? AND "+ ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ";
                    String [] whereEmailParamsStruc = new String[] {ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE,id};

                    Cursor nameEmailStruc = getContentResolver().query(ContactsContract.Data.CONTENT_URI,null,whereEmailStruc,whereEmailParamsStruc,null);
                    while (nameEmailStruc.moveToNext()) {
                        int i4 = nameEmailStruc.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                        String email = nameEmailStruc.getString(i4);

                        EditText mail = findViewById(R.id.editTextEmail);
                        mail.setText(email);
                    }



                }
            } else if (resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        }
    }
}