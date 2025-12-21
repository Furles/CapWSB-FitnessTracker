package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a User in the FitnessTracker system.
 * This class is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column // Dodajemy brakujące pole age
    private int age;

    /**
     * Constructs a new User with the specified details.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param birthdate the user's date of birth
     * @param email     the user's unique email address
     * @param age       the user's age (required for LAB04 filtering)
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email,
            final int age) { // Dodajemy age do konstruktora

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.age = age;
    }

    /**
     * Gets the unique identifier of the user.
     * @return the user ID, or null if the entity is not persisted yet
     */
    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets the email address of the user.
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the age of the user.
     * @return user age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     * @param age user age
     */
    public void setAge(int age) {
        this.age = age;
    }
}