package by.mitso.zooworld.command;

public class PagePath {

    public static final String SIGN_IN = "/jsp/signin.jsp";
    public static final String ABOUT = "/jsp/about.jsp";
    public static final String ADMIN = "/jsp/admin.jsp";
    public static final String CHANGE_PERSONAL_INFO = "/jsp/changepersonalinfo.jsp";
    public static final String ERROR = "/jsp/error.jsp";
    public static final String MAIN = "/jsp/main.jsp";

    public static final String PRODUCTS = "/jsp/products.jsp";
    public static final String SIGN_UP = "/jsp/signup.jsp";
    public static final String USER = "/jsp/user.jsp";
    public static final String CART = "/jsp/cart.jsp";
    public static final String ORDER = "/jsp/order.jsp";

    public static final String TO_CHANGE_PERSONAL_INFO_PAGE = "/controller?command=to_change_personal_info";
    public static final String TO_MAIN_PAGE = "/controller?command=to_main";
    public static final String TO_PRODUCTS_PAGE = "/controller?command=to_products_page";
    public static final String TO_PERSONAL_PAGE = "/controller?command=to_personal_page";
    public static final String TO_SIGN_IN_PAGE = "/controller?command=to_sign_in";
    public static final String TO_SIGN_UP_PAGE = "/controller?command=to_sign_up";

    public static final String TO_CART_PAGE = "/controller?command=to_cart_page";

    private PagePath() {
    }
}
