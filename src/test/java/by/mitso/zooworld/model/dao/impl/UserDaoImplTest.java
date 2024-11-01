package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.model.dao.UserDao;
import by.mitso.zooworld.util.Encoder;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.util.Base64;

import static org.mockito.Mockito.mock;

public class UserDaoImplTest {

    private UserDao userDao;


    @BeforeTest
    void setUp() {
        userDao = mock(UserDaoImpl.class);
    }


    @Test
    void findAllTest() {
        System.out.println(Encoder.encodePassword("22222222a"));
    }
}
