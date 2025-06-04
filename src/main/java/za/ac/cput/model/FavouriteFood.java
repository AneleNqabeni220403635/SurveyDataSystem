package za.ac.cput.model;

import java.sql.SQLOutput;
import java.util.Objects;

public enum FavouriteFood {
    PIZZA("Pizza"),
    PASTA("Pasta"),
    PapAndWors("Pap and wors"),
    Other("Other");

    private final String foodName;

    FavouriteFood(String foodName) {
        this.foodName = foodName;
    }
    public String getFood() {
        return foodName;
    }

    public static FavouriteFood fromKey(String key) {
        for (FavouriteFood food : values()) {
          //  System.out.println(food.foodName);
            if (Objects.equals(food.foodName, key.trim())) {
                return food;
            }
        }
        throw new IllegalArgumentException("Unknown key:" + key);
}

}
