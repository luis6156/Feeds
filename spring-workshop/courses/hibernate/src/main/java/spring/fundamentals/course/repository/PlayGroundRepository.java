package spring.fundamentals.course.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.fundamentals.course.model.Profile;
import spring.fundamentals.course.model.User;

import java.util.List;

@Repository
public class PlayGroundRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public void play() {

        User user = findUser(1);
//        user.setUsername("new_fake");
        System.out.println(user);

        System.out.println(findAll());

        insert(new User("alex_alex_2", "alex@test.com"));
    }

    @Transactional
    public void delete(Profile profile) {
        entityManager.remove(profile);
    }


    public Profile findById(int id) {
        return null;
    }


    @Transactional
    public List<User> findAll() {
        String hql = """
                from User
                """;
        return entityManager.createQuery(hql, User.class).getResultList();
    }


    public List<User> findByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);

        cq.select(userRoot)
                .where(builder.equal(userRoot.get("username"), username));

        return entityManager.createQuery(cq).getResultList();
    }


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void insert(User user) {
        entityManager.persist(user);
    }


    @Transactional(readOnly = true)
    public User findUser(int id) {
//        return entityManager.getReference(User.class, id); // referinta catre inregistrare
        return entityManager.find(User.class, id);
    }
}
