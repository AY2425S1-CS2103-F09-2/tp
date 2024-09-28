package seedu.address.logic.commands;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String COMMAND_WORD = "remark";
    private final Index index;
    private final String remark;

    public RemarkCommand(Index index, String remark) {
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), remark));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}