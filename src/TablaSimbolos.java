/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
import java.util.*;

public class TablaSimbolos {
    public String nombreAmbito;
    public TablaSimbolos padre; // Enlace a la tabla superior
    private Map<String, Simbolo> tabla = new LinkedHashMap<>();

    // Constructor
    public TablaSimbolos(String nombre, TablaSimbolos padre) {
        this.nombreAmbito = nombre;
        this.padre = padre;
    }

    public boolean insertar(Simbolo s) {
        if (tabla.containsKey(s.nombre)) return false;
        tabla.put(s.nombre, s);
        return true;
    }

    // Busca en la tabla actual, si no está, sube al padre (Recursivo)
    public Simbolo buscar(String nombre) {
        Simbolo s = tabla.get(nombre);
        if (s != null) return s;
        if (padre != null) return padre.buscar(nombre);
        return null;
    }

    public void imprimir() {
        System.out.println("===== TABLA: " + nombreAmbito + " =====");
        if (padre != null) System.out.println("Padre: " + padre.nombreAmbito);
        for (Simbolo s : tabla.values()) {
            System.out.println(String.format("%-10s | %-10s | %-15s | %s", 
                s.nombre, s.tipo, s.clase, "(" + s.linea + "," + s.columna + ")"));
        }
        System.out.println();
    }
}