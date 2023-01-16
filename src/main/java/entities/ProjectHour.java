package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project_hours")
public class ProjectHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "hours_spent", nullable = false, length = 45)
    private Integer hoursSpent;

    @NotNull
    @Column(name = "user_story", nullable = false)
    private Integer userStory;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public ProjectHour() {
    }

    public ProjectHour(Integer id, Integer hoursSpent, Integer userStory, String description) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.userStory = userStory;
        this.description = description;
    }

    public ProjectHour(Integer id, Integer hoursSpent, Integer userStory, String description, Developer developer, Project project) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.userStory = userStory;
        this.description = description;
        this.developer = developer;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Integer hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public Integer getUserStory() {
        return userStory;
    }

    public void setUserStory(Integer userStory) {
        this.userStory = userStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}