/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.pkg2;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;

public class Archivo_Fuente {

   public static String leerArchivoFuente(String rutaArchivo) throws IOException {
        Path path = Path.of(rutaArchivo);
        return Files.readString(path);}
  

}
