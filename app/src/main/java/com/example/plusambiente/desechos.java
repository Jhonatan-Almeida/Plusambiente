package com.example.plusambiente;

public class desechos {
    String sol_id, des_codigo, des_descripcion, cat_unidad, cat_peligroso, dsol_cantidad;


    public desechos(String sol_id, String des_codigo, String des_descripcion, String cat_unidad, String cat_peligroso, String dsol_cantidad) {
        this.sol_id = sol_id;
        this.des_codigo = des_codigo;
        this.des_descripcion = des_descripcion;
        this.cat_unidad = cat_unidad;
        this.cat_peligroso = cat_peligroso;
        this.dsol_cantidad = dsol_cantidad;
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
