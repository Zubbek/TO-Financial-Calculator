package Command;

import Interface.IInsuranceCommand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HealthInsuranceCommand implements IInsuranceCommand {


    private final IInsuranceCommand retirementInsuranceCommand;
    private final IInsuranceCommand pensionInsuranceCommand;
    private final IInsuranceCommand sicknessInsuranceCommand;

    double taxRate = 0.09;

    public HealthInsuranceCommand(
                                  IInsuranceCommand retirementInsuranceCommand,
                                  IInsuranceCommand pensionInsuranceCommand,
                                  IInsuranceCommand sicknessInsuranceCommand) {
        this.retirementInsuranceCommand = retirementInsuranceCommand;
        this.pensionInsuranceCommand = pensionInsuranceCommand;
        this.sicknessInsuranceCommand = sicknessInsuranceCommand;
    }

    @Override
    public double execute(double amount) {
        // Oblicz podatki z wyżej wymienionych klas
        double retirementTax = retirementInsuranceCommand.execute(amount);
        double pensionTax = pensionInsuranceCommand.execute(amount);
        double sicknessTax = sicknessInsuranceCommand.execute(amount);

//         Oblicz składkę zdrowotną od ceny brutto pomniejszonej o podatki
        double netAmount = amount - retirementTax - pensionTax - sicknessTax;
        double finalTax = netAmount * taxRate;
        BigDecimal bigDecimal = new BigDecimal(finalTax);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}