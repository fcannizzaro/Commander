package handler;

import model.Command;
import model.CommandHandler;

import java.util.LinkedList;

/**
 * Created by Francesco Cannizzaro (fcannizzaro).
 */

@SuppressWarnings("unused")
public class HistoryHandler implements CommandHandler {

    private LinkedList<Command> defaultHistory;
    private LinkedList<Command> redoHistory;
    private int length = 100;
    private boolean enabled = true;

    /**
     * Default Constructor
     */
    public HistoryHandler() {
        defaultHistory = new LinkedList<Command>();
        redoHistory = new LinkedList<Command>();
    }

    /**
     * Alternative Constructor
     *
     * @param length of history, 0 means NO COMMAND HISTORY
     */
    public HistoryHandler(int length) {
        this();
        this.length = length;
    }

    /**
     * Handle command
     *
     * @param command to execute
     */
    public void handle(Command command) {

        if (defaultHistory.size() > length)
            defaultHistory.removeLast();

        defaultHistory.add(command);
        redoHistory.clear();
        command.run();
    }

    /**
     * Redo last command if exist
     */
    public void redo() {
        if (enabled && !redoHistory.isEmpty()) {
            Command command = redoHistory.removeFirst();
            command.run();
            defaultHistory.addFirst(command);
        }
    }

    /**
     * Undo last command if exist
     */
    public void undo() {
        if (enabled && !defaultHistory.isEmpty()) {
            Command command = defaultHistory.removeFirst();
            command.restore();
            redoHistory.addFirst(command);
        }
    }

    /**
     * Disable undo/redo actions
     */
    public void disable() {
        enabled = false;
    }

    /**
     * Enable undo/redo actions
     */
    public void enable() {
        enabled = true;
    }

}
