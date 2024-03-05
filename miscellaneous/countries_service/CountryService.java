package countries_service;

import java.util.Arrays;
import java.util.List;

public class CountryService {

    public List<Country> getAllCountries() {
        return Arrays.asList(
                new Country("United States"),
                new Country("United Kingdom"),
                new Country("Germany"),
                new Country("France"),
                new Country("Canada")
                // Add more countries as needed
        );
    }
}
