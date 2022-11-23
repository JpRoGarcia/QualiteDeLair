package com.example.qualitedelair;

public class Estaciones {
    String Codigo;
    String Nombre;
    String Municipio;
    double Latitud;
    double Longitud;
    int Concentracion;

    public Estaciones(String codigo, String nombre, String municipio, double latitud, double longitud, int concentracion) {
        Codigo = codigo;
        Nombre = nombre;
        Municipio = municipio;
        Latitud = latitud;
        Longitud = longitud;
        Concentracion = concentracion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public int getConcentracion() {
        return Concentracion;
    }

    public void setConcentracion(int concentracion) {
        Concentracion = concentracion;
    }
}
