

import javax.swing.*;

public class TaxCalculatorInteractive extends JFrame {

    private JTextField incomeField;
    private JComboBox<String> contractTypeComboBox;
    private JCheckBox discountCheckBox;

    private JCheckBox duesCheckBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JTextArea detailsTextArea;
    private JComboBox<String> vatRateComboBox;

    private double deductibleCoast = 250;

    private double employmentAmount = 4242;

    private double mandateAmount = 200;

    private IInsuranceCommand retirementTax = new RetirementInsurenceCommand();
    private IInsuranceCommand pensionTax = new PensionInsuranceCommand();
    private IInsuranceCommand sicknessTax = new SicknessInsuranceCommand();
    private IInsuranceCommand healthTax = new HealthInsuranceCommand(retirementTax, pensionTax, sicknessTax);

    private PensionTaxCommand paymentTax = new PensionTaxCommand(deductibleCoast, retirementTax, pensionTax, sicknessTax);

    private ITaxStrategy taxStrategy;

    public TaxCalculatorInteractive() {
        initializeComponents();
        setLayout();
        setListeners();
        configureFrame();
    }

    private void initializeComponents() {
        incomeField = new JTextField(10);
        contractTypeComboBox = new JComboBox<>(new String[]{"Wybierz", "Praca", "Zlecenie", "VAT"});
        discountCheckBox = new JCheckBox("Zniżki");
        duesCheckBox = new JCheckBox("Składki");
        calculateButton = new JButton("Oblicz");
        resultLabel = new JLabel("Wynik:");
        detailsTextArea = new JTextArea(10, 30);
        detailsTextArea.setEditable(false);
        vatRateComboBox = new JComboBox<>(new String[]{"Wybierz", "23%", "8%", "5%"});
    }

    private void setLayout() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Wprowadź kwote:"));
        panel.add(incomeField);
        panel.add(new JLabel("Typ umowy:"));
        panel.add(contractTypeComboBox);
        panel.add(discountCheckBox);
        panel.add(duesCheckBox);
        panel.add(new JLabel("Stawka VAT:"));
        panel.add(vatRateComboBox);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(new JScrollPane(detailsTextArea));

