package ar.grupo6.datos;
import ar.grupo6.clases.Estudiante;
import ar.grupo6.clases.Casa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ProcesarArchivo {

    String nombreArchivo;
    Map<String, Integer> casas = new HashMap<>();

    Casa Gryffindor;
    Casa Slytherin;
    Casa Hufflepuff;
    Casa Ravenclaw;
    public ProcesarArchivo(String nombreArchivo) throws IOException {
        this.nombreArchivo = nombreArchivo;
        this.crearCasas();
        this.leerArchivo();
    }
    public void leerArchivo() throws IOException {

        String cadena = "";
        Path f = Paths.get(this.nombreArchivo);
        //System.out.println(f.toAbsolutePath());
        String[] linea;

        if (Files.exists(f) && Files.isReadable(f)) {
            int i = 0;
            for (String line : Files.readAllLines(f)) {
                if (i > 0) {
                    linea = line.split(",");
                    //int numero, String nombre, char genero, String trabajo ,Casa casa,String especie, String status
                    //try {
                    if(linea.length == 7) {
                        int numero;
                        String nombre;
                        char genero;
                        String trabajo;
                        String nombreCasa;
                        int idCasa;
                        String especie;
                        String status;
                        numero = Integer.parseInt(linea[0]);
                        nombre = linea[1];
                        if (linea[2].length() > 0) {
                            genero = linea[2].charAt(0);
                        } else {
                            genero = '0';
                        }
                        trabajo = linea[3];
                        nombreCasa = linea[4];
                        idCasa = this.getIdCasa(nombreCasa);
                        especie = linea[5];
                        //System.out.println(line);
                        //System.out.println(linea.length);
                        status = linea[6];
                        Estudiante e = new Estudiante(numero, nombre, genero, trabajo, idCasa, especie, status);
                        //System.out.println(idCasa);
                        switch (idCasa) {
                            case 1:
                                Gryffindor.agregarEstudiante(e);
                                break;
                            case 2:
                                Slytherin.agregarEstudiante(e);
                                break;
                            case 3:
                                Hufflepuff.agregarEstudiante(e);
                                break;

                            case 4:
                                Ravenclaw.agregarEstudiante(e);
                                break;

                            default:
                                break;
                        }
                    }
                    //} catch (Exception e) {
                    //    System.out.println(e);
                    //}
                    //System.out.println(e.getEstudiante());
                }
                i++;
            }

            this.getListadoEstudiantesPorCasa();
            this.getListadoNoHumanos();



        }else{
            System.out.println("El archivo no existe");
        }
    }

    public void crearCasas(){

        Gryffindor = new Casa("Gryffindor", 1);
        Slytherin = new Casa("Slytherin", 2);
        Hufflepuff = new Casa("Hufflepuff", 3);
        Ravenclaw = new Casa("Gryffindor", 4);

        casas.put("Gryffindor", 1);
        casas.put("Slytherin", 2);
        casas.put("Hufflepuff", 3);
        casas.put("Ravenclaw", 4);

    }

    public int getIdCasa(String nombreCasa){
        int idCasa;
        if (casas.containsKey(nombreCasa)) {
            //System.out.println(nombreCasa);
            idCasa = casas.get(nombreCasa);
        }else{
            idCasa = 0;
        }
            return idCasa;

    }

    public void getListadoEstudiantesPorCasa(){
        int cantEstudiantesGryffindor = Gryffindor.cantidadEstudiantes();
        int cantEstudiantesSlytherin = Slytherin.cantidadEstudiantes();
        int cantEstudiantesHufflepuff = Hufflepuff.cantidadEstudiantes();
        int cantEstudiantesRavenclaw = Ravenclaw.cantidadEstudiantes();
        System.out.println("");
        System.out.println("LISTADOS DE ESTUDIANTES POR CASA");
        System.out.println("--------------------------------");
        System.out.println("Gryffindor: " + cantEstudiantesGryffindor + " estudiantes.");
        System.out.println("Slytherin: " + cantEstudiantesSlytherin + " estudiantes.");
        System.out.println("Gryffindor: " + cantEstudiantesHufflepuff + " estudiantes.");
        System.out.println("Gryffindor: " + cantEstudiantesRavenclaw + " estudiantes.");
    }

    public void getListadoNoHumanos(){
        int cantEstudiantesNoHumanoGryffindor = Gryffindor.cantidadEstudiantesNoHumanos();
        int cantEstudiantesNoHumanosSlytherin = Slytherin.cantidadEstudiantesNoHumanos();
        int cantEstudiantesNoHumanosHufflepuff = Hufflepuff.cantidadEstudiantesNoHumanos();
        int cantEstudiantesNoHumanosRavenclaw = Ravenclaw.cantidadEstudiantesNoHumanos();
        System.out.println("");
        System.out.println("LISTADOS DE CANTIDAD DE NO HUMANOS POR CASA");
        System.out.println("------------------------------------------");
        System.out.println("Gryffindor: " + cantEstudiantesNoHumanoGryffindor + " estudiantes.");
        System.out.println("Slytherin: " + cantEstudiantesNoHumanosSlytherin + " estudiantes.");
        System.out.println("Gryffindor: " + cantEstudiantesNoHumanosHufflepuff + " estudiantes.");
        System.out.println("Gryffindor: " + cantEstudiantesNoHumanosRavenclaw + " estudiantes.");

    }

}
