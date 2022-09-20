package com.eze.catalogue.route;

import com.eze.catalogue.dto.GenericResponse;
import com.eze.catalogue.service.BookServiceImpl;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.RestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BookRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        RestConfiguration restConfiguration = getContext().getRestConfiguration();

        from("{{route.book.findAllBooks}}")
                .bean(BookServiceImpl.class, "findAllBooks");

        from("{{route.book.findBookById}}")
                .log("Received header : ${header.id}")
                .bean(BookServiceImpl.class, "findBookById(${header.id})");

        from("{{route.book.findBookByTitle}}")
                .log("Received header : ${header.title}")
                .bean(BookServiceImpl.class, "findBookByTitle(${header.title})");

        from("{{route.book.findBookAuthor}}")
                .log("Received header : ${header.author}")
                .bean(BookServiceImpl.class, "findBookByAuthor(${header.author})");

        from("{{route.book.findBookIsbn}}")
                .log("Received header : ${header.isbn}")
                .bean(BookServiceImpl.class, "findBookByIsbn(${header.isbn})");

        from("{{route.book.saveBook}}")
                .log("Received Body ${body}")
                .bean(BookServiceImpl.class, "saveBook(${body})");

        from("{{route.book.removeBook}}")
                .log("Received header : ${header.bookId}")
                .bean(BookServiceImpl.class, "removeBook(${header.bookId})");


        from("direct:findBooks")
                .doTry()
                .bean(BookServiceImpl.class, "findBooks")
                .doCatch(Exception.class)
                .to("direct:exceptionHandler")
                .end();


        from("direct:exceptionHandler")
                .process(this::reworkException)
                .log(LoggingLevel.WARN, "${body}")
                .to("log:reply");
    }

    private void reworkException(Exchange exchange) {
        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDescription(exception.getMessage() + " or book list is empty");
        genericResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exchange.getIn().setBody(genericResponse);
    }
}
