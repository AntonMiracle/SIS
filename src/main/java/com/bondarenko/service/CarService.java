package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Car;

public interface CarService {

	public Car save(Car car) throws RuntimeException;//test-ok

	public boolean delete(Long carId) throws RuntimeException;//test-ok

	public Car update(Car car) throws RuntimeException;//test-ok

	public Set<Car> getCars() throws RuntimeException;// test-ok

	public Set<Car> getByMark(String mark) throws RuntimeException;//test-ok

	public Set<Car> getByModel(String model) throws RuntimeException;//test-ok

	public Car getByNumber(String number) throws RuntimeException;// test-ok

	public Car getById(Long id) throws RuntimeException;// test-ok

	public boolean isNumberUnique(String number) throws RuntimeException;//test-ok

}
