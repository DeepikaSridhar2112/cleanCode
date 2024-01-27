package trivia;

import trivia.model.Place;

import java.util.Map;

public interface GameRulesContract {
    Map<Integer, Place> getGameConfigurationMap();

    }
