public class SicknessInsuranceCommand  implements IInsuranceCommand{

    private TaxContextStrategy taxContext;


    public SicknessInsuranceCommand(TaxContextStrategy taxContext) {
        this.taxContext = taxContext;
    }

    @Override
    public double execute(double amount) {
        return amount * 0.0245;
    }
}
