package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void saveOrUpdate(User user);
    User findById(Long id);
    void delete(Long id);
    List<User> findAll();

}
