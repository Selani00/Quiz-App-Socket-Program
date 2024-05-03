import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class QuestionPage extends JFrame {
    private JLabel timerLabel;
    private JLabel questionCountLabel;
    private JLabel questionLabel;
    private JCheckBox optionA;
    private JCheckBox optionB;
    private JCheckBox optionC;
    private JCheckBox optionD;
    private JButton nextButton;

    private int minutesLeft;
    private int currentQuestionNumber;

    public QuestionPage() {
        setTitle("Question Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel topicLabel = new JLabel("Question Page");
        topicLabel.setForeground(Color.BLUE);
        Font font = topicLabel.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.SIZE, 24); // Set font size to 24
        Font newFont = new Font(attributes);
        topicLabel.setFont(newFont);
        titlePanel.add(topicLabel);
        panel.add(titlePanel, BorderLayout.NORTH);

        // Timer and Question Count Panel
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timerLabel = new JLabel("Time Remaining: ");
        timerLabel.setFont(newFont);
        questionCountLabel = new JLabel("Question: ");
        questionCountLabel.setFont(newFont);
        infoPanel.add(timerLabel);
        infoPanel.add(questionCountLabel);
        panel.add(infoPanel, BorderLayout.CENTER);

        // Center Panel for Question and Options
        JPanel centerPanel = new JPanel(new GridLayout(5, 1));


        questionLabel = new JLabel("What is the size of float and double in Java?");
        attributes.put(TextAttribute.SIZE, 16); // Set font size to 16

        questionLabel.setFont(newFont);
        centerPanel.add(questionLabel);

        optionA = new JCheckBox("32");
        optionB = new JCheckBox("24");
        optionC = new JCheckBox("64");
        optionD = new JCheckBox("128");

        optionA.setFont(newFont);
        optionB.setFont(newFont);
        optionC.setFont(newFont);
        optionD.setFont(newFont);

        centerPanel.add(optionA);
        centerPanel.add(optionB);
        centerPanel.add(optionC);
        centerPanel.add(optionD);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel for Next Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.WHITE);
        nextButton.setPreferredSize(new Dimension(120, 40)); // Set button size
        bottomPanel.add(nextButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuestionPage questionPage = new QuestionPage();
            questionPage.setVisible(true);
        });
    }
}
