package com.eze.catalogue.route;

import com.eze.catalogue.dto.GenericResponse;
import com.eze.catalogue.service.MovieService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class MovieRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{route.movie.findAllMovies}}")
                .bean(MovieService.class, "findAllMovies");

        from("{{route.book.findMovieById}}")
                .log("Received header : ${header.id}")
                .bean(MovieService.class, "findMovieById(${header.id})");

        from("{{route.movie.findMovieByName}}")
                .log("Received header : ${header.name}")
                .bean(MovieService.class, "findMovieByName(${header.name})");

        from("{{route.movie.findMovieByLanguage}}")
                .log("Received header : ${header.lang}")
                .bean(MovieService.class, "findMovieByLanguage(${header.lang})");

        from("{{route.movie.findMovieByType}}")
                .log("Received header : ${header.type}")
                .bean(MovieService.class, "findMovieByType(${header.type})");

        from("{{route.movie.findMovieByRatingRange}}")
                .log("Received header : Low = ${header.low}, High = ${header.high}")
                .bean(MovieService.class, "findMovieByType(${header.low}, ${header.high})");

        from("{{route.movie.saveMovie}}")
                .log("Received Body ${body}")
                .bean(MovieService.class, "saveMovie(${body})");

        from("{{route.movie.removeMovie}}")
                .log("Received header : ${header.movieId}")
                .bean(MovieService.class, "removeMovie(${header.movieId})");
    }

    private void reworkException(Exchange exchange) {
        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDescription(exception.getMessage() + " or movie list is empty");
        genericResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exchange.getIn().setBody(genericResponse);
    }
}
