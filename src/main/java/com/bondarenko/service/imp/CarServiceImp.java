package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.CarDao;
import com.bondarenko.model.Car;
import com.bondarenko.service.CarService;

@Service
public class CarServiceImp implements CarService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private CarDao carDao;

	@Override
	@Transactional
	public Car save(Car car) throws RuntimeException {
		try {
			car.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			return carDao.save(car);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long carId) throws RuntimeException {
		try {
			return carDao.delete(getById(carId));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Car update(Car car) throws RuntimeException {
		try {
			return carDao.update(car);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Car> getCars() throws RuntimeException {
		try {
			Set<Car> cars = new HashSet<>();
			for (Car car : carDao.getAll()) {
				cars.add(car);
			}
			return cars;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Car> getByMark(String mark) throws RuntimeException {
		try {
			Set<Car> cars = new HashSet<>();
			for (Car car : carDao.getByMark(mark)) {
				cars.add(car);
			}
			return cars;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Car> getByModel(String model) throws RuntimeException {
		try {
			Set<Car> cars = new HashSet<>();
			for (Car car : carDao.getByModel(model)) {
				cars.add(car);
			}
			return cars;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Car getByNumber(String number) throws RuntimeException {
		try {
			return carDao.getByNumber(number);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Car getById(Long id) throws RuntimeException {
		try {
			return carDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isNumberUnique(String number) throws RuntimeException {
		try {
			return carDao.isNumberUnique(number);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}
}
