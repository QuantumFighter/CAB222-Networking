package io.github.quantumfighter.cab222.content;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LessonLoaderTest {

    @Test
    void loadsCompleteNetworkScopeLesson() {
        LessonLoader loader = new LessonLoader();

        String lesson = loader.load(
                "/week1/w1-l1-network-scope-and-devices.txt"
        );

        assertTrue(lesson.startsWith("ID: W1-L1"));
        assertTrue(lesson.contains(
                "Title: Network scope and devices"
        ));
        assertTrue(lesson.contains("Learning outcome:"));
        assertTrue(lesson.contains("Sources:"));
        assertTrue(lesson.stripTrailing().endsWith(
                "Local resource sharing can work without an Internet connection."
        ));
    }
}