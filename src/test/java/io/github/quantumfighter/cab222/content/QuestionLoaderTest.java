package io.github.quantumfighter.cab222.content;

import io.github.quantumfighter.cab222.model.AnswerOption;
import io.github.quantumfighter.cab222.model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionLoaderTest {

    @Test
    void loadsQuestionFromJsonResource() {
        QuestionLoader loader = new QuestionLoader();

        List<Question> questions = loader.load("/fixtures/one-question.json");

        assertEquals(1, questions.size());

        Question question = questions.getFirst();

        assertEquals("W1-Q3", question.id());
        assertEquals("W1-L2", question.lessonId());
        assertEquals(4, question.options().size());
        assertEquals("C", question.correctOptionId());
    }

    @Test
    void loadsBothOsiLessonQuestions() {
        assertLessonQuestions(
                "/week1/w1-l2-questions.json",
                "W1-L2"
        );
    }

    @Test
    void loadsBothEncapsulationLessonQuestions() {
        assertLessonQuestions(
                "/week1/w1-l3-questions.json",
                "W1-L3"
        );
    }

    @Test
    void loadsBothNetworkScopeLessonQuestions() {
        assertLessonQuestions(
                "/week1/w1-l1-questions.json",
                "W1-L1"
        );
    }


    private void assertLessonQuestions(
            String resourcePath,
            String expectedLessonId
    ) {
        QuestionLoader loader = new QuestionLoader();

        List<Question> questions = loader.load(resourcePath);

        assertEquals(2, questions.size());

        for (Question question : questions) {
            assertEquals(expectedLessonId, question.lessonId());
            assertEquals(4, question.options().size());

            long distinctOptionIds = question.options().stream()
                    .map(AnswerOption::id)
                    .distinct()
                    .count();

            assertEquals(4L, distinctOptionIds);

            long matchingOptions = question.options().stream()
                    .filter(option ->
                            option.id().equals(question.correctOptionId()))
                    .count();

            assertEquals(1L, matchingOptions);
        }
    }
}


