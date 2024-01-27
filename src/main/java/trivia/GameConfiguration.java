package trivia;

import trivia.model.Category;
import trivia.model.CategoryName;
import trivia.model.Place;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameConfiguration implements GameRulesContract {
    private static final int MAX_PLACE_RANGE_EXCLUSIVE = 12;
    private static final int MIN_PLACE_RANGE_INCLUSIVE = 0;
    private final CategoryFactory categoryFactory;

    GameConfiguration() {
        this.categoryFactory = new CategoryFactory();
    }

    public Map<Integer, Place> getGameConfigurationMap() {
        final var categoryMap = configureCategoryMap();
        final var placeList = configurePlacesForGame(categoryMap);
        return placeList.stream().collect(Collectors.toMap(Place::placeNumber, Function.identity()));
    }

    private void populateQuestionsForCategory(final Category category) {
        for (int i = 0; i < 50; i++) {
            category.populateQuestions(String.valueOf(i));
        }
    }

    private Map<CategoryName, Category> configureCategoryMap() {
        final var categoryNames = CategoryName.getCategoryNames();
        final var categoriesForGame = categoryNames.map(categoryFactory::createCategory).toList();
        categoriesForGame.forEach(this::populateQuestionsForCategory);
        return categoriesForGame.stream().collect(Collectors.toMap(Category::categoryName, Function.identity()));
    }

    private List<Place> configurePlacesForGame(final Map<CategoryName, Category> categoryMap) {
        final var placeNumbersForGame=IntStream.range(MIN_PLACE_RANGE_INCLUSIVE, MAX_PLACE_RANGE_EXCLUSIVE);
        return placeNumbersForGame.mapToObj(placeNumber -> new Place(placeNumber, getCategory(categoryMap, placeNumber))).collect(Collectors.toList());
    }

    private Category getCategory(Map<CategoryName, Category> categoryMap, int placeNumber) {
        final var categoryName=getCategoryNameBasedOnPlaceNumber(placeNumber);
        return categoryMap.get(categoryName);
    }

    private CategoryName getCategoryNameBasedOnPlaceNumber(final int placeNumber) {
        return switch (placeNumber) {
            case 1, 5, 9 -> CategoryName.SCIENCE;
            case 2, 6, 10 -> CategoryName.SPORTS;
            case 0, 4, 8 -> CategoryName.POP;
            default -> CategoryName.ROCK;
        };
    }
}
