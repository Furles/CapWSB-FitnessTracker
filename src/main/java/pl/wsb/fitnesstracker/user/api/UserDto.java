package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing a user's full information.
 * This record is used for transferring comprehensive user data, including personal details
 * and calculated age, between the server and clients.
 *
 * @param id        the unique identifier of the user
 * @param firstName the user's first name
 * @param lastName  the user's last name
 * @param birthdate the user's date of birth
 * @param email     the user's email address
 * @param age       the user's age, required for filtering operations in LAB04
 */

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email,
        int age) { // To pole jest niezbędne
}