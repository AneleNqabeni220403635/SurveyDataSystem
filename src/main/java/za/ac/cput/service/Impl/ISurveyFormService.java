package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.SurveyForm;

import java.util.Set;

@Service
public interface ISurveyFormService extends IService<SurveyForm, Long> {
    Set<SurveyForm> getall();
}
