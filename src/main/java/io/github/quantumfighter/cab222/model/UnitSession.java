package io.github.quantumfighter.cab222.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds challenge progress for one run through a unit.
 */
public class UnitSession {

    private final List<Question> questions;
    private final Map<String, Challenge> challenges = new LinkedHashMap<>();

    public UnitSession(List<Question> questions) {
        this.questions = List.copyOf(questions);
        for (Question question : this.questions) {
            if (challenges.containsKey(question.id())) {
                throw new IllegalArgumentException(
                        "Duplicate question ID: " + question.id()
                );
            }
            challenges.put(question.id(), new Challenge(question));
        }
    }

    public Challenge challengeFor(String questionId) {
        Challenge challenge = challenges.get(questionId);
        if (challenge == null) {
            throw new IllegalArgumentException("Unknown question ID: " + questionId);
        }
        return challenge;
    }
    public int completedCount() {
        int completed = 0;
        for (Challenge challenge : challenges.values()) {
            if (challenge.isCompleted()) {
                completed++;
            }
        }
        return completed;
    }

    /**
     * Checks whether every question in a nonempty unit is complete.
     *
     * @return whether the learner has completed the unit
     */
    public boolean isComplete() {
        return !challenges.isEmpty() && completedCount() == challenges.size();
    }

    /**
     * Replaces all attempts with fresh challenges for the same questions.
     */
    public void reset() {
        for (Question question : questions) {
            challenges.put(question.id(), new Challenge(question));
        }
    }
}