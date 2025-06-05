package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.model.UserPreference;
import za.ac.cput.repository.SurveyFormRepository;
import za.ac.cput.service.Impl.ISurveyFormService;

import java.time.LocalDate;
import java.time.Period;
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
        List<SurveyForm> forms = repository.findAll();
        return forms.stream()
                .mapToInt(this::calculateAge)
                .average()
                .orElse(0.0);
    }

    public int getMaxAge() {
        return repository.findAll().stream()
                .mapToInt(this::calculateAge)
                .max()
                .orElse(0);
    }

    public int getMinAge() {
        return repository.findAll().stream()
                .mapToInt(this::calculateAge)
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

    private int calculateAge(SurveyForm form) {
        // Assuming dob format is "yyyy-MM-dd"
        try {
            LocalDate dob = LocalDate.parse(form.getDateOfBirth());
            return Period.between(dob, LocalDate.now()).getYears();
        } catch (Exception e) {
            return 0;
        }
    }

}
