package snakepvp.snake.entities.items.food;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.items.base.Item;
import snakepvp.snake.entities.items.base.SpawnableFactory;
import snakepvp.snake.entities.items.food.types.Apple;

import java.util.Random;  // Import Random for generating random values
import java.util.function.Function;

public enum FoodFactory implements SpawnableFactory {
    // Enum constant for the food type "APPLE", which uses the Apple::new constructor reference
    APPLE(Apple::new);

    // Field to hold a function that takes a Coordinate2D and returns a Food object
    private final Function<Coordinate2D, Food> constructor;

    /**
     * Constructor for the FoodType enum.
     * Initializes the constructor field with a function that can create a Food object
     * when provided a Coordinate2D location.
     *
     * @param constructor A function that takes a Coordinate2D and returns a new Food object.
     */
    FoodFactory(Function<Coordinate2D, Food> constructor) {
        this.constructor = constructor;
    }

    /**
     * Returns a random FoodType from the available enum constants.
     *
     * @return A random FoodType.
     */
    public static FoodFactory getRandomFoodType() {
        // Get all the values in the FoodType enum
        FoodFactory[] foodTypes = FoodFactory.values();

        // Generate a random index to select a FoodType
        Random random = new Random();
        int randomIndex = random.nextInt(foodTypes.length);

        // Return the random FoodType
        return foodTypes[randomIndex];
    }

    /**
     * Creates a new instance of a Food object at the given Coordinate2D location.
     *
     * @param location The 2D coordinates where the food object should be placed.
     * @return A new instance of the corresponding Food object.
     */
    @Override
    public Food createInstance(Coordinate2D location) {
        return constructor.apply(location);
    }

}