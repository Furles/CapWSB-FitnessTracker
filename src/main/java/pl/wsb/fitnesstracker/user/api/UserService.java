package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining the business logic operations for managing {@link User} entities.
 * This API allows for CRUD operations and specialized search functionality.
 */
public interface UserService {

    /**
     * Persists a new user in the system.
     *
     * @param user the user entity to be created
     * @return the created {@link User} with an assigned ID
     * @throws IllegalArgumentException if the user already has an ID
     */
    User createUser(User user);

    /**
     * Removes a user from the system based on their unique identifier.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Updates the data of an existing user.
     *
     * @param id   the ID of the user to update
     * @param user the user entity containing updated information
     * @return the updated {@link User} entity
     * @throws IllegalArgumentException if the user with the given ID does not exist
     */
    User updateUser(Long id, User user);

    /**
     * Retrieves a list of users who are older than the specified age.
     *
     * @param age the age threshold for the search
     * @return a list of {@link User} entities matching the criteria
     */
    List<User> findUsersOlderThan(int age);

    /**
     * Retrieves a list of users who are older than the specified age.
     *
     * @param age the age threshold for the search
     * @return a list of {@link User} entities matching the criteria
     */
    List<User> findUsersByEmail(String email);

    /**
     * Retrieves all users currently registered in the system.
     *
     * @return a list of all {@link User} entities
     */
    List<User> findAllUsers();

    /**
     * Retrieves a single user by their unique identifier.
     *
     * @param id the ID of the user to find
     * @return an {@link Optional} containing the user if found, or empty otherwise
     */
    Optional<User> getUser(Long id);
}