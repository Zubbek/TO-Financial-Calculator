public class DuesCalculator {
        private IInsuranceCommand healthInsuranceCommand;

        public DuesCalculator(IInsuranceCommand healthInsuranceCommand) {
            this.healthInsuranceCommand = healthInsuranceCommand;
        }

        public double calculateAllTaxes(double amount, SocialDuesCalculator socialDuesCalculator) {
            // Oblicz podatki z każdej z klas
            double healthInsuranceTax = healthInsuranceCommand.execute(amount);

            // Sumuj wszystkie podatki
            return socialDuesCalculator.calculateAllDues(amount)+ healthInsuranceTax;
        }
}
