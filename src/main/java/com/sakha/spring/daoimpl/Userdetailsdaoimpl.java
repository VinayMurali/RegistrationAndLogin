package com.sakha.spring.daoimpl;

import java.io.Serializable;

import javax.transaction.Transaction;

import org.apache.poi.ss.usermodel.RichTextString;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sakha.spring.dao.UserdetailsDao;
import com.sakha.spring.model.Userdetails;

@Repository
public class Userdetailsdaoimpl implements UserdetailsDao {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Userdetails forLogin(String userName, String password) {// we are taking input from user
		Session session = sessionfactory.getCurrentSession();// to get the current session used as where conditoin or
																// groupy
		Criteria cr = session.createCriteria(Userdetails.class);// useredetails is from model,i.e is the table name
		cr.add(Restrictions.eq("userName", userName));// 1st from model,2nd enduser entered
		cr.add(Restrictions.eq("password", password));
		return (Userdetails) cr.uniqueResult();
	}

	@Override
	public int registration(Userdetails userdetails) {
		Session session = sessionfactory.getCurrentSession();
		int id = (int) session.save(userdetails);

		return id;
	}

	@Override
	public Userdetails getUserDetails(String userName) {
		Session session = sessionfactory.openSession();
		Transaction tx = null;

		tx = (Transaction) session.beginTransaction();
		Criteria cr = session.createCriteria(Userdetails.class);
		cr.add(Restrictions.eq("userName", userName));
		return (Userdetails) cr.uniqueResult();

	}
}
