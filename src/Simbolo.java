/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
public class Simbolo {
    public String nombre;
    public String tipo;
    public String clase;   // variable, funcion, parametro
    public String ambito;
    public int linea;
    public int columna;

    public Simbolo(String n, String t, String c, String a, int l, int col) {
        nombre = n;
        tipo = t;
        clase = c;
        ambito = a;
        linea = l;
        columna = col;
    }
}