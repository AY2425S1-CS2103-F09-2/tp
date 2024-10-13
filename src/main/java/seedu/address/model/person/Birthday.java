package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthday {

    public static final Birthday EMPTY_BIRTHDAY = Birthday.of("");
    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        value = birthday;
    }

    public static Birthday of(String birthday) {
        return new Birthday(birthday);
    }

    @Override
    public String toString() {
        return value;
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}