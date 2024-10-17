package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.INVALID_HISTORY_DATE;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_BIRTHDAY = "not a date";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATE_OF_CREATION = LocalDate.now().plusDays(100).toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_DATE_OF_CREATION = BENSON.getDateOfCreation().getDateOfCreation().toString();
    private static final List<JsonAdaptedHistoryEntry> VALID_HISTORY = BENSON.getHistory()
            .getHistoryEntries().entrySet().stream()
            .map(entry -> new JsonAdaptedHistoryEntry(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        System.out.println(BENSON.getHistory());
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS,
                                      VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_REMARK, INVALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = Birthday.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBirthday_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_REMARK, null, VALID_TAGS, VALID_DATE_OF_CREATION, VALID_HISTORY);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Birthday");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY,
                                      invalidTags, VALID_DATE_OF_CREATION, VALID_HISTORY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfCreation_throwsIllegalValueException() {

        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, null, VALID_HISTORY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfCreation_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, INVALID_DATE_OF_CREATION, VALID_HISTORY);
        String expectedMessage = INVALID_HISTORY_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    // Test for invalid history (entries with dates after dateOfCreation)
    @Test
    public void toModelType_invalidHistory_throwsIllegalValueException() {
        // Create an invalid history with dates after the dateOfCreation
        List<JsonAdaptedHistoryEntry> invalidHistory = new ArrayList<>(VALID_HISTORY);
        invalidHistory.add(new JsonAdaptedHistoryEntry(LocalDate.now().plusDays(2),
                List.of("Future activity")));

        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_BIRTHDAY, VALID_TAGS, VALID_DATE_OF_CREATION, invalidHistory);
        String expectedMessage = INVALID_HISTORY_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
