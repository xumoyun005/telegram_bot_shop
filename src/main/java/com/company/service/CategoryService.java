package com.company.service;

import com.company.container.ComponentContainer;
import com.company.database.Database;
import com.company.model.Category;

import java.sql.*;

public class CategoryService {
    public static Category getCategoryById(Integer id) {

        loadCategoryList();

        for (Category category : Database.categoryList) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public static void loadCategoryList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.categoryList.clear();

                String query = " SELECT * FROM category WHERE NOT deleted; ";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Category category = new Category(id, name, deleted);

                    Database.categoryList.add(category);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCategory(Category category) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " INSERT INTO category(name)" +
                    " VALUES(?); ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, category.getName());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        loadCategoryList();
    }

    public static void deleteCategory(Integer id) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " DELETE FROM category where id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println("executeUpdate = " + executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadCategoryList();
    }
}

