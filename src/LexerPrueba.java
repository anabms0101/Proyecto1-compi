/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 * Entrada: Un archivo txt con codigo o cualquier tipo de texto
 * Salida: Un archivo txt con la información detallada de cada token: tipo, lexema, línea y columna
 * Restricciones: Archivos de formato no valido, es decir que no sean txt
 * Objetivo: Escribir en un archivo todos los tokens encontrados, incluyendo identificador asociado con el lexema
 */
import java.io.*;
import java_cup.runtime.Symbol;

public class LexerPrueba {

    public static void main(String[] args) {
        try {
            Reader reader = new FileReader("entrada.txt");
            Lexer lexer = new Lexer(reader);

            BufferedWriter writer = new BufferedWriter(
                new FileWriter("tokens1.txt")
            );

            Symbol token;

            while ((token = lexer.next_token()).sym != sym.EOF) {
                writer.write(
                    String.format(
                        "Tipo: %-15s Lexema: %-15s Linea=%d Columna=%d",
                        sym.terminalNames[token.sym],
                        token.value,
                        token.left,
                        token.right
                    )
                );
                writer.newLine();
            }

            writer.close();
            System.out.println("Tokens generados");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}