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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static jdbc.Main.conn;

public class Order {

    Scanner scanner = new Scanner(System.in);

    public Order() {
    }

    /**
     * Lee y añade los datos del .xml a la base de datos
     */
    public void addData() {
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
                    try {
                        Element element = (Element) node;

                        // get text
                        String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                        String tipo1 = element.getElementsByTagName("tipo1").item(0).getTextContent();
                        String tipo2 = element.getElementsByTagName("tipo2").item(0).getTextContent();
                        String habilidad1 = element.getElementsByTagName("habilidad1").item(0).getTextContent();
                        String habilidad2 = element.getElementsByTagName("habilidad2").item(0).getTextContent();
                        String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();

                        PreparedStatement pstTipo = conn.prepareStatement(sqlTipo);
                            pstTipo.setString(1, tipo1);
                            pstTipo.executeUpdate();
                            pstTipo.setString(1, tipo2);
                            pstTipo.executeUpdate();

                        PreparedStatement pstHab = conn.prepareStatement(sqlHab);
                            pstHab.setString(1, habilidad1);
                            pstHab.executeUpdate();
                            pstHab.setString(1, habilidad2);
                            pstHab.executeUpdate();

                        PreparedStatement pstPok = conn.prepareStatement(sqlPok);

                        pstPok.setString(1, nombre);
                        pstPok.setString(2, tipo1);
                        pstPok.setString(3, tipo2);
                        pstPok.setString(4, habilidad1);
                        pstPok.setString(5, habilidad2);
                        pstPok.setString(6, descripcion);

                        pstPok.executeUpdate();

                        System.out.println(nombre + " insertado");
                    } catch (SQLException e) {

                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Te muestra todos los pokemons de la base de datos
     */
    public void showAllPokemons() {

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

    /**
     * Te permite buscar un pokemon por su nombre
     */
    public void searchPokemon() {

        try {
            System.out.println("Introduce el nombre del pokemon: ");
            String nomb = scanner.nextLine();
            String sql = "SELECT * FROM pokemons WHERE nombre=\'" + nomb + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + " | " + rs.getString("tipo1") + " | " + rs.getString("tipo2")
                        + " | Habilidad1: " + rs.getString("habilidad1") + " | Habilidad2: " + rs.getString("habilidad2")
                        + " | Descripcion: " + rs.getString("descripcion"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Te permite filtrar por su tipo a los pokemons
     * @param tipo el tipo a filtrar
     */
    public void searchPokemonsByType(String tipo) {
        try {
            String sql = "SELECT * FROM pokemons WHERE tipo1= \'" + tipo + "\' OR tipo2=\'" + tipo + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + " | " + rs.getString("tipo1") + " | " + rs.getString("tipo2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Te permite filtrar a los pokemons por su habilidad
     * @param habilidad la habilidad a filtrar
     */
    public static void searchPokemonsByHabilidad(String habilidad) {

        try {

            String sql = "SELECT * FROM pokemons WHERE habilidad1= \'" + habilidad + "\' OR habilidad2=\'" + habilidad + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                System.out.println("Nombre: " + rs.getString("nombre")+" | "+ rs.getString("habilidad1")+" | "+ rs.getString("habilidad2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Te permite editar un pokemon a tu eleccion
     */
    public void editPokemonName() {
        try {
            System.out.println("Introduce el nombre del pokemon a editar: ");
            String nomb1 = scanner.nextLine();
            System.out.println("Introduce el nuevo nombre del pokemon: ");
            String nomb2 = scanner.nextLine();
            String sql = "UPDATE pokemons SET nombre= \'" + nomb2 + "\' WHERE nombre= \'" + nomb1 + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println(nomb1 + " a cambiado a " + nomb2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Te permite eliminar un pokemon a tu eleccion
     */
    public void deletePokemon() {

        try {
            System.out.println("Introduce el nombre del pokemon: ");
            String nomb = scanner.nextLine();
            String sql = "DELETE FROM pokemons WHERE nombre=\'" + nomb + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            System.out.println(nomb + " eliminado por la ordern 66");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Te permite eliminar pokemons por su tipo
     * @param tipo el tipo a purgar
     */
    public void deletePokemonByType(String tipo) {

        try {
            String sql = "DELETE FROM pokemons WHERE tipo1= \'" + tipo + "\' OR tipo2=\'" + tipo + "\'";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            System.out.println("Pokemons del "+ tipo +" eliminados por la orden 66");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
