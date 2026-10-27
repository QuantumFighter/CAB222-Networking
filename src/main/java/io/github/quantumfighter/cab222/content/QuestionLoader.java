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

public class QuestionLoader {
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
