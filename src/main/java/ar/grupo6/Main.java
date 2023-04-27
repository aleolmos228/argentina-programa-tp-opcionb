package ar.grupo6;
import ar.grupo6.datos.ProcesarArchivo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el path completo del archivo: ");
        String rutaCompletaArchivo = sc.nextLine();

        //String rutaCompletaArchivo = "C:\\xampp5\\htdocs\\cursos\\java\\tp_ejemplo_profesor\\HarryPotter-data.csv";

        ProcesarArchivo proc = new ProcesarArchivo(rutaCompletaArchivo);


    }
}