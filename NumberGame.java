import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JLabel instructionsLabel;
    private JLabel feedbackLabel;
    private JButton submitButton;

    public NumberGame() {
        setTitle("Random Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        randomNumber = new Random().nextInt(100) + 1; // Generate a random number between 1 and 100

        instructionsLabel = new JLabel("Guess the number between 1 and 100:");
        guessField = new JTextField(10);
        feedbackLabel = new JLabel("");
        submitButton = new JButton("Submit Guess");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(instructionsLabel);
        panel.add(guessField);
        panel.add(submitButton);
        panel.add(feedbackLabel);

        add(panel);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == randomNumber) {
                feedbackLabel.setText("Congratulations! You guessed the correct number.");
            } else if (userGuess < randomNumber) {
                feedbackLabel.setText("Try again. Your guess is too low.");
            } else {
                feedbackLabel.setText("Try again. Your guess is too high.");
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }

        guessField.setText(""); // Clear the input field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGame().setVisible(true);
            }
        });
    }
}
