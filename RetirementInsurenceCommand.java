public class RetirementInsurenceCommand implements IInsuranceCommand{

    @Override
    public double execute(double amount) {
        return amount * 0.0976;
    }
}
