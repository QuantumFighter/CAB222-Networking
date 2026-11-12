package io.github.quantumfighter.cab222;

import io.github.quantumfighter.cab222.content.LessonLoader;
import io.github.quantumfighter.cab222.content.WeekOneContent;
import io.github.quantumfighter.cab222.controller.LearningController;
import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.model.UnitSession;
import io.github.quantumfighter.cab222.ui.LearningView;

import javax.swing.SwingUtilities;
import java.util.List;

/**
 * Loads application content and connects the Swing interface.
 */
public class App {

    /**
     * Starts the application.
     */
    static void main() {
        LessonLoader loader = new LessonLoader();
        WeekOneContent content = new WeekOneContent(loader);
        List<Lesson> lessons = content.lessons();
        UnitSession session = new UnitSession(content.questions());

        SwingUtilities.invokeLater(() -> {
            LearningView view = new LearningView();

            LearningController controller =
                    new LearningController(lessons, session, view);
            controller.start();
        });
    }
}