package by.sologub.model;

import java.time.LocalDate;

public class Movie {

    private int movieId;
    private String title;
    private double productionBudget;
    private double worldWideGross;
    private String genre;
    private LocalDate releaseDate;
    private String country;

    public Movie() {

    }

    public Movie(int movieId, String title, double productionBudget, double worldWideGross, String genre, LocalDate releaseDate, String country) {
        this.movieId = movieId;
        this.title = title;
        this.productionBudget = productionBudget;
        this.worldWideGross = worldWideGross;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.country = country;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getProductionBudget() {
        return productionBudget;
    }

    public void setProductionBudget(double productionBudget) {
        this.productionBudget = productionBudget;
    }

    public double getWorldWideGross() {
        return worldWideGross;
    }

    public void setWorldWideGross(double worldWideGross) {
        this.worldWideGross = worldWideGross;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
