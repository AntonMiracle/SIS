package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	private CarDao carDao;

	@Override
	@Transactional
	public Car save(Car car) throws RuntimeException {
		if (checkNewCarFields(car)) {
			carDao.save(car);
			car = getById(car.getId());
		}
		return car;
	}

	@Override
	@Transactional
	public boolean delete(Car car) throws RuntimeException {
		return carDao.delete(car);
	}

	@Override
	@Transactional
	public boolean delete(Long carId) throws RuntimeException {
		Car car = getById(carId);
		if (car != null) {
			return delete(car);
		}
		return false;
	}

	@Override
	@Transactional
	public Car update(Car car) throws RuntimeException {
		return carDao.update(car);
	}

	@Override
	@Transactional
	public List<Car> getCars() throws RuntimeException {
		return carDao.getAll();
	}

	@Override
	@Transactional
	public List<Car> getByMark(String mark) throws RuntimeException {
		List<Car> cars = carDao.getByMark(mark);
		return cars == null ? new ArrayList<Car>() : cars;
	}

	@Override
	@Transactional
	public List<Car> getByModel(String model) throws RuntimeException {
		List<Car> cars = carDao.getByModel(model);
		return cars == null ? new ArrayList<Car>() : cars;
	}

	@Override
	@Transactional
	public Car getByNumber(String number) throws RuntimeException {
		Car car = carDao.getByNumber(number);
		if (car != null) {
			nullFilter(car);
		}
		return car;
	}

	@Override
	@Transactional
	public Car getById(Long id) throws RuntimeException {
		Car car = carDao.getById(id);
		if (car != null) {
			nullFilter(car);
		}
		return car;
	}

	@Override
	@Transactional
	public boolean isNumberUnique(String number) throws RuntimeException {
		return carDao.isNumberUnique(number);
	}

	@Override
	public void nullFilter(Car car) throws RuntimeException {
		car.setProposals(car.getProposals() == null ? new ArrayList<Proposal>() : car.getProposals());
	}

	@Override
	public boolean checkNewCarFields(Car car) throws RuntimeException {
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
	}

}
