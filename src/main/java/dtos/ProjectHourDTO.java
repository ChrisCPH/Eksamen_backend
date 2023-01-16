package dtos;

import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ProjectHour} entity
 */
public class ProjectHourDTO implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private final Integer hoursSpent;
    @NotNull
    private final Integer userStory;
    @Size(max = 45)
    @NotNull
    private final String description;

    private final ProjectDTO project;

    private final DeveloperDTO developer;

    public ProjectHourDTO(Integer id, Integer hoursSpent, Integer userStory, String description, ProjectDTO project, DeveloperDTO developer) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.userStory = userStory;
        this.description = description;
        this.project = project;
        this.developer = developer;
    }

    public ProjectHourDTO(ProjectHour projectHour) {
        this.id = projectHour.getId();
        this.hoursSpent = projectHour.getHoursSpent();
        this.userStory = projectHour.getUserStory();
        this.description = projectHour.getDescription();
        this.project = new ProjectDTO(projectHour.getProject());
        this.developer = new DeveloperDTO(projectHour.getDeveloper());
    }

    public Integer getId() {
        return id;
    }

    public Integer getHoursSpent() {
        return hoursSpent;
    }

    public Integer getUserStory() {
        return userStory;
    }

    public String getDescription() {
        return description;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public DeveloperDTO getDeveloper() {
        return developer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "hoursSpent = " + hoursSpent + ", " +
                "userStory = " + userStory + ", " +
                "description = " + description + ")";
    }
}