import java.util.List;
import java.util.Map;


public class Sector{
    private String name;
    private Map<Integer, Double> yearEmissionsMap;

    public Sector(String name, Map<Integer, Double> yearEmissionsMap) {
        this.name = name;
        this.yearEmissionsMap = yearEmissionsMap;
    }
    public String getName(){
        return name;
    }


    public Map<Integer, Double> getEmissions() {
        return yearEmissionsMap;
    }

    public int getYearWithHighestEmissions(){
        Map<Integer,Double> sectorMap = this.getEmissions();
        double highestValue = 0;
        int highestYear = 0;

        for (Map.Entry<Integer, Double> currentEmissionEntry : sectorMap.entrySet()) {
            if (currentEmissionEntry.getValue() > highestValue){
                highestValue = currentEmissionEntry.getValue();
                highestYear = currentEmissionEntry.getKey();
            }
        }
        return highestYear;
    }


    public static Sector sectorWithBiggestChangeInEmissions(List<Sector> sectors, int
            startYear, int endYear) {
        double highestDifference = 0;
        Sector sectorHighestChange = null;

        for (Sector currentSector : sectors){
            double AverageEmissionsStartYear = (currentSector.getEmissions().get(startYear)) / 3;
            double AverageEmissionsEndYear = (currentSector.getEmissions().get(endYear)) / 3;
            double difference = AverageEmissionsEndYear - AverageEmissionsStartYear;

            if (difference > highestDifference){
                highestDifference = difference;
                sectorHighestChange = currentSector;
            }
        }
        return sectorHighestChange;
    }

}
