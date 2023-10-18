import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField assignmentField;
    private JTextField midtermField;
    private JTextField finalExamField;
    private JLabel resultLabel;
    public Calculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel assignmentLabel = new JLabel("Assignment Grade:");
        assignmentField = new JTextField();
        JLabel midtermLabel = new JLabel("Midterm Grade:");
        midtermField = new JTextField();
        JLabel finalExamLabel = new JLabel("Final Exam Grade:");
        finalExamField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });

        panel.add(assignmentLabel);
        panel.add(assignmentField);
        panel.add(midtermLabel);
        panel.add(midtermField);
        panel.add(finalExamLabel);
        panel.add(finalExamField);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel, BorderLayout.CENTER);
    }

    private void calculateGrade() {
        try {
            double assignmentGrade = Double.parseDouble(assignmentField.getText());
            double midtermGrade = Double.parseDouble(midtermField.getText());
            double finalExamGrade = Double.parseDouble(finalExamField.getText());

            double finalGrade = (assignmentGrade * 0.2) + (midtermGrade * 0.3) + (finalExamGrade * 0.5);

            String letterGrade;
            if (finalGrade >= 90) {
                letterGrade = "A (Excellent)";
            } else if (finalGrade >= 75) {
                letterGrade = "B (Good)";
            } else if (finalGrade >= 65) {
                letterGrade = "C (Average)";
            } else if (finalGrade >= 50) {
                letterGrade = "D (Below Average)";
            } else {
                letterGrade = "F (Fail)";
            }

            String finalGradeStr = String.format("%.2f", finalGrade);
            resultLabel.setText("Final Grade: " + finalGradeStr + " (" + letterGrade + ")");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }
}
