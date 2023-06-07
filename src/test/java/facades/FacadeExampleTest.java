//package facades;
//
//import entities.EntityExample;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
////Uncomment the line below, to temporarily disable this test
////@Disabled
//public class FacadeExampleTest {
//
//    private static EntityManagerFactory emf;
//    private static FacadeExample facade;
//
//    public FacadeExampleTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//       emf = EMF_Creator.createEntityManagerFactoryForTest();
//       facade = FacadeExample.getFacadeExample(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    //TODO -- Make sure to change the code below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("EntityExample.deleteAllRows").executeUpdate();
//            em.persist(new EntityExample("Some txt", "More text"));
//            em.persist(new EntityExample("aaa", "bbb"));
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//    }
//
//    // TODO: Delete or change this method
//    @Test
//    public void testAFacadeMethod() throws Exception {
//        assertEquals(2, facade.getEntityCount(), "Expects two rows in the database");
//    }
//
//
//}
