package trivia.model;

import trivia.model.Questions.Question;

public record Category(CategoryName categoryName, Question question) {
    @Override
    public String toString() {
        return categoryName.name();
    }

    public void populateQuestions(final String query) {
        question.storeQuestions(query);
    }

    public void retrieveQuestion() {
        System.out.println("The category is " + categoryName.getName());
        final var query = question.retrieveQuestion();
        System.out.println(query);
    }
}
