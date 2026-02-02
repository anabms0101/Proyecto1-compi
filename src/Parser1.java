/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
import java.io.*;

public class Parser1 {
    public static void main(String[] args) {
        try {
            // -----------------------------------------------------------
            // Configuración de archivos
            // -----------------------------------------------------------
            
            // Aquí defines tu archivo de entrada (o podrías usar args[0])
            String rutaEntrada = "src/entrada.txt"; 
            
            String rutaSalida;

            // Lógica para obtener el nombre sin extensión y agregar .ams
            int indicePunto = rutaEntrada.lastIndexOf('.');
            
            if (indicePunto > 0) {
                // Si tiene extensión, cortamos hasta el punto y agregamos .ams
                rutaSalida = rutaEntrada.substring(0, indicePunto) + ".ams";
            } else {
                // Si el archivo no tenía extensión, solo agregamos .ams al final
                rutaSalida = rutaEntrada + ".ams";
            }

            System.out.println("Abriendo archivo: " + rutaEntrada);
            Reader reader = new FileReader(rutaEntrada);
            Lexer lexer = new Lexer(reader);
            
            // Instanciar el Generador de Código con la ruta dinámica
            System.out.println("Archivo de salida configurado: " + rutaSalida);
            Generador generador = new Generador(rutaSalida);

            // Iniciar el Parser pasándole el Lexer Y el Generador
            Parser parser = new Parser(lexer, generador);

            System.out.println("***** INICIANDO ANALISIS Y GENERACION *****");
            
            // Ejecutar análisis
            parser.parse();
                    
            // Imprimir Errores (Sintácticos y Semánticos)
            if (parser.listaErrores.isEmpty()) {
                System.out.println(">> Codigo generado exitosamente en: " + rutaSalida);
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
