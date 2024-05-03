import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizOverview extends JFrame {

    private int numQuestions;
    private List<QuestionWindow> questionWindows;
    private int currentQuestionIndex;

    public QuizOverview() {
        setTitle("Quiz Overview");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use BoxLayout to align components vertically

        // Add Title
        JLabel titleLabel = new JLabel("Quiz Overview");
        titleLabel.setForeground(Color.GREEN); // Set text color to green
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Align to the center horizontally
        Dimension panelSize = panel.getPreferredSize(); // Get the size of the panel
        titleLabel.setPreferredSize(new Dimension(panelSize.width, 40)); // Set preferred size for titleLabel to match the width of the panel
        panel.add(titleLabel);

        // Add space between titleLabel and input fields
        panel.add(Box.createVerticalStrut(60));

        // Create input field for number of questions
        JLabel questionLabel = new JLabel("How many questions are you going to create?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(questionLabel);
        JTextField questionField = new JTextField();
        questionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionField.setMaximumSize(new Dimension(500, 50));
        panel.add(questionField);

        // Add space between components
        panel.add(Box.createVerticalStrut(10));

        // Create input field for quiz duration
        JLabel durationLabel = new JLabel("How long will the whole quiz run?");
        durationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(durationLabel);
        JTextField durationField = new JTextField();
        durationField.setAlignmentX(Component.CENTER_ALIGNMENT);
        durationField.setMaximumSize(new Dimension(500, 50));
        panel.add(durationField);

        // Add space between components and button
        panel.add(Box.createVerticalStrut(60));

        // Create button for creating questions
        JButton createQuestionsButton = new JButton("Create Questions");
        createQuestionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createQuestionsButton.setForeground(Color.WHITE);
        createQuestionsButton.setBackground(Color.GREEN);
        createQuestionsButton.setBorderPainted(false);
        createQuestionsButton.setPreferredSize(new Dimension(200, 60));

        Font buttonFont = createQuestionsButton.getFont();
        createQuestionsButton.setFont(buttonFont.deriveFont(Font.BOLD, 16));

        createQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the number of questions
                numQuestions = Integer.parseInt(questionField.getText());
                // Initialize the list to store question windows
                questionWindows = new ArrayList<>();

                // Close the current window
                dispose();

                // Show the first question window
                showNextQuestionWindow(1);
            }
        });

        panel.add(createQuestionsButton);

        // Add panel to the frame
        add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    // Method to show the next question window
    public void showNextQuestionWindow(int questionNumber) {
        // Close the previous question window if it exists
        if (currentQuestionIndex > 0 && currentQuestionIndex <= numQuestions) {
            questionWindows.get(currentQuestionIndex - 1).dispose();
        }
        // Show the next question window if there are more questions
        if (questionNumber <= numQuestions) {
            currentQuestionIndex = questionNumber;
            QuestionWindow questionWindow = new QuestionWindow(questionNumber,numQuestions, this);
            questionWindows.add(questionWindow);
        } else {
             // All questions answered, display Quiz Summary window
        String[] questions = new String[numQuestions];
        String[][] answers = new String[numQuestions][4];
        int[] correctAnswers = new int[numQuestions];
        for (int i = 0; i < numQuestions; i++) {
            questions[i] = questionWindows.get(i).getQuestion();
            answers[i] = questionWindows.get(i).getAnswers();
            correctAnswers[i] = questionWindows.get(i).getCorrectAnswerIndex();
        }
        new QuizSummary(questions, answers, correctAnswers);
        dispose(); // Close the current window

        }
    }

    public static void main(String[] args) {
        // Create and display the QuizOverview window
        SwingUtilities.invokeLater(() -> {
            QuizOverview quizOverview = new QuizOverview();
            quizOverview.setVisible(true);
        });
    }
}
