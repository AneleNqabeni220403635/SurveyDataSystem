package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.model.UserPreference;
import za.ac.cput.repository.SurveyFormRepository;
import za.ac.cput.service.Impl.ISurveyFormService;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public int countTotalSurveys() {

        return (int) repository.count();
    }

    public double calculateAverageAge() {
        List<SurveyForm> all = repository.findAll();
        return all.stream()
                .mapToInt(form -> calculateAge(form.getDateOfBirth()))
                .average()
                .orElse(0.0);
    }

    public int getMaxAge() {
        List<SurveyForm> all = repository.findAll();
        return all.stream()
                .filter(form -> form.getDateOfBirth() != null)
                .mapToInt(form -> calculateAge(form.getDateOfBirth()))
                .max()
                .orElse(0);
    }

    public int getMinAge() {
        List<SurveyForm> all = repository.findAll();
        return all.stream()
                .filter(form -> form.getDateOfBirth() != null)
                .mapToInt(form -> calculateAge(form.getDateOfBirth()))
                .min()
                .orElse(0);
    }


    public double calculatePercentageLikedFood(String food) {
        List<SurveyForm> forms = repository.findAll();
        long total = forms.size();
        long count = forms.stream()
                .filter(form -> form.getPreference() != null &&
                        form.getPreference().getFavouriteFood() != null &&
                        form.getPreference().getFavouriteFood().toString().contains(food))
                .count();
        return total == 0 ? 0 : ((double) count / total) * 100;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();

    }
}