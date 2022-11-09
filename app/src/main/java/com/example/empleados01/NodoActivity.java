package com.example.empleados01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodo);
    }

    public void ok(View v){
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }
    public void cancel(View v){
        Intent i = new Intent();
        setResult(RESULT_CANCELED,i);
        finish();
    }
}