package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int option;

    public Menu() {
        super();
    }

    /**
     * Menu principal
     * @return
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(" \nMENU PRINCIPAL \n");
            System.out.println("1. Cargar datos. ");
            System.out.println("2. Consultar datos. ");
            System.out.println("3. Buscar pokemon. ");
            System.out.println("4. Filtrar por tipo. ");
            System.out.println("5. Filtrar por habilidad. ");
            System.out.println("6. Editar nombre de pokemon. ");
            System.out.println("7. Eliminar pokemon. ");
            System.out.println("8. Eliminar pokemon por tipo. ");
            System.out.println("0. Salir. ");
            System.out.println("Escoger opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no valido");
                e.printStackTrace();
            }
        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 0);
        return option;
    }

    /**
     * Menu para visualizar los tipos
     * @return
     */
    public int typeMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(" \nMENU DE TIPOS \n");
            System.out.println("1. FUEGO. ");
            System.out.println("2. AGUA. ");
            System.out.println("3. PLANTA. ");
            System.out.println("0. Sortir. ");
            System.out.println("Escoger opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no valido");
                e.printStackTrace();
            }
        } while (option != 1 && option != 2 && option != 3 && option != 0);
        return option;
    }

    /**
     * Menu para visulaizar las habilidades
     * @return
     */
    public int habMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(" \nMENU DE HABILIDADES \n");
            System.out.println("1. Mar llamas. ");
            System.out.println("2. Torrente. ");
            System.out.println("3. Espesura. ");
            System.out.println("0. Sortir. ");
            System.out.println("Escoger opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no valido");
                e.printStackTrace();
            }
        } while (option != 1 && option != 2 && option != 3 && option != 0);
        return option;
    }






}