package rikkei.academy.service.user;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {
    private Connection connection = ConnectMySQL.getConnection();
    private final String CREATE_USER = "INSERT INTO users(name, username, email, password) values (?,?,?,?);";
    private final String INSERT_ROLE_ID_USER_TO ="INSERT INTO user_role(user_id, role_id) values (?,?);";
    private final String FIND_ALL_USERNAME = "SELECT username from users;";
    private final String FIND_ALL_EMAIL = "SELECT email from users;";
    private final String SQL_CHECK_LOGIN = "select id, name, email from `users` where username = ? and password = ?;";
    @Override
    public void save(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int user_id = 0;
            while (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_ROLE_ID_USER_TO);
            Set<Role> roles = user.getRoles();
            List<Role> roleList = new ArrayList<>();
            for (int i = 0; i < roleList.size(); i++) {
                preparedStatement1.setInt(1,user_id);
                preparedStatement1.setInt(2,roleList.get(i).getId());
                preparedStatement1.executeQuery();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existedByUsername(String username) {
        List<String> listUsername = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERNAME);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listUsername.add(resultSet.getString("username"));
            }
            for (int i = 0; i < listUsername.size(); i++) {
                if (username.equals(listUsername.get(i))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        List<String> listEmail = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EMAIL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listEmail.add(resultSet.getString("email"));
            }
            for (int i = 0; i < listEmail.size(); i++) {
                if (email.equals(listEmail.get(i))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user = null;
        try (
                PreparedStatement ps = connection.prepareStatement(SQL_CHECK_LOGIN)
        ) {
            connection.setAutoCommit(false);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);

                user = new User(id, name, username, email, password);
                Set<Role> roleSet = new HashSet<>();
                String SQL_FIND_ROLE = "select r.id, rolename from role_user ru join role r on ru.id_role = r.id where id_user = ?";
                PreparedStatement ps_role = connection.prepareStatement(SQL_FIND_ROLE);
                ps_role.setInt(1, user.getId());
                ResultSet rs_role = ps_role.executeQuery();

                while (rs_role.next()) {
                    int id_role = rs_role.getInt(1);
                    String roleName = rs_role.getString(2);
                    roleSet.add(new Role(id_role, RoleName.valueOf(roleName.toUpperCase())));
                }

                user.setRoles(roleSet);

            }


            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
