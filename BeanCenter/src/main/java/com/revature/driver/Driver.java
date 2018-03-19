package com.revature.driver;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Bucket;
import com.revature.util.HibernateUtil;

public class Driver {
	
	public static void main(String... args) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.getTransaction();
		tx.begin();
		Bucket b = new Bucket(0, "OOPS", "Object Oriented Programming", true);
		s.save(b);
		//Bucket b = (Bucket) s.createQuery("from Bucket").list().get(0);
		//System.out.println(b.getBucketId());
		tx.commit();
		s.close();
		System.exit(0);
	}
	
}
