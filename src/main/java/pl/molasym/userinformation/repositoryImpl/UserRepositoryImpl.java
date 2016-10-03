package pl.molasym.userinformation.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import pl.molasym.userinformation.config.HibernateUtil;
import pl.molasym.userinformation.entities.User;
import pl.molasym.userinformation.exceptions.UserNotFoundException;
import pl.molasym.userinformation.repository.UserRepository;
import pl.molasym.userinformation.sql.UserInformationSQL;

@Repository
public class UserRepositoryImpl implements UserRepository {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	
	public User getUserById(long id) throws UserNotFoundException {

		Session session = sessionFactory.openSession();
		User user;
		session.getTransaction().begin();
		Query query = session.createQuery(UserInformationSQL.GET_USER_BY_ID);
		query.setParameter("id", id);
		user = (User) query.uniqueResult();
		if (user == null)
			throw new UserNotFoundException();
		session.close();

		return user;
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList();
		Session session = sessionFactory.openSession();

		session.getTransaction().begin();
		
		Query query = session.createQuery(UserInformationSQL.GET_ALL_USERS);
		list = query.list();

		session.close();

		return list;
	}

	public User getUserByAccountId(long id) throws UserNotFoundException {

		User user ;

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery(UserInformationSQL.GET_USER_BY_ACCOUNT_ID);
		query.setParameter("id", id);
		user = (User) query.uniqueResult();

		session.close();
		
		if(user == null)
			throw new UserNotFoundException();
		return user;
	}

	

	public void updateUserById(long id, User user) throws UserNotFoundException {

		User userFromDb;
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();	
		
		userFromDb = (User) session.get(User.class, id);
		
		if(userFromDb == null)
			throw new UserNotFoundException();
		
		userFromDb = user;
		userFromDb.setUserId(user.getUserId());
		
		session.update(userFromDb);
		
		session.getTransaction().commit();
		session.close();		
	}

	public void addNewUser(User user) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();	
		session.save(user);
		session.getTransaction().commit();
		session.close();		
	}

	public void removeUser(long id) throws UserNotFoundException {
		User user;

		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();	
		
		user = (User) session.get(User.class, id);
		if(user == null)
			throw new UserNotFoundException();
		
		session.delete(user);
		session.getTransaction().commit();
		session.close();		
	}




}
