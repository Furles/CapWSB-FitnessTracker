package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository interface for {@link User} entity persistence.
 * Provides standard CRUD operations through JpaRepository and custom search methods.
 * This interface is package-private to ensure encapsulation within the internal package.
 */

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches for users with an email address containing the specified fragment,
     * ignoring case sensitivity.
     *
     * @param email the email fragment to search for
     * @return a list of matching {@link User} entities
     */
    List<User> findByEmailContainingIgnoreCase(String email);

    /**
     * Searches for users whose age is strictly greater than the specified value.
     *
     * @param age the age threshold
     * @return a list of users older than the given age
     */
    List<User> findByAgeGreaterThan(int age);

    /**
     * Searches for a user with an exact email match using Java Streams.
     * This is a default implementation as per technical requirements.
     *
     * @param email the exact email to search for
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }
}