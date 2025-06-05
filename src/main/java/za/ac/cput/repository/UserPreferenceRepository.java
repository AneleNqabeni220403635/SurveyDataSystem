package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.model.UserPreference;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference,String>{
}
