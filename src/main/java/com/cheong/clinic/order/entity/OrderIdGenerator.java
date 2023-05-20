package com.cheong.clinic.order.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

public class OrderIdGenerator implements IdentifierGenerator{

	private String prefix = "O";
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		Query<Order> query = session.createQuery("FROM Order ORDER BY id DESC",Order.class);
		List<Order> orderList = query.getResultList();
		
		if(orderList.size() == 0) {
			return prefix+"0";
		}
		String id = orderList.get(0).getId();
		
		return prefix + (Integer.parseInt(id.substring(1))+1);
	}

}
