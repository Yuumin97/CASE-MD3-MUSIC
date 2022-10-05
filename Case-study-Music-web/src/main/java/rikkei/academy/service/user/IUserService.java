package rikkei.academy.service.user;

import rikkei.academy.model.account.User;
import rikkei.academy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    User checkLogin(String username, String password);
}
