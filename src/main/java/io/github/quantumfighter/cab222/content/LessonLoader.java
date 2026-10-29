package io.github.quantumfighter.cab222.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

/**
 * Loads complete lesson test
 */
public class LessonLoader {
    /**
     * Reads a lesson resource and returns its complete text.
     *
     * @param resourcePath the resource path, starting with a slash
     * @return the complete lesson text
     * @throws IllegalArgumentException if the resource cannot be found
     * @throws UncheckedIOException if reading or closing the resource fails
     */
    public String load(String resourcePath) {
        try (InputStream input =
                     LessonLoader.class.getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "Resource not found: " + resourcePath
                );
            }

            return new String(
                    input.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException exception) {
            throw new UncheckedIOException(
                    "Could not read lesson resource: " + resourcePath,
                    exception
            );
        }
    }
}