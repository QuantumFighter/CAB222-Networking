package io.github.quantumfighter.cab222.content;

import io.github.quantumfighter.cab222.model.Lesson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeekOneContentTest {

    @Test
    void loadsWeekOneLessonsInTeachingOrder() {
        WeekOneContent content =
                new WeekOneContent(new LessonLoader());

        List<Lesson> lessons = content.lessons();

        assertEquals(
                List.of("W1-L1", "W1-L2", "W1-L3"),
                lessons.stream().map(Lesson::id).toList()
        );

        for (Lesson lesson : lessons) {
            assertTrue(
                    lesson.text().startsWith("ID: " + lesson.id()),
                    "Resource should match lesson " + lesson.id()
            );

            assertTrue(
                    lesson.text().contains("Title: " + lesson.title()),
                    "Resource should contain the expected title"
            );

            assertTrue(
                    lesson.text().contains("Learning outcome:"),
                    "Lesson should include its learning outcome"
            );

            assertTrue(
                    lesson.text().contains("Source:")
                            || lesson.text().contains("Sources:"),
                    "Lesson should include its source references"
            );
        }
    }
}