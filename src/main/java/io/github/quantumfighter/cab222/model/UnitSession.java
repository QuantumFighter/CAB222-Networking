package io.github.quantumfighter.cab222.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds question navigation and challenge progress for one run through a unit.
 */
public class UnitSession {

    private final List<Question> questions;
    private final Map<String, Challenge> challenges = new LinkedHashMap<>();
    private int currentIndex;

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
        currentIndex = this.questions.isEmpty() ? -1 : 0;
    }

    public Challenge challengeFor(String questionId) {
        Challenge challenge = challenges.get(questionId);
        if (challenge == null) {
            throw new IllegalArgumentException("Unknown question ID: " + questionId);
        }
        return challenge;
    }

    public int totalQuestions() {
        return questions.size();
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
     * Returns the displayed question, or {@code null} if the lesson has no questions.
     */
    public Question currentQuestion() {
        return currentIndex < 0 ? null : questions.get(currentIndex);
    }

    /**
     * Selects the lesson's first incomplete question, or its first question for review.
     *
     * @param lessonId the selected lesson's ID
     */
    public void selectLesson(String lessonId) {
        int firstMatch = -1;
        for (int index = 0; index < questions.size(); index++) {
            Question question = questions.get(index);
            if (!question.lessonId().equals(lessonId)) {
                continue;
            }
            if (firstMatch < 0) {
                firstMatch = index;
            }
            if (!challengeFor(question.id()).isCompleted()) {
                moveTo(index);
                return;
            }
        }
        moveTo(firstMatch);
    }

    /**
     * Checks whether Next can advance to the following question in teaching order.
     */
    public boolean canAdvance() {
        return currentIndex >= 0
                && currentIndex + 1 < questions.size()
                && challengeFor(currentQuestion().id()).isCompleted();
    }

    /**
     * Advances one question when the current question is complete and a successor exists.
     */
    public void advance() {
        if (canAdvance()) {
            moveTo(currentIndex + 1);
        }
    }

    /**
     * Replaces all attempts with fresh challenges and returns to the first question.
     */
    public void reset() {
        for (Question question : questions) {
            challenges.put(question.id(), new Challenge(question));
        }
        currentIndex = questions.isEmpty() ? -1 : 0;
    }

    private void moveTo(int questionIndex) {
        if (currentIndex != questionIndex) {
            if (currentIndex >= 0) {
                challengeFor(currentQuestion().id()).clearIncompleteFeedback();
            }
            currentIndex = questionIndex;
        }
    }
}