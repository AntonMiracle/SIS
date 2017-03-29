package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.CarDao;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.User;
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
			if (checkNewCarFields(car)) {
				carDao.save(car);
				car = getById(car.getId());
			}
			return car;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Car car) throws RuntimeException {
		try {
			return carDao.delete(car);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long carId) throws RuntimeException {
		try {
			Car car = getById(carId);
			if (car != null) {
				return delete(car);
			}
			return false;
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
	public List<Car> getCars() throws RuntimeException {
		try {
			return carDao.getAll();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Car> getByMark(String mark) throws RuntimeException {
		try {
			List<Car> cars = carDao.getByMark(mark);
			return cars == null ? new ArrayList<Car>() : cars;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Car> getByModel(String model) throws RuntimeException {
		try {
			List<Car> cars = carDao.getByModel(model);
			return cars == null ? new ArrayList<Car>() : cars;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Car getByNumber(String number) throws RuntimeException {
		try {
			Car car = carDao.getByNumber(number);
			if (car != null) {
				car = getById(car.getId());
			}
			return car;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Car getById(Long id) throws RuntimeException {
		try {
			Car car = carDao.getById(id);
			nullFilter(car);
			return car;
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

	@Override
	public void nullFilter(Car car) throws RuntimeException {
		try {
			if (car != null) {
				car.setProposals(car.getProposals() == null ? new ArrayList<Proposal>() : car.getProposals());
			}
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	public boolean checkNewCarFields(Car car) throws RuntimeException {
		try {
			String number = car.getNumber();
			User user = car.getUser();
			if (number != null && number.length() > 0 && user != null && user.getId() != null) {
				car.setCreateDate(car.getCreateDate() == null ? Timestamp.valueOf(LocalDateTime.now()) : car.getCreateDate());
				car.setDescription(car.getDescription() == null ? "" : car.getDescription());
				car.setMark(car.getMark() == null ? "" : car.getMark());
				car.setModel(car.getModel() == null ? "" : car.getModel());
				return true;
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
