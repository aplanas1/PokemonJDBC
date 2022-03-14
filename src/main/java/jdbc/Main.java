package jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static Connection conn;
    public static void main( String[] args ) throws Exception {
        conn = connect();
        Home home = new Home();
        home.show();
    }

    public static Connection connect() {
        try {
            //Carreguem el driver de postgreSQL
            Class.forName( "org.postgresql.Driver" );

            //Obrim la connexió amb la base de dades anomenada dbMail
            //utilitzant el driver de postgreSQL
            //Ens connectem amb "usuari" amb contrassenya "usuari"
            String dbURL="jdbc:postgresql://192.168.22.193/pokemons";
            Connection conn = DriverManager.getConnection( dbURL, "usuario","password");

            //Tanquem la connexió. No és estríctament necessari, però és un bon hàbit!
            //conn.close();
            return conn;
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
