package trivia.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum CategoryName {
    POP("Pop"),SCIENCE("Science"),SPORTS("Sports"),ROCK("Rock");
    private final String name;

    public String getName() {
        return name;
    }

    CategoryName(final String name){
        this.name=name;
    }

    public static Stream<CategoryName> getCategoryNames() {
        return Arrays.stream(CategoryName.values());
    }
}
