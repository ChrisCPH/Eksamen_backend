package dtos;

import entities.Project;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Project} entity
 */
public class ProjectDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;
    @Size(max = 45)
    @NotNull
    private final String description;
    private final Set<ProjectHourInnerDTO> projectHours = new HashSet<>();

    public ProjectDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProjectHourInnerDTO> getProjectHours() {
        return projectHours;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "projectHours = " + projectHours;
    }

    /**
     * A DTO for the {@link entities.ProjectHour} entity
     */
    public static class ProjectHourInnerDTO implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String hoursSpendt;
        @NotNull
        private final Integer userStory;
        @Size(max = 45)
        @NotNull
        private final String description;

        public ProjectHourInnerDTO(Integer id, String hoursSpendt, Integer userStory, String description) {
            this.id = id;
            this.hoursSpendt = hoursSpendt;
            this.userStory = userStory;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public String getHoursSpendt() {
            return hoursSpendt;
        }

        public Integer getUserStory() {
            return userStory;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "hoursSpendt = " + hoursSpendt + ", " +
                    "userStory = " + userStory + ", " +
                    "description = " + description + ")";
        }
    }
}