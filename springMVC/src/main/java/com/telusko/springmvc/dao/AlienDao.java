package com.telusko.springmvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.telusko.springmvc.model.Alien;

/**
 * Through Session we fetch the data in hibernate,and we use the current session
 * created by spring framework
 */

/** @Componet creates object of AlienDao, when called by spring framework. */
@Component
public class AlienDao {

	/**
	 * In Hibernate we have amazing feature called Sesion Factoty, to fetch data we
	 * need Session and to get Session, we need session factory
	 */

	/**
	 * Here the autowired annotation gives the object of Session Factory, we have
	 * mentioned in telusko-servlet.xml file, the bean id = sessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * When we use database, we have to make transaction into db, so that's why we
	 * are using transactional annotation here, in hibernate we do it manually like
	 * first create transaction and commit transaction when job is done. Here the
	 * transactional annotation do the job for us. so we don't need to write
	 * manually start or end transaction.
	 */
	@Transactional
	public List<Alien> getAliens() {
		// We don't create a new session, we actually use a session created by spring
		// framework, that session is in our container, so we can get that session.
		Session session = sessionFactory.getCurrentSession();

		/**
		 * if we want to fetch one single value then we can use get methods we have
		 * available in session. but if we want to fetch all records we need to create a
		 * query called JPQL or HQL. session.get(entityType, id);
		 */

		/**
		 * it will create query and return the records in Alien table, and we can get a
		 * list records by calling a method list().
		 */
		List<Alien> aliens = session.createQuery("from Alien", Alien.class).list();
		return aliens;
	}

	/** Saving the alien data in database. */
	@Transactional
	public void addAlien(Alien a) {
		Session session = sessionFactory.getCurrentSession();
		session.save(a);
	}

	/**
	 * Fetching one Alien data with matched id from database. we can use get method
	 * or load method to fetch single data from database
	 */
	@Transactional
	public Alien getAlien(int aid) {
		Session session = sessionFactory.getCurrentSession();
		Alien a = session.get(Alien.class, aid);
		/* Alien a = session.load(Alien.class, aid); */
		return a;
	}

	/** Delete particular Alien with matched id in Database */
	@Transactional
	public void deleteAlien(int aid) {
		Session session = sessionFactory.getCurrentSession();
		Alien a = session.load(Alien.class, aid);
		session.delete(a);
	}
}
