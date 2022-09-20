package com.eze.catalogue.service;

import com.eze.catalogue.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAllMovies();

    Movie findMovieById(Long id);

    Movie findMovieByName(String name);

    Movie findMovieByLanguage(String language);

    Movie findMovieByType(String type);

    Movie findMovieByRatingRange(double low, double high);

    Movie saveMovie(Movie movie);

    void removeMovie(Long id);
}
