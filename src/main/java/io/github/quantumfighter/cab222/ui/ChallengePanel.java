package io.github.quantumfighter.cab222.ui;

import io.github.quantumfighter.cab222.model.AnswerOption;
import io.github.quantumfighter.cab222.model.Question;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

final class ChallengePanel extends JPanel {

    private final JTextArea prompt = new JTextArea(6, 0);
    private final JPanel options = new JPanel(new GridLayout(0, 1, 0, 4));

    ChallengePanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Challenge"));

        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setWrapStyleWord(true);

        JPanel questionBody = new JPanel(new BorderLayout(0, 8));
        questionBody.add(new JScrollPane(prompt), BorderLayout.NORTH);
        questionBody.add(options, BorderLayout.CENTER);
        add(questionBody, BorderLayout.NORTH);
    }

    void showQuestion(Question question) {
        options.removeAll();

        if (question == null) {
            prompt.setText("No challenge available for this lesson.");
        } else {
            prompt.setText(question.prompt());
            ButtonGroup answers = new ButtonGroup();

            for (AnswerOption option : question.options()) {
                JRadioButton button = new JRadioButton(
                        option.id() + ". " + option.text()
                );
                button.setActionCommand(option.id());
                answers.add(button);
                options.add(button);
            }
        }

        prompt.setCaretPosition(0);
        options.revalidate();
        options.repaint();
    }
}