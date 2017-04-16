package dbms.oodbms;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

//hibernate.cfg.xml is in config folder
public class HibernateService {
	public static void main(String[] args) {
		HibernateEntity h = new HibernateEntity();
		h.setId(1);
		h.setName("GN");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(h);
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
		HibernateEntity h1 = (HibernateEntity) session.get(HibernateEntity.class, 1);
		System.out.println("Name is = " + h1.getName());
		session.close();
		sessionFactory.close();
	}
}