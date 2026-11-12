package io.github.quantumfighter.cab222.model;

import io.github.quantumfighter.cab222.content.QuestionLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
