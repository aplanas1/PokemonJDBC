package main.java.jdbc;

import java.sql.Connection;

import static main.java.jdbc.Main.conn;

public class Home {

    private Order order = new Order();

    public Home() {
    }

    public void show(){
        if (conn != null) {
            System.out.println("Sucesfull connection");

            Menu menu = new Menu();
            int opcio;
            opcio = menu.mainMenu();

            switch (opcio) {
                case 1:
                    order.addData();
                case 2:
                    order.showPokemons();
            }

        } else {
            System.out.println("Connection failed");
        }
    }
}
