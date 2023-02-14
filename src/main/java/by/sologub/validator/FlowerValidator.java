package by.sologub.validator;

import by.sologub.model.Flower;

import java.util.function.Predicate;

public class FlowerValidator {

    private FlowerValidator() {

    }

    public static final Predicate<Flower> isVaseMadeOfGlass = flower -> flower.getFlowerVaseMaterial().contains("Glass");
    public static final Predicate<Flower> isVaseMadeOfAluminum = flower -> flower.getFlowerVaseMaterial().contains("Aluminum");
    public static final Predicate<Flower> isVaseMadeOfSteel = flower -> flower.getFlowerVaseMaterial().contains("Steel");
    public static final Predicate<Flower> nameStartsWithS = flower -> flower.getCommonName().toLowerCase().startsWith("s");
    public static final Predicate<Flower> nameContainsC = flower -> flower.getCommonName().toLowerCase().contains("c");
}
