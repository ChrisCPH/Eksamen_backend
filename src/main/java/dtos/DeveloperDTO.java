package dtos;

import entities.Developer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Developer} entity
 */
public class DeveloperDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;
    @Size(max = 45)
    @NotNull
    private final String email;
    @Size(max = 45)
    @NotNull
    private final String phone;
    @NotNull
    private final Integer billingPrHour;
    private final Set<ProjectHourInnerDTO> projectHours = new HashSet<>();

    public DeveloperDTO(Integer id, String name, String email, String phone, Integer billingPrHour) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
    }

    public DeveloperDTO(Developer developer) {
        this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.phone = developer.getPhone();
        this.billingPrHour = developer.getBillingPrHour();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getBillingPrHour() {
        return billingPrHour;
    }

    public Set<ProjectHourInnerDTO> getProjectHours() {
        return projectHours;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "phone = " + phone + ", " +
                "billingPrHour = " + billingPrHour + ", " +
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