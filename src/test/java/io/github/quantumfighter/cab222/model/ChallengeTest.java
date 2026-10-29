package io.github.quantumfighter.cab222.model;

import io.github.quantumfighter.cab222.content.QuestionLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ChallengeTest {

    @Test
    void submittingWithoutSelectionRequestsAnAnswer() {
        Question question = new QuestionLoader()
                .load("/fixtures/one-question.json")
                .getFirst();

        Challenge challenge = new Challenge(question);

        challenge.submit(null);

        assertEquals(
                "Choose an answer first",
                challenge.feedback()
        );
        assertFalse(challenge.isCompleted());
    }
}