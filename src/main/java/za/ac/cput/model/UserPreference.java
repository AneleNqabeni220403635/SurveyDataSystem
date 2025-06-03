package za.ac.cput.model;

import jakarta.persistence.*;

@Entity
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cell;
    private String email;
    private String dob;
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
    public UserPreference setName(String name) {
        this.name = name;
        return this;
    }

    public UserPreference setCell(String cell) {
        this.cell = cell;
        return this;
    }
    public UserPreference setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public UserPreference setEmail(String email) {
        this.email = email;
        return this;}
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
    // Getters (standard)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCell() {
        return cell;
    }

    public String getDob() {
        return dob;
    }
    public String getEmail() {
        return email;
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
