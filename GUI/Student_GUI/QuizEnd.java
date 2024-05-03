import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizEnd extends JFrame {
    private JLabel congratulationLabel;
    private JLabel resultLabel;
    private JTextField marksField;
    private JButton endButton;

    public QuizEnd(int marks) {
        setTitle("Quiz End");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Congratulation Label
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        congratulationLabel = new JLabel("Well Done! You have Finished the Quiz.");
        congratulationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        topPanel.add(congratulationLabel);
        panel.add(topPanel, BorderLayout.NORTH);

        // Result Panel
        JPanel middlePanel = new JPanel(new GridLayout(2, 1));
        resultLabel = new JLabel("You have Got: ");
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        middlePanel.add(resultLabel);
        marksField = new JTextField(Integer.toString(marks) + " marks");
        marksField.setEditable(false);
        marksField.setHorizontalAlignment(JTextField.CENTER);
        marksField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        middlePanel.add(marksField);
        panel.add(middlePanel, BorderLayout.CENTER);

        // End Button Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endButton = new JButton("End");
        endButton.setBackground(Color.BLUE);
        endButton.setForeground(Color.WHITE);
        endButton.setPreferredSize(new Dimension(120, 40));
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });
        bottomPanel.add(endButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage
            QuizEnd quizEnd = new QuizEnd(80); // Pass marks obtained as argument
            quizEnd.setVisible(true);
        });
    }
}
