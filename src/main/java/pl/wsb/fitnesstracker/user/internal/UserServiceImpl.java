package pl.wsb.fitnesstracker.user.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacja serwisów {@link UserService} oraz {@link UserProvider}.
 * Obsługuje logikę biznesową zarządzania użytkownikami.
 */
@Service
class UserServiceImpl implements UserService, UserProvider {

    // Ręczna definicja loggera (rozwiązuje problem z Lombokiem)
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        log.info("Creating user {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(final Long id, final User user) {
        log.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setBirthdate(user.getBirthdate());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID %d not found".formatted(id)));
    }

    @Override
    public List<User> findOlderThan(final LocalDate date) {
        log.info("Searching for users older than: {}", date);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByEmailFragment(final String fragment) {
        log.info("Searching for users with email fragment: {}", fragment);
        final String loweredFragment = fragment.toLowerCase();
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().toLowerCase().contains(loweredFragment))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        log.info("Retrieving user with ID: {}", userId);
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        log.info("Retrieving user with email: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }
}