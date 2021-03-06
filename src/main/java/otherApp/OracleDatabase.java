package otherApp;

import java.sql.*;
import java.util.ArrayList;

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

    static ArrayList<Double> getMinerValueUSDList(String miner, String initialTime, String finalTime) {
        startConnection();

        try {
            System.out.println("Select BLOQUE by miner");
            String query = String.format("SELECT TRANSACCION.value_usd FROM TRANSACCION " +
                    "INNER JOIN BLOQUE ON BLOQUE.id = TRANSACCION.block_id " +
                    "WHERE BLOQUE.miner = '%s' AND TRANSACCION.value_usd > 0 AND " +
                    "TO_CHAR(BLOQUE.time,'hh24:mi') BETWEEN '%s' AND '%s'", miner, initialTime, finalTime);

            resultado = sentencia.executeQuery(query);

            ArrayList<Double> values = new ArrayList<Double>();

            while (resultado.next()) {
                values.add(resultado.getDouble("value_usd"));
            }

            closeConnection();
            return values;

        } catch (SQLException e) {
            System.out.println("Error in select Select BLOQUE by miner");
            System.out.println("Error: " +
                    e.getMessage());
            closeConnection();
            return null;
        }
    }

    static ArrayList<Transaction> getTransactionsByTime(String initialTime, String finalTime) {
        startConnection();

        try {
            System.out.println("Select TRANSACTION by TIME");
            String query = String.format("SELECT x, y, value_usd, fee_usd, block_id, sender, recipient, TO_CHAR(time,'hh24:mi') FROM TRANSACCION " +
                    "WHERE TO_CHAR(TRANSACCION.time,'hh24:mi') BETWEEN '%s' AND '%s'", initialTime, finalTime);

            resultado = sentencia.executeQuery(query);

            ArrayList<Transaction> transactions = new ArrayList<>();

            while (resultado.next()) {
                Double x = resultado.getDouble("x");
                Double y = resultado.getDouble("y");
                Double value_usd = resultado.getDouble("value_usd");
                Double fee_usd = resultado.getDouble("fee_usd");
                String block_id = resultado.getString("block_id");
                String sender = resultado.getString("sender");
                String recipient = resultado.getString("recipient");
                String date = resultado.getString("TO_CHAR(time,hh24:mi)");

                Transaction transaction = new Transaction(x, y, value_usd, fee_usd, block_id, sender, recipient, date);
                transactions.add(transaction);

            }

            closeConnection();
            return transactions;

        } catch (SQLException e) {
            System.out.println("Error in select Select TRANSACTION by TIME");
            System.out.println("Error: " +
                    e.getMessage());
            closeConnection();
            return null;
        }
    }

}


