package pl.molasym.userinformation.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pl.molasym.userinformation.entities.Account;
import pl.molasym.userinformation.entities.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Account.class);
			
			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("There was an error while creation session factory");
		}
	}


	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
