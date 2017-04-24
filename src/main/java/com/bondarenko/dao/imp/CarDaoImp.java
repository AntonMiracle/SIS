package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.CarDao;
import com.bondarenko.model.Car;

@Service
public class CarDaoImp implements CarDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Car save(Car car) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(car);
		session.flush();
		return getById(car.getId());
	}

	@Override
	public boolean delete(Car car) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(car);
		session.flush();
		return true;
	}

	@Override
	public Car update(Car car) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(car);
		session.flush();
		return getById(car.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Car> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Car> cars = session.createQuery("from Car").list();
		return cars;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Car> getByUserId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Car> cars = session.createQuery("from Car as car where car.user.id = '" + id.toString() + "'").list();
		return cars;
	}

	@Override
	public Car getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Car as car where car.id = '" + id.toString() + "'");
		return (Car) query.uniqueResult();
	}

	@Override
	public Car getByNumber(String number) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Car as car where car.number = '" + number + "'");
		return (Car) query.uniqueResult();
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Car> getByModel(String model) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Car> cars = session.createQuery("from Car as car where car.model = '" + model + "'").list();
		return cars;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Car> getByMark(String mark) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Car> cars = session.createQuery("from Car as car where car.mark = '" + mark + "'").list();
		return cars;
	}

	@Override
	public boolean isNumberUnique(String number) throws RuntimeException {
		return getByNumber(number) == null ? true : false;
	}

}
