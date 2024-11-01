package by.mitso.zooworld.command;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface Command {
    Router execute(HttpServletRequest request);
}
