package com.example.plusambiente;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "detalle_odt")
public class desechosEntity {

    @PrimaryKey (autoGenerate = true)
    public int id_detalle;
    public String dsol_id;
    public String sol_id;
    public String des_codigo;
    public String des_descripcion;
    public String cat_unidad;
    public String cat_peligroso;
    public String dsol_cantidad;

    public desechosEntity(String dsol_id, String sol_id, String des_codigo, String des_descripcion, String cat_unidad, String cat_peligroso, String dsol_cantidad) {
        this.dsol_id = dsol_id;
        this.sol_id = sol_id;
        this.des_codigo = des_codigo;
        this.des_descripcion = des_descripcion;
        this.cat_unidad = cat_unidad;
        this.cat_peligroso = cat_peligroso;
        this.dsol_cantidad = dsol_cantidad;
    }

    public String getDsol_id() {
        return dsol_id;
    }

    public void setDsol_id(String dsol_id) {
        this.dsol_id = dsol_id;
    }

    public String getSol_id() {
        return sol_id;
    }

    public void setSol_id(String sol_id) {
        this.sol_id = sol_id;
    }

    public String getDes_codigo() {
        return des_codigo;
    }

    public void setDes_codigo(String des_codigo) {
        this.des_codigo = des_codigo;
    }

    public String getDes_descripcion() {
        return des_descripcion;
    }

    public void setDes_descripcion(String des_descripcion) {
        this.des_descripcion = des_descripcion;
    }

    public String getCat_unidad() {
        return cat_unidad;
    }

    public void setCat_unidad(String cat_unidad) {
        this.cat_unidad = cat_unidad;
    }

    public String getCat_peligroso() {
        return cat_peligroso;
    }

    public void setCat_peligroso(String cat_peligroso) {
        this.cat_peligroso = cat_peligroso;
    }

    public String getDsol_cantidad() {
        return dsol_cantidad;
    }

    public void setDsol_cantidad(String dsol_cantidad) {
        this.dsol_cantidad = dsol_cantidad;
    }
}
