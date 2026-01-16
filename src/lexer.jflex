import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();

  // Para tokens sin valor (palabras reservadas, operadores)
  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }

  // Para tokens con valor (identificadores, literales)
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }
%}

/* --- Definiciones --- */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Comentarios */
SingleLineComment = "|" {InputCharacter}* {LineTerminator}?
MultLineComment   = "є" [^э]* "э"
Comment = {SingleLineComment} | {MultLineComment}

/* Identificadores */
Identifier = [a-zA-Z_][a-zA-Z0-9_]*

/* Dígitos */
Digit = [0-9]
DigitNoZero = [1-9]

/* Literales Numéricos (SOLO POSITIVOS, el parser maneja el menos unario) */
DecFloatLiteral   = (0 | {DigitNoZero}{Digit}*) \. {Digit}+
DecIntegerLiteral = (0 | {DigitNoZero}{Digit}*)

/* Literal Char */
CharLiteral = \'([^\'\\]|\\.)\'

%state STRING

%%

/* --- Palabras Reservadas (Prioridad Alta) --- */
<YYINITIAL> "abstract"      { return symbol(sym.ABSTRACT); }
<YYINITIAL> "break"         { return symbol(sym.BREAK); }
<YYINITIAL> "function"      { return symbol(sym.FUNCTION); }
<YYINITIAL> "return"        { return symbol(sym.RETURN); }
<YYINITIAL> "local"         { return symbol(sym.LOCAL); }
<YYINITIAL> "navidad"       { return symbol(sym.NAVIDAD); }
<YYINITIAL> "coal"          { return symbol(sym.COAL); }
<YYINITIAL> "decide"        { return symbol(sym.DECIDE); }
<YYINITIAL> "of"            { return symbol(sym.OF); }
<YYINITIAL> "end"           { return symbol(sym.END); }
<YYINITIAL> "exit"          { return symbol(sym.EXIT); }
<YYINITIAL> "when"          { return symbol(sym.WHEN); }
<YYINITIAL> "if"            { return symbol(sym.IF); }
<YYINITIAL> "else"          { return symbol(sym.ELSE); }
<YYINITIAL> "loop"          { return symbol(sym.LOOP); }
<YYINITIAL> "while"         { return symbol(sym.WHILE); }
<YYINITIAL> "for"           { return symbol(sym.FOR); }
<YYINITIAL> "world"         { return symbol(sym.WORLD); }
<YYINITIAL> "show"          { return symbol(sym.SHOW); }
<YYINITIAL> "get"           { return symbol(sym.GET); }
<YYINITIAL> "gift"          { return symbol(sym.GIFT); }
<YYINITIAL> "endl"          { return symbol(sym.ENDL); }

/* --- Tipos --- */
<YYINITIAL> "int"           { return symbol(sym.INT); }
<YYINITIAL> "float"         { return symbol(sym.FLOAT); }
<YYINITIAL> "boolean"       { return symbol(sym.BOOLEAN); }
<YYINITIAL> "char"          { return symbol(sym.CHAR); }
<YYINITIAL> "string"        { return symbol(sym.STRING); }
<YYINITIAL> "true"          { return symbol(sym.BOOL_LITERAL, Boolean.TRUE); }
<YYINITIAL> "false"         { return symbol(sym.BOOL_LITERAL, Boolean.FALSE); }

/* --- Operadores y Delimitadores --- */
<YYINITIAL> "=="            { return symbol(sym.IQIQ); }
<YYINITIAL> ">="            { return symbol(sym.MAIQ); }
<YYINITIAL> "<="            { return symbol(sym.MEIQ); }
<YYINITIAL> "!="            { return symbol(sym.DIFQ); }
<YYINITIAL> "="             { return symbol(sym.IQ); }
<YYINITIAL> ">"             { return symbol(sym.MAQ); }
<YYINITIAL> "<"             { return symbol(sym.MEQ); }
<YYINITIAL> "++"            { return symbol(sym.INC); }
<YYINITIAL> "--"            { return symbol(sym.DEC); }
<YYINITIAL> "+"             { return symbol(sym.PLUS); }
<YYINITIAL> "-"             { return symbol(sym.MINUS); }
<YYINITIAL> "*"             { return symbol(sym.TIMES); }
<YYINITIAL> "//"            { return symbol(sym.DIVINT); }
<YYINITIAL> "/"             { return symbol(sym.DIV); }
<YYINITIAL> "%"             { return symbol(sym.MOD); }
<YYINITIAL> "^"             { return symbol(sym.POW); }
<YYINITIAL> "->"            { return symbol(sym.ARROW); }
<YYINITIAL> "@"             { return symbol(sym.AND); }
<YYINITIAL> "~"             { return symbol(sym.OR); }
<YYINITIAL> "Σ"             { return symbol(sym.NOT); }
<YYINITIAL> "¿"             { return symbol(sym.PARENI); }
<YYINITIAL> "?"             { return symbol(sym.PAREND); }
<YYINITIAL> "¡"             { return symbol(sym.BLOQUE_ABI); }
<YYINITIAL> "!"             { return symbol(sym.BLOQUE_CER); }
<YYINITIAL> ","             { return symbol(sym.COMA); }
<YYINITIAL> ";"             { return symbol(sym.SEMICOLON); }
<YYINITIAL> "["             { return symbol(sym.BRACKETI); }
<YYINITIAL> "]"             { return symbol(sym.BRACKETD); }

/* --- Literales (Con conversión de tipo correcta) --- */
<YYINITIAL> {DecIntegerLiteral} { 
    return symbol(sym.INTEGER_LITERAL, Integer.parseInt(yytext())); 
}
<YYINITIAL> {DecFloatLiteral} { 
    return symbol(sym.FLOAT_LITERAL, Float.parseFloat(yytext())); 
}
<YYINITIAL> {CharLiteral} { 
    // Quitamos las comillas simples para guardar el char
    String txt = yytext();
    char val = txt.charAt(1); 
    // Nota: esto es básico, para caracteres escapados como '\n' necesitas más lógica
    return symbol(sym.CHAR_LITERAL, val); 
}

/* --- Identificadores (Debe ir DESPUÉS de palabras reservadas) --- */
<YYINITIAL> {Identifier}    { return symbol(sym.IDENTIFIER, yytext()); }

/* --- Comentarios y Espacios --- */
<YYINITIAL> {Comment}       { /* ignorar */ }
<YYINITIAL> {WhiteSpace}    { /* ignorar */ }

/* --- Manejo de Strings --- */
<YYINITIAL> \" { string.setLength(0); yybegin(STRING); }

<STRING> {
  \" { 
      yybegin(YYINITIAL); 
      return symbol(sym.STRING_LITERAL, string.toString()); 
  }
  [^\n\r\"\\]+ { string.append(yytext()); }
  \\t  { string.append('\t'); }
  \\n  { string.append('\n'); }
  \\r  { string.append('\r'); }
  \\\" { string.append('\"'); }
  \\\\ { string.append('\\'); }
}

/* --- Errores --- */
<YYINITIAL> . {
    System.err.println("Error léxico: Caracter ilegal <" + yytext() + "> en linea " + (yyline+1) + ", columna " + (yycolumn+1));
}