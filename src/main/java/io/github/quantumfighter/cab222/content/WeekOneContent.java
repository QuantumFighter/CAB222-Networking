package io.github.quantumfighter.cab222.content;

import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the Week 1 lessons and challenge questions.
 *
 * <p>Resource locations are kept here so callers work with lessons
 * and questions rather than filenames.
 */
public class WeekOneContent {

    private final List<Lesson> lessons;

    /**
     * Loads the Week 1 lesson resources.
     *
     * @param loader the loader used to read lesson text
     */
    public WeekOneContent(LessonLoader loader) {
        lessons = List.of(
                new Lesson(
                        "W1-L1",
                        "Network scope and devices",
                        loader.load(
                                "/week1/w1-l1-network-scope-and-devices.txt"
                        )
                ),
                new Lesson(
                        "W1-L2",
                        "Understanding the OSI model",
                        loader.load(
                                "/week1/w1-l2-osi-model.txt"
                        )
                ),
                new Lesson(
                        "W1-L3",
                        "Understanding encapsulation",
                        loader.load(
                                "/week1/w1-l3-encapsulation.txt"
                        )
                )
        );
    }

    /**
     * Returns the lessons in teaching order.
     *
     * @return an unmodifiable list of the loaded lessons
     */
    public List<Lesson> lessons() {
        return lessons;
    }

    /**
     * Loads the first question for the network scope and devices lesson.
     *
     * @return the first challenge question
     */
    public Question firstChallengeQuestion() {
        return questions().getFirst();
    }

    /**
     * Loads the Week 1 challenge questions in lesson and question order.
     *
     * @return an unmodifiable list of questions in teaching order
     */
    public List<Question> questions() {
        QuestionLoader loader = new QuestionLoader();
        List<Question> questions = new ArrayList<>();

        questions.addAll(loader.load("/week1/w1-l1-questions.json"));
        questions.addAll(loader.load("/week1/w1-l2-questions.json"));
        questions.addAll(loader.load("/week1/w1-l3-questions.json"));

        return List.copyOf(questions);
    }
}