package io.github.quantumfighter.cab222.model;

import io.github.quantumfighter.cab222.content.QuestionLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    void submittingIncorrectAnswerExplainsTheMistake() {
        Question question = new QuestionLoader()
                .load("/fixtures/one-question.json")
                .getFirst();

        AnswerOption incorrectOption = question.options().getFirst();

        Challenge challenge = new Challenge(question);

        challenge.submit(incorrectOption.id());

        assertEquals(
                "Not yet\n" + incorrectOption.explanation(),
                challenge.feedback()
        );
        assertFalse(challenge.isCompleted());
    }

    @Test
    void correctRetryCompletesChallengeAfterIncorrectAnswer() {
        Question question = new QuestionLoader()
                .load("/fixtures/one-question.json")
                .getFirst();

        Challenge challenge = new Challenge(question);

        challenge.submit("A");
        assertFalse(
                challenge.isCompleted(),
                "An incorrect answer should leave the challenge open"
        );

        challenge.submit("C");
        assertTrue(
                challenge.isCompleted(),
                "A correct retry should complete the challenge"
        );
    }
    @Test
    void submittingAfterCompletionPreservesCorrectFeedback() {
        Question question = new QuestionLoader()
                .load("/fixtures/one-question.json")
                .getFirst();

        Challenge challenge = new Challenge(question);

        challenge.submit("C");
        String completedFeedback = challenge.feedback();
        assertTrue(completedFeedback.startsWith("Correct\n"));

        challenge.submit("A");

        assertEquals(completedFeedback, challenge.feedback());
        assertTrue(challenge.isCompleted());
    }
}