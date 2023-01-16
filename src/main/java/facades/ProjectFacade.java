package facades;

import dtos.ProjectDTO;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProjectFacade {

    private static ProjectFacade instance;
    private static EntityManagerFactory emf;

    private ProjectFacade() {
    }

    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projectList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        query.getResultList().forEach(project -> {
            projectList.add(new ProjectDTO(project));
        });
        return projectList;
    }

    public ProjectDTO createProject (ProjectDTO projectDTO) {
        EntityManager em = getEntityManager();

        Project newProject = new Project(projectDTO.getId(), projectDTO.getName(), projectDTO.getDescription());

        em.getTransaction().begin();
        em.persist(newProject);
        em.getTransaction().commit();
        em.close();
        return new ProjectDTO(newProject);
    }
}
