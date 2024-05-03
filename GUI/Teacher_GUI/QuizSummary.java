import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizSummary extends JFrame {
    public QuizSummary(String[] questions, String[][] answers, int[] correctAnswers) {
        setTitle("Quiz Summary");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Display questions and answers
        for (int i = 0; i < questions.length; i++) {
            JLabel questionLabel = new JLabel("Question " + (i + 1) + ": " + questions[i]);
            panel.add(questionLabel);
            for (int j = 0; j < answers[i].length; j++) {
                JLabel answerLabel = new JLabel("Answer " + (char) ('A' + j) + ": " + answers[i][j]);
                panel.add(answerLabel);
            }
            // Highlight correct answer
            JLabel correctAnswerLabel = new JLabel("Correct Answer: " + (char) ('A' + correctAnswers[i]));
            correctAnswerLabel.setForeground(Color.RED); // Highlight correct answer in green
            panel.add(correctAnswerLabel);
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

         // Add "Start Quiz" button
         JButton startQuizButton = new JButton("Start Quiz");
         startQuizButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Close the current window and terminate
                 dispose();
                 System.exit(0);
             }
         });
         panel.add(startQuizButton);

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
