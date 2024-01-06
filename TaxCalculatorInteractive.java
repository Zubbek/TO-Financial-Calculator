//public class TaxCalculatorInteractive {
//
//    private Scanner scanner;
//
//    public TaxCalculatorInteractive() {
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void run() {
//        System.out.println("Wprowadź kwotę dochodu: ");
//        double income = scanner.nextDouble();
//        scanner.nextLine(); // Czyść bufor
//
//        System.out.println("Wybierz typ umowy: 1 - Praca, 2 - Zlecenie, 3 - VAT");
//        int contractType = scanner.nextInt();
//        scanner.nextLine(); // Czyść bufor
//
//        boolean hasDiscount = false;
//        if (contractType == 1 || contractType == 2) {
//            System.out.println("Czy przysługują Ci zniżki? (tak/nie)");
//            String discountResponse = scanner.nextLine().trim();
//            hasDiscount = discountResponse.equalsIgnoreCase("tak");
//        }
//
//        double finalAmount = calculateFinalAmount(income, contractType, hasDiscount);
//        System.out.printf("Końcowa kwota Netto: %.2f\n", finalAmount);
//    }
//
//    private double calculateFinalAmount(double income, int contractType, boolean hasDiscount) {
//        ITaxStrategy taxStrategy;
//
//        IInsuranceCommand retirementTax = new RetirementInsurenceCommand();
//        IInsuranceCommand pensionTax = new PensionInsuranceCommand();
//        IInsuranceCommand sicknessTax = new SicknessInsuranceCommand();
//        IInsuranceCommand healthTax = new HealthInsuranceCommand(retirementTax, pensionTax, sicknessTax);
//
//        SocialDuesCalculator socialDuesCalculator = new SocialDuesCalculator(retirementTax, pensionTax, sicknessTax);
//
//
//
//        switch (contractType) {
//            case 1: // Umowa o pracę
//                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, 250, healthTax, socialDuesCalculator); // Uzupełnij zależności
//                break;
//            case 2: // Umowa zlecenie
//                taxStrategy = new ContractOfMandateTaxStrategy(hasDiscount, 250, healthTax, socialDuesCalculator); // Uzupełnij zależności
//                break;
//            case 3: // VAT
//                taxStrategy = new VatCalculateTaxStrategy(0.23, true); // Przykładowe wartości
//                break;
//            default:
//                throw new IllegalArgumentException("Nieznany typ umowy.");
//        }
//
//        return taxStrategy.calculate(income);
//    }
//
//    public static void main(String[] args) {
//        TaxCalculatorInteractive calculatorInteractive = new TaxCalculatorInteractive();
//        calculatorInteractive.run();
//    }
//}



//----------------------------------------Dobra Wersja------------------------------------------------------------------
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TaxCalculatorInteractive extends JFrame {
//
//    private JTextField incomeField;
//    private JComboBox<String> contractTypeComboBox;
//    private JCheckBox discountCheckBox;
//    private JButton calculateButton;
//    private JLabel resultLabel;
//
//    public TaxCalculatorInteractive() {
//        initializeComponents();
//        setLayout();
//        setListeners();
//        configureFrame();
//    }
//
//    private void initializeComponents() {
//        incomeField = new JTextField(10);
//        contractTypeComboBox = new JComboBox<>(new String[]{"Wybierz", "Praca", "Zlecenie", "VAT"});
//        discountCheckBox = new JCheckBox("Zniżki");
//        calculateButton = new JButton("Oblicz");
//        resultLabel = new JLabel("Wynik:");
//    }
//
//    private void setLayout() {
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Kwota dochodu:"));
//        panel.add(incomeField);
//        panel.add(new JLabel("Typ umowy:"));
//        panel.add(contractTypeComboBox);
//        panel.add(discountCheckBox);
//        panel.add(calculateButton);
//        panel.add(resultLabel);
//
//        add(panel);
//    }
//
//    private void setListeners() {
//        calculateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                calculate();
//            }
//        });
//    }
//
//    private void configureFrame() {
//        setTitle("Kalkulator Podatkowy");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void calculate() {
//        try {
//            double income = Double.parseDouble(incomeField.getText());
//            int contractType = contractTypeComboBox.getSelectedIndex();
//            boolean hasDiscount = !discountCheckBox.isSelected();
//
//            double finalAmount = calculateFinalAmount(income, contractType, hasDiscount);
//            resultLabel.setText("Wynik: " + String.format("%.2f", finalAmount));
//        } catch (NumberFormatException ex) {
//            resultLabel.setText("Błędna kwota dochodu.");
//        }
//    }
//
//    private double calculateFinalAmount(double income, int contractType, boolean hasDiscount) {
//        ITaxStrategy taxStrategy;
//
//        double deductibleCoast = 250;
//
//        IInsuranceCommand retirementTax = new RetirementInsurenceCommand();
//        IInsuranceCommand pensionTax = new PensionInsuranceCommand();
//        IInsuranceCommand sicknessTax = new SicknessInsuranceCommand();
//        IInsuranceCommand healthTax = new HealthInsuranceCommand(retirementTax, pensionTax, sicknessTax);
//
//        SocialDuesCalculator socialDuesCalculator = new SocialDuesCalculator(retirementTax, pensionTax, sicknessTax);
//
//        switch (contractType) {
//            case 1: // Umowa o pracę
//                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator); // Uzupełnij zależności
//                break;
//            case 2: // Umowa zlecenie
//                taxStrategy = new ContractOfMandateTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator); // Uzupełnij zależności
//                break;
//            case 3: // VAT
//                taxStrategy = new VatCalculateTaxStrategy(0.23, true); // Przykładowe wartości
//                break;
//            default:
//                throw new IllegalArgumentException("Nieznany typ umowy.");
//        }
//
//        return taxStrategy.calculate(income);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(TaxCalculatorInteractive::new);
//    }
//}


//--------------------------------------------------------------------------------------------------------------------------


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalculatorInteractive extends JFrame {

    private JTextField incomeField;
    private JComboBox<String> contractTypeComboBox;
    private JCheckBox discountCheckBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JTextArea detailsTextArea;

    private IInsuranceCommand retirementTax = new RetirementInsurenceCommand();
    private IInsuranceCommand pensionTax = new PensionInsuranceCommand();
    private IInsuranceCommand sicknessTax = new SicknessInsuranceCommand();
    private IInsuranceCommand healthTax = new HealthInsuranceCommand(retirementTax, pensionTax, sicknessTax);

    private double deductibleCoast = 250;
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
        calculateButton = new JButton("Oblicz");
        resultLabel = new JLabel("Wynik:");
        detailsTextArea = new JTextArea(10, 30);
        detailsTextArea.setEditable(false);
    }

    private void setLayout() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Kwota dochodu:"));
        panel.add(incomeField);
        panel.add(new JLabel("Typ umowy:"));
        panel.add(contractTypeComboBox);
        panel.add(discountCheckBox);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(new JScrollPane(detailsTextArea));

        add(panel);
    }

    private void setListeners() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
    }

    private void configureFrame() {
        setTitle("Kalkulator Podatkowy");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private double calculateFinalAmount(double income, int contractType, boolean hasDiscount) {
        SocialDuesCalculator socialDuesCalculator = new SocialDuesCalculator(retirementTax, pensionTax, sicknessTax);

        switch (contractType) {
            case 1: // Umowa o pracę
                taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator);
                break;
            case 2: // Umowa zlecenie
                taxStrategy = new ContractOfMandateTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator);
                break;
            case 3: // VAT
                taxStrategy = new VatCalculateTaxStrategy(0.23, true);
                break;
            default:
                throw new IllegalArgumentException("Nieznany typ umowy.");
        }

        return taxStrategy.calculate(income);
    }

    private void calculate() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            int contractType = contractTypeComboBox.getSelectedIndex();
            boolean hasDiscount = discountCheckBox.isSelected();

            double finalAmount = calculateFinalAmount(income, contractType, hasDiscount);
            resultLabel.setText("Wynik: " + String.format("%.2f", finalAmount));

            displayDetails(contractType, hasDiscount, finalAmount);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Błędna kwota dochodu.");
        }
    }

    private void displayDetails(int contractType, boolean hasDiscount, double finalAmount) {
        detailsTextArea.setText("");

        switch (contractType) {
            case 1:
                displayEmploymentContractDetails(hasDiscount, finalAmount);
                break;
            case 2:
                displayContractOfMandateDetails(hasDiscount, finalAmount);
                break;
            case 3:
                displayVatDetails(finalAmount);
                break;
            default:
                throw new IllegalArgumentException("Nieznany typ umowy.");
        }
    }

    private void displayEmploymentContractDetails(boolean hasDiscount, double finalAmount) {
        ITaxStrategy taxStrategy;
        double deductibleCoast = 250;

        SocialDuesCalculator socialDuesCalculator = new SocialDuesCalculator(retirementTax, pensionTax, sicknessTax);

        detailsTextArea.append("Składki ubezpieczeniowe:\n");
        detailsTextArea.append("Retirement Tax: " + retirementTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Pension Tax: " + pensionTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Sickness Tax: " + sicknessTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Health Tax: " + healthTax.execute(finalAmount) + "\n");

        if (hasDiscount) {
            taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator);
        } else {
            taxStrategy = new EmploymentContractTaxStrategy(hasDiscount, deductibleCoast, healthTax, socialDuesCalculator);
            detailsTextArea.append("Podatek: 0 (Jesteś zwolniony z opłacenia podatku dochodowego :)\n");
        }

        displayFinalAmountAndTax(finalAmount, taxStrategy);
    }

    private void displayContractOfMandateDetails(boolean hasDiscount, double finalAmount) {
        if (hasDiscount) {
            detailsTextArea.append("Podatek: 0 (Jesteś zwolniony z płacenia wszystkich składek :)\n");
        } else {
            displayInsuranceDetails(new RetirementInsurenceCommand(), new PensionInsuranceCommand(), new SicknessInsuranceCommand(), new HealthInsuranceCommand(retirementTax, pensionTax, sicknessTax), finalAmount);
        }

        displayFinalAmountAndTax(finalAmount, new ContractOfMandateTaxStrategy(hasDiscount, 0, null, null));
    }

    private void displayVatDetails(double finalAmount) {
        detailsTextArea.append("Podatek: 0 (Obliczany jest tylko Vat!)\n");
        displayFinalAmount(finalAmount);
    }

    private void displayInsuranceDetails(IInsuranceCommand retirementTax, IInsuranceCommand pensionTax, IInsuranceCommand sicknessTax, IInsuranceCommand healthTax, double finalAmount) {
        detailsTextArea.append("Składki ubezpieczeniowe:\n");
        detailsTextArea.append("Retirement Tax: " + retirementTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Pension Tax: " + pensionTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Sickness Tax: " + sicknessTax.execute(finalAmount) + "\n");
        detailsTextArea.append("Health Tax: " + healthTax.execute(finalAmount) + "\n");
    }

    private void displayFinalAmountAndTax(double finalAmount, ITaxStrategy taxStrategy) {
        detailsTextArea.append("Końcowa kwota: " + String.format("%.2f", finalAmount) + "\n");
        detailsTextArea.append("Podatek dochodowy: " + String.format("%.2f", taxStrategy.calculate(finalAmount)) + "\n");
    }

    private void displayFinalAmount(double finalAmount) {
        detailsTextArea.append("Końcowa kwota: " + String.format("%.2f", finalAmount) + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaxCalculatorInteractive::new);
    }
}
