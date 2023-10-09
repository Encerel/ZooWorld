package by.mitso.zooworld.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Router {

    private String pagePath;
    private Type type;

    public enum Type {
        FORWARD,
        REDIRECT
    }

    public Router() {
        this.type = Type.FORWARD;
    }

    public Router(String pagePath) {
        this.pagePath = pagePath;
        this.type = Type.FORWARD;
    }

    public Router(String pagePath, Type type) {
        this.pagePath = pagePath;
        this.type = type;
    }

}