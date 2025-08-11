package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;
    private JTextArea textArea;
    private JButton button;

    public MainView() {
        frame = new JFrame("Huerto Loco");
        textArea = new JTextArea(20, 40);
        button = new JButton("Submit");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public String getInput() {
        return textArea.getText();
    }

    public void setOutput(String output) {
        textArea.setText(output);
    }

    public void addSubmitListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}