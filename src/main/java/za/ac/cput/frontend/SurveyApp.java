package za.ac.cput.frontend;

import org.springframework.boot.SpringApplication;
import za.ac.cput.SurveyApplication;
import za.ac.cput.domain.SurveyForm;
import za.ac.cput.model.FavouriteFood;
import za.ac.cput.model.PreferenceRating;
import za.ac.cput.model.UserPreference;
import za.ac.cput.repository.UserPreferenceRepository;
import za.ac.cput.service.SurveyFormService;
import za.ac.cput.service.UserPreferenceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SurveyApp {
    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");

        ApplicationContext context = SpringApplication.run(SurveyApplication.class, args);

// Get UserPreferenceService from Spring
        UserPreferenceService userPreferenceService = context.getBean(UserPreferenceService.class);
        SurveyFormService surveyFormService = context.getBean(SurveyFormService.class);

        JFrame frame = new JFrame("Survey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        panel.add(new JLabel("I like to watch movies:"));
        panel.add(moviesBox);
        panel.add(new JLabel("I like to listen to radio:"));
        panel.add(radioBox);
        panel.add(new JLabel("I like to eat out:"));
        panel.add(eatOutBox);
        panel.add(new JLabel("I like to watch TV:"));
        panel.add(tvBox);

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Handle form submission
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String dob = dobField.getText();
                String contact = contactField.getText();

// Collect favorite food selections
                StringBuilder favoriteFood = new StringBuilder();
                if (pizzaCheck.isSelected()) favoriteFood.append("Pizza ");
                if (pastaCheck.isSelected()) favoriteFood.append("Pasta ");
                if (papCheck.isSelected()) favoriteFood.append("Pap and Wors ");
                if (otherCheck.isSelected()) favoriteFood.append("Other ");

// Collect ratings
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

                SurveyForm surveyForm = new SurveyForm()
                        .setName(fullName)
                        .setCell(contact)
                        .setEmail(email)
                        .setDob(dob)
                        .setPreferenceId(userPreference.getId());

                surveyFormService.saveSurveyForm(surveyForm);


            }
        });
    }
}