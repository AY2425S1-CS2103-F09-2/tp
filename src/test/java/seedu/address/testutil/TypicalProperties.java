package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;


/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    // PropertyForRent instances
    public static final PropertyForRent PROPERTY_FOR_RENT_AMY = new PropertyForRent(
            "123, Downtown Ave", // Address
            "Downtown", // Town
            "Condo", // Property Type
            100.5, // Size
            2, // Number of Bedrooms
            1, // Number of Bathrooms
            3000.0, // Price
            LocalDate.of(2024, 11, 15) // Available From
    );

    public static final PropertyForRent PROPERTY_FOR_RENT_BOB = new PropertyForRent(
            "456, Uptown St", // Address
            "Uptown", // Town
            "Apartment", // Property Type
            200.0, // Size
            3, // Number of Bedrooms
            2, // Number of Bathrooms
            4500.0, // Price
            LocalDate.of(2024, 11, 15) // Available From
    );

    // PropertyForSale instances
    public static final PropertyForSale PROPERTY_FOR_SALE_AMY = new PropertyForSale(
            "123, Downtown Ave", // Address
            "Downtown", // Town
            "HDB", // Property Type
            100.5, // Size
            2, // Number of Bedrooms
            1, // Number of Bathrooms
            350000.0, // Sale Price
            LocalDate.of(2024, 11, 15) // Listing Date
    );

    public static final PropertyForSale PROPERTY_FOR_SALE_BOB = new PropertyForSale(
            "456, Uptown St", // Address
            "Uptown", // Town
            "Penthouse", // Property Type
            200.0, // Size
            3, // Number of Bedrooms
            2, // Number of Bathrooms
            1200000.0, // Sale Price
            LocalDate.of(2024, 11, 15) // Listing Date
    );

    public static final String KEYWORD_MATCHING_DOWNTOWN = "Downtown"; // Matches properties in Downtown

    private TypicalProperties() {} // Prevents instantiation

    /**
     * Returns an {@code List<Property>} with all the typical properties.
     */
    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(
                PROPERTY_FOR_RENT_AMY,
                PROPERTY_FOR_RENT_BOB,
                PROPERTY_FOR_SALE_AMY,
                PROPERTY_FOR_SALE_BOB
        ));
    }
}
