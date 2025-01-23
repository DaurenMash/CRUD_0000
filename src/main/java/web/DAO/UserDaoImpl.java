package web.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager entityManager;


    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return entityManager.createQuery("Select u FROM User u", User.class).getResultList();
    }
}
