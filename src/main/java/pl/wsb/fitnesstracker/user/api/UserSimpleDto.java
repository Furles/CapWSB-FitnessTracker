package pl.wsb.fitnesstracker.user.api;

/**
 * A simplified Data Transfer Object (DTO) representing a user.
 * This record is used to return only the essential information required for listing users,
 * specifically their unique identifier and full name.
 * * @param id the unique identifier of the user
 * @param firstName the user's first name
 * @param lastName the user's last name
 */
public record UserSimpleDto(
        Long id,
        String firstName,
        String lastName
) {}