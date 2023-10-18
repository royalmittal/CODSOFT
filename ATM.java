import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel messageLabel;

    public ATM() {
        setTitle("ATM Interface");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel accountLabel = new JLabel("Account Number:");
        accountField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();
        loginButton = new JButton("Login");
        messageLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        panel.add(accountLabel);
        panel.add(accountField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(loginButton);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(messageLabel);

        add(panel, BorderLayout.CENTER);
    }

    private void login() {
        String accountNumber = accountField.getText();
        String pin = new String(pinField.getPassword());

        if (isValidLogin(accountNumber, pin)) {
            // Remove login components
            getContentPane().removeAll();

            // Add main menu components
            JLabel menuLabel = new JLabel("Welcome to ATM");
            JButton checkBalanceButton = new JButton("Check Balance");
            JButton withdrawButton = new JButton("Withdraw");
            JButton depositButton = new JButton("Deposit");

            checkBalanceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("Checking balance...");
                    double accountBalance = getAccountBalance();
                    messageLabel.setText("Your account balance is: $" + accountBalance);
                }
            });

            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double withdrawalAmount = 100.0;
                    messageLabel.setText("Withdrawing $" + withdrawalAmount + "...");
                    simulateWithdrawal(withdrawalAmount);
                }
            });

            depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double depositAmount = 200.0;
                    messageLabel.setText("Depositing $" + depositAmount + "...");
                    simulateDeposit(depositAmount);
                }
            });

            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new GridLayout(4, 1, 10, 10));
            menuPanel.add(menuLabel);
            menuPanel.add(checkBalanceButton);
            menuPanel.add(withdrawButton);
            menuPanel.add(depositButton);

            getContentPane().add(menuPanel, BorderLayout.CENTER);
            validate();
        } else {
            messageLabel.setText("Invalid account number or PIN. Please try again.");
            pinField.setText("");
        }
    }

    private double getAccountBalance() {
        return 1000.0;
    }

    private void simulateWithdrawal(double amount) {
        JOptionPane.showMessageDialog(null, "Withdrawal of $" + amount + " complete.");
    }

    private void simulateDeposit(double amount) {
        JOptionPane.showMessageDialog(null, "Deposit of $" + amount + " complete.");
    }

    private boolean isValidLogin(String accountNumber, String pin) {
        return accountNumber.equals("123456") && pin.equals("1234");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATM().setVisible(true);
            }
        });
    }
}
