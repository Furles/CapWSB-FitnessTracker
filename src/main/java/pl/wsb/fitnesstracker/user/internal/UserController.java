package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.List;

/**
 * REST Controller for managing user-related operations.
 * Exposes API endpoints for CRUD operations and searching users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Constructs a new UserController with required dependencies.
     *
     * @param userService the service implementation for user operations
     * @param userMapper  the mapper for converting between entities and DTOs
     */
    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves basic information (ID and Name) for all users.
     *
     * @return a list of {@link UserSimpleDto} objects
     */
    @GetMapping
    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Retrieves full details of a specific user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a {@link UserDto} containing user details
     * @throws IllegalArgumentException if user is not found
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Creates a new user in the system.
     *
     * @param dto the user data transfer object
     * @return the created {@link UserDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto dto) {
        User created = userService.createUser(userMapper.toEntity(dto));
        return userMapper.toDto(created);
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id  the ID of the user to update
     * @param dto the new user data
     * @return the updated {@link UserDto}
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        User updated = userService.updateUser(id, userMapper.toEntity(dto));
        return userMapper.toDto(updated);
    }

    /**
     * Searches for users by an email fragment (case-insensitive).
     * Returns only IDs and email addresses.
     *
     * @param email the email fragment to search for
     * @return a list of {@link UserEmailDto}
     */
    @GetMapping("/search/emails")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmail(email).stream()
                .map(user -> new UserEmailDto(user.getId(), user.getEmail()))
                .toList();
    }

    /**
     * Searches for users older than the specified age.
     *
     * @param age the age threshold
     * @return a list of {@link UserSimpleDto}
     */
    @GetMapping("/search/age")
    public List<UserSimpleDto> getUsersOlderThan(@RequestParam int age) {
        return userService.findUsersOlderThan(age).stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
}