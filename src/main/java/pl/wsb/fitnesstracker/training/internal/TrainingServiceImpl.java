package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;

import java.util.List;

@Service
public class TrainingServiceImpl {

    private final TrainingRepository trainingRepository;

    // Ręczny konstruktor naprawia błąd inicjalizacji
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> findTrainingsForUser(Long userId) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
}