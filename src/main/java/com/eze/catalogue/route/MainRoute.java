package com.eze.catalogue.route;

import com.eze.catalogue.domain.Movie;
import com.eze.catalogue.dto.GenericResponse;
import com.eze.catalogue.domain.Book;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {
    @Value("${server.port}")
    String serverPort;
    @Value("${camel.springboot.name}")
    String contextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .contextPath(contextPath)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Spring Boot Camel Postgres Rest API.")
                .apiProperty("api.version", "1.0")
                .enableCORS(true)
                .port(serverPort)
                .bindingMode(RestBindingMode.json);

        rest("/book")
                .description("Book Catalogue")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)

                .get("/all")
                .responseMessage("200", "All Books found.")
                .responseMessage("404", "No books found.")
                .description("Find All books")
                .to("{{route.book.findAllBooks}}")

                .get("/id/{id}")
                .responseMessage("200", "Book with id was found.")
                .responseMessage("404", "Book with id was not found.")
                .description("Find book by id")
                .to("{{route.book.findBookById}}")

                .get("/title/{title}")
                .responseMessage("200", "Book with title was found.")
                .responseMessage("404", "Book with title was not found.")
                .description("Find book by title")
                .to("{{route.book.findBookByTitle}}")

                .get("/author/{author}")
                .responseMessage("200", "Book by author was found.")
                .responseMessage("404", "Book by author was not found.")
                .description("Find book by author")
                .to("{{route.book.findBookByAuthor}}")

                .get("/isbn/{isbn}")
                .responseMessage("200", "Book with isbn was found.")
                .responseMessage("404", "Book with isbn was not found.")
                .description("Find book by isbn")
                .to("{{route.book.findBookByIsbn}}")

                .post("/")
                .type(Book.class)
                .responseMessage("200", "Create new book was successful.")
                .responseMessage("404", "Create new book was not successful.")
                .description("Save a new book")
                .to("{{route.book.saveBook}}")

                .delete("/{bookId}")
                .responseMessage("200", "Book with id was found and deleted.")
                .responseMessage("404", "Book with id was not found.")
                .description("Delete book by id")
                .to("{{route.book.removeBook}}")
                .outType(GenericResponse.class)

                .get("/")
                .responseMessage("200", "Books found.")
                .responseMessage("404", "No books found")
                .description("Find books with exception handler")
                .to("direct:findBooks");


        rest("/movie")
                .description("Movie Catalogue")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)

                .get("/all")
                .responseMessage("200", "All Movies found.")
                .responseMessage("404", "No Movie found.")
                .description("Find All Movies")
                .to("{{route.movie.findAllMovies}}")

                .get("/id/{id}")
                .responseMessage("200", "Movie with id was found.")
                .responseMessage("404", "Movie with id was not found.")
                .description("Find movie by id")
                .to("{{route.movie.findMovieById}}")

                .get("/name/{name}")
                .responseMessage("200", "Movie with name was found.")
                .responseMessage("404", "Movie with name was not found.")
                .description("Find movie by name")
                .to("{{route.movie.findMovieByName}}")

                .get("/lang/{lang}")
                .responseMessage("200", "Movies with language was found.")
                .responseMessage("404", "Movies with language was not found.")
                .description("Find movies by language")
                .to("{{route.movie.findMovieByLanguage}}")

                .get("/type/{type}")
                .responseMessage("200", "Movie of type was found.")
                .responseMessage("404", "Movie of type was not found.")
                .description("Find movie by type")
                .to("{{route.movie.findMovieByType}}")

                .get("/rating/{low}/{high}")
                .responseMessage("200", "Movie with rating was found.")
                .responseMessage("404", "Movie with rating was not found.")
                .description("Find movie by rating")
                .to("{{route.movie.findMovieByRatingRange}}")

                .post("/")
                .type(Movie.class)
                .responseMessage("200", "Create new movie was successful.")
                .responseMessage("404", "Create new movie was not successful.")
                .description("Save a new movie")
                .to("{{route.movie.saveMovie}}")

                .delete("/{movieId}")
                .responseMessage("200", "Movie with id was found and deleted.")
                .responseMessage("404", "Movie with id was not found.")
                .description("Delete movie by id")
                .to("{{route.movie.removeMovie}}")
                .outType(GenericResponse.class);

    }
}
