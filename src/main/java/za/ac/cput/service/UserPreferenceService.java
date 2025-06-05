package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.model.UserPreference;
import za.ac.cput.repository.SurveyFormRepository;
import za.ac.cput.repository.UserPreferenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferenceService {
    @Autowired
    private final UserPreferenceRepository repository;

    public UserPreferenceService(UserPreferenceRepository repository) {
        this.repository = repository;
    }

    public UserPreference saveUserPreference(UserPreference preference) {
        return repository.save(preference);
    }
    public double getAverageRating(String category) {
        List<UserPreference> prefs = repository.findAll();
        return prefs.stream()
                .mapToInt(p -> switch (category.toLowerCase()) {
                    case "movies" -> p.getWatchingMovies().getNumericValue();
                    case "radio" -> p.getListeningToRadio().getNumericValue();
                    case "eatout" -> p.getEatOut().getNumericValue();
                    case "tv" -> p.getWatchingTV().getNumericValue();
                    default -> 0;
                })
                .average()
                .orElse(0.0);
    }
}
