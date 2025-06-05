package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.model.UserPreference;
import za.ac.cput.repository.SurveyFormRepository;
import za.ac.cput.service.Impl.ISurveyFormService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SurveyFormService {

    private final SurveyFormRepository repository;

    @Autowired
    public SurveyFormService(SurveyFormRepository repository) {
        this.repository = repository;
    }

    public SurveyForm saveSurveyForm(SurveyForm surveyForm) {
        return repository.save(surveyForm);
}


}
