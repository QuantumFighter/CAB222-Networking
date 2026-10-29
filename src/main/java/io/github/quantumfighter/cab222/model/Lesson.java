package io.github.quantumfighter.cab222.model;

/**
 * A lesson that can be selected and displayed independently of its storage.
 *
 * @param id the stable lesson identifier
 * @param title the title displayed in the lesson list
 * @param text the complete lesson text, including outcome and references
 */
public record Lesson(
    String id,
    String title,
    String text
){}
