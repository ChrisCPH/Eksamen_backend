package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "developer")
public class Developer {
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
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 45)
    @NotNull
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @NotNull
    @Column(name = "billing_pr_hour", nullable = false, length = 45)
    private Integer billingPrHour;

    @OneToMany(mappedBy = "developer")
    private Set<ProjectHour> projectHours = new LinkedHashSet<>();

    public Developer() {
    }

    public Developer(Integer id, String name, String email, String phone, Integer billingPrHour) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
    }

    public Developer(Integer id, String name, String email, String phone, Integer billingPrHour, Set<ProjectHour> projectHours) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBillingPrHour() {
        return billingPrHour;
    }

    public void setBillingPrHour(Integer billingPrHour) {
        this.billingPrHour = billingPrHour;
    }

    public Set<ProjectHour> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(Set<ProjectHour> projectHours) {
        this.projectHours = projectHours;
    }

}