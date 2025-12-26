/* JFlex example: partial Java language lexer specification */
   import java_cup.runtime.*;

   /* This class is a simple example lexer */
%%

%class Lexer
%unicode
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();
  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }

  private void reportError(String msg) {
    System.err.println(msg);
  }

%}
   
   /* cualquier salto de linea */
   LineTerminator = \r|\n|\r\n
   /* cualquier caracter que no sea salto de linea */
   InputCharacter = [^\r\n]
   /* espacios tab saltos de linea */
   WhiteSpace     = {LineTerminator} | [ \t\f]

   /* comments */
   /* Expresiones para reconocer los comentarios de una linea y multiples lineas*/
   SingleLineComment = \|{InputCharacter}*{LineTerminator}?
   MultLineComment = "є" ([^э])* "э"
   Comment = {SingleLineComment} | {MultLineComment}

   /* nombres de variables y funciones letra seguida de letras, dígitos o _ */
   Identifier = [a-zA-Z]([a-zA-Z0-9_])*


   /* expresiones para reconocer digitos */
   Digit = [0-9]
   DigitNoZero = [1-9]

   /* orden de reglas */
   /* expresiones para reconocer literales enteros y flotantes */
   DecFloatLiteral   = [-]? (0 | {DigitNoZero}{Digit}*) \. {Digit}+
   DecIntegerLiteral = [-]? (0 | {DigitNoZero}{Digit}*)

   /* expresion para reconocer literales de un solo caracter entre comillas */
   CharLiteral = \'([^\'\\]|\\.)\'

%state STRING

