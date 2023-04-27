package ar.grupo6.clases;

public class Estudiante {
    private int numero;
    private String nombre;
    private char genero;

    private String trabajo;
    private int idCasa;
    private String especie;
    private String status;


    public Estudiante(int numero, String nombre, char genero, String trabajo ,int idCasa,String especie, String status){
        this.numero = numero;
        this.nombre = nombre;
        this.genero = genero;
        this.trabajo = trabajo;
        this.idCasa = idCasa;
        this.especie = especie;
        this.status = status;
    }

    public Estudiante getEstudiante(){
        return this;
    }
    public String getEspecie(){
        return this.especie;
    }

}
