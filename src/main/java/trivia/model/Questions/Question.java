package trivia.model.Questions;

import java.util.LinkedList;

public abstract class Question {
    private final LinkedList<String> questionsList= new LinkedList<>();
    public String retrieveQuestion(){
        return questionsList.removeFirst();
    }
    public void storeQuestions(final String question){
     questionsList.addLast(generateQuestion(question));
    }
    public abstract String generateQuestion(final String question);
}
