package trivia;

import trivia.model.Category;
import trivia.model.CategoryName;
import trivia.model.Questions.*;

public class CategoryFactory {
    public Category createCategory(CategoryName categoryName) {
        final var question = getQuestionBasedOnCategoryName(categoryName);
        return new Category(categoryName, question);
    }

    private Question getQuestionBasedOnCategoryName(CategoryName categoryName) {
        return switch (categoryName) {
            case POP -> new PopQuestion();
            case ROCK -> new RockQuestion();
            case SPORTS -> new SportsQuestion();
            case SCIENCE -> new ScienceQuestion();
        };
    }
}