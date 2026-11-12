package io.github.quantumfighter.cab222.ui;

import io.github.quantumfighter.cab222.model.AnswerOption;
import io.github.quantumfighter.cab222.model.Question;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.function.Consumer;

final class ChallengePanel extends JPanel {

    private final JTextArea prompt = new JTextArea(6, 0);
    private final JPanel options = new JPanel(new GridLayout(0, 1, 0, 4));
    private final JButton submitButton = new JButton("Submit");
    private final JButton nextButton = new JButton("Next");
    private final JTextArea feedback = new JTextArea();

    private ButtonGroup answers = new ButtonGroup();

    ChallengePanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Challenge"));

        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setWrapStyleWord(true);

        submitButton.setEnabled(false);
        nextButton.setEnabled(false);
        feedback.setEditable(false);
        feedback.setLineWrap(true);
        feedback.setWrapStyleWord(true);

        JPanel questionBody = new JPanel(new BorderLayout(0, 8));
        questionBody.add(new JScrollPane(prompt), BorderLayout.NORTH);
        questionBody.add(options, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 0));
        actions.add(submitButton);
        actions.add(nextButton);
        questionBody.add(actions, BorderLayout.SOUTH);

        add(questionBody, BorderLayout.NORTH);
        add(new JScrollPane(feedback), BorderLayout.CENTER);
    }

    void showQuestion(Question question) {
        options.removeAll();
        answers = new ButtonGroup();

        if (question == null) {
            prompt.setText("No challenge available for this lesson.");
        } else {
            prompt.setText(question.prompt());

            for (AnswerOption option : question.options()) {
                String label = option.id() + ". " + option.text();
                String htmlLabel = label.replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;");
                JRadioButton button = new JRadioButton(
                        "<html><div style=\"width: 160px\">" + htmlLabel + "</div></html>"
                );
                button.getAccessibleContext().setAccessibleName(label);
                button.setActionCommand(option.id());
                answers.add(button);
                options.add(button);
            }
        }

        prompt.setCaretPosition(0);
        showFeedback("", null);
        options.revalidate();
        options.repaint();
    }

    void onAnswerSubmitted(Consumer<String> handler) {
        submitButton.addActionListener(_ -> {
            var selected = answers.getSelection();
            String optionId = selected == null
                    ? null
                    : selected.getActionCommand();

            handler.accept(optionId);
        });
    }

    void onNext(Runnable handler) {
        nextButton.addActionListener(_ -> handler.run());
    }

    void setNextEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    void showFeedback(String message, String lockedOptionId) {
        feedback.setText(message);
        feedback.setCaretPosition(0);

        boolean locked = lockedOptionId != null;

        for (AbstractButton button : Collections.list(answers.getElements())) {
            if (locked && lockedOptionId.equals(button.getActionCommand())) {
                button.setSelected(true);
            }
            button.setEnabled(!locked);
        }

        submitButton.setEnabled(answers.getButtonCount() > 0 && !locked);
    }
}