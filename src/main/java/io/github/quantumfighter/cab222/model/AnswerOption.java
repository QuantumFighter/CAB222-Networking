package io.github.quantumfighter.cab222.model;

/**
 * One selectable answer and its explanatory feedback.
 *
 * @param id the option identifier within its question, such as A or B
 * @param text the answer text displayed to the learner
 * @param explanation feedback explaining why this option is correct
 *                    or incorrect
 */
public record AnswerOption
        (
                String id,
                String text,
                String explanation
        ) {

}
