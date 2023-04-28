package ar.grupo6.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionSQLite {
    Connection conexion = null;
    public Connection conectar() {

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:tp.sqlite";
            conexion = DriverManager.getConnection(url);

            // Utilizar la conexión para realizar operaciones en la base de datos
            // Crear la tabla Casas
            String sqlCrearTablaCasas = "CREATE TABLE IF NOT EXISTS  Casas (id INTEGER PRIMARY KEY, nombre TEXT);";
            PreparedStatement statementCrearTablaCasas = conexion.prepareStatement(sqlCrearTablaCasas);
            statementCrearTablaCasas.executeUpdate();

            // Crear la tabla Estudiantes
            String sqlCrearTablaEstudiantes = "CREATE TABLE IF NOT EXISTS  Estudiantes (numero INTEGER PRIMARY KEY, nombre TEXT,  genero TEXT, trabajo TEXT, idCasa INTEGER, especie TEXT, status TEXT, FOREIGN KEY (idCasa) REFERENCES Casas(id));";
            PreparedStatement statementCrearTablaEstudiantes = conexion.prepareStatement(sqlCrearTablaEstudiantes);
            statementCrearTablaEstudiantes.executeUpdate();

            //System.out.println("Conexión a SQLite establecida.");

        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión a SQLite: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } /*finally {
            // Cerrar la conexión
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión a SQLite: " + ex.getMessage());
            }
        }*/
        return conexion;
    }
}