import java.util.List;
import java.util.Map;

public class Country {
    private String name;
    private Map<Integer, Emission> yearEmissions;

    public Country(String name, Map<Integer, Emission> yearEmissions){
        this.name = name;
        this.yearEmissions = yearEmissions;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Emission> getEmissions() {
        return yearEmissions;
    }

    public int getYearWithHighestEmissions(){
        Map<Integer, Emission> countryEmissions = this.getEmissions();
        double highestTotalEmission = 0;
        int highestTotalEmissionCountry = 0;

        for (Map.Entry<Integer, Emission> currentYearEmission : countryEmissions.entrySet()){
            double currentTotalEmissions = currentYearEmission.getValue().getN2O() + currentYearEmission.getValue().getCH4() + currentYearEmission.getValue().getCO2();
            if (currentTotalEmissions > highestTotalEmission) {
                highestTotalEmission = currentTotalEmissions;
                highestTotalEmissionCountry = currentYearEmission.getKey();
            }
        }
        return highestTotalEmissionCountry;
    }

    public static Country countryWithHighestCH4InYear(List<Country> countries, int year) {
        double highestCH4Emission = 0;
        Country highestEmissionCountry = null;

        for (Country currentCountry : countries) {
            Emission currentYearEmission = currentCountry.getEmissions().get(year);
            double currentYearEmissionCH4 = currentYearEmission.getCH4();

            if (currentYearEmissionCH4 > highestCH4Emission) {
                highestCH4Emission = currentYearEmissionCH4;
                highestEmissionCountry = currentCountry;
            }
        }
        return highestEmissionCountry;
    }

    public static Country highestChangeInEmissions(List<Country> countries, int
            startYear, int endYear) {
        double highestDifference = 0;
        Country countryHighestChange = null;

        for (Country currentCountry : countries){
            double emissionsStartYear = currentCountry.getEmissions().get(startYear).getCH4() + currentCountry.getEmissions().get(startYear).getCO2() + currentCountry.getEmissions().get(startYear).getN2O();
            double emissionsEndYear = currentCountry.getEmissions().get(endYear).getCH4() + currentCountry.getEmissions().get(endYear).getCO2() + currentCountry.getEmissions().get(endYear).getN2O();
            double difference = emissionsEndYear - emissionsStartYear;

            if (difference > highestDifference){
                highestDifference = difference;
                countryHighestChange = currentCountry;
            }
        }

        return countryHighestChange;

        // System.out.println("Country with the highest change in Emissions: countryHighestChangeInEmissions");
    }

}
