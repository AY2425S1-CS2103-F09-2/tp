package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_RENT_BOB;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_SALE_BOB;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PropertyForRentBuilder;
import seedu.address.testutil.PropertyForSaleBuilder;


public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getPropertyForSaleList());
        assertEquals(Collections.emptyList(), addressBook.getPropertyForRentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        // Assuming empty properties for simplicity, you can also add properties if needed
        AddressBookStub newData = new AddressBookStub(newPersons,
                Collections.emptyList(), Collections.emptyList());

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName()
                + "{persons="
                + addressBook.getPersonList()
                + ", propertiesForSale=" + addressBook.getPropertyForSaleList()
                + ", propertiesForRent=" + addressBook.getPropertyForRentList()
                + "}";

        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and properties lists can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<PropertyForSale> propertiesForSale =
                FXCollections.observableArrayList();
        private final ObservableList<PropertyForRent> propertiesForRent =
                FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<PropertyForSale> propertiesForSale,
                        Collection<PropertyForRent> propertiesForRent) {
            this.persons.setAll(persons);
            this.propertiesForSale.setAll(propertiesForSale);
            this.propertiesForRent.setAll(propertiesForRent);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<PropertyForSale> getPropertyForSaleList() {
            return propertiesForSale;
        }

        @Override
        public ObservableList<PropertyForRent> getPropertyForRentList() {
            return propertiesForRent;
        }
    }

    // PROPERTY RELATED TESTS
    @Test
    public void hasPropertyForSale_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPropertyForSale(null));
    }

    @Test
    public void hasPropertyForSale_propertyNotInAddressBook_returnsFalse() {
        PropertyForSale propertyForSale =
                new PropertyForSaleBuilder(PROPERTY_FOR_SALE_BOB).build(); // create a property for sale
        assertFalse(addressBook.hasPropertyForSale(propertyForSale));
    }

    @Test
    public void hasPropertyForSale_propertyInAddressBook_returnsTrue() {
        PropertyForSale propertyForSale =
                new PropertyForSaleBuilder(PROPERTY_FOR_SALE_BOB).build(); // create a property for sale
        addressBook.addPropertyForSale(propertyForSale);
        assertTrue(addressBook.hasPropertyForSale(propertyForSale));
    }

    @Test
    public void addPropertyForSale_duplicateProperty_throwsDuplicatePropertyException() {
        PropertyForSale propertyForSale = new PropertyForSaleBuilder(PROPERTY_FOR_SALE_BOB).build();
        addressBook.addPropertyForSale(propertyForSale);
        assertThrows(DuplicatePropertyException.class, () -> addressBook.addPropertyForSale(propertyForSale));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesPropertyData() {
        AddressBook newData = getTypicalAddressBook(); // This needs to include typical properties
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    // Similar tests for PropertyForRent
    @Test
    public void hasPropertyForRent_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPropertyForRent(null));
    }

    @Test
    public void hasPropertyForRent_propertyNotInAddressBook_returnsFalse() {
        PropertyForRent propertyForRent = new PropertyForRentBuilder(PROPERTY_FOR_RENT_BOB).build();
        assertFalse(addressBook.hasPropertyForRent(propertyForRent));
    }

    @Test
    public void hasPropertyForRent_propertyInAddressBook_returnsTrue() {
        PropertyForRent propertyForRent = new PropertyForRentBuilder(PROPERTY_FOR_RENT_BOB).build();
        addressBook.addPropertyForRent(propertyForRent);
        assertTrue(addressBook.hasPropertyForRent(propertyForRent));
    }

    @Test
    public void addPropertyForRent_duplicateProperty_throwsDuplicatePropertyException() {
        PropertyForRent propertyForRent = new PropertyForRentBuilder(PROPERTY_FOR_RENT_BOB).build();
        addressBook.addPropertyForRent(propertyForRent);
        assertThrows(DuplicatePropertyException.class, () -> addressBook.addPropertyForRent(propertyForRent));
    }
}
