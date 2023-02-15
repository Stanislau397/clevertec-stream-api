package by.kachan.validator;

import by.kachan.model.Flower;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FlowerValidator {

    private FlowerValidator() {

    }

    public static final Pattern flowerNamePattern = Pattern.compile(".*s[\\s\\S]*?c.*");

    public static final Predicate<Flower> isVaseMadeOfGlass = flower -> flower.getFlowerVaseMaterial().contains("Glass");
    public static final Predicate<Flower> isVaseMadeOfAluminum = flower -> flower.getFlowerVaseMaterial().contains("Aluminum");
    public static final Predicate<Flower> isVaseMadeOfSteel = flower -> flower.getFlowerVaseMaterial().contains("Steel");
}
