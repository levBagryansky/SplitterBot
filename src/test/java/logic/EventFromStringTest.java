package logic;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class EventFromStringTest {

    @Test
    void parsesCorrectly() {
        String input = "Dinner, 100.5, John, Alice - 25, Bob - 50, John - 25.5";
        MatcherAssert.assertThat(
            new EventFromString(input).get(),
            Matchers.equalTo(
                new Event(
                    "Dinner",
                    10050L,
                    "John",
                    Map.of("Alice", 2500L, "Bob", 5000L, "John", 2550L)
                )
            )
        );
    }

    @Test
    void parsesRussianCorrectly() {
        String input = "Обед, 100.5, Джон, Алиса - 25, Боба - 50, Джон - 25.5";
        MatcherAssert.assertThat(
            new EventFromString(input).get(),
            Matchers.equalTo(
                new Event(
                    "Обед",
                    10050L,
                    "Джон",
                    Map.of("Алиса", 2500L, "Боба", 5000L, "Джон", 2550L)
                )
            )
        );
    }
}
