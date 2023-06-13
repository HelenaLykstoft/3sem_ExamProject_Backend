package facades;

import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.AuthenticationException;

public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    //Null args constructor
    private UserFacade() {
    }

    //This method returns a instance of the UserFacade Class
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }
    //  Method retrieves a user from username and password and verifies user, then returns user once successfully verified.
    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User createUser(String username, String password, int phone, String job) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try{
            user = em.find(User.class, username);
            if(user == null){
                user = new User(username,password,phone,job);
                user.addRole(getUserRole());
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    private Role getUserRole() {
        EntityManager em = emf.createEntityManager();
        Role role = null;
        try {
            role = em.find(Role.class, "user");
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }


}
