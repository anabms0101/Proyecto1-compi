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
            // Configuración de archivos
            String rutaEntrada = "src/entrada.txt";
            String rutaSalida = "src/salida.asm"; // Archivo destino MIPS

            System.out.println("Abriendo archivo: " + rutaEntrada);
            Reader reader = new FileReader(rutaEntrada);
            Lexer lexer = new Lexer(reader);
            
            // Instanciar el Generador de Código
            // Este objeto se encargará de escribir las instrucciones .asm
            Generador generador = new Generador(rutaSalida);

            // Iniciar el Parser pasándole el Lexer Y el Generador
            Parser parser = new Parser(lexer, generador);

            System.out.println("***** INICIANDO ANALISIS Y GENERACION *****");
            
            // Ejecutar análisis
            // Al ejecutar parse(), se validan tipos Y se escribe en el archivo .asm simultáneamente
            parser.parse();
                    
            // Imprimir Errores (Sintácticos y Semánticos)
            if (parser.listaErrores.isEmpty()) {
                System.out.println(">> Codigo MIPS generado exitosamente en: " + rutaSalida);
            } else {
                System.out.println("\n***** REPORTE DE ERRORES *****");
                for (Object obj : parser.listaErrores) {
                    if (obj instanceof Errores) {
                        Errores err = (Errores) obj;
                        System.out.println("Tipo: " + err.tipo + 
                                           " | Linea: " + err.linea + 
                                           " | Mensaje: " + err.mensaje);
                    }
                }
                System.out.println("\n(i) El archivo " + rutaSalida + " se generó, pero puede contener errores lógicos debido a los fallos detectados.");
            }
            
            // Imprimir Tablas de Símbolos
            System.out.println("\n***** ESTADO DE LAS TABLAS DE SIMBOLOS *****");
            parser.imprimirTablas();
            
            System.out.println("\n***** PROCESO FINALIZADO *****");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}