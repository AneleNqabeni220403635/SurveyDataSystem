package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.repository.SurveyFormRepository;
import za.ac.cput.service.Impl.ISurveyFormService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SurveyFormService implements ISurveyFormService {

    private final SurveyFormRepository repository;

    @Autowired
    public SurveyFormService(SurveyFormRepository repository) {
        this.repository = repository;
    }

    @Override
    public SurveyForm create(SurveyForm survey) {

        return repository.save(survey);
    }

    @Override
    public SurveyForm read(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public SurveyForm update(SurveyForm survey) {

        return repository.save(survey);
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    @Override
    public Set<SurveyForm> getall() {

        return repository.findAll().stream().collect(Collectors.toSet());
    }
}
