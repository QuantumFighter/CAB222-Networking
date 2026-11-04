package io.github.quantumfighter.cab222.controller;

import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.model.Question;
import io.github.quantumfighter.cab222.ui.LearningView;

import java.util.List;
import java.util.Objects;

/**
 * Coordinates lesson selection and challenge display.
 */
public class LearningController {

    private final List<Lesson> lessons;
    private final Question firstQuestion;
    private final LearningView view;

    /**
     * Connects the available lessons and first challenge question to the view.
     *
     * @param lessons the lessons in teaching order
     * @param firstQuestion the question to show for its matching lesson
     * @param view the interface used to display the lesson and question
     */
    public LearningController(
            List<Lesson> lessons,
            Question firstQuestion,
            LearningView view
    ) {
        this.lessons = List.copyOf(lessons);
        this.firstQuestion = Objects.requireNonNull(firstQuestion);
        this.view = Objects.requireNonNull(view);
    }

    /**
     * Connects selection events, displays the first lesson
     * and opens the window.
     *
     * <p>Call once on Swing's event dispatch thread.
     */
    public void start() {
        view.onLessonSelected(lesson -> {
            view.showLesson(lesson);

            if (lesson.id().equals(firstQuestion.lessonId())) {
                view.showQuestion(firstQuestion);
            } else {
                view.showQuestion(null);
            }
        });

        view.setLessons(lessons);
        view.selectFirstLesson();
        view.open();
    }
}