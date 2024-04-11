package Command;

import Interface.IInsuranceCommand;

import java.math.BigDecimal;

public class PensionTaxCommand implements IInsuranceCommand {

    private double deductibleCoast;

    private double firstEtapTax = 0.12;
    private double secondEtapTax = 0.32;

    private double borderlineAmount = 120000;

    private double mandateAmount = 200;
    private IInsuranceCommand retirementInsuranceCommand;
    private IInsuranceCommand pensionInsuranceCommand;
    private IInsuranceCommand sicknessInsuranceCommand;


    public PensionTaxCommand(double deductibleCoast, IInsuranceCommand retirementInsuranceCommand,
                             IInsuranceCommand pensionInsuranceCommand,
                             IInsuranceCommand sicknessInsuranceCommand) {
        this.deductibleCoast = deductibleCoast;
        this.retirementInsuranceCommand = retirementInsuranceCommand;
        this.pensionInsuranceCommand = pensionInsuranceCommand;
        this.sicknessInsuranceCommand = sicknessInsuranceCommand;
    }

    public void setDeductibleCoast(double deductibleCoast) {
        this.deductibleCoast = deductibleCoast;
    }

    @Override
    public double execute(double amount) {

        if (amount <= mandateAmount && amount > 0) {
            return amount * firstEtapTax;
        }
        double paymentTax = (amount - retirementInsuranceCommand.execute(amount) - pensionInsuranceCommand.execute(amount) - sicknessInsuranceCommand.execute(amount) - deductibleCoast);
        double rounded = Math.round(paymentTax);

        if (amount <= borderlineAmount) {
            return Math.round(rounded * firstEtapTax);
        }
        return Math.round(rounded * secondEtapTax);
    }
}
