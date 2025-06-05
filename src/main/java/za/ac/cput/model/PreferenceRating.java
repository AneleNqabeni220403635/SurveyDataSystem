package za.ac.cput.model;

public enum PreferenceRating {
    STRONGLY_AGREE("Strongly Agree", 5),
    AGREE("Agree", 4),
    NEUTRAL("Neutral", 3),
    DISAGREE("Disagree", 2),
    STRONGLY_DISAGREE("Strongly Disagree", 1);

    private final String rating;
    private final int numericValue;

    PreferenceRating(String rating, int numericValue) {
        this.rating = rating;
        this.numericValue = numericValue;
    }

    public String getRating() {

        return rating;
    }

    public int getNumericValue() {
        return numericValue;
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
