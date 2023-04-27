package ar.grupo6.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Casa {
    private int idCasa;
    private String nombre;
    private ArrayList<Estudiante> estudiantes ;

    public Casa(String nombre, int idCasa){
        this.idCasa = idCasa;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<Estudiante>();
    }

    public void agregarEstudiante(Estudiante e){
        //if (e != null && e.getNombreCasa().equals(nombre)){
        //    e.setCasa(this);
            estudiantes.add(e);
        //}
        //else
        //    throw new RuntimeException("Nombre de casa inválido. [" + e.getNombreCasa() + "]");
    }

    public int cantidadEstudiantes(){
        return this.estudiantes.size();
    }

    public int cantidadEstudiantesNoHumanos(){
        int cant = 0;
        for (Estudiante estudiante : estudiantes) {
            String especie = estudiante.getEspecie();
            if(!especie.equals("Human") ) cant++;
           // System.out.println("-" + especie+"-");
        }
        return cant;
    }

    public int cantidadEstudiantesMasculinos(){
        return this.estudiantes.size();
    }

    public int cantidadEstudiantesFemeninos(){
        return this.estudiantes.size();
    }

    public int cantidadEstudiantesNoBinarios(){
        return this.estudiantes.size();
    }

    public int estudiantesNoHumanos(){
       // return this.estudiantes.
        return 0;
    }
    public String listadoEstudiantesHumanos(){
        return "";
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getIdCasa() {
        return this.idCasa;
    }

}
