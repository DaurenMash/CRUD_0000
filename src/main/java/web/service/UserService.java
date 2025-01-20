package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveOrUpdate(User user);
    User findById(Long id);
    void delete(Long id);
    List<User> findAll();
}
