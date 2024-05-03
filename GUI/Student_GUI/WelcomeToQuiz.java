import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeToQuiz extends JFrame {

    private JTextField nameField;
    private JTextField regNumberField;

    public WelcomeToQuiz() {
        setTitle("Welcome to the Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to the Quiz");
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension panelSize = panel.getPreferredSize();
        titleLabel.setPreferredSize(new Dimension(panelSize.width, 40));
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(20));

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setMaximumSize(new Dimension(300, 30));
        panel.add(nameField);

        panel.add(Box.createVerticalStrut(10));

        JLabel regNumberLabel = new JLabel("Enter Your Registration Number:");
        regNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(regNumberLabel);

        regNumberField = new JTextField();
        regNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        regNumberField.setMaximumSize(new Dimension(300, 30));
        panel.add(regNumberField);

        panel.add(Box.createVerticalStrut(60));

        JButton startQuizButton = new JButton("Start Quiz");
        startQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startQuizButton.setForeground(Color.WHITE);
        startQuizButton.setBackground(Color.GREEN);
        startQuizButton.setBorderPainted(false);
        startQuizButton.setPreferredSize(new Dimension(200, 60));
        Font buttonFont = startQuizButton.getFont();
        startQuizButton.setFont(buttonFont.deriveFont(Font.BOLD, 16));

        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openQuestionPage();
            }
        });

        panel.add(startQuizButton);

        add(panel);

        setLocationRelativeTo(null);
    }

    private void openQuestionPage() {
        // Get the name and registration number entered by the user
        String name = nameField.getText();
        String regNumber = regNumberField.getText();

        // Open the QuestionPage
        QuestionPage questionPage = new QuestionPage();
        questionPage.setVisible(true);

        // Close the current WelcomeToQuiz window
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeToQuiz welcomeToQuiz = new WelcomeToQuiz();
            welcomeToQuiz.setVisible(true);
        });
    }
}
