package facades;

import dtos.ProjectDTO;
import dtos.ProjectHourDTO;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProjectHourFacade {

    private static ProjectHourFacade instance;
    private static EntityManagerFactory emf;

    private ProjectHourFacade() {
    }

    public static ProjectHourFacade getProjectHourFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectHourFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<ProjectHourDTO> getAllDevProjects(String developerName) {
        List<ProjectHourDTO> projectHourList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<ProjectHour> query = em.createQuery("SELECT ph FROM ProjectHour ph JOIN ph.project p JOIN ph.developer d WHERE d.name =:name", ProjectHour.class);
        query.setParameter("name", developerName);
        query.getResultList().forEach(projectHour -> {
            projectHourList.add(new ProjectHourDTO(projectHour));
        });
        return projectHourList;
    }

    public List<ProjectHourDTO> getSpecificDevProject(String developerName, String projectName) {
        List<ProjectHourDTO> projectHourList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<ProjectHour> query = em.createQuery("SELECT ph FROM ProjectHour ph JOIN ph.project p JOIN ph.developer d WHERE d.name =:dName AND p.name =:pName", ProjectHour.class);
        query.setParameter("dName", developerName);
        query.setParameter("pName", projectName);
        query.getResultList().forEach(projectHour -> {
            projectHourList.add(new ProjectHourDTO(projectHour));
        });
        return projectHourList;
    }

    public List<ProjectHourDTO> getProjectPrice(String projectName) {
        List<ProjectHourDTO> projectHourList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<ProjectHour> query = em.createQuery("SELECT ph FROM ProjectHour ph JOIN ph.project p JOIN ph.developer d WHERE p.name =:name", ProjectHour.class);
        query.setParameter("name", projectName);
        query.getResultList().forEach(projectHour -> {
            projectHourList.add(new ProjectHourDTO(projectHour));
        });
        return projectHourList;
    }

    public ProjectHourDTO assignDeveloper (ProjectHourDTO projectHourDTO, Integer developerId, Integer projectId) {
        EntityManager em = getEntityManager();

        Developer developer = em.find(Developer.class, developerId);

        Project project = em.find(Project.class, projectId);

        ProjectHour newProjectHour = new ProjectHour(projectHourDTO.getId(), projectHourDTO.getHoursSpent(), projectHourDTO.getUserStory(), projectHourDTO.getDescription(), developer, project);

        em.getTransaction().begin();
        em.persist(newProjectHour);
        em.getTransaction().commit();
        em.close();
        return new ProjectHourDTO(newProjectHour);
    }

    public ProjectHourDTO updateHours (ProjectHourDTO projectHourDTO, Integer developerId, Integer projectId) {
        EntityManager em = getEntityManager();

        Developer developer = em.find(Developer.class, developerId);

        Project project = em.find(Project.class, projectId);

        ProjectHour projectHour = em.find(ProjectHour.class, projectHourDTO.getId());
        if(projectHour == null) {
            throw new EntityNotFoundException("No such project hour with id: " + projectHourDTO.getId());
        }

        ProjectHour updatedProjectHour = new ProjectHour(projectHourDTO.getId(), projectHourDTO.getHoursSpent(), projectHourDTO.getUserStory(), projectHourDTO.getDescription(), developer, project);

        em.getTransaction().begin();
        em.merge(updatedProjectHour);
        em.getTransaction().commit();
        em.close();
        return new ProjectHourDTO(updatedProjectHour);
    }

    public ProjectHourDTO deleteProjectHour (Integer projectHourId) {
        EntityManager em = getEntityManager();

        ProjectHour projectHour = em.find(ProjectHour.class, projectHourId);
        try {
            em.getTransaction().begin();
            em.remove(projectHour);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectHourDTO(projectHour);
    }
}
