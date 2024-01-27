package trivia.model;

public class Player {
    private String playerName;
    private int playerNumber;
    private Place place;
    private boolean isInPenaltyBox;
    private int score;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }

    public int getPlaceNumber() {
        return place.placeNumber();
    }

    public Category getCategory() {
        return place.category();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        score++;
    }


    public Player(final String playerName, final int playerNumber, final Place place) {
        setPlayerName(playerName);
        setPlayerNumber(playerNumber);
        setPlace(place);
        setInPenaltyBox(false);
        setScore(0);
    }

    public void retrieveQuestion() {
        getCategory().retrieveQuestion();
    }
}
