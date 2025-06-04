package za.ac.cput.repository;

import org.springframework.stereotype.Repository;
import za.ac.cput.model.UserPreference;

@Repository
public class UserPreferenceRepository {
    public UserPreference save(UserPreference userPreference) {
        int counter = 1;

        System.out.println("Saving UserPreference: " + userPreference + ", ID: " + counter);
        return userPreference;
}

}
