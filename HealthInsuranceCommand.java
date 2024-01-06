public class HealthInsuranceCommand implements IInsuranceCommand {


    private IInsuranceCommand retirementInsuranceCommand;
    private IInsuranceCommand pensionInsuranceCommand;
    private IInsuranceCommand sicknessInsuranceCommand;


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
        double healthInsuranceRate = 0.09; // Przykładowa stawka składki zdrowotnej
        return netAmount * healthInsuranceRate;
    }
}