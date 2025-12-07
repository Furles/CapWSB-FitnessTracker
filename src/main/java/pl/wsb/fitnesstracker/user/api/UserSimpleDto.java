// Plik: pl.wsb.fitnesstracker.user.api.UserSimpleDto.java

package pl.wsb.fitnesstracker.user.api;

/**
 * Record do zwracania uproszczonych danych użytkownika (ID, Imię i Nazwisko).
 * Używany do spełnienia wymagania listowania podstawowych informacji o wszystkich użytkownikach.
 */
public record UserSimpleDto(
        Long id,
        String firstName,
        String lastName
) {}