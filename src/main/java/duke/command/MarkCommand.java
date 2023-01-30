package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.task.Task;
import duke.ui.Ui;

/**
 * MarkCommand
 */
public class MarkCommand extends Command {

    private int taskId;
    private boolean markDone;

    /**
     * Default constructor
     *
     * @param taskId   int
     * @param markDone boolean
     */
    public MarkCommand(int taskId, boolean markDone) {
        this.taskId = taskId;
        this.markDone = markDone;
    }

    /**
     * Mark or unmark task from database and print the output.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            Task tk = db.getTask(taskId);

            if (markDone) {
                tk.markAsDone();
                ui.printConsole(Message.MARK_TASK);
            } else {
                tk.unmarkDone();
                ui.printConsole(Message.UNMARK_TASK);
            }

            ui.printConsole("\t" + tk);
        } catch (IndexOutOfBoundsException e) {
            ui.printConsole(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
