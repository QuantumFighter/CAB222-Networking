package io.github.quantumfighter.cab222.model;

import java.util.List;

/**
 *
 * @param id
 * @param lessonId
 * @param prompt
 * @param options
 * @param correctOptionId
 * @param source
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
