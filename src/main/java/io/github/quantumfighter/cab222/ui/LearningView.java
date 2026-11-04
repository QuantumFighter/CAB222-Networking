package io.github.quantumfighter.cab222.ui;

import io.github.quantumfighter.cab222.model.Lesson;
import io.github.quantumfighter.cab222.model.Question;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Displays lesson choices, a lesson reader and a challenge panel.
 *
 * <p>Create and use this class on Swing's event dispatch thread.
 */
public class LearningView {

    private final JFrame window =
            new JFrame("CAB222 Network Academy");

    private final DefaultListModel<Lesson> lessonModel =
            new DefaultListModel<>();

    private final JList<Lesson> lessonList =
            new JList<>(lessonModel);

    private final JTextArea reader = new JTextArea();
    private final ChallengePanel challengePanel = new ChallengePanel();

    private Consumer<Lesson> lessonSelectionHandler = _ -> {
    };

    /**
     * Creates the window and its components without showing it.
     */
    public LearningView() {
        configureLessonList();
        configureReader();

        JScrollPane lessonChoices = new JScrollPane(lessonList);
        lessonChoices.setBorder(
                BorderFactory.createTitledBorder("Week 1 lessons")
        );
        lessonChoices.setPreferredSize(new Dimension(250, 0));

        JScrollPane lessonReader = new JScrollPane(reader);
        lessonReader.setBorder(
                BorderFactory.createTitledBorder("Lesson")
        );
        lessonReader.setMinimumSize(new Dimension(250, 0));

        challengePanel.setMinimumSize(new Dimension(160, 0));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                lessonReader,
                challengePanel
        );
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.7);

        JPanel content = new JPanel(new BorderLayout(12, 0));
        content.setBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );
        content.add(lessonChoices, BorderLayout.WEST);
        content.add(splitPane, BorderLayout.CENTER);

        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1100, 750);
    }

    /**
     * Replaces the available lessons without selecting one.
     *
     * @param lessons the lessons in their display order
     */
    public void setLessons(List<Lesson> lessons) {
        lessonModel.clear();

        for (Lesson lesson : lessons) {
            lessonModel.addElement(lesson);
        }
    }

    /**
     * Registers the action invoked for a completed, non-empty selection.
     *
     * @param handler the action receiving the selected lesson
     */
    public void onLessonSelected(Consumer<Lesson> handler) {
        lessonSelectionHandler = Objects.requireNonNull(handler);
    }

    /**
     * Registers the action invoked when the learner clicks Submit.
     *
     * @param handler the action receiving the selected option ID,
     *                or {@code null} when no option is selected
     */
    public void onAnswerSubmitted(Consumer<String> handler) {
        challengePanel.onAnswerSubmitted(handler);
    }

    /**
     * Selects the first lesson, notifying the selection handler.
     *
     * <p>Does nothing when there are no lessons.
     */
    public void selectFirstLesson() {
        if (!lessonModel.isEmpty()) {
            lessonList.setSelectedIndex(0);
        }
    }

    /**
     * Displays a lesson and returns the reader to its beginning.
     *
     * @param lesson the lesson to display
     */
    public void showLesson(Lesson lesson) {
        reader.setText(lesson.text());
        reader.setCaretPosition(0);
    }

    /**
     * Displays the question and its options in the challenge panel.
     *
     * @param question the question to display, or {@code null} to clear it
     */
    public void showQuestion(Question question) {
        challengePanel.showQuestion(question);
    }

    /**
     * Displays feedback and applies the completed answer's lock state.
     *
     * @param message the feedback to display
     * @param lockedOptionId the completed answer's ID, or {@code null}
     *                       while the question can still be answered
     */
    public void showChallengeFeedback(String message, String lockedOptionId) {
        challengePanel.showFeedback(message, lockedOptionId);
    }

    /**
     * Centres and shows the window.
     */
    public void open() {
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void configureLessonList() {
        lessonList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        DefaultListCellRenderer renderer =
                new DefaultListCellRenderer();

        lessonList.setCellRenderer(
                (list, lesson, index, selected, focused) ->
                        renderer.getListCellRendererComponent(
                                list,
                                lesson.title(),
                                index,
                                selected,
                                focused
                        )
        );

        lessonList.addListSelectionListener(event -> {
            if (event.getValueIsAdjusting()) {
                return;
            }

            Lesson selectedLesson = lessonList.getSelectedValue();
            if (selectedLesson != null) {
                lessonSelectionHandler.accept(selectedLesson);
            }
        });
    }

    private void configureReader() {
        reader.setEditable(false);
        reader.setLineWrap(true);
        reader.setWrapStyleWord(true);
        reader.setFont(
                new Font(Font.SANS_SERIF, Font.PLAIN, 16)
        );
        reader.setMargin(new Insets(12, 12, 12, 12));
    }
}