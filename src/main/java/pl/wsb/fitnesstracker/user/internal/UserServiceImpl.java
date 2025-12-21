package pl.wsb.fitnesstracker.user.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link User} entities.
 * This class handles business logic, logging, and interacts with {@link UserRepository}.
 * It implements both {@link UserService} for modifications and {@link UserProvider} for data retrieval.
 */
@Service
class UserServiceImpl implements UserService, UserProvider {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    /**
     * Package-private constructor for dependency injection.
     *
     * @param userRepository the repository used for data persistence
     */
    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Persists a new user in the repository.
     *
     * @param user the user entity to be created
     * @return the saved {@link User} entity
     * @throws IllegalArgumentException if the user entity already contains a database ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating user {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a user based on the provided ID.
     *
     * @param userId the unique identifier of the user to delete
     */
    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
    }

    /**
     * Updates an existing user's attributes.
     * Maps fields from the provided user entity to the existing record in the database.
     *
     * @param id   the ID of the user to update
     * @param user the entity containing updated data
     * @return the updated and saved {@link User} entity
     * @throws IllegalArgumentException if no user is found with the specified ID
     */
    @Override
    public User updateUser(final Long id, final User user) {
        log.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setBirthdate(user.getBirthdate());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setAge(user.getAge());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID %d not found".formatted(id)));
    }

    /**
     * Finds users based on an age threshold.
     *
     * @param age the minimum age (exclusive)
     * @return a list of matching users
     */
    @Override
    public List<User> findUsersOlderThan(final int age) {
        log.info("Searching for users older than age: {}", age);
        return userRepository.findByAgeGreaterThan(age);
    }

    /**
     * Searches for users whose email contains a specific string.
     *
     * @param email the email fragment to search for
     * @return a list of users matching the search criteria
     */
    @Override
    public List<User> findUsersByEmail(final String email) {
        log.info("Searching for users with email containing: {}", email);
        return userRepository.findByEmailContainingIgnoreCase(email);
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID to search for
     * @return an {@link Optional} containing the user if found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        log.info("Retrieving user with ID: {}", userId);
        return userRepository.findById(userId);
    }
    /**
     * Retrieves a user by their exact email address.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the user if found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        log.info("Retrieving user with email: {}", email);
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users stored in the database.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }
}