package com.example.plusambiente;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class AdapterDetalle extends RecyclerView.Adapter<AdapterDetalle.ViewHolderDetalle> implements View.OnClickListener {
    ArrayList<desechosEntity> listaDetalle;
    String[] cantidades, detalleSolicitud;
    private View.OnClickListener listener;
    public AdapterDetalle(ArrayList<desechosEntity> listaDetalle) {
        this.listaDetalle = listaDetalle;
        cantidades = new  String[listaDetalle.size()];
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override

   public AdapterDetalle.ViewHolderDetalle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item, null, false);
        view.setOnClickListener(this);
        return new AdapterDetalle.ViewHolderDetalle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterDetalle.ViewHolderDetalle holder, int i) {
        holder.txtSolicitud.setText(listaDetalle.get(i).getSol_id());
        holder.txtDsolid.setText(listaDetalle.get(i).getDsol_id());
        holder.txtDescripcion.setText(listaDetalle.get(i).getDes_codigo());
        holder.txtDescripcion.setText(listaDetalle.get(i).getDes_descripcion());
        holder.txtUnidad.setText(listaDetalle.get(i).getCat_unidad());
        holder.txtPeligroso.setText(listaDetalle.get(i).getCat_peligroso());

    }

    @Override
    public int getItemCount() {
        if (listaDetalle != null){
            return listaDetalle.size();
        }else{
            return 0;
        }

    }

    public String[] getCantidades() {
        return cantidades;
    }

    public String[] getDetalleSolicitud() {
        return detalleSolicitud;
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

    public class ViewHolderDetalle extends RecyclerView.ViewHolder {
        TextView txtSolicitud, txtDsolid, txtDescripcion, txtUnidad, txtPeligroso;
        EditText edtCantidad;
        public ViewHolderDetalle(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtSolicitud = itemView.findViewById(R.id.txtSolicitud);
            txtDsolid = itemView.findViewById(R.id.txtDsolid);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtUnidad = itemView.findViewById(R.id.txtUnidad);
            txtPeligroso = itemView.findViewById(R.id.txtPeligroso);
            edtCantidad = itemView.findViewById(R.id.edtCantidad);
            edtCantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    cantidades[getAbsoluteAdapterPosition()] = s.toString();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });
            /*txtDsolid.addExtraDataToAccessibilityNodeInfo(new AccessibilityNodeInfo(
                    detalleSolicitud[getAbsoluteAdapterPosition()] =
            ));*/
        }
    }
}
