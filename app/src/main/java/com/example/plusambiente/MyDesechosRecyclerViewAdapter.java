package com.example.plusambiente;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.plusambiente.databinding.FragmentItemBinding;

import java.util.List;


public class MyDesechosRecyclerViewAdapter extends RecyclerView.Adapter<MyDesechosRecyclerViewAdapter.ViewHolder> {

    private final List<desechos> mValues;

    public MyDesechosRecyclerViewAdapter(List<desechos> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Rescatar el elemento que acupa esa posición
        holder.mItem = mValues.get(position);
        holder.txtCodigo.setText(holder.mItem.getDes_codigo());
        holder.txtSolicitud.setText(holder.mItem.getSol_id());
        holder.txtDescripcion.setText(holder.mItem.getDes_descripcion());
        holder.txtUnidad.setText(holder.mItem.getCat_unidad());
        holder.txtPeligroso.setText(holder.mItem.getCat_peligroso());
        holder.edtCantidad.setText(holder.mItem.getDsol_cantidad());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtCodigo;
        public final TextView txtSolicitud;
        public final TextView txtDescripcion;
        public final TextView txtUnidad;
        public final TextView txtPeligroso;
        public final EditText edtCantidad;
        public desechos mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            txtCodigo = binding.txtCodigo;
            txtSolicitud = binding.txtSolicitud;
            txtDescripcion = binding.txtDescripcion;
            txtUnidad = binding.txtUnidad;
            txtPeligroso = binding.txtUnidad;
            edtCantidad = binding.edtCantidad;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtCodigo.getText() + "'";
        }
    }
}