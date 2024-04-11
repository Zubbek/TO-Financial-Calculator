package Command;

import Interface.IInsuranceCommand;

public class PensionTaxCommand implements IInsuranceCommand {

    private double deductibleCoast;

    private final IInsuranceCommand retirementInsuranceCommand;
    private final IInsuranceCommand pensionInsuranceCommand;
    private final IInsuranceCommand sicknessInsuranceCommand;


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

        double firstEtapTax = 0.12;
        double mandateAmount = 200;
        if (amount <= mandateAmount && amount > 0) {
            return amount * firstEtapTax;
        }
        double paymentTax = (amount - retirementInsuranceCommand.execute(amount) - pensionInsuranceCommand.execute(amount) - sicknessInsuranceCommand.execute(amount) - deductibleCoast);
        double rounded = Math.round(paymentTax);

        double borderlineAmount = 120000;
        if (amount <= borderlineAmount) {
            return Math.round(rounded * firstEtapTax);
        }
        double secondEtapTax = 0.32;
        return Math.round(rounded * secondEtapTax);
    }
}
