package trivia.model.Questions;

public class SportsQuestion extends Question {
    @Override
    public String generateQuestion(String query) {
        return "Sports Question " + query;
    }
}
