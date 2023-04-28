package ar.grupo6.bd;

import ar.grupo6.clases.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class GestionarDatosSQL {
    ConexionSQLite bd = new ConexionSQLite();
    Connection conn = bd.conectar();
    public void cargarCasas() throws SQLException {

        this.insertarCasa(1, "Gryffindor");
        this.insertarCasa(2, "Slytherin");
        this.insertarCasa(3, "Hufflepuff");
        this.insertarCasa(4, "Ravenclaw");

    }
    public void insertarCasa(int idCasa, String nombre) throws SQLException {
        if (!conn.isClosed()) {
            try {
                String sql = "INSERT INTO casas (id, nombre) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, idCasa);
                pstmt.setString(2, nombre);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                //System.out.println(e.getMessage());
            }
        }
        //conn.close();
    }

    public void cargarEstudiantes(ArrayList<Estudiante> estudiantes) throws SQLException {
        for (Estudiante e : estudiantes) {
            this.insertarEstudiante(e);
        }
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
    public void insertarEstudiante(Estudiante es) throws SQLException {
        if (!conn.isClosed()) {
            try {

                String nombre = es.getNombre();

                String sql = "INSERT INTO estudiantes (numero, nombre, genero, trabajo, idCasa, especie, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, es.getNumero());
                pstmt.setString(2, es.getNombre());
                pstmt.setString(3, Character.toString(es.getGenero()));
                pstmt.setString(4, es.getTrabajo());
                pstmt.setInt(5, es.getIdCasa());
                pstmt.setString(6, es.getEspecie());
                pstmt.setString(7, es.getStatus());
                pstmt.executeUpdate();
            } catch (SQLException e) {
               // System.out.println(e.getMessage());
            }
        }
    }

    public int getCantEstXCasa(String nombreCasa) throws SQLException {
        int cantidad = 0;
        if (!conn.isClosed()) {
            String sql = "SELECT COUNT(*) FROM casas JOIN estudiantes " +
                    "ON casas.id = estudiantes.idCasa " +
                    "WHERE casas.nombre = ? AND TRIM(estudiantes.trabajo) = 'Student'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, especie);
            pstmt.setString(1, nombreCasa);
            ResultSet rs = pstmt.executeQuery();
            cantidad = rs.getInt(1);
        }
        return cantidad;
    }

    public int getCantEstNoHumanXCasa(String nombreCasa, String especie) throws SQLException {
        int cantidad = 0;
        if (!conn.isClosed()) {
            String sql = "SELECT COUNT(*) FROM casas JOIN estudiantes " +
                    "ON casas.id = estudiantes.idCasa " +
                    "WHERE casas.nombre = ? AND estudiantes.especie <> ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, especie);
            pstmt.setString(1, nombreCasa);
            pstmt.setString(2, especie);
            ResultSet rs = pstmt.executeQuery();
            cantidad = rs.getInt(1);
        }
        return cantidad;
    }

    public void getListadoEstNoHuman() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String str="";
        str   = "\nLISTADO DE ESTUDIANTES NO HUMANOS (desde bd)";
        str   += "\n---------------------------------";
        int cantidad = 0;
        if (!conn.isClosed()) {
            String sql = "SELECT estudiantes.nombre as estudiante_nombre, casas.nombre as casa_nombre " +
                    "FROM casas JOIN estudiantes " +
                    "ON casas.id = estudiantes.idCasa " +
                    " WHERE estudiantes.trabajo='Student' AND TRIM(especie) != 'Human'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String estudianteNombre = rs.getString("estudiante_nombre");
                String casaNombre = rs.getString("casa_nombre");
                str += "\n* Nombre: " + estudianteNombre + " - Casa: " + casaNombre + "";
                //System.out.println("id = " + id + ", name = " + name + ", value = " + value);
            }
            System.out.println(str);
        }

    }

}
