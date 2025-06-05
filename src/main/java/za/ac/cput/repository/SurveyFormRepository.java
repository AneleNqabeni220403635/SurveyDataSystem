package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.SurveyForm;

@Repository
public interface SurveyFormRepository extends JpaRepository<SurveyForm,Long> {

}
