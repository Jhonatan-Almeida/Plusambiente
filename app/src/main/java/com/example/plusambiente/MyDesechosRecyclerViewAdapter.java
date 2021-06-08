package com.example.plusambiente;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.plusambiente.databinding.FragmentItemBinding;

import org.w3c.dom.Text;

import java.util.List;


public class MyDesechosRecyclerViewAdapter extends RecyclerView.Adapter<MyDesechosRecyclerViewAdapter.ViewHolder> {
    private Context ctx;
    private final List<desechos> mValues;
    String[] cantidades;

    public MyDesechosRecyclerViewAdapter(Context context,List<desechos> items) {
        ctx = context;
        mValues = items;
        cantidades = new String[items.size()];
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
        holder.txtDsolid.setText(holder.mItem.getDsol_id());
        holder.txtDescripcion.setText(holder.mItem.getDes_descripcion());
        holder.txtUnidad.setText(holder.mItem.getCat_unidad());
        holder.txtPeligroso.setText(holder.mItem.getCat_peligroso());
//        holder.edtCantidad.setText(holder.mItem.getDsol_cantidad());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public String[] getCantidades() {
        return cantidades;
    }

    public void setCantidades(String[] cantidades) {
        this.cantidades = cantidades;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //EditText edtCantidad;


        public final TextView txtCodigo;
        public final TextView txtSolicitud;
        public final TextView txtDsolid;
        public final TextView txtDescripcion;
        public final TextView txtUnidad;
        public final TextView txtPeligroso;
        public final EditText edtCantidad;
        public desechos mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            txtCodigo = binding.txtCodigo;
            txtSolicitud = binding.txtSolicitud;
            txtDsolid = binding.txtDsolid;
            txtDescripcion = binding.txtDescripcion;
            txtUnidad = binding.txtUnidad;
            txtPeligroso = binding.txtPeligroso;
            edtCantidad = binding.edtCantidad;
            edtCantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    cantidades[getBindingAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtCodigo.getText() + "'";
        }
    }
}