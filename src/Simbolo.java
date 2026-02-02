/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
import java.util.ArrayList;
public class Simbolo {
    public String nombre;
    public String tipo;
    public String clase;   // variable, funcion, parametro
    public String ambito;
    public int linea;
    public int columna;
    // Guarda la posición en memoria (ej: -4, -8, -12) respecto al Frame Pointer
    public int offset;
    
    public ArrayList<String> listaParametros;
    
    public boolean esArreglo;
    public int filas;
    public int cols;

public Simbolo(String n, String t, String c, String a, int l, int col) {
        this.nombre = n;
        this.tipo = t;
        this.clase = c;
        this.ambito = a;
        this.linea = l;
        this.columna = col;
        
        // Inicializamos las listas y banderas por defecto
        this.listaParametros = new ArrayList<>();
        this.esArreglo = false;
        this.filas = 0;
        this.cols = 0;
        this.offset = 0;
    }

    public void addParametro(String tipoParam) {
            this.listaParametros.add(tipoParam);
    }
    
    
    public void setArreglo2D(int f, int c) {
        this.esArreglo = true;
        this.filas = f;
        this.cols = c;
        
        if(!this.tipo.endsWith("[][]")) { 
            this.tipo = this.tipo + "[][]"; 
        }
    }
    
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public String getTipo() {
        return this.tipo;
    }

}