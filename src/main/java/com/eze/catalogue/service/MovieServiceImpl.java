package com.eze.catalogue.service;

import com.eze.catalogue.domain.Movie;
import com.eze.catalogue.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.orElse(null);
    }

    @Override
    public Movie findMovieByName(String name) {
        Optional<Movie> movieOptional = movieRepository.findByName(name);
        return movieOptional.orElse(null);
    }

    @Override
    public Movie findMovieByLanguage(String language) {
        Optional<Movie> movieOptional = movieRepository.findByName(language);
        return movieOptional.orElse(null);
    }

    @Override
    public Movie findMovieByType(String type) {
        Optional<Movie> movieOptional = movieRepository.findByName(type);
        return movieOptional.orElse(null);
    }

    @Override
    public Movie findMovieByRatingRange(double low, double high) {
        List<Movie> movieLList = movieRepository.findByRatingBetween(low, high);
        return (Movie) movieLList;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void removeMovie(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        optionalMovie.ifPresent(movieRepository::delete);
    }
}
