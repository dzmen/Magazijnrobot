package asrs.controlesysteem.readers;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLReader {

    private Connection connection;
    private String melding;

    public SQLReader() {
        this.checkConnection();
    }

    public String getMelding() {
        return this.melding;
    }

    private void openConnection()
            throws MalformedURLException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "knf01");
            properties.setProperty("password", "P@ssword");
            this.connection = DriverManager.getConnection("jdbc:mysql://db4free.net/knf01", properties);
        } catch (ClassNotFoundException exception) {
            this.melding = "De Mysql driver onbreekt op de computer";
            this.connection = null;
        } catch (SQLException exception) {
            this.melding = "Kan geen verbinding maken met server:" + exception.getMessage();
            this.connection = null;
        }
    }

    public boolean checkConnection() {
        if (this.connection == null) {
            try {
                openConnection();
                this.melding = "Verbinding met database voltooid!";
                return this.connection != null;
            } catch (MalformedURLException exception) {
                this.melding = "MalformedURLException! " + exception.getMessage();
            } catch (InstantiationException exception) {
                this.melding = "InstantiationExceptioon! " + exception.getMessage();
            } catch (IllegalAccessException exception) {
                this.melding = "IllegalAccessException! " + exception.getMessage();
            }
            return false;
        }
        try {
            return !this.connection.isClosed();
        } catch (SQLException localSQLException) {
        }
        return false;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (Exception e) {
            this.melding = "Kon de verbinding niet sluiten! " + e.getMessage();
        }
    }

    public Connection getConnection()
            throws MalformedURLException, InstantiationException, IllegalAccessException {
        if (this.connection == null) {
            openConnection();
            return this.connection;
        }
        try {
            if (this.connection.isClosed()) {
                openConnection();
            }
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(5);
            ResultSet result = statement.executeQuery("SELECT 1");
            if (result.next()) {
                return this.connection;
            }
            openConnection();
            return this.connection;
        } catch (SQLException exception) {
            try {
                openConnection();

                Statement statement = this.connection.createStatement();
                statement.setQueryTimeout(5);
                ResultSet result = statement.executeQuery("SELECT 1");
                if (result.next()) {
                    return this.connection;
                }
            } catch (SQLException exception2) {
                this.melding = "Database verbindings probleem: " + exception2.getMessage();
            }
        }
        return null;
    }

    public ResultSet sqlQuery(String query)
            throws MalformedURLException, InstantiationException, IllegalAccessException, SQLException {
        Connection connectionLocal = getConnection();
        if (connectionLocal == null) {
            return null;
        }
        Statement statement = connectionLocal.createStatement();

        statement.setQueryTimeout(10);

        return statement.executeQuery(query);
    }

    public void updateQuery(String query)
            throws MalformedURLException, InstantiationException, IllegalAccessException {
        try {
            Connection connectionLocal = getConnection();
            if (connectionLocal != null) {
                Statement statement = connectionLocal.createStatement();
                statement.executeUpdate(query);
            }
        } catch (SQLException exception) {
            if (!exception.toString().contains("not return ResultSet")) {
                this.melding = "Exception at SQL UPDATE Query: " + exception;
            }
        }
    }
}
