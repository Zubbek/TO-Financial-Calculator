public class SocialDuesCalculator {
    private IInsuranceCommand retirementInsuranceCommand;
    private IInsuranceCommand pensionInsuranceCommand;
    private IInsuranceCommand sicknessInsuranceCommand;

    public SocialDuesCalculator(IInsuranceCommand retirementInsuranceCommand,
                          IInsuranceCommand pensionInsuranceCommand,
                          IInsuranceCommand sicknessInsuranceCommand ){
        this.retirementInsuranceCommand = retirementInsuranceCommand;
        this.pensionInsuranceCommand = pensionInsuranceCommand;
        this.sicknessInsuranceCommand = sicknessInsuranceCommand;
    }

    public double calculateAllDues(double amount) {
        // Oblicz podatki z każdej z klas
        double retirementTax = retirementInsuranceCommand.execute(amount);
        double pensionTax = pensionInsuranceCommand.execute(amount);
        double sicknessTax = sicknessInsuranceCommand.execute(amount);

        // Sumuj wszystkie podatki społeczne
        return retirementTax + pensionTax + sicknessTax;
    }
}
