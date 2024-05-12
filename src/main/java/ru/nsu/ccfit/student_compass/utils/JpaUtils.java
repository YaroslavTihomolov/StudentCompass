package ru.nsu.ccfit.student_compass.utils;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.repository.JpaRepository;

@UtilityClass
public class JpaUtils {
    @Nonnull
    public static <T> T findByIdOrThrow(JpaRepository<T, Long> repository, Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
