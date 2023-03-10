package by.kachan;

import by.kachan.model.Animal;
import by.kachan.model.Car;
import by.kachan.model.Flower;
import by.kachan.model.House;
import by.kachan.model.Movie;
import by.kachan.model.Person;
import by.kachan.service.CarService;
import by.kachan.service.FlowerService;
import by.kachan.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.kachan.validator.FlowerValidator.flowerNamePattern;
import static by.kachan.validator.FlowerValidator.isVaseMadeOfAluminum;
import static by.kachan.validator.FlowerValidator.isVaseMadeOfGlass;
import static by.kachan.validator.FlowerValidator.isVaseMadeOfSteel;
import static by.kachan.validator.PersonValidator.isAgeGreaterOrEqual18;
import static by.kachan.validator.PersonValidator.isAgeLessOrEqual27;
import static by.kachan.validator.PersonValidator.isAgelessThan18;
import static by.kachan.validator.PersonValidator.isRetiredMan;
import static by.kachan.validator.PersonValidator.isRetiredWomen;
import static by.kachan.validator.PersonValidator.isNotRetired;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(7 * 2)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> "Female".equals(animal.getGender())
                        ? animal.getBread()
                        : animal.getBread().toUpperCase())
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femaleAnimals = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.println(femaleAnimals);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean anyHungarians = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println(anyHungarians);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean malesAndFemales = animals.stream()
                .allMatch(animal -> "Female".equals(animal.getGender())
                        || "Male".equals(animal.getGender()));
        System.out.println(malesAndFemales);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        System.out.println(isOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .ifPresent(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(chars -> chars.length))
                .ifPresent(shortestChar -> System.out.println(shortestChar.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long animalsAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(animalsAge);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(isAgeGreaterOrEqual18.and(isAgeLessOrEqual27))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = new ArrayList<>(Util.getHouses());
        List<Person> people = new ArrayList<>();

        houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .forEach(people::add);

        houses.stream()
                .filter(house -> !house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(isAgelessThan18.or(isRetiredWomen).or(isRetiredMan))
                .forEach(people::add);

        houses.stream()
                .filter(house -> !house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(isAgeGreaterOrEqual18.and(isNotRetired))
                .forEach(people::add);

        people.stream()
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = new ArrayList<>(Util.getCars());
        CarService carService = new CarService();

        Map<String, List<Car>> carsMap = new LinkedHashMap<>();
        carsMap.put("Turkmenistan", carService.findTurkmenistanCars(cars));
        carsMap.put("Uzbekistan", carService.findUzbekistanCars(cars));
        carsMap.put("Kazakhstan", carService.findKazakhstanCars(cars));
        carsMap.put("Kyrgyzstan", carService.findKyrgyzstanCars(cars));
        carsMap.put("Russian", carService.findRussianCars(cars));
        carsMap.put("Mongolia", carService.findMongolianCars(cars));

        Map<String, Double> transportationCost = carsMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        map -> map.getValue()
                                .stream()
                                .mapToDouble(carService::calculateTransportationCost)
                                .sum(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));

        double profit = carsMap.values()
                .stream()
                .flatMap(List::stream)
                .mapToDouble(carService::calculateProfit)
                .sum();

        System.out.println("Transportation cost for each country: ");
        transportationCost.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("Profit made: " + profit);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        FlowerService flowerService = new FlowerService();
        double flowersMaintenanceCost = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparingInt(Flower::getPrice)
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> flowerNamePattern.matcher(flower.getCommonName().toLowerCase()).matches())
                .filter(Flower::isShadePreferred)
                .filter(isVaseMadeOfGlass.or(isVaseMadeOfAluminum).or(isVaseMadeOfSteel))
                .mapToDouble(flowerService::calculateTotalCostOfPlantsMaintenance)
                .sum();
        System.out.println(flowersMaintenanceCost);
    }

    private static void task16() throws IOException {
        List<Movie> movies = Util.getMovies();
        movies.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed()
                        .thenComparing(Movie::getTitle).reversed())
                .filter(movie -> movie.getReleaseDate().isAfter(LocalDate.of(1990, 1, 1)))
                .filter(movie -> "USA".equalsIgnoreCase(movie.getCountry()))
                .collect(Collectors.groupingBy(Movie::getGenre,
                        Collectors.summingDouble(movie -> movie.getWorldWideGross() - movie.getProductionBudget())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .peek(System.out::println)
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .ifPresent(genre -> System.out.println("Most profitable genre is " + genre));
    }
}