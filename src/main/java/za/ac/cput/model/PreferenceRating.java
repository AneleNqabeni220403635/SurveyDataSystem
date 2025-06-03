package za.ac.cput.model;

public enum PreferenceRating {
    STRONGLY_AGREE("Strongly Agree"),
    AGREE("Agree"),
    NEUTRAL("Neutral"),
    DISAGREE("Disagree"),
    STRONGLY_DISAGREE("Strongly Disagree");

    private final String rating;

    PreferenceRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public static PreferenceRating fromKey(String key) {
        for (PreferenceRating rating : values()) {
            if (rating.rating.equalsIgnoreCase(key)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown key: " + key);
}

}
