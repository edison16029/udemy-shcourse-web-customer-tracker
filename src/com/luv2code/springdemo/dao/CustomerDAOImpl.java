package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName",
															Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, theId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
//		Different approach to delete the customer
//		Customer customer = session.get(Customer.class, theId);
//		
//		session.delete(customer);
		return;
	}

}
