import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * NOTE THAT THIS CLASS WILL NOT COMPILE UNTIL YOU HAVE COMPLETED PART 2 OF THIS LAB
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Country> countries = getCountries();
        List<Sector> sectors = getSectors();
        // Which country had the highest methane gas emissions in 2000?
        System.out.println("Country with the highest methane gas emissions in 2000:");
        System.out.print(Country.countryWithHighestCH4InYear(countries, 2000).getName() + " : ");
        System.out.print(Country.countryWithHighestCH4InYear(countries, 2000).getEmissions().get(2000).getCH4() + " kilotons" + "\n\n");

        /*Country With Highest Change in Emission between 1970 and 2000 */
        System.out.println("Country with the highest change in emissions 1970 and 2000:");
        System.out.print(Country.highestChangeInEmissions(countries, 1970, 2000).getName() + " : ");
        double chinaCH4Emission1970 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(1970).getCH4();
        double chinaCO2Emission1970 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(1970).getCO2();
        double chinaN2OEmission1970 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(1970).getN2O();
        double totalChinaEmission1970 = chinaCO2Emission1970 + chinaCH4Emission1970 + chinaN2OEmission1970;

        double chinaCH4Emission2000 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(2000).getCH4();
        double chinaN2OEmission2000 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(2000).getN2O();
        double chinaCO2Emission2000 = Country.highestChangeInEmissions(countries, 1970, 2000).getEmissions().get(2000).getCO2();
        double totalChinaEmission2000 = chinaCO2Emission2000 + chinaCH4Emission2000 + chinaN2OEmission2000;
        System.out.print(totalChinaEmission2000 - totalChinaEmission1970 + " kilotons" + "\n");

        /* Which sector had the highest change in greenhouse gas emissions between 1970 and 2000?
        */
        double powerIndustryEmission1970 = Sector.highestChangeInEmissions(sectors, 1970, 2000).getEmissions().get(1970);
        double powerIndustryEmission2000 = Sector.highestChangeInEmissions(sectors, 1970, 2000).getEmissions().get(2000);

        System.out.print("\nSector with the highest change in greenhouse gas emission between 1970 and 2000: ");
        double powerIndustryChange = powerIndustryEmission2000 - powerIndustryEmission1970;
        System.out.print("\n" + Sector.highestChangeInEmissions(sectors, 1970, 2000).getName() + " : ");
        System.out.println(powerIndustryChange + " kilotons" + "\n");

        // Which country had the highest methane gas emissions in 2000?
        System.out.println("Country with the highest methane gas emissions in 2012:");
        System.out.print(Country.countryWithHighestCH4InYear(countries, 2012).getName() + " : ");
        System.out.print(Country.countryWithHighestCH4InYear(countries, 2012).getEmissions().get(2012).getCH4() + " kilotons" + "\n\n");

        /*Country With Highest Change in Emission between 1970 and 2000 */
        System.out.println("Country with the highest change in emissions 2000 and 2012:");
        System.out.print(Country.highestChangeInEmissions(countries, 2000, 2012).getName() + " : ");
        double countryCH4Emission2000 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2000).getCH4();
        double countryCO2Emission2000 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2000).getCO2();
        double countryN2OEmission2000 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2000).getN2O();
        double totalCountryEmission2000 = countryCO2Emission2000 + countryCH4Emission2000 + countryN2OEmission2000;

        double countryCH4Emission2012 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2012).getCH4();
        double countryN2OEmission2012 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2012).getN2O();
        double countryCO2Emission2012 = Country.highestChangeInEmissions(countries, 2000, 2012).getEmissions().get(2012).getCO2();
        double totalCountryEmission2012 = countryCO2Emission2012 + countryCH4Emission2012 + countryN2OEmission2012;
        System.out.print(totalCountryEmission2012 - totalCountryEmission2000 + " kilotons" + "\n");


        /* Sector with the highest change in greenhouse gas emission between 2005 and 2012 */
        double sectorEmission2005 = Sector.highestChangeInEmissions(sectors, 2005, 2012).getEmissions().get(2005);
        double sectorEmission2012 = Sector.highestChangeInEmissions(sectors, 2005, 2012).getEmissions().get(2012);

        System.out.print("\nSector with the highest change in greenhouse gas emission between 2005 and 2012: ");
        double sectorChange = sectorEmission2012 - sectorEmission2005;

        System.out.print("\n" + Sector.highestChangeInEmissions(sectors, 2005, 2012).getName() + " : ");
        System.out.println(sectorChange + " kilotons" + "\n");


    }

	/**
     * Reads country emissions data from the countries.csv file. Do not modify this
     * method. Note that this method won't compile until you have implemented the
     * Country class.
     *
     * @return A List of Country objects.
     * @throws FileNotFoundException If the countries.csv file does not exist
     */
    private static List<Country> getCountries() throws FileNotFoundException {
        File dataFile = new File("countries.csv");
        Map<String, Map<Integer, Emission>> emissions = new HashMap<>();

        Scanner scan = new Scanner(dataFile);
        scan.nextLine(); // Skip the header line
        while (scan.hasNextLine()) {
            String[] data = scan.nextLine().split(",");

            // Each line contains Country, Year, CO2, N20, CH4 --- in that order
            String name = data[0];
            int year = Integer.parseInt(data[1]);
            double co2emissions = Double.parseDouble(data[2]);
            double n2oemissions = Double.parseDouble(data[3]);
            double ch4emissions = Double.parseDouble(data[4]);

            Emission emission = new Emission(co2emissions, n2oemissions, ch4emissions);

            if (!emissions.containsKey(name)) {
                emissions.put(name, new HashMap<>());
            }
            emissions.get(name).put(year, emission);
        }
        scan.close();

        // Process emissions into a List of Countries
        List<Country> result = new LinkedList<>();
        for (Map.Entry<String, Map<Integer, Emission>> entry : emissions.entrySet()) {
            Country country = new Country(entry.getKey(), entry.getValue());
            result.add(country);
        }

        return result;
    }

    /**
     * Reads sector emissions data from the sectors.csv file. Do not modify this
     * method. Note that this method won't compile until you have implemented the
     * Country class.
     *
     * @return A List of Sector objects
     * @throws FileNotFoundException If the sectors.csv file does not exist
     */
    private static List<Sector> getSectors() throws FileNotFoundException {
        File dataFile = new File("sectors.csv");
        Map<String, Map<Integer, Double>> tempMap = new HashMap<>();
        Scanner scan = new Scanner(dataFile);
        scan.nextLine(); // Skip the header line
        while (scan.hasNextLine()) {
            String[] data = scan.nextLine().split(",");

            // Each line contains Sector, Year, Emissions --- in that order
            String name = data[0].split("\\.")[2]; // Sector names are "Emissions.Sector.X" — we only want "X"
            int year = Integer.parseInt(data[1]);
            double greenhouseGasEmissions = Double.parseDouble(data[2]);

            if (!tempMap.containsKey(name)) {
                tempMap.put(name, new HashMap<>());
            }
            tempMap.get(name).put(year, greenhouseGasEmissions);
        }
        scan.close();

        // Process tempMap into a List of Countries
        List<Sector> result = new LinkedList<>();
        for (Map.Entry<String, Map<Integer, Double>> entry : tempMap.entrySet()) {
            Sector sector = new Sector(entry.getKey(), entry.getValue());
            result.add(sector);
        }

        return result;
    }


}
