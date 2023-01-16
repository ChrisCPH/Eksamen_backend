package rest;

import entities.Project;
import entities.ProjectHour;
import entities.Role;
import entities.User;
import entities.Developer;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
@Disabled
public class EndPointTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    ProjectHour ph1, ph2;

    Developer d1, d2;

    Project p1, p2;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();

        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
        em.createQuery("delete from ProjectHour ").executeUpdate();
        em.createQuery("delete from Developer ").executeUpdate();
        em.createQuery("delete from Project ").executeUpdate();

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User user = new User("user", "test");
        user.addRole(userRole);
        User admin = new User("admin", "test");
        admin.addRole(adminRole);
        User both = new User("user_admin", "test");
        both.addRole(userRole);
        both.addRole(adminRole);


        p1 = new Project(1,"Dating App","Dating app til android og IOS");
        p2 = new Project(2,"Review app","En app hvor man kan anmelde restauranter");
        d2 = new Developer(4,"Amanda Schmidt","AmandaSchmidtn@gmail.com","23115567",240);
        d1 = new Developer(1,"Frederik Andersen","FrederikAndersen@gmail.com","28205532",150);
        ph1 = new ProjectHour(1,4,1,"4 timer arbejdet på userstory 1",d1,p1);
        ph2 = new ProjectHour(2,3,2,"3 timer arbejdet på userstory 2",d2,p2);

        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.persist(p1);
        em.persist(p2);
        em.persist(d1);
        em.persist(d2);
        em.persist(ph1);
        em.persist(ph2);
        em.getTransaction().commit();

        em.close();
    }

    @Test
    public void serverIsRunning() {
        given().when().get("/info").then().statusCode(200);
    }

    @Test
    public void getProjects() {
        given().when().get("/project/getall").then().statusCode(200);
    }

    @Test
    public void createProject() {
        String json = "{id: 3, name: Test, description: Test}";
        given()
                .contentType("application/json")
                .body(json)
                .when().post("/project/create").then()
                .statusCode(200);
    }

    @Test
    public void getDevProjects() {
        given().when().get("/project_hour/getdev/" + d1.getName()).then().statusCode(200);
    }

    @Test
    public void getAllDevProjects() {
        given().when().get("/project_hour/getdev/" + d1.getName() + "/" + p1.getName()).then().statusCode(200);
    }

    @Test
    public void getProjectPrice() {
        given().when().get("/project_hour/get/" + p1.getName()).then().statusCode(200);
    }

    @Test
    public void assignDeveloper() {
        String json = "{hoursSpent: 3, userStory: 2, description: Test}";
        given()
                .contentType("application/json")
                .body(json)
                .when().post("/project_hour/assign/" + d1.getId() + "/" + p1.getId()).then()
                .statusCode(200);
    }

    @Test
    public void updateHours() {
        String json = "{hoursSpent: 3, userStory: 2, description: Test}";
        given()
                .contentType("application/json")
                .body(json)
                .when().put("/project_hour/update/" + ph1.getId() + "/" + d1.getId() + "/" + p1.getId()).then()
                .statusCode(200);
    }

    @Test
    public void deleteHours() {
        given().when().delete("/project_hour/delete/" + ph1.getId()).then().statusCode(200);
    }

    @Test
    public void getAllDevelopers() {
        given().when().get("/developer/getall").then().statusCode(200);
    }

}
