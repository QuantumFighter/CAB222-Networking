package io.github.quantumfighter.cab222.content;

import io.github.quantumfighter.cab222.model.Question;

import com.google.gson.Gson;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

/**
 * Loads question data from UTF-8 JSON resources on the classpath.
 *
 * <p>Each resource contains an array of question objects whose field names match the components of {@link Question}.</p>
 */
public class QuestionLoader {
    /**
     * Reads a JSON resource and converts its question objects into a list.
     *
     * <p>The resource must contain a JSON array of non-null questions.
     * The returned list cannot be modified through its list operations.
     * @param resourcePath the resource name; a leading slash resolves it
     *                     from the classpath root,
     *                     for example {@code /week1/w1-l2-questions.json}
     * @return the questions in their original JSON array order
     * @throws IllegalArgumentException if the resource cannot be found
     * @throws com.google.gson.JsonParseException if Gson cannot read
     *         or convert the JSON into a question array
     * @throws NullPointerException if the path is null, or the parsed
     *         array or one of its elements is null
     * @throws UncheckedIOException if closing the resource fails
     */
    public List<Question> load(String resourcePath) {
        InputStream input = QuestionLoader.class.getResourceAsStream(resourcePath);

        if (input == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Question[] questions = gson.fromJson(reader, Question[].class);

            return List.of(questions);
        } catch (IOException exception) {
            throw new UncheckedIOException("Could not read resource:" + resourcePath, exception);
        }
    }
}
