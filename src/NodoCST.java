/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
import java.util.ArrayList;

/**
 * Sirve para representar un nodo genérico en el Árbol Sintáctico.
 * Sirve para almacenar tanto No Terminales (ej: "Expresion") como Terminales (ej: "+", "int").
 */
public class NodoCST {
    public String lexema; // El nombre del nodo o el valor del token
    public String tipo;
    public ArrayList<NodoCST> hijos; // Lista de sub-nodos (ramas)

    public NodoCST(String lexema) {
        this.lexema = lexema;
        this.tipo = "";
        this.hijos = new ArrayList<>();
    }


    public NodoCST(String lexema, String tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.hijos = new ArrayList<>();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    // Método para agregar hijo nodo
    public void addHijo(NodoCST hijo) {
        if (hijo != null) {
            this.hijos.add(hijo);
        }
    }

    public void addHijo(String lexemaHijo) {
        this.hijos.add(new NodoCST(lexemaHijo));
    }

    /**
     * Método Recursivo de Impresión
     * Recorre el árbol en profundidad (DFS) e imprimir la jerarquía
     * en consola.
     */
    public void arbol() {
        if (this.hijos.size() > 0) {
            // Imprime el Padre
            System.out.println("Padre: " + this.lexema);
            
            // Imprime la lista de hijos inmediatos (solo nombres)
            System.out.println("Hijos:");
            for (NodoCST hijo : this.hijos) {
                System.out.println("\t" + hijo.lexema);
            }

            // Llamada recursiva para imprimir los sub-árboles
            for (NodoCST hijo : this.hijos) {
                hijo.arbol();
            }
        } else {
            // Es una hoja
            System.out.println("Nodo: " + this.lexema);
        }
    }
}