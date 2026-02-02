import java.io.PrintWriter;

public class Generador {
    private PrintWriter writer;
    private int labelCounter = 0;

    public Generador(String rutaSalida) {
        try {
            writer = new PrintWriter(rutaSalida, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Escribir cabecera básica
    public void iniciarCodigo() {
        writer.println(".data");
    }

    public void iniciarTexto() {
        writer.println("");
        writer.println(".text");
        writer.println(".globl main");
    }
    
    // Declarar variable global
    public void declararGlobal(String nombre, String tipo) {
        // En MIPS, .word usa 4 bytes. 
        writer.println(nombre + ": .word 0");
    }

    // Instrucciones MIPS genéricas
    public void print(String instr) {
        writer.println("\t" + instr);
    }
    
    public void comment(String c) {
        writer.println("\t# " + c);
    }
    
    // Generar etiquetas únicas
    public String nuevaEtiqueta() {
        return "L" + (labelCounter++);
    }
    
    public void label(String lbl) {
        writer.println(lbl + ":");
    }

    public void cerrar() {
        // Syscall para salir
        print("li $v0, 10");
        print("syscall");
        writer.close();
    }
    
    // LÓGICA DE PILA
    
    // Push: Meter valor de $t0 a la pila
    public void push() {
        print("sw $t0, 0($sp)");  // Guardar $t0 en el tope
        print("addiu $sp, $sp, -4"); // Mover el tope hacia abajo
    }
    
    // Pop: Sacar valor de la pila a $t0
    public void pop() {
        print("addiu $sp, $sp, 4"); // Recuperar espacio
        print("lw $t0, 0($sp)");    // Cargar a $t0
    }
    
    // Pop a $t1 (para el segundo operando)
    public void pop2() {
        print("addiu $sp, $sp, 4");
        print("lw $t1, 0($sp)");    // Cargar a $t1
    }
}