package com.cheong.clinic.admin.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

public class DoctorIdGenerator implements IdentifierGenerator{

	private String prefix = "D";
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		Query<Doctor> query= session.createQuery("FROM Doctor ORDER BY id DESC",Doctor.class);
		
		List<Doctor> doctorList = query.getResultList();
		
		if(doctorList.size() == 0) {
			return prefix + "0";
		}
		
		String id = doctorList.get(0).getId();
		
		return prefix + (Integer.parseInt(id.substring(1))+1);
	}

}
