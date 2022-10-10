package rikkei.academy.service.singer;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.singer.Singer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerServiceIMPL implements ISingerService{
    private Connection connection = ConnectMySQL.getConnection();
    private final String CREATE_SINGER = "INSERT INTO singer(name,birthday,gender) VALUES (?,?,?)";
    private final String LIST_SINGER = "SELECT * FROM singer";
    private final String SINGER_BY_ID = "SELECT * FROM singer WHERE id=?;";
    private final String UPDATE_SINGER = "UPDATE singer SET name=?, brithday=?,gender=?, WHERE id=?;";
    private final String DELETE_SINGER = "DELETE FROM singer WHERE id=?";
    private final String SEARCH_BY_NAME_SINGER = "SELECT * FROM singer WHERE name LIKE ?";
    @Override
    public List<Singer> findByName(String nameSingerSearch) {
        List<Singer> singerListSearch = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_SINGER);
            preparedStatement.setString(1, '%'+nameSingerSearch+'%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                int birthday = resultSet.getInt("birthday");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");

                Singer singer = new Singer(id,name,birthday,gender);
                singerListSearch.add(singer);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singerListSearch;
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> singerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_SINGER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int birthday= resultSet.getInt("birthday");
                String gender = resultSet.getString("gender");
                Singer singer = new Singer(id, name, birthday,gender);
                singerList.add(singer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singerList;
    }

    @Override
    public void save(Singer singer) {
        try {
            if (findById(singer.getId())==null){
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SINGER);
                preparedStatement.setString(1, singer.getName());
                preparedStatement.setInt(2, singer.getBirthDay());
                preparedStatement.setString(3, singer.getGender());
                preparedStatement.executeUpdate();
            }else {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SINGER);
                preparedStatement.setString(1, singer.getName());
                preparedStatement.setInt(2, singer.getBirthDay());
                preparedStatement.setString(3, singer.getGender());
                preparedStatement.setInt(4, singer.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Singer findById(int id) {
        Singer singer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SINGER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int birthday = resultSet.getInt("birthday");
                String gender = resultSet.getString("gender");

                singer = new Singer(id, name, birthday,gender);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singer;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINGER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
