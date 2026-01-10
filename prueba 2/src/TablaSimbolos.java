import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class TablaSimbolos {

    static class Simbolo {
        String lexema;
        String token;
        int linea;
        int columna;

        Simbolo(String l, String t, int li, int co) {
            lexema = l;
            token = t;
            linea = li;
            columna = co;
        }
    }

    private ArrayList<Simbolo> tabla = new ArrayList<>();

    public void insertar(Symbol s) {
        tabla.add(new Simbolo(
            s.value.toString(),
            sym.terminalNames[s.sym],
            s.left,
            s.right
        ));
    }

    public void imprimir() {
        System.out.println("TABLA DE SIMBOLOS");
        System.out.println("Lexema | Token | Linea | Columna");
        for (Simbolo s : tabla) {
            System.out.println(
                s.lexema + " | " + s.token + " | " + s.linea + " | " + s.columna
            );
        }
    }
}
