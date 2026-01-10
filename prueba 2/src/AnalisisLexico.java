import java.io.FileReader;
import java.io.Reader;
import java_cup.runtime.Symbol;

public class AnalisisLexico {

    public static TablaSimbolos tabla = new TablaSimbolos();

    public static void salida() {
        try {
            Reader reader = new FileReader("src/entrada.txt");
            Lexer lexer = new Lexer(reader);

            Symbol token;

            while ((token = lexer.next_token()).sym != sym.EOF) {

                // SOLO agregamos ciertos tokens
                if (token.sym == sym.IDENTIFIER) {
                    tabla.insertar(token);
                }

            }

            tabla.imprimir();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
