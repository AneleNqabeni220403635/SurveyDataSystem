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
}
