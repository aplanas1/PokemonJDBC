package jdbc;

import static jdbc.Main.conn;

public class Home {

    private Order order = new Order();

    /**
     * Constructor
     */
    public Home() {
    }

    /**
     * Metodo principal donde se ejecutan los menus y las "ordenes"
     */
    public void show(){
        if (conn != null) {
            System.out.println("Sucesfull connection");

            Menu menu = new Menu();
            int opcio;
            opcio = menu.mainMenu();

            switch (opcio) {
                case 1:
                    order.addData();
                    break;
                case 2:
                    order.showAllPokemons();
                    break;
                case 3:
                    order.searchPokemon();
                    break;
                case 4:
                    opcio = menu.typeMenu();
                    switch (opcio) {
                        case 1:
                            order.searchPokemonsByType("Tipo fuego");
                            break;
                        case 2:
                            order.searchPokemonsByType("Tipo agua");
                            break;
                        case 3:
                            order.searchPokemonsByType("Tipo planta");
                            break;
                    }
                    break;
                case 5:
                    opcio = menu.habMenu();
                    switch (opcio) {
                        case 1:
                            order.searchPokemonsByHabilidad("Mar llamas");
                            break;
                        case 2:
                            order.searchPokemonsByHabilidad("Torrente");
                            break;
                        case 3:
                            order.searchPokemonsByHabilidad("Espesura");
                            break;
                    }
                    break;
                case 6:
                    order.editPokemonName();
                    break;
                case 7:
                    order.deletePokemon();
                    break;
                case 8:
                    opcio = menu.typeMenu();
                    switch (opcio) {
                        case 1:
                            order.deletePokemonByType("Tipo fuego");
                            break;
                        case 2:
                            order.deletePokemonByType("Tipo agua");
                            break;
                        case 3:
                            order.deletePokemonByType("Tipo planta");
                            break;
                    }
            }
        } else {
            System.out.println("Connection failed");
        }
    }
}
