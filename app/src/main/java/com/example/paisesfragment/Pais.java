package com.example.paisesfragment;

import java.io.Serializable;

public class Pais implements Serializable {
    private final String codigo;
    private final String nombre;
    private final int poblacion;
    private final String capital;
    private final String isoAlpha3;

    public Pais(String codigo, String nombre, int poblacion, String capital, String isoAlpha3) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.capital = capital;
        this.isoAlpha3 = isoAlpha3;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public String getCapital() {
        return capital;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }
}
