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


    //String sqlCrearTablaEstudiantes = "CREATE TABLE IF NOT EXISTS
    // Estudiantes (
    // numero INTEGER PRIMARY KEY
    // , nombre TEXT,
    // genero TEXT,
    // trabajo TEXT,
    // idCasa INTEGER,
    // especie TEXT,
    // status TEXT,
    // FOREIGN KEY (idCasa) REFERENCES Casas(id));";

    public int getNumero(){
        return this.numero;
    }
    public String getNombre(){
        return this.nombre;
    }
    public char getGenero(){
        return this.genero;
    }
    public String getTrabajo(){
        return this.trabajo;
    }
    public int getIdCasa(){
        return this.idCasa;
    }
    public String getEspecie(){
        return this.especie;
    }
    public String getStatus(){
        return this.status;
    }


}
