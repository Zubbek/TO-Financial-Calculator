public class PensionInsuranceCommand implements IInsuranceCommand{

    @Override
    public double execute(double amount) {
        return amount * 0.015;
    }
}
