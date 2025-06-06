package za.ac.cput.frontend;

import za.ac.cput.model.FavouriteFood;
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
        panel.setBackground(Color.LIGHT_GRAY);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = Color.BLACK;

        // Centered and styled title
        JLabel titleLabel = new JLabel("Survey Results", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(labelColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Simulate data retrieval and calculations (replace these with actual logic)
        int totalSurveys = surveyFormService.countTotalSurveys();
        double averageAge = surveyFormService.calculateAverageAge();
        int maxAge = surveyFormService.getMaxAge();
        int minAge = surveyFormService.getMinAge();
        double percentagePizza = surveyFormService.calculatePercentageLikedFood(FavouriteFood.PIZZA);
        double percentagePasta = surveyFormService.calculatePercentageLikedFood(FavouriteFood.PASTA);
        double percentagePap = surveyFormService.calculatePercentageLikedFood(FavouriteFood.PapAndWors);

        double avgMovies = userPreferenceService.getAverageRating("movies");
        double avgRadio = userPreferenceService.getAverageRating("radio");
        double avgEatOut = userPreferenceService.getAverageRating("eatOut");
        double avgTV = userPreferenceService.getAverageRating("tv");

        panel.add(new JLabel("Survey Results"));
        panel.add(new JLabel("Total number of surveys: " + totalSurveys));
        panel.add(new JLabel("Average Age: " + String.format("%.1f", averageAge)));
        panel.add(new JLabel("Oldest person who participated in the survey: " + maxAge));
        panel.add(new JLabel("Youngest person who participated in the survey: " + minAge));
        panel.add(new JLabel("Percentage of people who like Pizza: " + String.format("%.1f", percentagePizza) + "%"));
        panel.add(new JLabel("Percentage of people who like Pasta: " + String.format("%.1f", percentagePasta) + "%"));
        panel.add(new JLabel("Percentage of people who like Pap and Wors: " + String.format("%.1f", percentagePap) + "%"));
        panel.add(new JLabel("People who like to watch movies: " + String.format("%.1f", avgMovies)));
        panel.add(new JLabel("People who like to listen to radio: " + String.format("%.1f", avgRadio)));
        panel.add(new JLabel("People who like to eat out: " + String.format("%.1f", avgEatOut)));
        panel.add(new JLabel("People who like to watch TV: " + String.format("%.1f", avgTV)));


        add(panel);
        setVisible(true);
    }
}
