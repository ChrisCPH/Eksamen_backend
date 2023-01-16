package facades;

import dtos.DeveloperDTO;
import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DeveloperFacade {

    private static DeveloperFacade instance;
    private static EntityManagerFactory emf;

    private DeveloperFacade() {
    }

    public static DeveloperFacade getDeveloperFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DeveloperFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<DeveloperDTO> getAllDevelopers() {
        List<DeveloperDTO> developerList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Developer> query = em.createQuery("SELECT d FROM Developer d", Developer.class);
        query.getResultList().forEach(developer -> {
            developerList.add(new DeveloperDTO(developer));
        });
        return developerList;
    }
}
