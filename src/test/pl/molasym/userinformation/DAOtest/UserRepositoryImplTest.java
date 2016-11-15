package pl.molasym.userinformation.DAOtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.AssertionErrors;
import pl.molasym.userinformation.config.HibernateUtil;
import pl.molasym.userinformation.config.UserInfConfig;
import pl.molasym.userinformation.config.WebAppInitializer;
import pl.molasym.userinformation.entities.Account;
import pl.molasym.userinformation.entities.Address;
import pl.molasym.userinformation.entities.User;
import pl.molasym.userinformation.exceptions.UserNotFoundException;
import pl.molasym.userinformation.repository.UserRepository;
import pl.molasym.userinformation.repositoryImpl.UserRepositoryImpl;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by moliq on 15.11.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserInfConfig.class})
@WebAppConfiguration public class UserRepositoryImplTest {

    @Autowired
    UserRepository userDao;

    User user;
    @Before
    public void setUp(){
        user = new User();
        user.setFirstName("Mateusz");
        user.setLastName("Molasy");
        user.setBirthDate(new Date());
        user.setCreatedDate(new Date());
        user.setEmailAddress("moliq@o2.pl");

        Address address = new Address();
        address.setAddressLine1("Akacjowa 100");
        address.setCity("Warszawa");
        address.setZipCode("00-000");

        user.getAddresses().add(address);

        Account account = new Account();
        account.setName("First");
        account.setUser(user);
        account.setCurrentBalance(1005.10);

        user.getAccounts().add(account);
    }

    @Test
    @Transactional
    @Rollback
    public void addNewUser() {

        userDao.addNewUser(user);

        java.util.List<User> userList = new ArrayList();
        userList = userDao.getAllUsers();

        Assert.assertEquals(user.getFirstName(), userList.get(0).getFirstName());
    }

    @Test(expected = UserNotFoundException.class)
    @Transactional
    public void readUser() throws UserNotFoundException {

        userDao.getUserById(100000000);

    }

    @Test()
    @Transactional
    @Rollback
    public void readUser2() throws UserNotFoundException {

        userDao.addNewUser(user);
        long userId = user.getUserId();
        User founded = userDao.getUserById(userId);
        Assert.assertEquals(Long.valueOf(founded.getUserId()), Long.valueOf(userId));
        Assert.assertEquals(founded.getFirstName(), user.getFirstName());
    }

    @Test(expected = UserNotFoundException.class)
    @Transactional
    @Rollback
    public void removeUser() throws UserNotFoundException {
        int size = userDao.getAllUsers().size();
        userDao.addNewUser(user);
        userDao.removeUser(user.getUserId());
        Assert.assertEquals(size,userDao.getAllUsers().size());
        userDao.getUserById(user.getUserId());
    }

    @Test
    @Transactional
    @Rollback
    public void updateUser() throws UserNotFoundException {
        userDao.addNewUser(user);
        User updatedUser = userDao.getUserById(user.getUserId());
        updatedUser.setEmailAddress("mateusz.molasy95@gmail.com");
        userDao.updateUserById(user, updatedUser);
        Assert.assertEquals(updatedUser.getEmailAddress(), userDao.getUserById(user.getUserId()).getEmailAddress());
    }

}

