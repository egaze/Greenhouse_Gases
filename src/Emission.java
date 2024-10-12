public class Emission {
    private double co2 = 288000.0;
    private double n2o = 684000.0;
    private double ch4 = 4690000.0;

    public Emission(double co2, double n20, double ch4) {
        this.co2 = co2;
        this.n2o = n20;
        this.ch4 = ch4;
    }

    public double getCO2() {
        return co2;
    }

    public double getN2O() {
        return n2o;
    }

    public double getCH4() {
        return ch4;
    }
}
