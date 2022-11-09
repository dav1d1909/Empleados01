package com.example.empleados01;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {

    ArrayList<Empleado>lista;
    public MiAdaptador(ArrayList<Empleado>lista){
        this.lista = lista;
    }

    public static class MiViewHolder extends RecyclerView.ViewHolder{

        TextView txtid,txtnombre, txtapellido, txtemail, txttelefono;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.txt_nombre);
            txtapellido = itemView.findViewById(R.id.txt_apellido);
            txttelefono = itemView.findViewById(R.id.txt_telefono);
            txtemail = itemView.findViewById(R.id.txt_email);
            txtid = itemView.findViewById(R.id.txt_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    Intent i = new Intent(itemView.getContext(),SegundoActivity.class);
                    i.putExtra("nombre",txtnombre.getText().toString());
                    i.putExtra("apellido",txtapellido.getText().toString());
                    i.putExtra("telefono",txttelefono.getText().toString());
                    i.putExtra("email",txtemail.getText().toString());
                    i.putExtra("id",txtid.getText().toString());

                    itemView.getContext().startActivity(i);

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(itemView.getContext());
                    ab.setTitle("Borrar empleado");
                    ab.setMessage("¿Seguro que desea borrar el empleado "+txtnombre.getText().toString()+"?");
                    ab.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase myDB = itemView.getContext().openOrCreateDatabase(itemView.getContext().getResources().getString(R.string.db),itemView.getContext().MODE_PRIVATE, null);
                            myDB.execSQL("DELETE FROM empleado WHERE id="+txtid.getText().toString());
                            Toast.makeText(itemView.getContext(), "Empleado: "+txtnombre.getText().toString()+" borrado correctamente",Toast.LENGTH_SHORT).show();
                            ((Activity)itemView.getContext()).recreate();


                        }
                    });
                    ab.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(), "Empleado no borrado",Toast.LENGTH_SHORT).show();
                        }
                    });
                    ab.show();





                    return false;
                }
            });

        }
    }
    @NonNull
    @Override
    public MiAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View v = inflador.inflate(R.layout.elemento,parent,false);

        MiViewHolder mvh = new MiViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiViewHolder holder, int position) {

        holder.txtid.setText(""+lista.get(position).getId());
        holder.txtnombre.setText(lista.get(position).getNombre());
        holder.txtapellido.setText(lista.get(position).getApellido());
        holder.txttelefono.setText(lista.get(position).getTelefono());
        holder.txtemail.setText(lista.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
