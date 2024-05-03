
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Welcome Screen");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a JPanel for the top section (topic with blue color)
        JPanel topPanel = new JPanel();
        
        JLabel topicLabel = new JLabel("Welcome to Quiz Application");
        topicLabel.setForeground(Color.BLUE); 
        Font font = topicLabel.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.SIZE, 24); // Set font size to 24
        Font newFont = new Font(attributes);
        topicLabel.setFont(newFont);      
        topPanel.add(topicLabel);

        // Create a JPanel for the middle section (image)

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout()); // Use BorderLayout for positioning the image at the center
        ImageIcon originalImageIcon = new ImageIcon("./asserts/Background.jpg"); // Replace "your_image_path.jpg" with the actual path to your image
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Specify desired dimensions (width, height)
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        middlePanel.add(imageLabel, BorderLayout.CENTER);

        // Create a JPanel for the bottom section (button with blue color background)
        JPanel bottomPanel = new JPanel();
        
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setBackground(Color.BLUE);
        getStartedButton.setBorderPainted(false);
        getStartedButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size (width, height)
        // Set font size of button text
        Font buttonFont = getStartedButton.getFont();
        getStartedButton.setFont(buttonFont.deriveFont(Font.BOLD, 16)); 
        bottomPanel.add(getStartedButton);


        // Add action listener to the button
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                frame.dispose();
                
                // Open the input window
                QuizOverview inputWindow = new QuizOverview();
                inputWindow.setVisible(true);
            }
        });

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }
}
