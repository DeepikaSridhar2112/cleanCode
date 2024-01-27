package trivia.model.Questions;

public class ScienceQuestion extends Question {
    @Override
    public String generateQuestion(String query) {
        return "Science Question " + query;
    }
}
