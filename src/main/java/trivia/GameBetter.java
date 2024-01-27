package trivia;

import trivia.model.Place;
import trivia.model.Player;

import java.util.ArrayList;
import java.util.Map;

// REFACTOR ME
public class GameBetter implements IGame {
    private static final int MAXIMUM_ALLOWED_PLACE_VALUE_PLUS_ONE = 12;

    private final Map<Integer, Place> gameConfiguration;
    private final ArrayList<Player> players;
    private int currentPlayerNumber = -1;

    public GameBetter(GameRulesContract gameRulesContract) {
        players = new ArrayList<>();
        this.gameConfiguration= gameRulesContract.getGameConfigurationMap();
    }

    public boolean add(String playerName) {
        final var player = new Player(playerName, generatePlayerNumber(),gameConfiguration.get(0));
        players.add(player);
        logPlayerAddedDetails(player);
        return true;
    }

    public void roll(int roll) {
        chooseCurrentPlayerNumber();
        final var currentPlayer = players.get(currentPlayerNumber);
        logRollDetailsForPlayer(currentPlayer.getPlayerName(), roll);
        if (currentPlayer.isInPenaltyBox()) {
            handleRollForPenaltyUsers(roll, currentPlayer);
        } else {
            updatePositionAndAskQuestion(roll, currentPlayer);
        }

    }

    private void handleRollForPenaltyUsers(int roll, Player currentPlayer) {
        if (isAnOddRoll(roll)) {
            freePlayerFromPenaltyBox(currentPlayer);
            updatePositionAndAskQuestion(roll, currentPlayer);
        } else {
            retainPlayerInPenaltyBox(currentPlayer);
        }
    }


    public boolean wasCorrectlyAnswered() {
        final var player = players.get(currentPlayerNumber);
        if (!player.isInPenaltyBox()) {
            player.incrementScore();
            logCorrectAnswerDetails(player);
            return didPlayerWin(player);
        } else {
            return true;
        }
    }

    public boolean wrongAnswer() {
        final var player = players.get(currentPlayerNumber);
        logWrongAnswer(player);
        player.setInPenaltyBox(true);
        return true;
    }
    private void logCorrectAnswerDetails(Player player) {
        System.out.println("Answer was correct!!!!");
        final var details = String.format("%s now has %s Gold Coins.",players.get(currentPlayerNumber).getPlayerName(),player.getScore());
        System.out.println(details);
    }

    private int generatePlayerNumber() {
        return players.size() + 1;
    }

    private void logPlayerAddedDetails(final Player player) {
        System.out.println(player.getPlayerName() + " was added");
        System.out.println("They are player number " + player.getPlayerNumber());
    }

    private void updatePositionAndAskQuestion(int roll, final Player player) {
        updatePlayerPlaceBasedOnRoll(roll, player);
        logPlayersNewLocation(player);
        player.retrieveQuestion();
    }

    private void updatePlayerPlaceBasedOnRoll(final int roll, final Player player) {
        final var updatedPlace = player.getPlaceNumber() + roll;
        final var scopeLimitedPlace = (updatedPlace >= MAXIMUM_ALLOWED_PLACE_VALUE_PLUS_ONE) ? updatedPlace - MAXIMUM_ALLOWED_PLACE_VALUE_PLUS_ONE : updatedPlace;
        player.setPlace(gameConfiguration.get(scopeLimitedPlace));
    }

    private void retainPlayerInPenaltyBox(final Player player) {
        System.out.println(player.getPlayerName() + " is not getting out of the penalty box");
        player.setInPenaltyBox(true);
    }

    private void freePlayerFromPenaltyBox(Player player) {
        player.setInPenaltyBox(false);
        System.out.println(player.getPlayerName() + " is getting out of the penalty box");
    }

    private boolean isAnOddRoll(int roll) {
        return roll % 2 != 0;
    }

    private void logPlayersNewLocation(final Player player) {
        final var logMessage = String.format("%s's new location is %s", player.getPlayerName(), player.getPlaceNumber());
        System.out.println(logMessage);
    }

    private void logRollDetailsForPlayer(String playerName, int rolledNumber) {
        System.out.println(playerName + " is the current player");
        System.out.println("They have rolled a " + rolledNumber);
    }
    private static void logWrongAnswer(Player player) {
        System.out.println("Question was incorrectly answered");
        System.out.println(player.getPlayerName() + " was sent to the penalty box");
    }

    private void chooseCurrentPlayerNumber() {
        currentPlayerNumber++;
        if (currentPlayerNumber == players.size()) currentPlayerNumber = 0;
    }
    private boolean didPlayerWin(final Player player) {
        return !(player.getScore() == 6);
    }
}
