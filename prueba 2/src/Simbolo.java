/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anabe
 */
public class Simbolo {
    public String nombre;
    public String token;
    public String ambito;
    public int linea;
    public int columna;

    public Simbolo(String nombre, String token, String ambito, int linea, int columna) {
        this.nombre = nombre;
        this.token = token;
        this.ambito = ambito;
        this.linea = linea;
        this.columna = columna;
    }
}

