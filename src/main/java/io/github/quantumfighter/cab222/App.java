package io.github.quantumfighter.cab222;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    static void main() {
        SwingUtilities.invokeLater(App::createWindow);
    }

    private static void createWindow() {
        JFrame window = new JFrame("CAB222 Network Academy");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1100, 750);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}