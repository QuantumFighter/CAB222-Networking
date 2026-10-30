package io.github.quantumfighter.cab222.model;

/**
 * Manages answer attempts, feedback and completion for one question.
 */
public class Challenge {

    private String feedback = "";
    public Challenge(Question question) {
    }

    public void submit(String optionId) {
        if (optionId == null) {
            feedback = "Choose an answer first";
            return;
        }
    }

    public String feedback() {
        return feedback;
    }

    public boolean isCompleted() {
        return false;
    }
}