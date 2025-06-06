package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.model.UserPreference;

import java.time.LocalDate;

@Entity
public class SurveyForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String contactNumber;
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "preference_id")
    private UserPreference preference;


    public SurveyForm() {}

    // Fluent setters
    public SurveyForm setName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public SurveyForm setCell(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public SurveyForm setDob(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public SurveyForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public SurveyForm setPreference(UserPreference preference){
        this.preference = preference;
        return this;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserPreference getPreference() {
        return preference;
    }
}