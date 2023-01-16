package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<ProjectHour> projectHours = new LinkedHashSet<>();

    public Project() {
    }

    public Project(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Project(Integer id, String name, String description, Set<ProjectHour> projectHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectHours = projectHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProjectHour> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(Set<ProjectHour> projectHours) {
        this.projectHours = projectHours;
    }

}