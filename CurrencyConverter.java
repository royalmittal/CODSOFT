import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> sourceCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    private static final Map<String, String> currencySymbols = new HashMap<>();

    static {
        currencySymbols.put("USD", "United States Dollar $");
        currencySymbols.put("EUR", "Euro €");
        currencySymbols.put("GBP", "British Pound Sterling £");
        currencySymbols.put("INR", "Indian Rupee ₹");
        currencySymbols.put("JPY", "Japanese Yen ¥");
        currencySymbols.put("AUD", "Australian Dollar $");
        currencySymbols.put("CAD", "Canadian Dollar $");
    }

    private static final double USD_TO_EUR_RATE = 0.85;
    private static final double USD_TO_GBP_RATE = 0.73;
    private static final double EUR_TO_USD_RATE = 1.18;
    private static final double EUR_TO_GBP_RATE = 0.86;
    private static final double GBP_TO_USD_RATE = 1.37;
    private static final double GBP_TO_EUR_RATE = 1.16;
    private static final double USD_TO_INR_RATE = 73.66;
    private static final double USD_TO_JPY_RATE = 109.97;
    private static final double USD_TO_AUD_RATE = 1.36;
    private static final double USD_TO_CAD_RATE = 1.26;
    private static final double INR_TO_USD_RATE = 0.014;
    private static final double INR_TO_JPY_RATE = 1.49;
    private static final double INR_TO_AUD_RATE = 0.019;
    private static final double INR_TO_CAD_RATE = 0.017;
    private static final double JPY_TO_USD_RATE = 0.0091;
    private static final double JPY_TO_EUR_RATE = 0.0082;
    private static final double JPY_TO_AUD_RATE = 0.012;
    private static final double JPY_TO_CAD_RATE = 0.011;
    private static final double AUD_TO_USD_RATE = 0.74;
    private static final double AUD_TO_EUR_RATE = 0.64;
    private static final double AUD_TO_JPY_RATE = 82.83;
    private static final double AUD_TO_CAD_RATE = 0.92;
    private static final double CAD_TO_USD_RATE = 0.79;
    private static final double CAD_TO_EUR_RATE = 0.68;
    private static final double CAD_TO_JPY_RATE = 89.83;
    private static final double CAD_TO_AUD_RATE = 1.09;

    private static final double[][] exchangeRates = {
            // USD EUR GBP INR JPY AUD CAD
            { 1.0, 0.85, 0.73, 73.66, 109.97, 1.36, 1.26 }, // USD
            { 1.18, 1.0, 0.86, 88.24, 131.95, 1.64, 1.51 }, // EUR
            { 1.37, 1.16, 1.0, 102.28, 152.69, 1.89, 1.74 }, // GBP
            { 0.014, 0.012, 0.0091, 1.0, 1.49, 0.019, 0.017 }, // INR
            { 0.0091, 0.0082, 0.0075, 0.67, 1.0, 0.013, 0.012 }, // JPY
            { 0.74, 0.64, 0.55, 56.37, 82.83, 1.0, 0.92 }, // AUD
            { 0.79, 0.68, 0.59, 60.93, 89.83, 1.09, 1.0 } // CAD
    };

    private int getCurrencyIndex(String currency) {
        String[] currencies = { "USD", "EUR", "GBP", "INR", "JPY", "AUD", "CAD" };
        for (int i = 0; i < currencies.length; i++) {
            if (currencies[i].equals(currency)) {
                return i;
            }
        }
        return -1;
    }

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        sourceCurrencyComboBox = new JComboBox<>(currencySymbols.values().toArray(new String[0]));
        targetCurrencyComboBox = new JComboBox<>(currencySymbols.values().toArray(new String[0]));

        amountTextField = new JTextField();
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        panel.add(new JLabel("Source Currency:"));
        panel.add(sourceCurrencyComboBox);
        panel.add(new JLabel("Target Currency:"));
        panel.add(targetCurrencyComboBox);
        panel.add(new JLabel("Amount:"));
        panel.add(amountTextField);
        panel.add(new JLabel());
        panel.add(convertButton);
        panel.add(new JLabel("Converted Amount:"));
        panel.add(resultLabel);
        add(panel, BorderLayout.CENTER);
    }

    private void convertCurrency() {
        try {
            String sourceCurrency = sourceCurrencyComboBox.getSelectedItem().toString();
            String targetCurrency = targetCurrencyComboBox.getSelectedItem().toString();
            double amount = Double.parseDouble(amountTextField.getText());
            System.out.println("Source Currency: " + sourceCurrency);
            System.out.println("Target Currency: " + targetCurrency);
            double convertedAmount = convert(sourceCurrency, targetCurrency, amount);
            DecimalFormat df = new DecimalFormat("#.##");
            resultLabel.setText(currencySymbols.get(targetCurrency) + df.format(convertedAmount));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double convert(String sourceCurrency, String targetCurrency, double amount) {
        if (sourceCurrency.equals(targetCurrency)) {
            return amount;
        }

        int sourceIndex = getCurrencyIndex(sourceCurrency);
        int targetIndex = getCurrencyIndex(targetCurrency);

        if (sourceIndex != -1 && targetIndex != -1) {
            return amount * exchangeRates[sourceIndex][targetIndex];
        } else {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
