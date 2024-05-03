import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionWindow extends JFrame {
    private JButton nextButton;
    private JTextField questionField;
    private JTextField[] answerFields;
    private JCheckBox[] answerCheckBoxes;
    private int questionNumber;
    private int totalQuestions;
    private QuizOverview quizOverview;

    public QuestionWindow(int questionNumber, int totalQuestions, QuizOverview quizOverview) {
        this.quizOverview = quizOverview;
        this.questionNumber = questionNumber;
        this.totalQuestions = totalQuestions;
        setTitle("Question " + questionNumber + " / " + totalQuestions);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Question input field
        JPanel questionPanel = new JPanel();
        questionField = new JTextField(30);
        questionPanel.add(new JLabel("Question " + questionNumber + ": "));
        questionPanel.add(questionField);
        panel.add(questionPanel);

        // Answer input fields and checkboxes
        answerFields = new JTextField[4];
        answerCheckBoxes = new JCheckBox[4];
        for (int i = 0; i < 4; i++) {
            JPanel answerPanel = new JPanel();
            answerFields[i] = new JTextField(30);
            answerCheckBoxes[i] = new JCheckBox();
            answerPanel.add(new JLabel("Answer " + (char) ('A' + i) + ": "));
            answerPanel.add(answerFields[i]);
            answerPanel.add(answerCheckBoxes[i]);
            panel.add(answerPanel);
        }

        // Next button
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.GREEN); // Set background color
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose();
                // Show the next question window
                quizOverview.showNextQuestionWindow(questionNumber + 1);
            }
        });
        panel.add(nextButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to get the question text
    public String getQuestion() {
        return questionField.getText();
    }

    // Method to get the answer texts
    public String[] getAnswers() {
        String[] answers = new String[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = answerFields[i].getText();
        }
        return answers;
    }

    // Method to get the correct answer index
    public int getCorrectAnswerIndex() {
        for (int i = 0; i < 4; i++) {
            if (answerCheckBoxes[i].isSelected()) {
                return i;
            }
        }
        return -1; // No correct answer selected
    }
}
