package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher collin = new Publisher("Collin", 1234);
        this.publisherRepository.save(collin);

        Author lovecraft = new Author("Howard", "Lovecraft");
        Book cthulhu = new Book("The Call of Cthulhu", "123");
        cthulhu.setPublisher(collin);
        collin.getBooks().add(cthulhu);
        lovecraft.getBooks().add(cthulhu);
        cthulhu.getAuthors().add(lovecraft);

        this.authorRepository.save(lovecraft);
        this.bookRepository.save(cthulhu);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123");
        noEJB.setPublisher(collin);
        collin.getBooks().add(noEJB);
        rod.getBooks().add(cthulhu);
        noEJB.getAuthors().add(lovecraft);

        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);

        System.out.println("Number of books: " + bookRepository.count());
    }
}
