package planificador_procesos.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ejercicio4_Aleatorios2 {

    //EL PROGRAMA COMIENZA EJECUTANDO PROCESO HIJO, pidiendole una cadena, esta cadena la convierte en un int
    //este int aleatorio que se genera se lo pasa al bucle for donde el hijo 2 interactua tantas veces como N
    //si sale un 2 por ejemplo se pasan 2 cadenas  a mayusculas y luego vuelve a pedir cadena para el hijo1


    public static void main(String[] args) {

        String line;

        try{

            Process hijo = new ProcessBuilder("src\\ejecutables\\aleatorioshijo.exe").start();
            Process hijo2 = new ProcessBuilder("src\\ejecutables\\mayusculashijo.exe").start();

            //br es un stream de entrada conectado  (mediante una pipe) a la salida estándar del proceso hijo
            // el proceso aleatorios (padre) leerá en él la información que el proceso hijo le deja.
            // en este caso un nuevo número aleatorio
            BufferedReader brHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            // ps es un stream de salida conectado (mediante una pipe) a la entrada estandar del hijo.
            PrintStream psHijo = new PrintStream(hijo.getOutputStream());

            BufferedReader brHijo2 = new BufferedReader(new InputStreamReader(hijo2.getInputStream()));
            PrintStream psHijo2 = new PrintStream(hijo2.getOutputStream());

            //  el proceso padre envía cadenas al hijo ( aquí con cada cadena solicitando un número aleatorio más)
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Introduce una cadena (o 'fin' para terminar): ");
                String input = in.readLine();


                if (input.equals("fin")) {
                    break;
                }
//Si el usuario ingresa cualquier otra cadena, el programa envía esa cadena al proceso hijo (aleatorioshijo.exe) utilizando un PrintStream llamado psHijo.
// Este proceso hijo procesa la cadena y devuelve un número entero n.

                psHijo.println(input); // se envia la cadena al proceso hijo
                psHijo.flush();// garantiza que la cadena se envíe inmediatamente en caso de que haya datos pendientes en el flujo de salida.

                //aqui se lee el numero aleatorio que se genero y se le asigna a N
                int n = Integer.parseInt(brHijo.readLine());
                //ESTE SOUT ES PARA VERIFICAR QUE NUMERO ALEATORIO HA SALIDO
                System.out.println("numero aleatorio generado por el proceso hijo:   -->   "+ n);

                for (int i = 0; i < n; i++) {
                    System.out.println("Introduce una cadena para el proceso hijo2: ");
                    String input2 = in.readLine();

                    psHijo2.println(input2);
                    psHijo2.flush();

                    //Aquí se utiliza una asignación dentro de la condición del if.
                    // Esto significa que primero se ejecutará brHijo2.readLine() para leer una línea y asignarla a la variable line,
                    // y luego se verificará si line es diferente de null. Esto es una forma compacta de realizar ambas acciones.
                    if ((line = brHijo2.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }

        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
