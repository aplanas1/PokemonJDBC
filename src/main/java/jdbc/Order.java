package main.java.jdbc;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.java.jdbc.Main.conn;

public class Order {

    public Order() {
    }

    public static void addData() {
        String FILENAME = "src/main/resources/pokemons.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String sqlTipo = "INSERT INTO tipos(tipo) VALUES(?)";
        String sqlHab = "INSERT INTO habilidades(habilidad) VALUES(?)";
        String sqlPok = "INSERT INTO pokemons(nombre, tipo1, tipo2, habilidad1, habilidad2, descripcion) VALUES(?,?,?,?,?,?)";

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList list = doc.getElementsByTagName("Pokemons");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get text
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String tipo1 = element.getElementsByTagName("tipo1").item(0).getTextContent();
                    String tipo2 = element.getElementsByTagName("tipo2").item(0).getTextContent();
                    String habilidad1 = element.getElementsByTagName("habilidad1").item(0).getTextContent();
                    String habilidad2 = element.getElementsByTagName("habilidad2").item(0).getTextContent();
                    String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();

                    PreparedStatement pstTipo = conn.prepareStatement(sqlTipo);
                    try {
                        pstTipo.setString(1, tipo1);
                        pstTipo.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pstTipo.setString(1, tipo2);
                        pstTipo.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    PreparedStatement pstHab = conn.prepareStatement(sqlHab);
                    try {
                        pstHab.setString(1, habilidad1);
                        pstHab.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        pstHab.setString(1, habilidad2);
                        pstHab.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    PreparedStatement pstPok = conn.prepareStatement(sqlPok);

                    pstPok.setString(1, nombre);
                    pstPok.setString(2, tipo1);
                    pstPok.setString(3, tipo2);
                    pstPok.setString(4, habilidad1);
                    pstPok.setString(5, habilidad2);
                    pstPok.setString(6, descripcion);

                    pstPok.executeUpdate();

                }
            }

        } catch (ParserConfigurationException | SAXException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPokemons() {
        try {
            String sql = "SELECT * FROM pokemons";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                System.out.println("Nombre: " + rs.getString("nombre")+" | "+ rs.getString("tipo1")+" | "+ rs.getString("tipo2")
                        +" | Habilidad1: "+ rs.getString("habilidad1")+" | Habilidad2: "+ rs.getString("habilidad2")
                        +" | Descripcion: "+ rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}