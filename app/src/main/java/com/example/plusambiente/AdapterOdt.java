package com.example.plusambiente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterOdt extends RecyclerView.Adapter<AdapterOdt.ViewHolderEquipos> {
    ArrayList<odt> listaOdt;

    public AdapterOdt(ArrayList<odt> listaOdt) {
        this.listaOdt = listaOdt;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolderEquipos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_odt, null, false);
        return new ViewHolderEquipos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOdt.ViewHolderEquipos holder, int i) {
        holder.txtSolicitud.setText(listaOdt.get(i).getSol_id());
        holder.txtManifiesto.setText(listaOdt.get(i).getSol_manifiesto());
        holder.txtCliente.setText(listaOdt.get(i).getCli_nombre());
        holder.txtTecnico.setText(listaOdt.get(i).getTec_nombre());
        holder.txtConductor.setText(listaOdt.get(i).getConductor());
    }

    @Override
    public int getItemCount() {
       return listaOdt.size();
    }

    public class ViewHolderEquipos extends RecyclerView.ViewHolder {
        TextView txtManifiesto, txtSolicitud, txtCliente, txtTecnico, txtConductor;
        public ViewHolderEquipos(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtManifiesto = itemView.findViewById(R.id.txtManifiesto);
            txtSolicitud = itemView.findViewById(R.id.txtSolicitud);
            txtCliente = itemView.findViewById(R.id.txtCliente);
            txtTecnico = itemView.findViewById(R.id.txtTecnico);
            txtConductor = itemView.findViewById(R.id.txtConductor);
        }
    }
}
