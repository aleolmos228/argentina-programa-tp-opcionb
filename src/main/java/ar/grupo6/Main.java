package ar.grupo6;
import ar.grupo6.datos.ProcesarArchivo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el path completo del archivo: ");
        String rutaCompletaArchivo = sc.nextLine();

        ProcesarArchivo proc = new ProcesarArchivo(rutaCompletaArchivo);

    }
}