        add(panel);
    }

    private void setListeners() {
        calculateButton.addActionListener(e -> calculate());
    }

    private void configureFrame() {
        setTitle("Kalkulator Podatkowy");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private double calculateFinalAmount(double income, int contractType, boolean hasDiscount, boolean hasDues, double vatRate) {
        switch (contractType) {
            case 1 -> { // Umowa o pracę
                deductibleCoast = 250;
                paymentTax.setDeductibleCoast(deductibleCoast);
                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, retirementTax, pensionTax, sicknessTax, healthTax, paymentTax);
            }
            case 2 -> { // Umowa zlecenie
                deductibleCoast = income * 0.20;
                paymentTax.setDeductibleCoast(deductibleCoast);
                taxStrategy = new ContractOfMandateTaxStrategy(hasDiscount, hasDues, deductibleCoast, retirementTax, pensionTax, sicknessTax, healthTax, paymentTax);
            }
            case 3 -> // VAT
                    taxStrategy = new VatCalculateTaxStrategy(vatRate / 100.0, true);
            default -> throw new IllegalArgumentException("Nieznany typ umowy.");
        }

        return taxStrategy.calculate(income);
    }

    private void calculate() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            int contractType = contractTypeComboBox.getSelectedIndex();
            boolean hasDiscount = discountCheckBox.isSelected();
            boolean hasDues = duesCheckBox.isSelected();
            double vatRate = 0.0;

            if (contractType == 3) { // Jeśli wybrano VAT, pobierz stawkę VAT
                String selectedVatRate = (String) vatRateComboBox.getSelectedItem();
                vatRate = switch (selectedVatRate) {
                    case "23%" -> 23.0;
                    case "8%" -> 8.0;
                    case "5%" -> 5.0;
                    default -> throw new IllegalArgumentException("Wybierz stawkę VAT.");
                };
            }

            double finalAmount = calculateFinalAmount(income, contractType, hasDiscount, hasDues, vatRate);
            resultLabel.setText("Wynik: " + String.format("%.2f", finalAmount));
            displayDetails(contractType, hasDiscount, hasDues, income, vatRate);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Błędna kwota dochodu.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void displayDetails(int contractType, boolean hasDiscount, boolean hasDues, double amount, double vatRate) {
        StringBuilder details = new StringBuilder();
        detailsTextArea.setText("");

        switch (contractType) {
            case 1 -> displayEmploymentContractDetails(hasDiscount, amount);
            case 2 -> displayContractOfMandateDetails(hasDiscount, hasDues, amount);
            case 3 -> {
                details.append("Stawka VAT: ").append(vatRate).append("%\n");
                displayVatDetails(amount);
                detailsTextArea.setText(details.toString());
            }
            default -> throw new IllegalArgumentException("Nieznany typ umowy.");
        }
    }

    private void displayEmploymentContractDetails(boolean hasDiscount, double amount) {

        if (amount < employmentAmount) {
            resultLabel.setText("Podana kwota jest mniejsza od kwoty minimalnej\n wprowadź poprawą kwotę:\n");
        }

        else {

            detailsTextArea.append("Składki ubezpieczeniowe:\n");
            detailsTextArea.append("Składka emerytowa: " + retirementTax.execute(amount) + "\n");
            detailsTextArea.append("Składka rentowa: " + pensionTax.execute(amount) + "\n");
            detailsTextArea.append("Składka chorobowa: " + sicknessTax.execute(amount) + "\n");
            detailsTextArea.append("Składka zdrowotna: " + healthTax.execute(amount) + "\n");

            if (!hasDiscount) {
                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, retirementTax, pensionTax, sicknessTax, healthTax, paymentTax);
                detailsTextArea.append("Zaliczka na podatek dochodowy: " + paymentTax.execute(amount) + "\n");
            } else {
                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, retirementTax, pensionTax, sicknessTax, healthTax, paymentTax);
                detailsTextArea.append("Podatek: 0 (Jesteś zwolniony z opłacenia zaliczki na podatek dochodowy :)\n");
            }
        }
    }

    private void displayContractOfMandateDetails(boolean hasDiscount, boolean hasDues, double amount) {
        if(amount <= 0) {
            resultLabel.setText("Błędna kwota dochodu");
        }

        if (amount <= mandateAmount && amount > 0) {
            detailsTextArea.append("Składki ubezpieczeniowe:\n");
            detailsTextArea.append("Zaliczka na podatek zryczałtowany: " + paymentTax.execute(amount) + "\n");
        }
        else if (hasDiscount) {
            detailsTextArea.append("Podatek: 0 (Jesteś zwolniony z płacenia wszystkich składek :)\n");
        } else if (!hasDues && !hasDiscount) {
            detailsTextArea.append("Składki ubezpieczeniowe:\n");
            detailsTextArea.append("Zaliczka na podatek dochodowy: " + paymentTax.execute(amount) + "\n");
        } else {
            detailsTextArea.append("Składki ubezpieczeniowe:\n");
            detailsTextArea.append("Składka emerytowa: " + retirementTax.execute(amount) + "\n");
            detailsTextArea.append("Składka rentowa: " + pensionTax.execute(amount) + "\n");
            detailsTextArea.append("Składka chorobowa: " + sicknessTax.execute(amount) + "\n");
            detailsTextArea.append("Składka zdrowotna: " + healthTax.execute(amount) + "\n");
            detailsTextArea.append("Zaliczka na podatek dochodowy: " + paymentTax.execute(amount) + "\n");
        }

    }

    private void displayVatDetails(double finalAmount) {
        detailsTextArea.append("Podatek: 0 (Obliczany jest tylko Vat!)\n");
        displayFinalAmount(finalAmount);
    }

    private void displayFinalAmount(double finalAmount) {
        detailsTextArea.append("Końcowa kwota: " + String.format("%.2f", finalAmount) + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaxCalculatorInteractive::new);
    }
}

