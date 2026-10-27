package io.github.quantumfighter.cab222.model;

import java.util.List;

/**
 * A learning question with answer options and a supporting source.
 *
 * @param id the stable question identifier, such as W1-Q3
 * @param lessonId the identifier of the lesson containing this question
 * @param prompt the question presented to the learner
 * @param options the available answer choices
 * @param correctOptionId the identifier of the correct answer option
 * @param source the course reference supporting the question and answer
 */
public record Question
        (
                String id,
                String lessonId,
                String prompt,
                List<AnswerOption> options,
                String correctOptionId,
                String source

        ) {
}
