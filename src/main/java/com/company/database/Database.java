package com.company.database;

import com.company.model.CartProduct;
import com.company.model.Category;
import com.company.model.Customer;
import com.company.model.Product;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public interface Database {
    List<Customer> customerList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    List<CartProduct> CART_PRODUCTS = new ArrayList<>();

    static Connection getConnection() {

        final String DB_USERNAME = "postgres";
        final String DB_PASSWORD = "5432";
        final String DB_URL = "jdbc:postgresql://localhost:5432/apple_shop_bot_db";

        Connection conn = null;
        try {

            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (conn != null) {
                System.out.println("Connection worked");
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
