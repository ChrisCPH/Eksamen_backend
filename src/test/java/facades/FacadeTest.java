package facades;

import dtos.DeveloperDTO;
import dtos.ProjectDTO;
import dtos.ProjectHourDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static ProjectHourFacade projectHourFacade;

    private static ProjectFacade projectFacade;

    private static DeveloperFacade developerFacade;

    ProjectHour ph1, ph2;

    Developer d1, d2;

    Project p1, p2;
    ProjectHourDTO phDTO1, phDTO2;

    DeveloperDTO dDTO1, dDTO2;

    ProjectDTO pDTO1, pDTO2;

    public FacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        projectHourFacade = ProjectHourFacade.getProjectHourFacade(emf);
        projectFacade = ProjectFacade.getProjectFacade(emf);
        developerFacade = DeveloperFacade.getDeveloperFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Role userRole = new Role("admin");
        User u1 = new User("Oscar","test123");
        User u2 = new User("Mark","test123");
        u1.addRole(userRole);
        u2.addRole(userRole);
        p1 = new Project(1,"Dating App","Dating app til android og IOS");
        p2 = new Project(2,"Review app","En app hvor man kan anmelde restauranter");
        d1 = new Developer(1,"Frederik Andersen","FrederikAndersen@gmail.com","28205532",150);
        d2 = new Developer(4,"Amanda Schmidt","AmandaSchmidtn@gmail.com","23115567",240);
        ph1 = new ProjectHour(1,4,1,"4 timer arbejdet på userstory 1",d1,p1);
        ph2 = new ProjectHour(2,3,2,"3 timer arbejdet på userstory 2",d2,p2);

        em.getTransaction().begin();
        em.createQuery("DELETE FROM User").executeUpdate();
        em.createQuery("DELETE FROM Role").executeUpdate();
        em.createQuery("DELETE FROM ProjectHour").executeUpdate();
        em.createQuery("DELETE FROM Project").executeUpdate();
        em.createQuery("DELETE FROM Developer ").executeUpdate();
        em.persist(userRole);
        em.persist(u1);
        em.persist(u2);
        em.persist(p1);
        em.persist(p2);
        em.persist(d1);
        em.persist(d2);
        em.persist(ph1);
        em.persist(ph2);
        em.getTransaction().commit();

        pDTO1 = new ProjectDTO(p1);
        pDTO2 = new ProjectDTO(p2);
        dDTO1 = new DeveloperDTO(d1);
        dDTO2 = new DeveloperDTO(d2);
        phDTO1 = new ProjectHourDTO(ph1);
        phDTO2 = new ProjectHourDTO(ph2);
        em.close();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void createProjectFacadeTest() {
        ProjectDTO projectDTO = new ProjectDTO(new Project(3,"Random","Random app til android og IOS"));
        projectFacade.createProject(projectDTO);
        assertNotNull(projectDTO.getName());
        int actualSize = projectFacade.getAllProjects().size();
        assertEquals(3, actualSize);
    }

    @Test
    void getAllProjectsFacadeTest() {
        int actualSize = projectFacade.getAllProjects().size();
        assertEquals(2, actualSize);
    }

    @Test
    void getDevFacadeTest() {
        String developerName = dDTO1.getName();
        int actualSize = projectHourFacade.getAllDevProjects(developerName).size();
        assertEquals(1, actualSize);
    }

    @Test
    void getDevProjectFacadeTest() {
        String projectName = pDTO1.getName();
        String developerName = dDTO1.getName();
        int actualSize = projectHourFacade.getSpecificDevProject(developerName, projectName).size();
        assertEquals(1, actualSize);
    }

    @Test
    void getProjectPriceFacadeTest() {
        String projectName = pDTO1.getName();
        int actualSize = projectHourFacade.getProjectPrice(projectName).size();
        assertEquals(1, actualSize);
    }

    @Test
    void assignDeveloperFacadeTest() {
        ProjectHourDTO projectHourDTO = new ProjectHourDTO(new ProjectHour(3,3,1,"4 timer arbejdet på userstory 1", d1, p1));
        projectHourFacade.assignDeveloper(projectHourDTO, d1.getId(), p1.getId());
        assertNotNull(projectHourDTO.getId());
        int actualSize = projectHourFacade.getAllDevProjects(d1.getName()).size();
        assertEquals(2, actualSize);
    }
    /*
    @Test
    void updateHoursFacadeTest() {
        ProjectHourDTO projectHourDTO = new ProjectHourDTO(new ProjectHour(18,3,1,"3 timer arbejdet på userstory 1", d1, p1));
        projectHourFacade.updateHours(projectHourDTO, d1.getId(), p1.getId());
        assertNotNull(projectHourDTO.getId());
        Integer actualHours = projectHourFacade.getProjectPrice(p1.getName()).get(0).getHoursSpent();
        assertEquals(3, actualHours);
    }
    */
    @Test
    void deleteHoursFacadeTest() {
        projectHourFacade.deleteProjectHour(p1.getId());;
        int actualSize = projectHourFacade.getAllDevProjects(d1.getName()).size();
        assertEquals(1, actualSize);
    }

    @Test
    void getAllDevelopersFacadeTest() {
        int actualSize = developerFacade.getAllDevelopers().size();
        assertEquals(2, actualSize);
    }
}
