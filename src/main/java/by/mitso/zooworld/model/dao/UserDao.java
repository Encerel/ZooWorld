package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    List<User> findUsersByRole(Role role);

    Optional<String> findPasswordByEmail(String email);

    Optional<User> findUserByEmail(String email);

    boolean save(User user);

    boolean changeUserRole(long id, Role role);

    boolean changePersonalInfo(User user);
}
