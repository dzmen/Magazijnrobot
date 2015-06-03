package asrs.controlesysteem.readers;

import asrs.controlesysteem.bestelling.Locatie;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SQLReader {

    private Connection connection;
    private String melding;
    private boolean werkt = false;

    public SQLReader() {
        werkt = this.checkConnection();
    }

    public boolean getWerkt() {
        return this.werkt;
    }

    public String getMelding() {
        return this.melding;
    }

    //Maak verbinding met de database
    private void openConnection()
            throws MalformedURLException, InstantiationException, IllegalAccessException {
        try {
            //Pak de mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //Genereer een virtual propertie bestand met username en password van database
            Properties properties = new Properties();
            properties.setProperty("user", "knf01");
            properties.setProperty("password", "P@ssword");
            //Maak verbinding met de database
            this.connection = DriverManager.getConnection("jdbc:mysql://db4free.net/knf01", properties);
        } catch (ClassNotFoundException exception) {
            this.melding = "De Mysql driver onbreekt op de computer";
            this.connection = null;
        } catch (SQLException exception) {
            this.melding = "Kan geen verbinding maken met server:" + exception.getMessage();
            this.connection = null;
        }
    }

    //Controlleerd of de verbinding nog steeds werkt
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

    //Sluit de verbinding
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (Exception e) {
            this.melding = "Kon de verbinding niet sluiten! " + e.getMessage();
        }
    }

    //Krijg een mysqlverbinding. Hij kijkt eerst of er nog een verbinding open is. Is dat niet het geval dan opent hij er één
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
            //Controle of de verbinding werkt
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(5);
            ResultSet result = statement.executeQuery("SELECT 1");
            if (result.next()) {
                return this.connection;
            }
            openConnection();
            return this.connection;
        } catch (SQLException exception) {
            //Nog een keer proberen ook al deed de verbinding het eerst niet
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

    //Krijg een artikel uit een database
    public ArrayList<Object> getArtikel(int nr) {
        ArrayList<Object> list = new ArrayList<>();
        try {
            ResultSet rs = sqlQuery("SELECT * FROM Artikelen WHERE nummer = " + nr);
            while (rs.next()) {
                list.add(nr);//0
                list.add(rs.getString("naam"));//1
                list.add(new Locatie(rs.getInt("locx"), rs.getInt("locy")));//2
                list.add(rs.getDouble("grote"));//3
                list.add(rs.getInt("aantal"));//4
            }
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    //Zorgt ervoor dat wanneer er een artikel uit het schap is gehaald, de database aantal wordt aangepast
    public boolean updateArtikelAantal(int artikelnr) {
        try {
            updateQuery("UPDATE Artikelen set aantal = aantal - 1 where nummer = " + artikelnr);
            return true;
        } catch (Exception ex) {
            return false;
        }
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
