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
import java.util.*;
import java_cup.runtime.Symbol;

public class LexerPrueba {

    public static void main(String[] args) {
        try {
            Reader reader = new FileReader("src/entrada.txt");
            Lexer lexer = new Lexer(reader);

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("salida.txt")
            );

            // ===== TABLAS DE SÍMBOLOS =====
            TablaSimbolos tablaGlobal = new TablaSimbolos();
            Map<String, TablaSimbolos> tablasFunciones = new LinkedHashMap<>();

            TablaSimbolos tablaActual = tablaGlobal;
            String ambitoActual = "global";

            boolean viendoGift = false;
            boolean viendoTipoFuncion = false;
            boolean dentroParametros = false;
            boolean esperandoTipo = false;

            String tipoActual = "";
            String nombreFuncion = "";

            Symbol token;

            while ((token = lexer.next_token()).sym != sym.EOF) {

                String tipoToken = sym.terminalNames[token.sym];
                String lexema = token.value != null ? token.value.toString() : "";

                // ===== SALIDA DE TOKENS =====
                writer.write(
                        String.format(
                                "Tipo: %-15s Lexema: %-15s Linea=%d Columna=%d",
                                tipoToken,
                                lexema,
                                token.left,
                                token.right
                        )
                );
                writer.newLine();

                /* =============================
                   MANEJO DE FUNCIONES
                   ============================= */

                if (token.sym == sym.GIFT) {
                    viendoGift = true;
                    viendoTipoFuncion = true;
                    continue;
                }

                // Tipo de retorno de función
                if (viendoTipoFuncion &&
                        (token.sym == sym.INT || token.sym == sym.FLOAT ||
                         token.sym == sym.BOOLEAN || token.sym == sym.CHAR ||
                         token.sym == sym.COAL)) {

                    tipoActual = tipoToken;
                    viendoTipoFuncion = false;
                    continue;
                }

                // Nombre de la función
                if (viendoGift && token.sym == sym.IDENTIFIER) {
                    nombreFuncion = lexema;
                    ambitoActual = nombreFuncion;

                    tablaActual = new TablaSimbolos();
                    tablasFunciones.put(nombreFuncion, tablaActual);

                    tablaGlobal.insertar(new Simbolo(
                            nombreFuncion,
                            tipoActual,
                            "funcion",
                            "global",
                            token.left,
                            token.right
                    ));

                    viendoGift = false;
                    continue;
                }

                /* =============================
                   PARÁMETROS
                   ============================= */

                if (token.sym == sym.PARENI) {
                    dentroParametros = true;
                    esperandoTipo = true;
                    continue;
                }

                if (token.sym == sym.PAREND) {
                    dentroParametros = false;
                    continue;
                }

                if (dentroParametros &&
                        (token.sym == sym.INT || token.sym == sym.FLOAT ||
                         token.sym == sym.BOOLEAN || token.sym == sym.CHAR)) {

                    tipoActual = tipoToken;
                    esperandoTipo = false;
                    continue;
                }

                if (dentroParametros && token.sym == sym.IDENTIFIER) {
                    tablaActual.insertar(new Simbolo(
                            lexema,
                            tipoActual,
                            "parametro",
                            ambitoActual,
                            token.left,
                            token.right
                    ));
                    esperandoTipo = true;
                    continue;
                }

                /* =============================
                   VARIABLES GLOBALES
                   ============================= */

                if (token.sym == sym.WORLD) {
                    esperandoTipo = true;
                    continue;
                }

                if (esperandoTipo &&
                        (token.sym == sym.INT || token.sym == sym.FLOAT ||
                         token.sym == sym.BOOLEAN || token.sym == sym.CHAR)) {

                    tipoActual = tipoToken;
                    esperandoTipo = false;
                    continue;
                }

                if (token.sym == sym.IDENTIFIER && ambitoActual.equals("global")) {
                    tablaGlobal.insertar(new Simbolo(
                            lexema,
                            tipoActual,
                            "variable",
                            "global",
                            token.left,
                            token.right
                    ));
                    continue;
                }

                /* =============================
                   VARIABLES LOCALES
                   ============================= */

                if (token.sym == sym.LOCAL) {
                    esperandoTipo = true;
                    continue;
                }

                if (!ambitoActual.equals("global") && token.sym == sym.IDENTIFIER) {
                    tablaActual.insertar(new Simbolo(
                            lexema,
                            tipoActual,
                            "variable",
                            ambitoActual,
                            token.left,
                            token.right
                    ));
                }

                /* =============================
                   FIN DE FUNCIÓN
                   ============================= */

                if (token.sym == sym.BLOQUE_CER) {
                    ambitoActual = "global";
                    tablaActual = tablaGlobal;
                }
            }

            writer.close();

            // ===== MOSTRAR RESULTADOS =====
            tablaGlobal.imprimir("GLOBAL");

            for (String f : tablasFunciones.keySet()) {
                tablasFunciones.get(f).imprimir("FUNCION " + f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

