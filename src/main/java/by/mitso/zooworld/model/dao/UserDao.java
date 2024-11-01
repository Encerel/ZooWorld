package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(long id);

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    List<User> findUsersByRole(Role role);


    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<String> findPasswordByEmail(String email);

    Optional<User> findUserByEmail(String email);

    boolean save(User user);

    boolean changeUserRole(long id, Role role);

    boolean changePersonalInfo(User user);

    long findNumberOfRows();

    List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage);

    Optional<User> findByEmailAndLastName(String email, String lastName);

    Optional<User> findByPhoneNumberAndLastName(String phoneNumber, String lastName);

    Optional<User> findByPhoneNumberAndEmail(String phoneNumber, String email);

    Optional<User> findByAllParameters(String email, String phoneNumber, String lastName);
}
