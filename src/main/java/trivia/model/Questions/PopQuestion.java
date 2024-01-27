package trivia.model.Questions;

public class PopQuestion extends Question {
    @Override
    public String generateQuestion(String query) {
        return "Pop Question " + query;
    }
}
