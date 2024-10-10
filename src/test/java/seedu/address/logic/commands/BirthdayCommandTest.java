package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class BirthdayCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }
}
