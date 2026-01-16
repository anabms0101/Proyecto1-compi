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
            
            
            parser.parse();
            parser.imprimirTablas();
            System.out.println("*****ANALISIS FINALIZADO*****");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}