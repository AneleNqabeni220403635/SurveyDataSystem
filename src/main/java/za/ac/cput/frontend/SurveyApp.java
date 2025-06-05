package za.ac.cput.frontend;

import org.springframework.boot.SpringApplication;
import za.ac.cput.SurveyApplication;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.model.FavouriteFood;
import za.ac.cput.model.PreferenceRating;
import za.ac.cput.model.UserPreference;
import za.ac.cput.service.SurveyFormService;
import za.ac.cput.service.UserPreferenceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationContext;

public class SurveyApp {
    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");

        ApplicationContext context = SpringApplication.run(SurveyApplication.class, args);

        // Get services from Spring
        UserPreferenceService userPreferenceService = context.getBean(UserPreferenceService.class);
        SurveyFormService surveyFormService = context.getBean(SurveyFormService.class);

        JFrame frame = new JFrame("Survey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700); // Increased size to fit everything

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField fullNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField dobField = new JTextField(20);
        JTextField contactField = new JTextField(20);

        JCheckBox pizzaCheck = new JCheckBox("Pizza");
        JCheckBox pastaCheck = new JCheckBox("Pasta");
        JCheckBox papCheck = new JCheckBox("Pap and Wors");
        JCheckBox otherCheck = new JCheckBox("Other");

        String[] options = {"Strongly Agree", "Agree", "Neutral", "Disagree", "Strongly Disagree"};
        JComboBox<String> moviesBox = new JComboBox<>(options);
        JComboBox<String> radioBox = new JComboBox<>(options);
        JComboBox<String> eatOutBox = new JComboBox<>(options);
        JComboBox<String> tvBox = new JComboBox<>(options);

        JButton submitButton = new JButton("Submit");
        JButton viewResultsButton = new JButton("View Survey Results");

        panel.add(new JLabel("Full Names:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactField);

        panel.add(new JLabel("What is your favorite food?"));
        panel.add(pizzaCheck);
        panel.add(pastaCheck);
        panel.add(papCheck);
        panel.add(otherCheck);

        panel.add(new JLabel("Please rate your level of agreement on a scale from 1 to 5, with 1 being strongly agree and 5 being strongly disagree."));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(new JLabel("I like to watch movies:"));
        panel.add(moviesBox);
        panel.add(new JLabel("I like to listen to radio:"));
        panel.add(radioBox);
        panel.add(new JLabel("I like to eat out:"));
        panel.add(eatOutBox);
        panel.add(new JLabel("I like to watch TV:"));
        panel.add(tvBox);

        // Create horizontal button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(viewResultsButton);

        // Add spacing then buttons
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(buttonPanel);

        // Add main panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Submit button logic
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String dob = dobField.getText();
                String contact = contactField.getText();

                StringBuilder favoriteFood = new StringBuilder();
                if (pizzaCheck.isSelected()) favoriteFood.append("Pizza ");
                if (pastaCheck.isSelected()) favoriteFood.append("Pasta ");
                if (papCheck.isSelected()) favoriteFood.append("Pap and Wors ");
                if (otherCheck.isSelected()) favoriteFood.append("Other ");

                String moviesRating = (String) moviesBox.getSelectedItem();
                String radioRating = (String) radioBox.getSelectedItem();
                String eatOutRating = (String) eatOutBox.getSelectedItem();
                String tvRating = (String) tvBox.getSelectedItem();

                System.out.println(fullName);
                System.out.println(email);
                System.out.println(dob);
                System.out.println(contact);

                UserPreference userPreference = new UserPreference()
                        .setFavouriteFood(FavouriteFood.fromKey(favoriteFood.toString()))
                        .setWatchingTV(PreferenceRating.fromKey(tvRating))
                        .setEatOut(PreferenceRating.fromKey(eatOutRating))
                        .setWatchingMovies(PreferenceRating.fromKey(moviesRating))
                        .setListeningToRadio(PreferenceRating.fromKey(radioRating));

                userPreferenceService.saveUserPreference(userPreference);

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate parsedDob = LocalDate.parse(dob, formatter);

                    SurveyForm surveyForm = new SurveyForm()
                            .setName(fullName)
                            .setCell(contact)
                            .setEmail(email)
                            .setDob(parsedDob);

                    surveyFormService.saveSurveyForm(surveyForm);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please use yyyy-MM-dd.");
                }

            }
        });

        // View results button logic
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SurveyResultsScreen(surveyFormService, userPreferenceService);
            }
        });
    }
}