%%

   /* Reglas para palabras reservadas */
   /* Estado    Patron              Accion */
   /* Estado indica que la regla solo aplica en un estado del lexer */
   /* Patron expresión regular que se va a reconocer */
   /* Accion código Java que se ejecuta al reconocer el patrónr */
   <YYINITIAL> "abstract"           { return symbol(sym.ABSTRACT); }
   <YYINITIAL> "break"              { return symbol(sym.BREAK); }

   <YYINITIAL> "function"           { return symbol(sym.FUNCTION); }
   <YYINITIAL> "return"             { return symbol(sym.RETURN); }
   <YYINITIAL> "local"              { return symbol(sym.LOCAL); }
   <YYINITIAL> "navidad"            { return symbol(sym.NAVIDAD); }
   <YYINITIAL> "coal"               { return symbol(sym.COAL); }

   <YYINITIAL> "decide"             { return symbol(sym.DECIDE); }
   <YYINITIAL> "of"                 { return symbol(sym.OF); }
   <YYINITIAL> "end"                { return symbol(sym.END); }
   <YYINITIAL> "exit"               { return symbol(sym.EXIT); }
   <YYINITIAL> "when"               { return symbol(sym.WHEN); }
   <YYINITIAL> "if"                 { return symbol(sym.IF); }
   <YYINITIAL> "else"               { return symbol(sym.ELSE); }
   <YYINITIAL> "loop"               { return symbol(sym.LOOP); }
   <YYINITIAL> "while"              { return symbol(sym.WHILE); }
   <YYINITIAL> "for"                { return symbol(sym.FOR); }
   <YYINITIAL> "world"              { return symbol(sym.WORLD); }
   <YYINITIAL> "show"               { return symbol(sym.SHOW); }
   <YYINITIAL> "get"                { return symbol(sym.GET); }
   <YYINITIAL> "gift"               { return symbol(sym.GIFT); }

   <YYINITIAL> "int"                { return symbol(sym.INT); }
   <YYINITIAL> "float"              { return symbol(sym.FLOAT); }
   <YYINITIAL> "boolean"            { return symbol(sym.BOOLEAN); }
   <YYINITIAL> "char"               { return symbol(sym.CHAR); }
   <YYINITIAL> "string"             { return symbol(sym.STRING); }

   /*Reglas para operadores compuestos */
   <YYINITIAL> "==" { return symbol(sym.IQIQ); }
   <YYINITIAL> ">=" { return symbol(sym.MAIQ); }
   <YYINITIAL> "<=" { return symbol(sym.MEIQ); }
   <YYINITIAL> "!=" { return symbol(sym.DIFQ); }

   /* operadores simples */
   <YYINITIAL> "="  { return symbol(sym.IQ); }
   <YYINITIAL> ">"  { return symbol(sym.MAQ); }
   <YYINITIAL> "<"  { return symbol(sym.MEQ); }

   <YYINITIAL> "++" { return symbol(sym.INC); }
   <YYINITIAL> "--" { return symbol(sym.DEC); }

   <YYINITIAL> "+"  { return symbol(sym.PLUS); }
   <YYINITIAL> "-"  { return symbol(sym.MINUS); }
   <YYINITIAL> "*"  { return symbol(sym.TIMES); }
   <YYINITIAL> "//" { return symbol(sym.DIVINT); }
   <YYINITIAL> "/"  { return symbol(sym.DIV); }
   <YYINITIAL> "%"  { return symbol(sym.MOD); }
   <YYINITIAL> "^"  { return symbol(sym.POW); }

   /* Regla para operadores de control */
   <YYINITIAL> "->" { return symbol(sym.ARROW); }

   /* Reglas para operadores logicos */
   <YYINITIAL> "@" { return symbol(sym.AND); }
   <YYINITIAL> "~" { return symbol(sym.OR); }
   <YYINITIAL> "Σ" { return symbol(sym.NOT); }



   /* Reglas para delimitadores y simbolos */
   <YYINITIAL> "¿"  { return symbol(sym.PARENI); }
   <YYINITIAL> "?"  { return symbol(sym.PAREND); }

   <YYINITIAL> "¡"  { return symbol(sym.BLOQUE_ABI); }
   <YYINITIAL> "!"  { return symbol(sym.BLOQUE_CER); }

   <YYINITIAL> ","  { return symbol(sym.COMA); }
   <YYINITIAL> ";"  { return symbol(sym.SEMICOLON); }
   <YYINITIAL> "endl" { return symbol(sym.ENDL); }

   <YYINITIAL> "["  { return symbol(sym.BRACKETI); }
   <YYINITIAL> "]"  { return symbol(sym.BRACKETD); }

   /* Reglas para literales */
   <YYINITIAL> {DecFloatLiteral}   { return symbol(sym.FLOAT_LITERAL); }
   <YYINITIAL> {DecIntegerLiteral} { return symbol(sym.INTEGER_LITERAL); }

   <YYINITIAL> "true"              { return symbol(sym.BOOL_LITERAL); }
   <YYINITIAL> "false"             { return symbol(sym.BOOL_LITERAL); }

   <YYINITIAL> {CharLiteral}       {return symbol(sym.CHAR_LITERAL, yytext()); }

   /* Regla para identificadores */    
   /* yytext devuelve el lexema encontrado */
   <YYINITIAL> {Identifier}        { return symbol(sym.IDENTIFIER, yytext()); }

   /* Regla para comentarios */
   <YYINITIAL> {Comment} { /* ignore */ }

   /* Regla para espacios en blanco */
   <YYINITIAL> {WhiteSpace} { /* ignore */ }

   /* Regla para el manejo de strings */
   /* Cuando encuentra ", entra en el estado STRING */
   /* Mientras este en STRING, acumula todos los caracteres hasta cerrar las comillas */
   <YYINITIAL> \"                  {string.setLength(0);yybegin(STRING);}

    <STRING> {
        \" {
            yybegin(YYINITIAL);
            return symbol(sym.STRING_LITERAL, string.toString());
        }
        [^\n\r\"\\]+ { string.append(yytext()); }
        \\t          { string.append('\t'); }
        \\n          { string.append('\n'); }
        \\r          { string.append('\r'); }
        \\\"         { string.append('\"'); }
        \\\\         { string.append('\\'); }
    }

    /* Para reportar los errores, ignorar los caracteres erroneos y continuar */
    /* Se reportan en la terminal */
    /* Cualquier carácter que no cumpla ninguna regla: se reporta como error, pero no detiene la ejecución modo pánico */
    <YYINITIAL> . {
        // Reporta el error
        System.err.println(
            "Error léxico: caracter ilegal '" + yytext() +
            "' en línea " + (yyline + 1) +
            ", columna " + (yycolumn + 1)
        );
    }

