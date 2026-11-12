package io.github.quantumfighter.cab222.model;

import io.github.quantumfighter.cab222.content.LessonLoader;
import io.github.quantumfighter.cab222.content.QuestionLoader;
import io.github.quantumfighter.cab222.content.WeekOneContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnitSessionTest {

    @Test
    void wrongThenCorrectAnswerCountsTheQuestionOnlyOnce() {
        UnitSession session = new UnitSession(new QuestionLoader()
                .load("/week1/w1-l1-questions.json"));

        assertEquals(0, session.completedCount());

        session.challengeFor("W1-Q1").submit("A");
        assertEquals(0, session.completedCount());

        session.challengeFor("W1-Q1").submit("B");
        assertEquals(1, session.completedCount());

        session.challengeFor("W1-Q1").submit("B");
        assertEquals(1, session.completedCount());
    }

    @Test
    void completesOnlyAfterAllSixQuestionsAreCorrect() {
        List<Question> questions = new WeekOneContent(new LessonLoader()).questions();
        assertEquals(
                List.of("W1-Q1", "W1-Q2", "W1-Q3", "W1-Q4", "W1-Q5", "W1-Q6"),
                questions.stream().map(Question::id).toList()
        );

        UnitSession session = new UnitSession(questions);

        for (Question question : questions.subList(0, 5)) {
            session.challengeFor(question.id()).submit(question.correctOptionId());
        }

        assertEquals(5, session.completedCount());
        assertFalse(session.isComplete());

        Question last = questions.getLast();
        session.challengeFor(last.id()).submit(last.correctOptionId());

        assertEquals(6, session.completedCount());
        assertTrue(session.isComplete());
    }

    @Test
    void nextRequiresCompletionAndContinuesAcrossLessons() {
        List<Question> questions = new WeekOneContent(new LessonLoader()).questions();
        UnitSession session = new UnitSession(questions);

        session.advance();
        assertEquals("W1-Q1", session.currentQuestion().id());

        session.challengeFor("W1-Q1").submit("A");
        assertFalse(session.canAdvance());
        session.challengeFor("W1-Q1").submit("B");
        assertTrue(session.canAdvance());
        session.advance();
        assertEquals("W1-Q2", session.currentQuestion().id());

        session.challengeFor("W1-Q2").submit("C");
        session.advance();
        assertEquals("W1-Q3", session.currentQuestion().id());
        assertEquals("W1-L2", session.currentQuestion().lessonId());

        session.selectLesson("W1-L3");
        session.challengeFor("W1-Q5").submit("D");
        session.advance();
        session.challengeFor("W1-Q6").submit("A");
        assertFalse(session.canAdvance());
        session.advance();
        assertEquals("W1-Q6", session.currentQuestion().id());
    }

    @Test
    void returningToLessonSelectsFirstUnansweredThenFirstForReview() {
        List<Question> questions = new WeekOneContent(new LessonLoader()).questions();
        UnitSession session = new UnitSession(questions);

        session.challengeFor("W1-Q1").submit("B");
        String correctFeedback = session.challengeFor("W1-Q1").feedback();
        session.advance();
        session.challengeFor("W1-Q2").submit("A");
        assertTrue(session.challengeFor("W1-Q2").feedback().startsWith("Not yet"));

        session.selectLesson("W1-L2");
        session.selectLesson("W1-L1");
        assertEquals("W1-Q2", session.currentQuestion().id());
        assertEquals("", session.challengeFor("W1-Q2").feedback());
        assertEquals(correctFeedback, session.challengeFor("W1-Q1").feedback());

        session.challengeFor("W1-Q2").submit("C");
        session.selectLesson("W1-L2");
        session.selectLesson("W1-L1");
        assertEquals("W1-Q1", session.currentQuestion().id());
        assertEquals(correctFeedback, session.challengeFor("W1-Q1").feedback());
        assertEquals(2, session.completedCount());
    }

    @Test
    void resetClearsCorrectAndIncorrectAttempts() {
        List<Question> questions = new WeekOneContent(new LessonLoader()).questions();
        UnitSession session = new UnitSession(questions);

        session.challengeFor("W1-Q1").submit("B");
        session.advance();
        session.challengeFor("W1-Q2").submit("A");
        assertEquals(1, session.completedCount());
        assertTrue(session.challengeFor("W1-Q2").feedback().startsWith("Not yet"));

        session.reset();

        assertEquals("W1-Q1", session.currentQuestion().id());
        assertFalse(session.canAdvance());
        assertEquals(0, session.completedCount());
        assertFalse(session.isComplete());
        assertEquals("", session.challengeFor("W1-Q1").feedback());
        assertEquals("", session.challengeFor("W1-Q2").feedback());
    }
}
