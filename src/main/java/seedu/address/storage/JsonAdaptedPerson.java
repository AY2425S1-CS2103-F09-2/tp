package seedu.address.storage;

import static seedu.address.model.person.Birthday.EMPTY_BIRTHDAY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.DateOfCreation;
import seedu.address.model.person.Email;
import seedu.address.model.person.History;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String INVALID_HISTORY_DATE = "History contains entries with dates after the date of creation!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String remark;
    private final String dateOfCreation;
    private final List<JsonAdaptedHistoryEntry> historyEntries = new ArrayList<>();
    private final String birthday;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedPropertyForRent> propertiesForRent = new ArrayList<>();
    private final List<JsonAdaptedPropertyForSale> propertiesForSale = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("remark") String remark,
                             @JsonProperty("birthday") String birthday,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("dateOfCreation") String dateOfCreation,
                             @JsonProperty("history") List<JsonAdaptedHistoryEntry> historyEntries,
                             @JsonProperty("propertiesForRent") List<JsonAdaptedPropertyForRent> propertiesForRent,
                             @JsonProperty("propertiesForSale") List<JsonAdaptedPropertyForSale> propertiesForSale) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.dateOfCreation = dateOfCreation;
        this.birthday = birthday;

        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (historyEntries != null) {
            this.historyEntries.addAll(historyEntries);
        }
        if (propertiesForRent != null) {
            this.propertiesForRent.addAll(propertiesForRent);
        }
        if (propertiesForSale != null) {
            this.propertiesForSale.addAll(propertiesForSale);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        birthday = source.getBirthday().toString();

        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        dateOfCreation = source.getDateOfCreation().toString();

        historyEntries.addAll(source.getHistory().getHistoryEntries().entrySet().stream()
                .map(entry -> new JsonAdaptedHistoryEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        propertiesForRent.addAll(source.getPropertiesForRent().stream()
                .map(JsonAdaptedPropertyForRent::new)
                .collect(Collectors.toList()));

        propertiesForSale.addAll(source.getPropertiesForSale().stream()
                .map(JsonAdaptedPropertyForSale::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        if (dateOfCreation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfCreation.class.getSimpleName()));
        }
        final LocalDate modelDateOfCreation = LocalDate.parse(dateOfCreation);

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthday(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = birthday.isEmpty() ? EMPTY_BIRTHDAY : new Birthday(birthday);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<PropertyForRent> modelPropertiesForRent = propertiesForRent.stream()
                .map(JsonAdaptedPropertyForRent::toModelType)
                .collect(Collectors.toList());

        final List<PropertyForSale> modelPropertiesForSale = propertiesForSale.stream()
                .map(JsonAdaptedPropertyForSale::toModelType)
                .collect(Collectors.toList());

        final History modelHistory = History.fromJsonEntries(modelDateOfCreation, historyEntries);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelRemark,
                modelBirthday, modelTags, modelDateOfCreation, modelHistory,
                modelPropertiesForRent, modelPropertiesForSale);
    }
}
