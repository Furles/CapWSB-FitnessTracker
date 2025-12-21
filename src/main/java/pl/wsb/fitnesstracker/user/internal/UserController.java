package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Zwraca wszystkich użytkowników (Pełne dane).
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Wymaganie: wylistowanie podstawowych informacji (ID, imię i nazwisko).
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Wymaganie: pobranie szczegółów wybranego użytkownika po ID.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Wymaganie: utworzenie nowego użytkownika.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto dto) {
        User created = userService.createUser(userMapper.toEntity(dto)); // Upewnij się, że masz metodę toEntity lub fromDto w Mapperze
        return userMapper.toDto(created);
    }

    /**
     * Wymaganie: usunięcie użytkownika po ID.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Wymaganie: aktualizowanie użytkownika.
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        User updated = userService.updateUser(id, userMapper.toEntity(dto));
        return userMapper.toDto(updated);
    }

    /**
     * Wymaganie: wyszukiwanie po e-mailu (fragment nazwy).
     */
    @GetMapping("/email")
    public List<UserSimpleDto> getUsersByEmail(@RequestParam String email) {
        return userService.findByEmailFragment(email).stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Wymaganie: wyszukiwanie użytkowników starszych niż zdefiniowany wiek.
     * Uwaga: Wiek przeliczamy na datę przed wysłaniem do serwisu.
     */
    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate date) {
        return userService.findOlderThan(date).stream()
                .map(userMapper::toDto)
                .toList();
    }
}