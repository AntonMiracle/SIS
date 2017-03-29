package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.ProposalDao;
import com.bondarenko.model.Proposal;

@Service
public class ProposalDaoImp implements ProposalDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Proposal save(Proposal proposal) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(proposal);
		return getById(proposal.getId());
	}

	@Override
	public boolean delete(Proposal proposal) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(proposal);
		session.flush();
		return true;
	}

	@Override
	public Proposal update(Proposal proposal) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(proposal);
		session.flush();		
		return getById(proposal.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Proposal> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Proposal> proposals = session.createQuery("from Proposal").list();
		return proposals;
	}

	@Override
	public Proposal getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Proposal as proposal where proposal.id = '" + id.toString() + "'");
		return (Proposal) query.uniqueResult();
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Proposal> getByUserId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Proposal> proposals = session
				.createQuery("from Proposal as proposal where proposal.user.id = '" + id.toString() + "'").list();
		return proposals;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Proposal> getByStatusId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Proposal> proposals = session
				.createQuery("from Proposal as proposal where proposal.status.id = '" + id.toString() + "'").list();
		return proposals;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Proposal> getByCarId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Proposal> proposals = session
				.createQuery("from Proposal as proposal where proposal.car.id = '" + id.toString() + "'").list();
		return proposals;
	}

}
