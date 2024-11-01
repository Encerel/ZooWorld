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
        commands.put("make_order", new MakeOrderCommand());
        commands.put("to_order_page", new ToOrderPageCommand());
        commands.put("clear_cart", new ClearCartCommand());
        commands.put("to_product_page", new ToProductPageCommand());
        commands.put("to_order_details", new ToOrderDetailsCommand());
        commands.put("to_order_details_admin", new ToOrderDetailsForAdminCommand());
        commands.put("delete_cart_item", new DeleteCartItemCommand());
        commands.put("find_users_pagination", new FindUsersPaginationCommand());
        commands.put("find_all_users", new FindAllUsersCommand());
        commands.put("find_products_pagination", new FindProductsPaginationCommand());
        commands.put("change_personal_info", new ChangePersonalInfoCommand());
        commands.put("to_filtered_products", new ToFilteredProductsCommand());
        commands.put("cancel_order", new CancelOrderCommand());
        commands.put("cancel_order_admin", new CancelOrderAdminCommand());
        commands.put("find_product_by_name", new FindProductByNameCommand());
        commands.put("find_filtered_users", new FindFilteredUsersCommand());
        commands.put("find_user_by_id", new FindUserByIdCommand());
        commands.put("to_orders", new ToOrdersCommand());
        commands.put("confirm_order", new ConfirmOrderCommand());
    }


    public static Command getCurrentCommand(String command) {
        return commands.get(command);
    }

    private CommandType() {}

}
