package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_CREATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withDateOfCreation("2024-01-12")
            .withHistory(LocalDate.of(2024, 1, 12), "Created")
            .withHistory(LocalDate.of(2024, 10, 10), "Attended seminar")
            .withHistory(LocalDate.of(2024, 10, 11), "Met with client")
            .withRemark("").withBirthday("2001-01-01")
            .withTags("friends").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withRemark("")
            .withBirthday("2001-02-02")
            .withDateOfCreation("2022-09-19")
            .withHistory(LocalDate.of(2022, 9, 19), "Created")
            .withHistory(LocalDate.of(2024, 9, 25), "Completed project A")
            .withHistory(LocalDate.of(2024, 10, 1), "Attended team meeting")
            .build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withRemark("")
            .withBirthday("2001-03-03")
            .withDateOfCreation("2021-09-10")
            .withHistory(LocalDate.of(2021, 9, 10), "Created")
            .build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withRemark("")
            .withBirthday("2001-04-04")
            .withDateOfCreation("2012-02-19")
            .withHistory(LocalDate.of(2012, 2, 19), "Created")
            .build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withRemark("")
            .withBirthday("2001-05-05")
            .withDateOfCreation("2024-02-20")
            .withHistory(LocalDate.of(2024, 2, 20), "Created")
            .build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withRemark("")
            .withBirthday("2001-06-06")
            .withDateOfCreation("2018-01-29")
            .withHistory(LocalDate.of(2018, 1, 29), "Created")
            .build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withRemark("")
            .withBirthday("2001-07-07")
            .withDateOfCreation("2019-12-11")
            .withHistory(LocalDate.of(2019, 12, 11), "Created")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withRemark("")
            .withBirthday("2001-08-08")
            .withDateOfCreation("2021-03-31")
            .withHistory(LocalDate.of(2021, 3, 31), "Created")
            .build();

    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withRemark("")
            .withBirthday("2001-09-09")
            .withDateOfCreation("2012-02-19")
            .withHistory(LocalDate.of(2012, 2, 19), "Created")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withRemark(VALID_REMARK_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY)
            .withDateOfCreation(VALID_DATE_OF_CREATION)
            .withHistory(LocalDate.now(), VALID_LOG_MESSAGE)
            .build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withRemark(VALID_REMARK_BOB).withBirthday(VALID_BIRTHDAY_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
