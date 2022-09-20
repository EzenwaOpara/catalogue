package com.eze.catalogue.repository;

import com.eze.catalogue.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);

    Optional<Movie> findByLanguage(String language);

    List<Movie> findByType(String language);

    List<Movie> findByRatingBetween(double low, double high);

}
