package com.company.service;

import com.company.database.Database;
import com.company.model.Product;

import java.sql.*;
import java.util.Optional;

public class ProductService {

    public static void loadProductList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.productList.clear();

                String query = " SELECT * FROM product WHERE NOT deleted; ";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int categoryId = resultSet.getInt("category_id");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Product product = new Product(id, categoryId, name, price, image, deleted);

                    Database.productList.add(product);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void addProduct(Product product) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " INSERT INTO product(name, category_id, price, image)" +
                    " VALUES(?, ?, ?, ?); ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getCategoryId());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setString(4, product.getImage());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        loadProductList();
    }

    public static void deleteProduct(Integer id) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " DELETE FROM product WHERE id = ? ;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setInt(1, id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println("executeUpdate = " + executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Product getProductById(Integer productId) {
        Optional<Product> optional = Database.productList.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void updateProductName(Integer id, String text) {
        Connection connection = Database.getConnection();
        if (connection != null) {


            String query = " UPDATE product SET name = ? WHERE id = ? ;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setString(1, text);
                preparedStatement.setInt(2, id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println("executeUpdate = " + executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }


    public static void updateProductPrice(Double productPrice, Integer id) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " UPDATE product SET price = ? WHERE id = ? ;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setDouble(1, productPrice);
                preparedStatement.setInt(2, id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println("executeUpdate = " + executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }

}
