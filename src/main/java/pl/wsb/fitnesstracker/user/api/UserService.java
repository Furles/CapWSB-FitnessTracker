package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Deletes a user from the system.
     *
     * @param id ID of the user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Updates an existing user's data.
     *
     * @param id   ID of the user to update
     * @param user User object containing new data
     * @return The updated user
     */
    User updateUser(Long id, User user);

    /**
     * Finds users older than a specific date.
     *
     * @param date The threshold date
     * @return List of matching users
     */
    List<User> findOlderThan(LocalDate date);

    /**
     * Finds users by a fragment of their email address.
     *
     * @param fragment The email fragment to search for
     * @return List of matching users
     */
    List<User> findByEmailFragment(String fragment);

    /**
     * Retrieves all users.
     *
     * @return List of all users
     */
    List<User> findAllUsers();

    /**
     * Retrieves a single user by ID.
     *
     * @param id User ID
     * @return Optional containing the user if found
     */
    Optional<User> getUser(Long id);
}