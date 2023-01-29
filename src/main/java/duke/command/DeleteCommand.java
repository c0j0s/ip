package duke.command;

import java.util.function.Consumer;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskId;

    /**
     * Default constructor
     * 
     * @param taskId int
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Delete task from database and print the output.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            ui.println(Message.DELETE_TASK);
            ui.println("\t" + db.removeTask(taskId));
            ui.println(String.format(Message.COUNT_TASK, db.count()));
        } catch (IndexOutOfBoundsException e) {
            ui.println(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Consumer<String> con) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(Message.DELETE_TASK + "\n");
            sb.append("\t" + db.removeTask(taskId) + "\n");
            sb.append(String.format(Message.COUNT_TASK, db.count()) + "\n");
        } catch (IndexOutOfBoundsException e) {
            sb.append(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
        con.accept(sb.toString());
    }

    /**
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
