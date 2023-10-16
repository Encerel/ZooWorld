package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.model.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import static org.mockito.Mockito.mock;

public class UserDaoImplTest {

    private UserDao userDao;


    @BeforeTest
    void setUp() {
        userDao = mock(UserDaoImpl.class);
    }


    @Test
    void findAllTest() {

    }
}
