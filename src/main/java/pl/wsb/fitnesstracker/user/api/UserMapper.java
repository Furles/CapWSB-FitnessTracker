package pl.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between {@link User} entity and various Data Transfer Objects (DTOs).
 * It ensures that internal database structures are not exposed directly to the API layer.
 */

@Component
public class UserMapper {

    /**
     * Maps a {@link User} entity to a full {@link UserDto}.
     *
     * @param user the entity to be mapped
     * @return a {@link UserDto} containing all user details including age and birthdate
     */

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail(),
                user.getAge()); // Dodano wiek
    }

    /**
     * Maps a {@link User} entity to a simplified {@link UserSimpleDto}.
     * Used for general listings where only basic information (ID, name) is required.
     *
     * @param user the entity to be mapped
     * @return a {@link UserSimpleDto} containing only ID, first name, and last name
     */
    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    /**
     * Converts a {@link UserDto} back into a {@link User} entity.
     * Primarily used during user creation or updates.
     *
     * @param dto the Data Transfer Object to be converted
     * @return a {@link User} entity populated with data from the DTO
     */
    public User toEntity(UserDto dto) {
        return new User(
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                dto.email(),
                dto.age() // Dodano przekazywanie wieku do konstruktora
        );
    }
}