//package rest;
//
//import com.google.gson.Gson;
//import dtos.HouseDTO;
//import dtos.RentalDTO;
//import entities.EntityExample;
//import entities.House;
//import entities.Rental;
//import utils.EMF_Creator;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.given;
//import io.restassured.parsing.Parser;
//import java.net.URI;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.core.UriBuilder;
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.grizzly.http.util.HttpStatus;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import static org.hamcrest.Matchers.equalTo;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
////Uncomment the line below, to temporarily disable this test
////@Disabled
//
//public class RentalResourceTest {
//
//    private static final int SERVER_PORT = 7777;
//    private static final String SERVER_URL = "http://localhost/api";
//    private static Rental rental;
//    private static String securityToken;
//
//    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//
//    static HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        //This method must be called before you request the EntityManagerFactory
//        EMF_Creator.startREST_TestWithDB();
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//
//        httpServer = startServer();
//        //Setup RestAssured
//        RestAssured.baseURI = SERVER_URL;
//        RestAssured.port = SERVER_PORT;
//        RestAssured.defaultParser = Parser.JSON;
//    }
//
//    @AfterAll
//    public static void closeTestServer() {
//        //System.in.read();
//
//        //Don't forget this, if you called its counterpart in @BeforeAll
//        EMF_Creator.endREST_TestWithDB();
//        httpServer.shutdownNow();
//    }
//
//    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
//    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        House house1 = new House("address", "city",3);
//        rental = new Rental("startDate", "endDate", 1000, 1000, "contactPerson",house1);
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("rental.deleteAllRows").executeUpdate();
//            em.persist(rental);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Test
//    public void testServerIsUp() {
//        given().when().get("/crud").then().statusCode(200);
//    }
//
//    //This test assumes the database contains two rows
//    @Test
//    public void testDummyMsg() throws Exception {
//        given()
//                .contentType("application/json")
//                .get("/xxx/").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("msg", equalTo("Hello World"));
//    }
//
//    @Test
//    public void testCount() throws Exception {
//        given()
//                .contentType("application/json")
//                .get("/xxx/count").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("count", equalTo(2));
//    }
//    @Test
//    public void testCreateNewRental() throws Exception {
//        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
//        HouseDTO house1 = new HouseDTO("address", "city", 3);
//        RentalDTO rentalDTO = new RentalDTO("startDate", "endDate", 1000, 1000, "contactPerson", house1);
//        String json = gson.toJson(rentalDTO);
//        given()
//                .contentType("application/json")
//                .body(json)
//                .when()
//                .post("/rental")
//                .then()
//                .body("startDate", equalTo("startDate"))
//                .body("endDate", equalTo("endDate"))
//                .body("price", equalTo(1000))
//                .body("deposit", equalTo(1000))
//                .body("contactPerson", equalTo("contactPerson"))
//                .body("house.address", equalTo("address"))
//                .body("house.city", equalTo("city"))
//                .body("house.numberOfRooms", equalTo(3))
//                .statusCode(200);
//    }
//
//}
