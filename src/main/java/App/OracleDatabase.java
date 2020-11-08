package App;

import multichain.command.MultichainException;

import java.security.InvalidParameterException;
import java.sql.*;

public class OracleDatabase {
    private static Connection connection;
    private static Statement sentencia;
    private static ResultSet resultado;

    private static void startConnection() {
        System.out.println("\nConexión a la base de datos...");

        try { // Se carga el driver JDBC-ODBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el driver JDBC");
            System.out.println(e);
            return;
        }
        try { // Se establece la conexión con la base de datos
            connection = DriverManager.getConnection
                    ("jdbc:oracle:thin:@192.168.0.3:1521:xe", "gato", "gato");
            sentencia = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("No hay conexión con la base de datos.");
            System.out.println(e);
        }
    }

    private static void closeConnection() {
        try {
            connection.close(); //Cierre de la conexión
            System.out.println("Conexión con la BD cerrada");
        } catch (SQLException e) {
            System.out.println("Error: " +
                    e.getMessage());
        }
    }

    static void insertUsuario(String nombreUsuario, String contrasena) throws MultichainException, SQLException {

        if (getUsuarioByNombreUsuario(nombreUsuario) == null) {

            String direccion = MultiChainAPI.getNewAddress();

            try {
                startConnection();
                System.out.println("Insert usuario");
                String query = String.format("INSERT INTO usuario" +
                        " VALUES('%s', '%s', '%s')", nombreUsuario, contrasena, direccion);

                resultado = sentencia.executeQuery(query);
                closeConnection();

            } catch (SQLException e) {
                System.out.println("Error in Insert usuario");
                System.out.println("Error: " +
                        e.getMessage());
                closeConnection();
                throw e;
            }
        }
        else{
            throw new InvalidParameterException("El nombre de usuario ingresado ya existe.");
        }
    }

    static Usuario getUsuario(String nombreUsuario, String contrasena) {
        startConnection();

        try {
            System.out.println("Select usuario by nombreUsuario and contrasena");
            String query = String.format("SELECT * FROM usuario WHERE" +
                    " nombre_usuario = '%s' AND contrasena = '%s'", nombreUsuario, contrasena);

            resultado = sentencia.executeQuery(query);

            if (resultado.next()) {
                Usuario usuario = new Usuario(resultado.getString("nombre_usuario"),
                        resultado.getString("contrasena"),
                        resultado.getString("direccion"));
                closeConnection();
                return usuario;
            }

            closeConnection();
            return null;

        } catch (SQLException e) {
            System.out.println("Error in select usuario by nombreUsuario and contrasena");
            System.out.println("Error: " +
                    e.getMessage());
            closeConnection();
            return null;
        }
    }

    static Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
        startConnection();

        try {
            System.out.println("Select usuario by nombreUsuario");
            String query = String.format("SELECT * FROM usuario WHERE" +
                    " nombre_usuario = '%s'", nombreUsuario);

            resultado = sentencia.executeQuery(query);

            if (resultado.next()) {
               Usuario usuario = new Usuario(resultado.getString("nombre_usuario"),
                        resultado.getString("contrasena"),
                        resultado.getString("direccion"));
                closeConnection();
                return usuario;

            }

            closeConnection();
            return null;

        } catch (SQLException e) {
            System.out.println("Error in select usuario by nombreUsuario");
            System.out.println("Error: " +
                    e.getMessage());
            closeConnection();
            return null;
        }
    }

}


