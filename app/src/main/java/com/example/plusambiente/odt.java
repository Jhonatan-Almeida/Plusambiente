package com.example.plusambiente;

public class odt{
    String sol_id,sol_manifiesto,cli_nombre,tec_nombre,conductor;

    public odt(String sol_id, String sol_manifiesto, String cli_nombre, String tec_nombre, String conductor) {
        this.sol_id = sol_id;
        this.sol_manifiesto = sol_manifiesto;
        this.cli_nombre = cli_nombre;
        this.tec_nombre = tec_nombre;
        this.conductor = conductor;
    }

    public String getSol_id() {
        return sol_id;
    }

    public void setSol_id(String sol_id) {
        this.sol_id = sol_id;
    }

    public String getSol_manifiesto() {
        return sol_manifiesto;
    }

    public void setSol_manifiesto(String sol_manifiesto) {
        this.sol_manifiesto = sol_manifiesto;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public String getTec_nombre() {
        return tec_nombre;
    }

    public void setTec_nombre(String tec_nombre) {
        this.tec_nombre = tec_nombre;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}
