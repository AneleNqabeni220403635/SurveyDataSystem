package za.ac.cput.frontend;

import za.ac.cput.service.SurveyFormService;
import za.ac.cput.service.UserPreferenceService;

import javax.swing.*;
import java.awt.*;

public class SurveyResultsScreen extends JFrame {

    public SurveyResultsScreen(SurveyFormService surveyFormService, UserPreferenceService userPreferenceService) {
        setTitle("Survey Results");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Simulate data retrieval and calculations (replace these with actual logic)
        int totalSurveys = surveyFormService.countTotalSurveys();
        double averageAge = surveyFormService.calculateAverageAge();
        int maxAge = surveyFormService.getMaxAge();
        int minAge = surveyFormService.getMinAge();
        double percentagePizza = surveyFormService.calculatePercentageLikedFood("Pizza");
        double percentagePap = surveyFormService.calculatePercentageLikedFood("Pap and Wors");

        double avgMovies = userPreferenceService.getAverageRating("movies");
        double avgRadio = userPreferenceService.getAverageRating("radio");
        double avgEatOut = userPreferenceService.getAverageRating("eatOut");
        double avgTV = userPreferenceService.getAverageRating("tv");

        panel.add(new JLabel("Survey Results"));
        panel.add(new JLabel("Total number of surveys: " + totalSurveys));
        panel.add(new JLabel("Average Age: " + averageAge));
        panel.add(new JLabel("Oldest person: " + maxAge));
        panel.add(new JLabel("Youngest person: " + minAge));
        panel.add(new JLabel("Percentage who like Pizza: " + percentagePizza + "%"));
        panel.add(new JLabel("Percentage who like Pap and Wors: " + percentagePap + "%"));
        panel.add(new JLabel("Rating - Watching Movies: " + avgMovies));
        panel.add(new JLabel("Rating - Listening to Radio: " + avgRadio));
        panel.add(new JLabel("Rating - Eating Out: " + avgEatOut));
        panel.add(new JLabel("Rating - Watching TV: " + avgTV));

        add(panel);
        setVisible(true);
    }
}
