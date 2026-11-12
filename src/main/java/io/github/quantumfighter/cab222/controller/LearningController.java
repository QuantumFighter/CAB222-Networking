package io.github.quantumfighter.cab222.controller;

import io.github.quantumfighter.cab222.model.Challenge;
import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.model.Question;
import io.github.quantumfighter.cab222.model.UnitSession;
import io.github.quantumfighter.cab222.ui.LearningView;

import java.util.List;
import java.util.Objects;

/**
 * Connects lesson navigation and answer events to the current unit session.
 */
public class LearningController {

    private final List<Lesson> lessons;
    private final UnitSession session;
    private final LearningView view;

    /**
     * Connects the available lessons and unit session to the view.
     *
     * @param lessons the lessons in teaching order
     * @param session the current run through the unit's questions
     * @param view the interface used to display the lesson and question
     */
    public LearningController(
            List<Lesson> lessons,
            UnitSession session,
            LearningView view
    ) {
        this.lessons = List.copyOf(lessons);
        this.session = Objects.requireNonNull(session);
        this.view = Objects.requireNonNull(view);
    }

    /**
     * Connects view events, displays the first lesson and opens the window.
     *
     * <p>Call once on Swing's event dispatch thread.
     */
    public void start() {
        view.setLessons(lessons);
        view.onLessonSelected(this::selectLesson);
        view.onAnswerSubmitted(this::submitAnswer);
        view.onNext(this::advance);
        view.onNewSession(this::resetSession);
        showCurrentLesson();
        view.open();
    }

    private void selectLesson(Lesson lesson) {
        session.selectLesson(lesson.id());
        view.showLesson(lesson);
        showCurrentQuestion();
    }

    private void submitAnswer(String optionId) {
        Question question = session.currentQuestion();
        if (question != null) {
            session.challengeFor(question.id()).submit(optionId);
            refreshQuestionStatus();
        }
    }

    private void advance() {
        if (!session.canAdvance()) {
            return;
        }

        String previousLessonId = session.currentQuestion().lessonId();
        session.advance();
        if (previousLessonId.equals(session.currentQuestion().lessonId())) {
            showCurrentQuestion();
        } else {
            showCurrentLesson();
        }
    }

    private void resetSession() {
        session.reset();
        showCurrentLesson();
    }

    private void showCurrentLesson() {
        if (!lessons.isEmpty()) {
            Question question = session.currentQuestion();
            Lesson lesson = question == null
                    ? lessons.getFirst()
                    : lessons.stream()
                            .filter(candidate -> candidate.id().equals(question.lessonId()))
                            .findFirst()
                            .orElseThrow();

            view.selectLesson(lesson);
            view.showLesson(lesson);
        }
        showCurrentQuestion();
    }

    private void showCurrentQuestion() {
        view.showQuestion(session.currentQuestion());
        refreshQuestionStatus();
    }

    private void refreshQuestionStatus() {
        Question question = session.currentQuestion();
        if (question != null) {
            Challenge challenge = session.challengeFor(question.id());
            String lockedOptionId = challenge.isCompleted() ? question.correctOptionId() : null;
            view.showChallengeFeedback(challenge.feedback(), lockedOptionId);
        }

        view.setNextEnabled(session.canAdvance());
        view.showProgress(session.completedCount(), session.totalQuestions(), session.isComplete());
    }
}
