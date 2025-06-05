package za.ac.cput.model;

import jakarta.persistence.*;
import za.ac.cput.domain.SurveyForm;

@Entity
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FavouriteFood favouriteFood;

    @Enumerated(EnumType.STRING)
    private PreferenceRating watchingMovies;

    @Enumerated(EnumType.STRING)
    private PreferenceRating listeningToRadio;

    @Enumerated(EnumType.STRING)
    private PreferenceRating eatOut;

    @Enumerated(EnumType.STRING)
    private PreferenceRating watchingTV;

    public UserPreference() {}

    // Fluent setters
    public UserPreference setFavouriteFood(FavouriteFood favouriteFood) {
        this.favouriteFood = favouriteFood;
        return this;
    }

    public UserPreference setWatchingMovies(PreferenceRating watchingMovies) {
        this.watchingMovies = watchingMovies;
        return this;
    }

    public UserPreference setListeningToRadio(PreferenceRating listeningToRadio) {
        this.listeningToRadio = listeningToRadio;
        return this;
    }

    public UserPreference setEatOut(PreferenceRating eatOut) {
        this.eatOut = eatOut;
        return this;
    }

    public UserPreference setWatchingTV(PreferenceRating watchingTV) {
        this.watchingTV = watchingTV;
        return this;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public FavouriteFood getFavouriteFood() {
        return favouriteFood;
    }

    public PreferenceRating getWatchingMovies() {
        return watchingMovies;
    }

    public PreferenceRating getListeningToRadio() {
        return listeningToRadio;
    }

    public PreferenceRating getEatOut() {
        return eatOut;
    }

    public PreferenceRating getWatchingTV() {
        return watchingTV;
    }
}
