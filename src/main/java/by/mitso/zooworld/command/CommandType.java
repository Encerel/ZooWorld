package by.mitso.zooworld.command;

import by.mitso.zooworld.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("unknown_command", new UnknownCommand());
        commands.put("log_in", new LogInCommand());
        commands.put("to_main", new ToMainCommand());
        commands.put("to_sign_in", new ToSignInCommand());
        commands.put("to_personal_page", new ToPersonalPageCommand());
        commands.put("to_sign_up", new ToSignUpCommand());
        commands.put("sign_up", new SignUpCommand());
        commands.put("log_out", new LogOutCommand());
        commands.put("to_cart_page", new ToCartPageCommand());
        commands.put("to_products_page", new ToProductsPageCommand());
        commands.put("add_product_to_cart", new AddProductToCartCommand());
    }


    public static Command getCurrentCommand(String command) {
        return commands.get(command);
    }

    private CommandType() {}

}
