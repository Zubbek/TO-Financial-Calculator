public class SicknessInsuranceCommand  implements IInsuranceCommand{

    @Override
    public double execute(double amount) {
        return amount * 0.0245;
    }
}
