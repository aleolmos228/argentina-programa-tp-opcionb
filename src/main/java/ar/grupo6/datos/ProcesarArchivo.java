package ar.grupo6.datos;
import ar.grupo6.bd.ConexionSQLite;
import ar.grupo6.bd.GestionarDatosSQL;
import ar.grupo6.clases.Estudiante;
import ar.grupo6.clases.Casa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcesarArchivo {

    String nombreArchivo;
    Map<String, Integer> casas = new HashMap<>();

    Casa Gryffindor;
    Casa Slytherin;
    Casa Hufflepuff;
    Casa Ravenclaw;
    public ProcesarArchivo(String nombreArchivo) throws IOException, SQLException {

        this.nombreArchivo = nombreArchivo;

        this.crearCasas();

        this.leerArchivo();

        this.cargarBD();

    }
    public void leerArchivo() throws IOException {

        String cadena = "";
        Path f = Paths.get(this.nombreArchivo);
        //System.out.println(f.toAbsolutePath());
        String[] linea;

        if (Files.exists(f) && Files.isReadable(f)) {
            int i = 0;
            for (String line : Files.readAllLines(f)) {
                //if (i > 0) {
                    linea = line.split(",");

                    try {

                    if(linea[3].equals("Student")) {
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
                    } catch (Exception e) {
                    //    System.out.println(e);
                    }
                    //System.out.println(e.getEstudiante());
                //}
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

        this.printCantEstPorCasa("Gryffindor",Gryffindor.cantidadEstudiantes(),true, "LISTADOS DE ESTUDIANTES POR CASA (desde clase archivo)");
        this.printCantEstPorCasa("Slytherin",Slytherin.cantidadEstudiantes(), false,"");
        this.printCantEstPorCasa("Hufflepuff",Hufflepuff.cantidadEstudiantes(), false,"");
        this.printCantEstPorCasa("Ravenclaw",Ravenclaw.cantidadEstudiantes(), false,"");

    }

    public void getListadoNoHumanos(){
        String str   = "\nLISTADO DE ESTUDIANTES NO HUMANOS (desde clase archivo)";
        str   += "\n---------------------------------";
        str += Gryffindor.listadoEstudiantesNoHumanos();
        str += Slytherin.listadoEstudiantesNoHumanos();
        str += Hufflepuff.listadoEstudiantesNoHumanos();
        str += Ravenclaw.listadoEstudiantesNoHumanos();

        System.out.println(str);

        //this.printCantEstPorCasa("Gryffindor",Gryffindor.cantidadEstudiantesNoHumanos(),true, "LISTADOS DE ESTUDIANTES NO HUMANOS POR CASA (desde Archivo - Clase");
        //this.printCantEstPorCasa("Slytherin",Slytherin.cantidadEstudiantesNoHumanos(), false,"");
        //this.printCantEstPorCasa("Hufflepuff",Hufflepuff.cantidadEstudiantesNoHumanos(), false,"");
        //this.printCantEstPorCasa("Ravenclaw",Ravenclaw.cantidadEstudiantesNoHumanos(), false,"");
    }

    public void cargarBD() throws SQLException {
        GestionarDatosSQL db = new GestionarDatosSQL();
        db.cargarCasas();

        ArrayList<Estudiante> estudiantesGryffindor = Gryffindor.getListadoEstudiantes();
        db.cargarEstudiantes(estudiantesGryffindor);

        ArrayList<Estudiante> estudiantesSlytherin = Slytherin.getListadoEstudiantes();
        db.cargarEstudiantes(estudiantesSlytherin);

        ArrayList<Estudiante> estudiantesHufflepuff = Hufflepuff.getListadoEstudiantes();
        db.cargarEstudiantes(estudiantesHufflepuff);

        ArrayList<Estudiante> estudiantesRavenclaw = Ravenclaw.getListadoEstudiantes();
        db.cargarEstudiantes(estudiantesRavenclaw);


        this.printCantEstPorCasa("Gryffindor",db.getCantEstXCasa("Gryffindor"),true,"LISTADOS DE ESTUDIANTES POR CASA (desde BD)");
        this.printCantEstPorCasa("Slytherin",db.getCantEstXCasa("Slytherin"),false,"");
        this.printCantEstPorCasa("Hufflepuff",db.getCantEstXCasa("Hufflepuff"),false,"");
        this.printCantEstPorCasa("Ravenclaw",db.getCantEstXCasa("Ravenclaw"),false,"");

        //this.printCantEstPorCasa("Gryffindor",db.getCantEstNoHumanXCasa("Gryffindor","Human"),true,"LISTADOS DE ESTUDIANTES NO HUMANOS POR CASA (desde BD)");
        //this.printCantEstPorCasa("Slytherin",db.getCantEstNoHumanXCasa("Slytherin","Human"),false,"");
        //this.printCantEstPorCasa("Hufflepuff",db.getCantEstNoHumanXCasa("Hufflepuff","Human"),false,"");
        //this.printCantEstPorCasa("Ravenclaw",db.getCantEstNoHumanXCasa("Ravenclaw","Human"),false,"");
        db.getListadoEstNoHuman();
    }

    public void printCantEstPorCasa(String nombreCasa, int cantidad, boolean titulo, String strTitulo ){
        if(titulo) {
            System.out.println("");
            System.out.println(strTitulo);
            System.out.println("------------------------------------------");
        }
        System.out.println(nombreCasa+": " + cantidad + " estudiantes.");
    }






}
