package rikkei.academy.service.user;

import rikkei.academy.model.account.User;
import rikkei.academy.service.IGenericService;

import java.sql.SQLException;

public interface IUserService extends IGenericService<User> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    User findById(int id);
    User findByUsernameAndPassword(String username, String password);
    void changeAvatar(String avatar, int id);
    void update(User user) throws SQLException;

}
