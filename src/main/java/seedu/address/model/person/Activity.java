package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * The {@code Activity} class represents an activity that occurred on a specific date.
 * Each activity consists of a date and a descriptive message.
 * Guarantees: immutable;
 */
public class Activity {
    // The date when the activity occurred
    private final LocalDate date;

    // The message describing the activity
    private final String message;

    /**
     * Constructs an {@code Activity} object with the specified date and message.
     * This constructor is private to encourage the use of the static factory method {@link #of(LocalDate, String)}.
     *
     * @param date The {@code LocalDate} when the activity occurred.
     * @param message The message describing the activity.
     */
    private Activity(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    /**
     * Static factory method to create a new {@code Activity} instance.
     * This method is preferred over directly calling the constructor.
     *
     * @param date The {@code LocalDate} when the activity occurred.
     * @param message The message describing the activity.
     * @return A new {@code Activity} object.
     * @throws IllegalArgumentException if the date or message is null.
     */
    public static Activity of(LocalDate date, String message) {
        requireNonNull(date, message);
        if (message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }
        return new Activity(date, message);
    }

    /**
     * Returns a string representation of the activity.
     * The string includes the date and the activity message in the format "[date] message".
     *
     * @return A string representation of the activity.
     */
    @Override
    public String toString() {
        return message;
    }
}
