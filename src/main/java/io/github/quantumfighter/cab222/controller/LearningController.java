package io.github.quantumfighter.cab222.controller;

import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.ui.LearningView;

import java.util.List;
import java.util.Objects;

/**
 * Coordinates lesson selection and display during application startup.
 */
public class LearningController {

    private final List<Lesson> lessons;
    private final LearningView view;

    /**
     * Connects the available lessons to their view.
     *
     * @param lessons the lessons in teaching order
     * @param view the interface used to display them
     */
    public LearningController(
            List<Lesson> lessons,
            LearningView view
    ) {
        this.lessons = List.copyOf(lessons);
        this.view = Objects.requireNonNull(view);
    }

    /**
     * Connects selection events, displays the first lesson
     * and opens the window.
     *
     * <p>Call once on Swing's event dispatch thread.
     */
    public void start() {
        view.onLessonSelected(view::showLesson);
        view.setLessons(lessons);
        view.selectFirstLesson();
        view.open();
    }
}