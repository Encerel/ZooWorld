package by.mitso.zooworld.command;

import by.mitso.zooworld.command.impl.UnknownCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("unknown_command", new UnknownCommand());
    }


    public static Command getCurrentCommand(String command) {
        return commands.get(command);
    }

    private CommandType() {}

}
