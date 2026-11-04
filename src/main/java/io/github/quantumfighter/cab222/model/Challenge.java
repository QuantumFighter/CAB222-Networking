package io.github.quantumfighter.cab222.model;


/**
 * Manages answer attempts, feedback and completion for one question.
 */
public class Challenge {

    private final Question question;
    private String feedback = "";

    public Challenge(Question question) {
        this.question = question;
    }

    public void submit(String optionId) {
        if (optionId == null) {
            feedback = "Choose an answer first";
            return;
        }

        AnswerOption selectedOption = question.options().stream()
                .filter(option -> option.id().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown answer option: " + optionId
                ));

        if (!question.correctOptionId().equals(selectedOption.id())) {
            feedback = "Not yet\n" + selectedOption.explanation();
        }
    }

    public String feedback() {
        return feedback;
    }

    public boolean isCompleted() {
        return false;
    }
}