package com.bondarenko.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bondarenko.model.Car;
import com.bondarenko.model.User;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:config/sis-context.xml")
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class CarServiceTest extends DBUnitConfig {
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;

	@Test
	public void shouldGetAllCars() throws Exception {
		int countUsers = 3;
		List<Car> users = carService.getCars();
		Assert.assertEquals(countUsers, users.size());
	}

	@Test
	public void shouldGetCarById() throws Exception {
		Long carId = new Long(1);
		Car car = carService.getById(carId);
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.getMark());
		Assert.assertNotNull(car.getModel());
		Assert.assertNotNull(car.getNumber());
		Assert.assertNotNull(car.getDescription());
		Assert.assertNotNull(car.getCreateDate());
		Assert.assertNotNull(car.getProposals());
		Assert.assertNotNull(car.getUser());
		Assert.assertNotNull(car.getUser().getId());
		Assert.assertEquals(carId, car.getId());
	}

	@Test
	public void shouldGetCarByNumber() throws Exception {
		String number = "number1";
		Car car = carService.getByNumber(number);
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.getMark());
		Assert.assertNotNull(car.getModel());
		Assert.assertNotNull(car.getNumber());
		Assert.assertNotNull(car.getDescription());
		Assert.assertNotNull(car.getCreateDate());
		Assert.assertNotNull(car.getProposals());
		Assert.assertNotNull(car.getId());
		Assert.assertNotNull(car.getUser());
		Assert.assertNotNull(car.getUser().getId());
		Assert.assertEquals(number, car.getNumber());
	}

	@Test
	public void shouldDeleteCar() throws Exception {
		int countCars = carService.getCars().size();
		String number = "number1";
		Car car = carService.getByNumber(number);
		Assert.assertNotNull(car);
		carService.delete(car);
		Assert.assertNull(carService.getByNumber(number));
		Assert.assertEquals(countCars - 1, carService.getCars().size());
	}

	@Test
	public void shouldDeleteCarById() throws Exception {
		int countCars = carService.getCars().size();
		Long carId = new Long(1);
		Car car = carService.getById(carId);
		Assert.assertNotNull(car);
		carService.delete(car);
		Assert.assertNull(carService.getById(carId));
		Assert.assertEquals(countCars - 1, carService.getCars().size());
	}

	@Test
	public void shouldUpdateCar() throws Exception {
		int countCars = carService.getCars().size();
		Long carId = new Long(1);
		Long userId = new Long(3);
		String number = "someNumber";
		String model = "modell4";
		String mark = "markk2";
		String description = "dedd";
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		Assert.assertNotNull(car);
		Assert.assertNotEquals(userId, car.getUser().getId());
		Assert.assertNotEquals(number, car.getNumber());
		Assert.assertNotEquals(model, car.getModel());
		Assert.assertNotEquals(mark, car.getMark());
		Assert.assertNotEquals(description, car.getDescription());
		car.setDescription(description);
		car.setMark(mark);
		car.setModel(model);
		car.setNumber(number);
		car.setUser(user);
		car = carService.update(car);
		Assert.assertNotNull(car);
		Assert.assertEquals(userId, car.getUser().getId());
		Assert.assertEquals(number, car.getNumber());
		Assert.assertEquals(model, car.getModel());
		Assert.assertEquals(mark, car.getMark());
		Assert.assertEquals(description, car.getDescription());
		Assert.assertEquals(countCars, carService.getCars().size());
	}

	@Test
	public void shouldSaveCar() throws Exception {
		String number = "number100";
		Long userId = new Long(6);
		User user = userService.getById(userId);
		Car car = new Car();
		Assert.assertNull(carService.save(car).getId());
		car.setNumber(number);
		Assert.assertNull(carService.save(car).getId());
		car.setUser(user);
		Assert.assertNull(car.getDescription());
		Assert.assertNull(car.getMark());
		Assert.assertNull(car.getModel());
		Assert.assertNull(car.getCreateDate());
		Assert.assertNull(car.getId());
		Assert.assertNull(car.getProposals());
		car = carService.save(car);
		Assert.assertNotNull(car.getDescription());
		Assert.assertNotNull(car.getMark());
		Assert.assertNotNull(car.getModel());
		Assert.assertNotNull(car.getCreateDate());
		Assert.assertNotNull(car.getId());
		Assert.assertNotNull(car.getProposals());
		Assert.assertNotNull(car.getUser());
		Assert.assertEquals(number, car.getNumber());
		Assert.assertEquals(userId, car.getUser().getId());
	}

	@Test
	public void shouldSaveCarIfNecessaryFieldsFill() throws Exception {
		String number = "number100";
		Long userId = new Long(6);
		User user = userService.getById(userId);
		Car car = new Car();
		Assert.assertNull(carService.save(car).getId());
		car.setNumber(number);
		Assert.assertNull(carService.save(car).getId());
		car.setUser(user);
		Assert.assertNotNull(carService.save(car));
	}

	@Test
	public void shouldFillCarNullFieldsAfterGet() throws Exception {
		String number = "number100";
		Long userId = new Long(6);
		User user = userService.getById(userId);
		Car car = new Car();
		car.setNumber(number);
		car.setUser(user);
		Assert.assertNull(car.getDescription());
		Assert.assertNull(car.getMark());
		Assert.assertNull(car.getModel());
		Assert.assertNull(car.getCreateDate());
		Assert.assertNull(car.getId());
		Assert.assertNull(car.getProposals());
		carService.save(car);
		car = carService.getById(car.getId());
		Assert.assertNotNull(car.getDescription());
		Assert.assertNotNull(car.getMark());
		Assert.assertNotNull(car.getModel());
		Assert.assertNotNull(car.getCreateDate());
		Assert.assertNotNull(car.getId());
		Assert.assertNotNull(car.getProposals());
		Assert.assertNotNull(car.getUser());
		Assert.assertEquals(number, car.getNumber());
		Assert.assertEquals(userId, car.getUser().getId());
	}

	@Test
	public void shouldCheckCarNumberForUnique() throws Exception {
		String number1 = "number1";
		String number2 = "number19210";
		Assert.assertNotNull(carService.getByNumber(number1));
		Assert.assertFalse(carService.isNumberUnique(number1));
		Assert.assertNull(carService.getByNumber(number2));
		Assert.assertTrue(carService.isNumberUnique(number2));
	}

	@Test
	public void shouldGetCarsByModel() throws Exception {
		String model1 = "model1";
		String model2 = "model2";
		String model3 = "model3";
		int countCarsModel1 = 2;
		int countCarsModel2 = 0;
		int countCarsModel3 = 1;
		List<Car> cars = carService.getByModel(model1);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsModel1, cars.size());
		cars = carService.getByModel(model2);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsModel2, cars.size());
		cars = carService.getByModel(model3);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsModel3, cars.size());
	}

	@Test
	public void shouldGetCarsByMark() throws Exception {
		String mark1 = "mark1";
		String mark2 = "mark2";
		String mark3 = "mark3";
		int countCarsMark1 = 2;
		int countCarsMark2 = 1;
		int countCarsMark3 = 0;
		List<Car> cars = carService.getByMark(mark1);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsMark1, cars.size());
		cars = carService.getByMark(mark2);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsMark2, cars.size());
		cars = carService.getByMark(mark3);
		Assert.assertNotNull(cars);
		Assert.assertEquals(countCarsMark3, cars.size());
	}
}
