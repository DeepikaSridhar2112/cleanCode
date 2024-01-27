package trivia.model.Questions;

public class RockQuestion extends Question {
    @Override
    public String generateQuestion(String query) {
        return "Rock Question " + query;
    }
}
