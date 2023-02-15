package by.kachan.service;

import by.kachan.model.Car;

import java.util.List;

public class CarService {

    public List<Car> findTurkmenistanCars(List<Car> cars) {
        List<Car> turkmenistanCars = cars.stream()
                .filter(car -> "Jaguar".equals(car.getCarMake())
                        || "White".equals(car.getColor()))
                .toList();
        cars.removeAll(turkmenistanCars);
        return turkmenistanCars;
    }

    public List<Car> findUzbekistanCars(List<Car> cars) {
        List<Car> uzbekistanCars = cars.stream()
                .filter(car -> car.getMass() < 1500)
                .filter(car -> "BMW".equals(car.getCarMake())
                        || "Lexus".equals(car.getCarMake())
                        || "Chrysler".equals(car.getCarMake())
                        || "Toyota".equals(car.getCarMake())
                )
                .toList();
        cars.removeAll(uzbekistanCars);
        return uzbekistanCars;
    }

    public List<Car> findKazakhstanCars(List<Car> cars) {
        List<Car> kazakhstanCars = cars.stream()
                .filter(car -> "Black".equals(car.getColor()) && car.getMass() > 4000
                        || "GMC".equals(car.getCarMake()) || "Dodge".equals(car.getCarMake()))
                .toList();
        cars.removeAll(kazakhstanCars);
        return kazakhstanCars;
    }

    public List<Car> findKyrgyzstanCars(List<Car> cars) {
        List<Car> kyrgyzstanCars = cars.stream()
                .filter(car -> car.getReleaseYear() < 1982
                        || "Civic".equals(car.getCarMake())
                        || "Cherokee".equals(car.getCarMake()))
                .toList();
        cars.removeAll(kyrgyzstanCars);
        return kyrgyzstanCars;
    }

    public List<Car> findMongolianCars(List<Car> cars) {
        List<Car> mongoliaCars = cars.stream()
                .filter(car -> car.getVin().contains("59"))
                .toList();
        cars.removeAll(mongoliaCars);
        return mongoliaCars;
    }


    public List<Car> findRussianCars(List<Car> cars) {
        List<Car> russianCars = cars.stream()
                .filter(car -> car.getPrice() > 40000
                        || !"Yellow".equals(car.getColor())
                        && !"Red".equals(car.getColor())
                        && !"Blue".equals(car.getColor())
                        && !"Green".equals(car.getColor()))
                .toList();
        cars.removeAll(russianCars);
        return russianCars;
    }

    public Double calculateTransportationCost(Car car) {
        return (car.getMass() / 1000.0) * 7.14;
    }

    public Double calculateProfit(Car car) {
        return car.getPrice() - (car.getMass() / 1000.0) * 7.14;
    }
}
