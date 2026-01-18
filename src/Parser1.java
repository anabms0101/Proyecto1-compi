/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
import java.io.*;
import java_cup.runtime.*;

public class Parser1 {
    public static void main(String[] args) {
        try {
            Reader reader = new FileReader("src/entrada.txt");
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);

            System.out.println("*****INICIANDO ANALISIS*****");
            
            // Lee todo el archivo. Aquí ocurren 3 cosas
            // Se validan las reglas gramaticales.
            // Se llenan las tablas de símbolos.
            // Se construye el árbol CST en memoria.
            parser.parse();
                   
            if (parser.listaErrores.isEmpty()) {
                System.out.println("*****SIN ERRORES*****");
            } else {
                System.out.println("\n*****REPORTE DE ERRORES*****");
                for (Errores err : parser.listaErrores) {
                    System.out.println("Tipo: " + err.tipo + 
                                       " | Mensaje: " + err.mensaje + 
                                       " | Línea: " + err.linea + 
                                       " | Columna: " + err.columna);
                }
            }
            
            
            parser.imprimirTablas();
            System.out.println("*****ANALISIS FINALIZADO*****");
            
            System.out.println("\n***** ARBOL SINTACTICO *****");
            if (parser.raiz != null) {
                // Llamamos al método arbol() sobre la raíz guardada en el parser
                parser.raiz.arbol(); 
            } else {
                System.out.println("El árbol está vacío (hubo errores fatales).");
            }

            System.out.println("\n***** ANALISIS FINALIZADO *****");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}