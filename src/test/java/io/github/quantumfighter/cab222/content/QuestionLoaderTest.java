package io.github.quantumfighter.cab222.content;

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
        QuestionLoader loader = new QuestionLoader();

        List<Question> questions =
                loader.load("/week1/w1-l2-questions.json");

        assertEquals(2, questions.size());

        for (Question question : questions) {
            assertEquals("W1-L2", question.lessonId());
            assertEquals(4, question.options().size());

            long matchingOptions = question.options().stream()
                    .filter(option ->
                            option.id().equals(question.correctOptionId()))
                    .count();

            assertEquals(1L, matchingOptions);
        }
    }

    @Test
    void loadsBothEncapsulationLessonQuestions() {
        QuestionLoader loader = new QuestionLoader();

        List<Question> questions =
                loader.load("/week1/w1-l3-questions.json");

        assertEquals(2, questions.size());

        for (Question question : questions) {
            assertEquals("W1-L3", question.lessonId());
            assertEquals(4, question.options().size());

            long matchingOptions = question.options().stream()
                    .filter(option ->
                            option.id().equals(question.correctOptionId()))
                    .count();

            assertEquals(1L, matchingOptions);
        }
    }
}
