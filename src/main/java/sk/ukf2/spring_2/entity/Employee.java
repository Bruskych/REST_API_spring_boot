package sk.ukf2.spring_2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Meno je povinné")
    @Size(min = 2, max = 255, message = "Meno musí mať 2-255 znakov")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné")
    @Size(min = 2, max = 255, message = "Priezvisko musí mať 2-255 znakov")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Dátum narodenia je povinný")
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @NotBlank(message = "Email je povinný")
    @Email(message = "Neplatný formát emailu")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email musí mať platnú štruktúru (napr. meno@domain.sk)"
    )
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Telefónne číslo je povinné")
    @Pattern(regexp = "^\\+421\\d{9}$", message = "Telefónne číslo musí začínať +421 a obsahovať 9 číslic")
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @NotBlank(message = "Pracovná pozícia je povinná")
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @PositiveOrZero(message = "Mzda nemôže byť záporná")
    @Column(name = "salary")
    private BigDecimal salary;

    @NotNull(message = "Údaj o plnom úväzku je povinný")
    @Column(name = "full_time", nullable = false)
    private Boolean fullTime;

    public Employee() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public Boolean getFullTime() { return fullTime; }
    public void setFullTime(Boolean fullTime) { this.fullTime = fullTime; }
}