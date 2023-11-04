package planificador_procesos.src.ejemplo8.src.ejemplo8;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
// redireccionando la entrada estandar de un proceso
// y sus salidas estandar y de errores
//nuestro fichero.bat será la entrada para el comando CMD

public class Ejemplo8 {


    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD");

        File fBat = new File("fichero.bat");
        File fOut = new File("salidaEjemplo8.txt");
        File fErr = new File("errorEjemplo8.txt");

        pb.redirectInput(fBat);
        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        pb.start();

    }

}
