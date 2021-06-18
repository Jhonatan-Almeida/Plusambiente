package com.example.plusambiente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterOdt extends RecyclerView.Adapter<AdapterOdt.ViewHolderEquipos> implements View.OnClickListener{
    ArrayList<odtEntity> listaOdtEntity;
    private View.OnClickListener listener;
    public AdapterOdt(ArrayList<odtEntity> listaOdtEntity) {
        this.listaOdtEntity = listaOdtEntity;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolderEquipos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_odt, null, false);
        view.setOnClickListener(this);
        return new ViewHolderEquipos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOdt.ViewHolderEquipos holder, int i) {
        holder.txtSolicitud.setText(listaOdtEntity.get(i).getSol_id());
        holder.txtManifiesto.setText(listaOdtEntity.get(i).getSol_manifiesto());
        holder.txtCliente.setText(listaOdtEntity.get(i).getCli_nombre());
        holder.txtTecnico.setText(listaOdtEntity.get(i).getTec_nombre());
        holder.txtConductor.setText(listaOdtEntity.get(i).getConductor());
       /* holder.txtManifiesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),DesechoActivity.class);
                intent.putExtra("sol_id",listaOdt.get(i).sol_id);
                //System.out.println("Ingreso al ODT"+listaOdt.get(i).sol_id);

            }
        });*/
    }

    @Override
    public int getItemCount() {
       return listaOdtEntity.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
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
